package com.suitfit.portal.base.service.utils;

import com.suitfit.portal.base.service.DepartmentService;
import com.suitfit.portal.base.service.RoleDepartmentService;
import com.suitfit.portal.base.service.UserRoleService;
import com.suitfit.portal.base.service.UserService;
import com.suitfit.portal.model.entity.Department;
import com.suitfit.portal.model.entity.Role;
import com.suitfit.portal.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class DataScope {

    private final Integer[] scopeType = {0, 1, 2};

    @Autowired
    private UserService userService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RoleDepartmentService roleDepartmentService;

    @Autowired
    private SecurityFactory securityFactory;

    public Set<Long> getDeptIds() {
        User user = userService.findByName(securityFactory.getUsername());
        // 用于存储部门id
        Set<Long> deptIds = new HashSet<>();
        // 查询用户角色
        List<Role> roleSet = userRoleService.findByUserId(user.getId());
        for (Role role : roleSet) {
            if (scopeType[0].equals(role.getDataType())) {
                return new HashSet<>();
            }
            // 存储本级的数据权限
            if (scopeType[1].equals(role.getDataType())) {
                deptIds.add(user.getDepartmentId());
            }
            // 存储自定义的数据权限
            if (scopeType[2].equals(role.getDataType())) {
                List<Department> depts = roleDepartmentService.findByRoleId(role.getId());
                for (Department dept : depts) {
                    deptIds.add(dept.getId());
                    List<Department> deptChildren = departmentService.getByPid(dept.getId());
                    if (deptChildren != null && deptChildren.size() != 0) {
                        deptIds.addAll(getDeptChildren(deptChildren));
                    }
                }
            }
        }
        return deptIds;
    }

    public List<Long> getDeptChildren(List<Department> deptList) {
        List<Long> list = new ArrayList<>();
        deptList.forEach(dept -> {
                    if (dept != null) {
                        List<Department> depts = departmentService.getByPid(dept.getId());
                        if (deptList != null && deptList.size() != 0) {
                            list.addAll(getDeptChildren(depts));
                        }
                        list.add(dept.getId());
                    }
                }
        );
        return list;
    }
}
