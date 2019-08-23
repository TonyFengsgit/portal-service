package com.suitfit.portal.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.suitfit.framework.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: User
 * @description:
 * @author: Chonzi.xiao
 * @create: 2019-08-15 16:34
 */
@Data
@TableName("user")
public class User extends BaseEntity {

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "手机")
    private String phone;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "用户头像")
    private String avatar;

    @ApiModelProperty(value = "状态 默认0正常 -1拉黑")
    private Integer state;

    @ApiModelProperty(value = "所属部门id")
    private Long departmentId;

    @TableField(exist = false)
    @ApiModelProperty(value = "所属部门名称")
    private String departmentName;

}
