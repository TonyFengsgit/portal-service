package com.suitfit.portal.model.pojo.vo.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class MenuReq {

    private Long id;

    @NotBlank
    private String name;

    private Integer sort;

    private String path;

    private String component;

    private String icon;

    private Long parentId;

    private Boolean frame;
}
