package com.suitfit.portal.model.pojo.vo.resp;

import lombok.Data;

import java.util.List;

@Data
public class MenuVO {

    private Long id;

    private String name;

    private Long parentId;

    private String path;

    private String redirect;

    private String component;

    private Boolean alwaysShow;

    private MenuMetaVO meta;

    private List<MenuVO> children;
}
