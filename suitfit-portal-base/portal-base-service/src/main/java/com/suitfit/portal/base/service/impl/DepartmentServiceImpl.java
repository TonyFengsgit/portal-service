package com.suitfit.portal.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.suitfit.framework.service.impl.BaseServiceImpl;
import com.suitfit.portal.base.dao.mapper.DepartmentMapper;
import com.suitfit.portal.base.service.DepartmentService;
import com.suitfit.portal.model.entity.Department;
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
}
