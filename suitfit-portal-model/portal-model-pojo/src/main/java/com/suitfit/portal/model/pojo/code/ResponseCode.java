package com.suitfit.portal.model.pojo.code;

import com.suitfit.framework.exception.code.BaseCode;

/**
 * @program: ResponseCode
 * @description:
 * @author: Chonzi.xiao
 * @create: 2019-08-19 09:45
 */
public class ResponseCode extends BaseCode {

    /**
     * 注册相关
     */
    public static ResponseCode REGISTER_SUCCESS = new ResponseCode("7010", "注册成功");
    public static ResponseCode REGISTER_FAILURE = new ResponseCode("7011", "注册失败");
    public static ResponseCode ACCOUNT_EXISTS = new ResponseCode("7012", "该用户名已被注册");

    /**
     * 登陆相关
     */
    public static ResponseCode USER_PASSWORD_ERROR = new ResponseCode("7020", "用户名或密码错误");
    public static ResponseCode LOGIN_SUCCESS = new ResponseCode("7021", "登录成功");
    public static ResponseCode ACCOUNT_DISABLED = new ResponseCode("7022", "账户被禁用，请联系管理员");
    public static ResponseCode TOKEN_EXPIRE = new ResponseCode("7023", "登录已失效，请重新登录");
    public static ResponseCode LOGIN_TOO_TIMES = new ResponseCode("7024", "登录错误次数超过限制");
    public static ResponseCode PASSWORD_ERROR = new ResponseCode("7025", "密码不正确");
    /**
     * 验证码相关
     */
    public static ResponseCode CAPTCHA_PARAM_INCOMPLETE = new ResponseCode("7030", "请传入图形验证码所需参数captchaId或code");
    public static ResponseCode CAPTCHA_EXPIRE = new ResponseCode("7031", "验证码已过期，请重新获取");
    public static ResponseCode CAPTCHA_ERROR = new ResponseCode("7032", "图形验证码输入错误");

    public static ResponseCode PERMISSION_ERROR = new ResponseCode("7040", "抱歉，您没有访问权限");
    public static ResponseCode PERMISSION_LEVEL_ERROR = new ResponseCode("7041", "权限不足");

    public static ResponseCode MOBILE_EXISTS = new ResponseCode("7051", "该手机号已绑定其他账户");
    public static ResponseCode EMAIL_EXISTS = new ResponseCode("7052", "该邮箱已绑定其他账户");
    public static ResponseCode USER_NAME_EXISTS = new ResponseCode("7053", "用户名已存在");
    public static ResponseCode USER_DONT_EXISTS = new ResponseCode("7054", "用户不存在");
    public static ResponseCode PERMISSION_EXISTS = new ResponseCode("7055", "权限类型已经存在");
    public static ResponseCode PERMISSION_DEL_ERROR = new ResponseCode("7056", "删除失败，包含正被角色使用关联的权限");
    public static ResponseCode ROLE_DONT_EXISTS = new ResponseCode("7057", "角色不存在");
    public static ResponseCode ROLE_DEL_ERROR = new ResponseCode("7058", "删除失败，包含正被用户使用关联的角色");
    public static ResponseCode PARENT_NOT_SELF_ERROR = new ResponseCode("7059", "上级不能为自己");
    public static ResponseCode HTTP_URL_ERROR = new ResponseCode("7060", "外链必须以http://或者https://开头");
    public static ResponseCode MENU_EXISTS_ERROR = new ResponseCode("7061", "菜单已存在");
    public static ResponseCode PRODUCT_CODE_EXISTS = new ResponseCode("7062", "产品code已存在");
    public static ResponseCode ROLE_EXISTS = new ResponseCode("7063", "角色已经存在");
    public static ResponseCode OLD_PASSWORD_ERROR = new ResponseCode("7064", "旧密码不正确");
    public static ResponseCode PASSWORD_SAME_ERROR = new ResponseCode("7065", "新密码不能与旧密码相同");
    public static ResponseCode CHANNEL_USER_DONT_EXISTS = new ResponseCode("7066", "请先添加渠道商用户");

    public ResponseCode(String code, String message) {
        super(code, message);
    }
}
