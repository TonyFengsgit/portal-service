package com.suitfit.portal.model.pojo.dto;

import lombok.Data;

import java.util.List;

@Data
public class MenuDTO {
    private Long id;

    private String name;

    private Long sort;

    private String path;

    private String component;

    private Long parentId;

    private Boolean frame;

    private String icon;

    private List<MenuDTO> children;

}
