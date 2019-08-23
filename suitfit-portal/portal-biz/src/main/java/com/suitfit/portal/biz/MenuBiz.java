package com.suitfit.portal.biz;

import com.suitfit.portal.model.entity.Menu;
import com.suitfit.portal.model.pojo.dto.MenuDTO;
import com.suitfit.portal.model.pojo.vo.req.MenuReq;
import com.suitfit.portal.model.pojo.vo.resp.MenuVO;

import java.util.List;
import java.util.Map;

public interface MenuBiz {
    List<MenuVO> init();

    List<Map<String, Object>> getMenuTree(List<Menu> menus);

    List<MenuDTO> getMenus(MenuReq req);

    void create(MenuReq req);

    void update(MenuReq req);

    void delete(Long id);
}
