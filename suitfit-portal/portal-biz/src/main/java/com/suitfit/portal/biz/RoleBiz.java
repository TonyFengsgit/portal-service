package com.suitfit.portal.biz;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.suitfit.framework.utils.page.PageVO;
import com.suitfit.portal.model.pojo.vo.req.RoleReq;
import com.suitfit.portal.model.pojo.vo.resp.RoleVO;

import java.util.List;

public interface RoleBiz {
    RoleVO getRoles(Long id);

    PageVO<RoleVO> getAll(Page initPage);

    PageVO<RoleVO> getRoles(RoleReq req, Page initPage);

    List<Integer> getLevel();

    void create(RoleReq req);

    void update(RoleReq req);

    void updateMenu(RoleReq req);

    void updatePermission(RoleReq req);

    void delete(Long id);
}
