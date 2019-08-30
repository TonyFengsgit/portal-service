package com.suitfit.portal.biz.impl;

import com.suitfit.framework.exception.BaseException;
import com.suitfit.framework.utils.ListUtils;
import com.suitfit.framework.utils.bean.BeanUtils;
import com.suitfit.portal.base.service.PermissionService;
import com.suitfit.portal.base.service.RolePermissionService;
import com.suitfit.portal.biz.PermissionBiz;
import com.suitfit.portal.model.entity.Permission;
import com.suitfit.portal.model.entity.RolePermission;
import com.suitfit.portal.model.pojo.code.ResponseCode;
import com.suitfit.portal.model.pojo.criteria.QueryCriteria;
import com.suitfit.portal.model.pojo.vo.req.PermissionReq;
import com.suitfit.portal.model.pojo.vo.resp.PermissionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PermissionBizImpl implements PermissionBiz {

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private RolePermissionService rolePermissionService;

    @Override
    public List<Map<String, Object>> getPermissionTree(List<Permission> parentList) {
        List<Map<String, Object>> list = new LinkedList<>();
        if (parentList == null) {
            parentList = permissionService.getByPid(0L);
        }
        if (parentList==null){
            return null;
        }
        parentList.forEach(permission -> {
                    if (permission != null) {
                        List<Permission> permissionList = permissionService.getByPid(permission.getId());
                        Map<String, Object> map = new HashMap<>();
                        map.put("id", permission.getId());
                        map.put("label", permission.getAlias());
                        if (permissionList != null && permissionList.size() != 0) {
                            map.put("children", getPermissionTree(permissionList));
                        }
                        list.add(map);
                    }
                }
        );
        return list;
    }

    @Override
    public List<PermissionVO> getPermission(PermissionReq req) {
        List<PermissionVO> trees = new ArrayList<>();
        QueryCriteria query = new QueryCriteria();
        BeanUtils.copyProperties(req, query);
        List<Permission> entityList = permissionService.queryAll(query);
        List<PermissionVO> permissionList = (List<PermissionVO>) BeanUtils.convert(entityList, PermissionVO.class);
        if (permissionList!=null){
            for (PermissionVO parent : permissionList) {
                if ("0".equals(parent.getParentId().toString())) {
                    trees.add(parent);
                }
                for (PermissionVO it : permissionList) {
                    if (it.getParentId().equals(parent.getId())) {
                        if (parent.getChildren() == null) {
                            parent.setChildren(new ArrayList<PermissionVO>());
                        }
                        parent.getChildren().add(it);
                    }
                }
            }
        }
        if (ListUtils.isNullOrEmpty(trees)) {
            return permissionList;
        } else {
            return trees;
        }
    }

    @Override
    public void create(PermissionReq req) {
        req.setId(null);
        if (permissionService.findByName(req.getName()) != null) {
            throw new BaseException(ResponseCode.PERMISSION_EXISTS);
        }
        Permission entity = new Permission();
        BeanUtils.copyProperties(req, entity);
        permissionService.save(entity);
    }

    @Override
    public void update(PermissionReq req) {
        Permission p1 = permissionService.findById(req.getId());
        if (req.getId().equals(req.getParentId())) {
            throw new BaseException("上级不能为自己");
        }

        Permission p2 = permissionService.findByName(req.getName());
        if (p2 != null && !p2.getId().equals(p1.getId())) {
            throw new BaseException(ResponseCode.PERMISSION_EXISTS);
        }
        Permission entity = new Permission();
        BeanUtils.copyProperties(req, entity);
        req.setId(p1.getId());
        permissionService.updateEntity(entity);
    }

    @Override
    public void delete(Long id) {
        List<RolePermission> rolePermissions = rolePermissionService.findByPermissionId(id);
        if (!ListUtils.isNullOrEmpty(rolePermissions)) {
            throw new BaseException(ResponseCode.PERMISSION_DEL_ERROR);
        }
        permissionService.deleteById(id);
    }
}
