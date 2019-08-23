package com.suitfit.portal.model.pojo.criteria;

import lombok.Data;

import java.util.Set;

@Data
public class UserQueryCriteria {
    private Long id;
    private Set<Long> deptIds;
    private String userName;
    private String phone;
    private String email;
    private Integer state;
}
