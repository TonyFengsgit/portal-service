package com.suitfit.portal.model.pojo.vo.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
public class UserReq {

    private Long id;

    @NotBlank
    private String userName;

    @NotBlank
    private String phone;

    @NotBlank
    @Pattern(regexp = "([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}", message = "格式错误")
    private String email;

    @NotNull
    private String state;

    private Long departmentId;

    private List<Long> roles;
}
