package com.suitfit.portal.base.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.suitfit.framework.dynamic.annotation.DBSource;
import com.suitfit.portal.model.entity.RolePermission;
import org.apache.ibatis.annotations.Mapper;

/**
 * @program: RolePermissionMapper
 * @description:
 * @author: Chonzi.xiao
 * @create: 2019-08-16 10:02
 */
@DBSource("portal")
@Mapper
public interface RolePermissionMapper extends BaseMapper<RolePermission> {
}
