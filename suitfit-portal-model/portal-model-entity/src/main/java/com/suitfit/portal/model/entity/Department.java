package com.suitfit.portal.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.suitfit.framework.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: Department
 * @description:
 * @author: Chonzi.xiao
 * @create: 2019-08-15 16:33
 */
@Data
@TableName("department")
public class Department extends BaseEntity {

    @ApiModelProperty("部门名称")
    private String name;

    @ApiModelProperty("父id")
    private Long parentId;

    @ApiModelProperty("排序")
    private Integer sort;

}
