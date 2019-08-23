package com.suitfit.portal.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.suitfit.framework.service.impl.BaseServiceImpl;
import com.suitfit.portal.base.dao.mapper.RoleDepartmentMapper;
import com.suitfit.portal.base.service.RoleDepartmentService;
import com.suitfit.portal.model.entity.Department;
import com.suitfit.portal.model.entity.RoleDepartment;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: RoleDepartmentServiceImpl
 * @description:
 * @author: Chonzi.xiao
 * @create: 2019-08-16 10:10
 */
@Service
public class RoleDepartmentServiceImpl extends BaseServiceImpl<RoleDepartmentMapper, RoleDepartment> implements RoleDepartmentService {


    @Override
    public List<Department> findByRoleId(Long roleId) {
        return this.baseMapper.findByRoleId(roleId);
    }

    @Override
    public void deleteByRoleId(Long id) {
        QueryWrapper<RoleDepartment> query = new QueryWrapper<>();
        query.lambda().eq(RoleDepartment::getRoleId, id);
        this.remove(query);
    }
}
