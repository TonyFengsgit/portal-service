package com.suitfit.portal.biz;


import com.suitfit.portal.model.entity.Permission;
import com.suitfit.portal.model.pojo.vo.req.PermissionReq;
import com.suitfit.portal.model.pojo.vo.resp.PermissionVO;

import java.util.List;
import java.util.Map;

public interface PermissionBiz {

    List<Map<String, Object>> getPermissionTree(List<Permission> parentList);

    List<PermissionVO> getPermission(PermissionReq req);

    void create(PermissionReq req);

    void update(PermissionReq req);

    void delete(Long id);
}
