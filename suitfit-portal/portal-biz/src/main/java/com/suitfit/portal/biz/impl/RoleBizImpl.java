package com.suitfit.portal.biz.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.suitfit.framework.exception.BaseException;
import com.suitfit.framework.utils.ListUtils;
import com.suitfit.framework.utils.bean.BeanUtils;
import com.suitfit.portal.base.dao.utils.PageUtils;
import com.suitfit.portal.base.service.*;
import com.suitfit.portal.base.service.utils.SecurityFactory;
import com.suitfit.portal.biz.RoleBiz;
import com.suitfit.portal.model.entity.Role;
import com.suitfit.portal.model.entity.RoleMenu;
import com.suitfit.portal.model.entity.RolePermission;
import com.suitfit.portal.model.entity.UserRole;
import com.suitfit.portal.model.pojo.code.ResponseCode;
import com.suitfit.portal.model.pojo.criteria.QueryCriteria;
import com.suitfit.portal.model.pojo.vo.common.PageVO;
import com.suitfit.portal.model.pojo.vo.req.RoleReq;
import com.suitfit.portal.model.pojo.vo.resp.RoleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleBizImpl implements RoleBiz {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RoleMenuService roleMenuService;

    @Autowired
    private RoleDepartmentService roleDepartmentService;

    @Autowired
    private RolePermissionService rolePermissionService;


    @Autowired
    private SecurityFactory securityFactory;

    @Override
    public RoleVO getRoles(Long id) {
        Role entity = roleService.get(id);
        RoleVO role = new RoleVO();
        BeanUtils.copyProperties(entity, role);
        return role;
    }

    @Override
    public PageVO<RoleVO> getAll(Page initPage) {
        IPage<Role> roleIPage = roleService.selectByPage(initPage);
        PageVO<RoleVO> rolePage = PageUtils.fromIpage(roleIPage, RoleVO.class);
        return rolePage;
    }

    @Override
    public PageVO<RoleVO> getRoles(RoleReq req, Page initPage) {
        QueryCriteria criteria = new QueryCriteria();
        IPage<Role> roleIPage = roleService.findByCriteria(criteria, initPage);
        PageVO<RoleVO> rolePage = PageUtils.fromIpage(roleIPage, RoleVO.class);
        return rolePage;
    }

    @Override
    public List<Integer> getLevel() {
        List<Role> roles = userRoleService.findByUserId(securityFactory.getUserId());
        List<Integer> levels = roles.stream().map(Role::getLevel).collect(Collectors.toList());
        return levels;
    }

    @Override
    public void create(RoleReq req) {
        if (roleService.findByName(req.getName()) != null) {
            throw new BaseException("角色已经存在");
        }

        Role entity = new Role();
        BeanUtils.copyProperties(req, entity);
        roleService.save(entity);
    }

    @Override
    public void update(RoleReq req) {
        Role old1 = roleService.get(req.getId());
        Role old2 = roleService.findByName(req.getName());

        if (old2 != null && !old2.getId().equals(old1.getId())) {
            throw new BaseException("角色已经存在");
        }
        Role entity = new Role();
        BeanUtils.copyProperties(req, entity);
        entity.setId(old1.getId());
        roleService.updateEntity(entity);
    }

    @Override
    public void updateMenu(RoleReq req) {
        if (!ObjectUtils.isEmpty(req.getMenus())) {
            roleMenuService.deleteByRoleId(req.getId());
            for (Long menuId : req.getMenus()) {
                RoleMenu roleMenu = new RoleMenu();
                roleMenu.setMenuId(menuId);
                roleMenu.setRoleId(req.getId());
                roleMenuService.save(roleMenu);
            }
        }
    }

    @Override
    public void updatePermission(RoleReq req) {
        if (!ObjectUtils.isEmpty(req.getMenus())) {
            rolePermissionService.deleteByRoleId(req.getId());
            for (Long permissionId : req.getPermissions()) {
                RolePermission rolePermission = new RolePermission();
                rolePermission.setPermissionId(permissionId);
                rolePermission.setRoleId(req.getId());
                rolePermissionService.save(rolePermission);
            }
        }
    }

    @Override
    public void delete(Long id) {
        List<UserRole> userRoleList = userRoleService.findByRoleId(id);
        if (!ListUtils.isNullOrEmpty(userRoleList)) {
            throw new BaseException(ResponseCode.ROLE_DEL_ERROR);
        }

        rolePermissionService.deleteByRoleId(id);
        roleMenuService.deleteByRoleId(id);
        roleDepartmentService.deleteByRoleId(id);
        roleService.deleteById(id);
    }
}
