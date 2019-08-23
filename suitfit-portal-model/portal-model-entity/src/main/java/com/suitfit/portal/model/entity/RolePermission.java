package com.suitfit.portal.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.suitfit.framework.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: RolePermission
 * @description:
 * @author: Chonzi.xiao
 * @create: 2019-08-15 16:34
 */
@Data
@TableName("role_permission")
public class RolePermission extends BaseEntity {
    @ApiModelProperty(value = "角色id")
    private Long roleId;

    @ApiModelProperty(value = "权限id")
    private Long permissionId;
}
