package com.suitfit.portal.model.pojo.dto;

import lombok.Data;

import java.util.List;

@Data
public class AuthModel {
    private Long id;
    private String userName;
    private String password;
    private Integer state;
    private List<RoleModel> roles;
}
