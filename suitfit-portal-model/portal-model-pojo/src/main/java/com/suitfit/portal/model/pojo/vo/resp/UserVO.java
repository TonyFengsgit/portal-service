package com.suitfit.portal.model.pojo.vo.resp;

import lombok.Data;

@Data
public class UserVO {
    private Long id;
    private String userName;
    private String nickName;
    private String phone;
    private String email;
    private String state;
    private String departmentName;
}
