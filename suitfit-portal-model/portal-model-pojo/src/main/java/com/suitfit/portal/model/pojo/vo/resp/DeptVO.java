package com.suitfit.portal.model.pojo.vo.resp;

import lombok.Data;

import java.util.List;

@Data
public class DeptVO {

    private Long id;

    private String name;

    private Long parentId;

    private List<DeptVO> children;
}
