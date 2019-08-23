package com.suitfit.portal.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.suitfit.framework.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("menu")
public class Menu extends BaseEntity {

    @ApiModelProperty("菜单名称")
    private String name;

    private Long parentId;

    @ApiModelProperty("前端路由")
    private String component;

    @ApiModelProperty("请求地址")
    private String path;

    @ApiModelProperty("是否外链")
    private Boolean frame;

    @ApiModelProperty("图标")
    private String icon;

    @ApiModelProperty("排序")
    private Integer sort;


}
