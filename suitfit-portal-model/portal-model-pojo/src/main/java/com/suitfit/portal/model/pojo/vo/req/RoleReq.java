package com.suitfit.portal.model.pojo.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
public class RoleReq {

    private Long id;

    @NotBlank
    @ApiModelProperty(value = "角色名 以ROLE_开头")
    private String name;

    @ApiModelProperty(value = "是否为注册默认角色")
    private Boolean defaultRole = false;

    @ApiModelProperty(value = "数据权限类型 0全部默认 1.当前 2.自定义")
    private Integer dataType = 2;

    @ApiModelProperty(value = "备注")
    private String description;

    private Integer level = 1;

    private Set<Long> menus;

    private Set<Long> permissions;

}
