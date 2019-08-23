package com.suitfit.portal.model.pojo.vo.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RoleVO {

    private Long id;

    @ApiModelProperty(value = "角色名 以ROLE_开头")
    private String name;

    @ApiModelProperty(value = "是否为注册默认角色")
    private Boolean defaultRole;

    @ApiModelProperty(value = "数据权限类型 0全部默认 1.当前 2.自定义")
    private Integer dataType;

    @ApiModelProperty(value = "备注")
    private String description;

    private Integer level;
}
