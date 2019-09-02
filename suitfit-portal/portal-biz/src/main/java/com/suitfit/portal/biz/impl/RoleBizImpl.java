package com.suitfit.portal.biz.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.suitfit.framework.exception.BaseException;
import com.suitfit.framework.utils.ListUtils;
import com.suitfit.framework.utils.bean.BeanUtils;
import com.suitfit.portal.base.dao.utils.PageUtils;
import com.suitfit.portal.base.service.*;
import com.suitfit.portal.base.service.utils.SecurityFactory;
import com.suitfit.portal.biz.RoleBiz;
import com.suitfit.portal.model.entity.*;
import com.suitfit.portal.model.pojo.code.ResponseCode;
import com.suitfit.portal.model.pojo.criteria.QueryCriteria;
import com.suitfit.portal.model.pojo.vo.common.PageVO;
import com.suitfit.portal.model.pojo.vo.req.RoleReq;
import com.suitfit.portal.model.pojo.vo.resp.MenuVO;
import com.suitfit.portal.model.pojo.vo.resp.PermissionVO;
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
        if (entity!=null) {
            List<Menu> menuEntity = roleMenuService.findByRoleIds(Lists.newArrayList(entity.getId()));
            List<MenuVO> menus = (List<MenuVO>) BeanUtils.convert(menuEntity, MenuVO.class);
            List<Permission> permissionList = rolePermissionService.findByRoleId(role.getId());
            List<PermissionVO> permissions = (List<PermissionVO>) BeanUtils.convert(permissionList, PermissionVO.class);
            role.setPermissions(permissions);
            role.setMenus(menus);
        }
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
        BeanUtils.copyProperties(req, criteria);
        IPage<Role> roleIPage = roleService.findByCriteria(criteria, initPage);
        PageVO<RoleVO> rolePage = PageUtils.fromIpage(roleIPage, RoleVO.class);
        return rolePage;
    }

    @Override
    public List<Integer> getLevel() {
        List<Role> roles = userRoleService.findByUserId(securityFactory.getUserId());
        if (roles==null){
            return null;
        }
        List<Integer> levels = roles.stream().map(Role::getLevel).collect(Collectors.toList());
        return levels;
    }

    @Override
    public void create(RoleReq req) {
        req.setId(null);
        if (roleService.findByName(req.getName()) != null) {
            throw new BaseException(ResponseCode.ROLE_EXISTS);
        }

        Role entity = new Role();
        BeanUtils.copyProperties(req, entity);
        roleService.save(entity);

        if (req.getDepts()!=null){
            for (Long deptId:req.getDepts()){
                RoleDepartment roleDepartment = new RoleDepartment();
                roleDepartment.setRoleId(entity.getId());
                roleDepartment.setDepartmentId(deptId);
                roleDepartmentService.save(roleDepartment);
            }
        }
    }

    @Override
    public void update(RoleReq req) {
        Role old1 = roleService.get(req.getId());
        Role old2 = roleService.findByName(req.getName());

        if (old2 != null && !old2.getId().equals(old1.getId())) {
            throw new BaseException(ResponseCode.ROLE_EXISTS);
        }
        Role entity = new Role();
        BeanUtils.copyProperties(req, entity);
        entity.setId(old1.getId());

        roleService.updateEntity(entity);

        if (2==req.getDataType()){
            roleDepartmentService.deleteByRoleId(req.getId());
            if (req.getDepts()!=null){
                for (Long deptId:req.getDepts()){
                    RoleDepartment roleDepartment = new RoleDepartment();
                    roleDepartment.setRoleId(entity.getId());
                    roleDepartment.setDepartmentId(deptId);
                    roleDepartmentService.save(roleDepartment);
                }
            }
        }
    }

    @Override
    public void updateMenu(RoleReq req) {
        roleMenuService.deleteByRoleId(req.getId());
        if (!ObjectUtils.isEmpty(req.getMenus())) {
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
        rolePermissionService.deleteByRoleId(req.getId());
        if (!ObjectUtils.isEmpty(req.getPermissions())) {
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
