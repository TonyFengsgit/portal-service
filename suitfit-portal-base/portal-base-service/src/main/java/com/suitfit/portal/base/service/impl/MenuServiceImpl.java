package com.suitfit.portal.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.suitfit.framework.service.impl.BaseServiceImpl;
import com.suitfit.framework.utils.StringUtils;
import com.suitfit.portal.base.dao.mapper.MenuMapper;
import com.suitfit.portal.base.service.MenuService;
import com.suitfit.portal.model.entity.Menu;
import com.suitfit.portal.model.pojo.criteria.QueryCriteria;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl extends BaseServiceImpl<MenuMapper, Menu> implements MenuService {
    @Override
    public List<Menu> findByPid(long l) {
        QueryWrapper<Menu> query = new QueryWrapper<>();
        query.lambda().eq(Menu::getParentId, l);
        return list(query);
    }

    @Override
    public List<Menu> findByCriteria(QueryCriteria query) {
        LambdaQueryWrapper<Menu> q = new LambdaQueryWrapper<>();
        if (query.getId() != null)
            q.eq(Menu::getId, query.getId());
        if (!StringUtils.isNullOrEmpty(query.getName())) {
            q.eq(Menu::getName, query.getName());
        }
        return list(q);
    }

    @Override
    public Menu findByName(String name) {
        LambdaQueryWrapper<Menu> query = new LambdaQueryWrapper();
        query.eq(Menu::getName, name);
        query.last("limit 1");
        return getOne(query);
    }

    @Override
    public Menu findById(Long id) {
        return this.getById(id);
    }

    @Override
    public void updateEntity(Menu entity) {
        this.updateById(entity);
    }

    @Override
    public void deleteById(Long id) {
        this.removeById(id);
    }
}
