package com.suitfit.portal.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.suitfit.framework.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: Role
 * @description:
 * @author: Chonzi.xiao
 * @create: 2019-08-15 16:34
 */
@Data
@TableName("role")
public class Role extends BaseEntity {
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
