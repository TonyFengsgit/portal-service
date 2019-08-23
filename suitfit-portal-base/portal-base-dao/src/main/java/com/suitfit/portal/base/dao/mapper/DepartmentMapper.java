package com.suitfit.portal.base.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.suitfit.framework.dynamic.annotation.DBSource;
import com.suitfit.portal.model.entity.Department;
import org.apache.ibatis.annotations.Mapper;

/**
 * @program: DepartmentMapper
 * @description:
 * @author: Chonzi.xiao
 * @create: 2019-08-16 09:59
 */
@DBSource("portal")
@Mapper
public interface DepartmentMapper extends BaseMapper<Department> {

}
