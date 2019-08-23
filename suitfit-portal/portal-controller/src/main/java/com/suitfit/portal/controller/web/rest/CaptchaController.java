package com.suitfit.portal.controller.web.rest;

import com.suitfit.framework.annotation.Log;
import com.suitfit.framework.utils.captcha.CaptchaUtils;
import com.suitfit.framework.utils.redis.RedisService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author Exrickx
 */
@RequestMapping("captcha")
@RestController
public class CaptchaController {

    @Autowired
    private RedisService redisTemplate;

    @RequestMapping(value = "init", method = RequestMethod.GET)
    @ApiOperation(value = "根据验证码ID获取图片")
    public void drawCaptcha(@Log(ignore = true) HttpServletResponse response) throws IOException {
        //得到验证码 生成指定验证码
        String captchaId = UUID.randomUUID().toString().replace("-", "");
        String code = new CaptchaUtils().randomStr(4);
        // 缓存验证码
        redisTemplate.set(captchaId, code, 10L, TimeUnit.MINUTES);
        CaptchaUtils vCode = new CaptchaUtils(116, 36, 4, 10, code);
        response.setContentType("image/png");
        response.addHeader("captchaId", captchaId);
        vCode.write(response.getOutputStream());
    }
}
