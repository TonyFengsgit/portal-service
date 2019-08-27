package com.suitfit.portal.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.suitfit.framework.service.impl.BaseServiceImpl;
import com.suitfit.framework.utils.StringUtils;
import com.suitfit.portal.base.dao.mapper.DepartmentMapper;
import com.suitfit.portal.base.service.DepartmentService;
import com.suitfit.portal.model.entity.Department;
import com.suitfit.portal.model.pojo.criteria.DeptQueryCriteria;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @program: DepartmentServiceImpl
 * @description:
 * @author: Chonzi.xiao
 * @create: 2019-08-16 10:08
 */
@Service
public class DepartmentServiceImpl extends BaseServiceImpl<DepartmentMapper, Department> implements DepartmentService {

    @Override
    public List<Department> getByPid(Long id) {
        QueryWrapper<Department> query = new QueryWrapper<>();
        query.lambda().eq(Department::getParentId, id);
        return list(query);
    }

    @Override
    public Department findById(Long departmentId) {
        return this.getById(departmentId);
    }

    @Override
    public List<Department> findByCriteria(DeptQueryCriteria criteria) {
        LambdaQueryWrapper<Department> query = new LambdaQueryWrapper<>();
        if (!StringUtils.isNullOrEmpty(criteria.getName())) {
            query.eq(Department::getName, criteria.getName());
        }
        return this.list(query);
    }

    @Override
    public String findNameById(Long parentId) {
        QueryWrapper<Department> query = new QueryWrapper<>();
        query.lambda().eq(Department::getParentId, parentId);
        Department entity = this.getOne(query);
        if (entity == null) {
            return null;
        } else {
            return entity.getName();
        }
    }

    @Override
    public void updateEntity(Department entity) {
        this.updateById(entity);
    }

    @Override
    public void deleteById(Long id) {
        this.removeById(id);
    }
}
