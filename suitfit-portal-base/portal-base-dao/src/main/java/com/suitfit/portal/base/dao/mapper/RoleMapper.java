package com.suitfit.portal.base.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.suitfit.framework.dynamic.annotation.DBSource;
import com.suitfit.portal.model.entity.Role;
import org.apache.ibatis.annotations.Mapper;

/**
 * @program: RoleMapper
 * @description:
 * @author: Chonzi.xiao
 * @create: 2019-08-16 10:01
 */
@DBSource("portal")
@Mapper
public interface RoleMapper extends BaseMapper<Role> {
}
