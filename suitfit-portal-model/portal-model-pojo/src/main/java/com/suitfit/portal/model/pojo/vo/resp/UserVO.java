package com.suitfit.portal.model.pojo.vo.resp;

import com.suitfit.framework.utils.StringUtils;
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

    public String getNickName() {
        if (StringUtils.isNullOrEmpty(nickName)) {
            return userName;
        } else {
            return nickName;
        }
    }
}
