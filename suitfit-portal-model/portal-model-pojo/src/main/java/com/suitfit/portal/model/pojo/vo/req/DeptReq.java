package com.suitfit.portal.model.pojo.vo.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
public class DeptReq {
    private Long id;

    @NotBlank
    private String name;

    @NotNull
    private Long parentId;

    private Set<Long> roles;
}
