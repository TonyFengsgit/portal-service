package com.suitfit.portal.model.pojo.criteria;

import lombok.Data;

import java.util.Set;

@Data
public class DeptQueryCriteria {
    private Set<Long> ids;

    private String name;

    private Long parentId;
}

