package com.suitfit.portal.model.pojo.vo.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class PermissionReq {

    private Long id;

    @NotBlank
    private String name;

    @NotNull
    private Long parentId;

    @NotBlank
    private String alias;

}
