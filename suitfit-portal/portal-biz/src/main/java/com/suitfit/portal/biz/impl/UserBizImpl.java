package com.suitfit.portal.biz.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.suitfit.framework.exception.BaseException;
import com.suitfit.framework.utils.ListUtils;
import com.suitfit.framework.utils.StringUtils;
import com.suitfit.framework.utils.bean.BeanUtils;
import com.suitfit.framework.utils.redis.RedisService;
import com.suitfit.portal.base.dao.utils.PageUtils;
import com.suitfit.portal.base.service.DepartmentService;
import com.suitfit.portal.base.service.RoleService;
import com.suitfit.portal.base.service.UserRoleService;
import com.suitfit.portal.base.service.UserService;
import com.suitfit.portal.base.service.utils.DataScope;
import com.suitfit.portal.base.service.utils.SecurityFactory;
import com.suitfit.portal.biz.UserBiz;
import com.suitfit.portal.model.entity.Department;
import com.suitfit.portal.model.entity.Role;
import com.suitfit.portal.model.entity.User;
import com.suitfit.portal.model.entity.UserRole;
import com.suitfit.portal.model.pojo.auth.AuthUser;
import com.suitfit.portal.model.pojo.code.ResponseCode;
import com.suitfit.portal.model.pojo.consts.CommonConstant;
import com.suitfit.portal.model.pojo.criteria.UserQueryCriteria;
import com.suitfit.portal.model.pojo.vo.common.PageVO;
import com.suitfit.portal.model.pojo.vo.req.UserPassReq;
import com.suitfit.portal.model.pojo.vo.req.UserReq;
import com.suitfit.portal.model.pojo.vo.resp.RoleVO;
import com.suitfit.portal.model.pojo.vo.resp.UserVO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserBizImpl implements UserBiz {

    @Autowired
    private DataScope dataScope;

    @Autowired
    private UserService userService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private SecurityFactory securityFactory;

    @Autowired
    private RedisService redisTemplate;

    @Override
    public PageVO<UserVO> users(UserReq req, Page initPage) {
        PageVO<UserVO> pageVO = new PageVO<>();
        BeanUtils.copyProperties(initPage, pageVO);

        UserQueryCriteria query = new UserQueryCriteria();
        BeanUtils.copyProperties(req, query);
        Set<Long> deptSet = new HashSet<>();
        Set<Long> result = new HashSet<>();
        if (!ObjectUtils.isEmpty(req.getDepartmentId())) {
            deptSet.add(req.getDepartmentId());
            deptSet.addAll(dataScope.getDeptChildren(departmentService.getByPid(req.getDepartmentId())));
        }
        Set<Long> deptIds = dataScope.getDeptIds();
        // 查询条件不为空并且数据权限不为空则取交集
        if (!CollectionUtils.isEmpty(deptIds) && !CollectionUtils.isEmpty(deptSet)) {
            // 取交集
            result.addAll(deptSet);
            result.retainAll(deptIds);
            // 若无交集，则代表无数据权限
            query.setDeptIds(result);
            if (result.size() != 0) {
                pageVO = getUserFromPage(query, initPage);
            }
        } else {
            result.addAll(deptSet);
            result.addAll(deptIds);
            query.setDeptIds(result);
            pageVO = getUserFromPage(query, initPage);
        }
        return pageVO;
    }

    private PageVO<UserVO> getUserFromPage(UserQueryCriteria query, Page initPage) {
        IPage<User> userIPage = userService.findByCriteria(query, initPage);
        PageVO<UserVO> pageVO = PageUtils.fromIpage(userIPage, UserVO.class);
        if (!ListUtils.isNullOrEmpty(pageVO.getRecords())) {
            pageVO.getRecords().stream().forEach(user -> {
                Department dept = departmentService.findById(user.getDepartmentId());
                if (dept != null) {
                    user.setDepartmentName(dept.getName());
                }
                List<Role> roleEntityList = userRoleService.findByUserId(user.getId());
                List<RoleVO> roles = (List<RoleVO>) BeanUtils.convert(roleEntityList, RoleVO.class);
                user.setRoles(roles);
            });
        }
        return pageVO;
    }

    @Transactional
    @Override
    public void create(UserReq query) {
        query.setId(null);
        //checkLevel(query);
        if (userService.findByName(query.getUserName()) != null) {
            throw new BaseException(ResponseCode.USER_NAME_EXISTS);
        }

        if (userService.findByEmail(query.getEmail()) != null) {
            throw new BaseException(ResponseCode.EMAIL_EXISTS);
        }
        if (userService.findByPhone(query.getPhone()) != null) {
            throw new BaseException(ResponseCode.MOBILE_EXISTS);
        }
        User entity = new User();
        BeanUtils.copyProperties(query, entity);
        String encryptPass = new BCryptPasswordEncoder().encode("123456");
        entity.setPassword(encryptPass);
        entity.setAvatar(CommonConstant.USER_DEFAULT_AVATAR);
        userService.save(entity);

        List<Long> roles = new ArrayList<>();
        if (CollectionUtils.isEmpty(query.getRoles())) {
            roles.add(roleService.getDefaultRole().getId());
        } else {
            roles.addAll(query.getRoles());
        }
        for (Long roleId : roles) {
            UserRole userRole = new UserRole();
            userRole.setUserId(entity.getId());
            userRole.setRoleId(roleId);
            userRoleService.save(userRole);
        }
    }

    @Override
    public void update(UserReq req) {
        checkLevel(req);

        if (userService.findByName(req.getUserName()) != null) {
            throw new BaseException(ResponseCode.USER_NAME_EXISTS);
        }

        if (userService.findByEmail(req.getEmail()) != null) {
            throw new BaseException(ResponseCode.EMAIL_EXISTS);
        }
        if (userService.findByPhone(req.getPhone()) != null) {
            throw new BaseException(ResponseCode.MOBILE_EXISTS);
        }

        User entity = new User();
        BeanUtils.copyProperties(req, entity);
        entity.setPassword(null);
        userService.updateEntity(entity);

        if (!CollectionUtils.isEmpty(req.getRoles())) {
            userRoleService.removeByUserId(req.getId());
            for (Long roleId : req.getRoles()) {
                UserRole userRole = new UserRole();
                userRole.setUserId(entity.getId());
                userRole.setRoleId(roleId);
                userRoleService.save(userRole);
            }
        }
    }

    @Override
    public void delete(Long id) {
        Integer currentLevel = Collections.min(userRoleService.findByUserId(securityFactory.getUserId()).stream().map(Role::getLevel).collect(Collectors.toList()));
        Integer optLevel = Collections.min(userRoleService.findByUserId(id).stream().map(Role::getLevel).collect(Collectors.toList()));
        if (currentLevel > optLevel) {
            throw new BaseException("角色权限不足");
        }
        userService.delete(id);
        userRoleService.removeByUserId(id);
    }

    @Override
    public void updatePass(UserPassReq req) {
        AuthUser currentUser = securityFactory.getCurrentUser();
        if (!new BCryptPasswordEncoder().matches(req.getOldPass(), currentUser.getPassword())) {
            throw new BaseException("旧密码不正确");
        }
        String newEncryptPass = new BCryptPasswordEncoder().encode(req.getNewPass());
        if (StringUtils.equals(newEncryptPass, currentUser.getPassword())) {
            throw new BaseException("新密码不能与旧密码相同");
        }
        User entity = new User();
        entity.setId(currentUser.getId());
        entity.setPassword(newEncryptPass);
        userService.updateEntity(entity);
    }

    private void checkLevel(UserReq user) {
        Integer currentLevel = Collections.min(userRoleService.findByUserId(securityFactory.getUserId()).stream().map(Role::getLevel).collect(Collectors.toList()));
        Integer optLevel = Collections.min(roleService.findByRoleIds(user.getRoles()).stream().map(Role::getLevel).collect(Collectors.toList()));
        if (currentLevel > optLevel) {
            throw new BaseException("角色权限不足");
        }
    }
}
