package com.suitfit.portal.model.pojo.vo.req;

import lombok.Data;

@Data
public class LoginUser {
    private String username;
    private String password;
    private String confirmPassword;
}
