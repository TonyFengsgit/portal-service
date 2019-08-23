package com.suitfit.portal.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.suitfit.framework.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: Permission
 * @description:
 * @author: Chonzi.xiao
 * @create: 2019-08-15 16:33
 */
@Data
@TableName("permission")
public class Permission extends BaseEntity {

    @ApiModelProperty(value = "权限名称")
    private String name;

    private String alias;

    @ApiModelProperty(value = "父id")
    private Long parentId;

    @ApiModelProperty(value = "排序值")
    private Integer sort;
}
