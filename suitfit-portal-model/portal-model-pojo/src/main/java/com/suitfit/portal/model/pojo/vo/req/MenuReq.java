package com.suitfit.portal.model.pojo.vo.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class MenuReq {

    private Long id;

    @NotBlank
    private String name;

    @NotNull
    private Integer sort;

    private String path;

    private String component;

    private String icon;

    private Long parentId;

    private Boolean frame;
}
