package com.suitfit.portal.model.pojo.vo.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class PermissionVO {

    private Long id;

    @ApiModelProperty(value = "权限名称")
    private String name;

    private String alias;

    @ApiModelProperty(value = "父id")
    private Long parentId;

    @ApiModelProperty(value = "排序值")
    private Integer sort;

    private List<PermissionVO> children;

}
