package com.suitfit.portal.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.suitfit.framework.entity.BaseEntity;
import lombok.Data;

@Data
@TableName("role_menu")
public class RoleMenu extends BaseEntity {
    private Long roleId;
    private Long menuId;
}
