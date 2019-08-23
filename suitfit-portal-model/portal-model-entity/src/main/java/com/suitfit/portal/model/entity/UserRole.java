package com.suitfit.portal.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.suitfit.framework.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: UserRole
 * @description:
 * @author: Chonzi.xiao
 * @create: 2019-08-15 16:34
 */
@Data
@TableName("user_role")
public class UserRole extends BaseEntity {
    @ApiModelProperty(value = "用户唯一id")
    private Long userId;

    @ApiModelProperty(value = "角色唯一id")
    private Long roleId;

}
