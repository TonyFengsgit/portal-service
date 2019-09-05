package com.suitfit.portal.base.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.suitfit.framework.dynamic.annotation.DBSource;
import com.suitfit.portal.model.entity.Role;
import com.suitfit.portal.model.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @program: UserRoleMapper
 * @description:
 * @author: Chonzi.xiao
 * @create: 2019-08-16 10:03
 */
@DBSource("user_role")
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {
    List<Role> findByUserId(Long userId);

}
