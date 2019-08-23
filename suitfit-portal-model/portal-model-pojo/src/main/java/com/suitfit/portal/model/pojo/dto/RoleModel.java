package com.suitfit.portal.model.pojo.dto;

import lombok.Data;

import java.util.List;

@Data
public class RoleModel {
    private String name;
    private List<PermissionModel> permissions;
}
