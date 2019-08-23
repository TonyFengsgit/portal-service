package com.suitfit.portal.base.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.suitfit.framework.dynamic.annotation.DBSource;
import com.suitfit.portal.model.entity.Department;
import com.suitfit.portal.model.entity.RoleDepartment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @program: RoleDepartmentMapper
 * @description:
 * @author: Chonzi.xiao
 * @create: 2019-08-16 10:01
 */
@DBSource("portal")
@Mapper
public interface RoleDepartmentMapper extends BaseMapper<RoleDepartment> {
    List<Department> findByRoleId(Long roleId);
}
