package com.suitfit.portal.model.pojo.code;

import com.suitfit.framework.exception.code.BaseCode;

/**
 * @program: ResponseCode
 * @description:
 * @author: Chonzi.xiao
 * @create: 2019-08-19 09:45
 */
public class ResponseCode extends BaseCode {
    public static ResponseCode USER_PASSWORD_ERROR = new ResponseCode("", "用户名或密码错误");
    public static ResponseCode LOGIN_SUCCESS = new ResponseCode("", "登录成功");
    public static ResponseCode REGISTER_SUCCESS = new ResponseCode("", "注册成功");
    public static ResponseCode REGISTER_FAILURE = new ResponseCode("", "注册失败");
    public static ResponseCode ACCOUNT_DISABLED = new ResponseCode("", "账户被禁用，请联系管理员");
    public static ResponseCode TOKEN_EXPIRE = new ResponseCode("", "登录已失效，请重新登录");
    public static ResponseCode PERMISSION_ERROR = new ResponseCode("", "抱歉，您没有访问权限");
    public static ResponseCode CAPTCHA_PARAM_INCOMPLETE = new ResponseCode("", "请传入图形验证码所需参数captchaId或code");
    public static ResponseCode CAPTCHA_EXPIRE = new ResponseCode("", "验证码已过期，请重新获取");
    public static ResponseCode CAPTCHA_ERROR = new ResponseCode("", "图形验证码输入错误");
    public static ResponseCode LOGIN_TOO_TIMES = new ResponseCode("", "登录错误次数超过限制");
    public static ResponseCode ACCOUNT_EXISTS = new ResponseCode("", "该用户名已被注册");
    public static ResponseCode PASSWORD_ERROR = new ResponseCode("", "密码不正确");
    public static ResponseCode MOBILE_EXISTS = new ResponseCode("", "该手机号已绑定其他账户");
    public static ResponseCode EMAIL_EXISTS = new ResponseCode("", "该邮箱已绑定其他账户");
    public static ResponseCode USER_NAME_EXISTS = new ResponseCode("", "用户名已被注册");
    public static ResponseCode USER_DONT_EXISTS = new ResponseCode("", "用户不存在");
    public static ResponseCode PERMISSION_EXISTS = new ResponseCode("", "权限类型已经存在");
    public static ResponseCode PERMISSION_DEL_ERROR = new ResponseCode("", "删除失败，包含正被角色使用关联的权限");
    public static ResponseCode ROLE_DONT_EXISTS = new ResponseCode("", "角色不存在");
    public static ResponseCode ROLE_DEL_ERROR = new ResponseCode("", "删除失败，包含正被用户使用关联的角色");

    public ResponseCode(String code, String message) {
        super(code, message);
    }
}
