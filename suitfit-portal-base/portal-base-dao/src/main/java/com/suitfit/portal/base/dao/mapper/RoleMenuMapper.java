package com.suitfit.portal.base.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.suitfit.framework.dynamic.annotation.DBSource;
import com.suitfit.portal.model.entity.Menu;
import com.suitfit.portal.model.entity.RoleMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@DBSource("portal")
@Mapper
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {
    List<Menu> findByRoleIds(List<Long> roleIds);
}
