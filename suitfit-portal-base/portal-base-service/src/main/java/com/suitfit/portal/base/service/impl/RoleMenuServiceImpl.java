package com.suitfit.portal.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.suitfit.framework.service.impl.BaseServiceImpl;
import com.suitfit.portal.base.dao.mapper.RoleMenuMapper;
import com.suitfit.portal.base.service.RoleMenuService;
import com.suitfit.portal.model.entity.Menu;
import com.suitfit.portal.model.entity.RoleMenu;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleMenuServiceImpl extends BaseServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService {
    @Override
    public void deleteByRoleId(Long id) {
        QueryWrapper<RoleMenu> query = new QueryWrapper<>();
        query.lambda().eq(RoleMenu::getRoleId, id);
        this.remove(query);
    }

    @Override
    public List<Menu> findByRoleIds(List<Long> roleIds) {
        return this.baseMapper.findByRoleIds(roleIds);
    }

    @Override
    public void removeByMenuId(Long id) {
        QueryWrapper<RoleMenu> query = new QueryWrapper<>();
        query.lambda().eq(RoleMenu::getMenuId, id);
        this.remove(query);
    }
}
