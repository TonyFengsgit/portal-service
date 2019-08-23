package com.suitfit.portal.model.pojo.vo.req;

import lombok.Data;

@Data
public class UserPassReq {
    private String oldPass;
    private String newPass;
}
