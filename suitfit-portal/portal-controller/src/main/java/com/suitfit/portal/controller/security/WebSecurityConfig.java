package com.suitfit.portal.controller.security;

import com.suitfit.framework.utils.redis.RedisService;
import com.suitfit.portal.base.service.utils.SecurityFactory;
import com.suitfit.portal.controller.security.filter.ImageValidateFilter;
import com.suitfit.portal.controller.security.filter.JWTAuthenticationFilter;
import com.suitfit.portal.controller.security.handler.AuthenticationFailHandler;
import com.suitfit.portal.controller.security.handler.AuthenticationSuccessHandler;
import com.suitfit.portal.controller.security.handler.DefaultAuthenticationEntryPoint;
import com.suitfit.portal.controller.security.handler.RestAccessDeniedHandler;
import com.suitfit.portal.model.pojo.properties.CaptchaProperties;
import com.suitfit.portal.model.pojo.properties.IgnoredUrlsProperties;
import com.suitfit.portal.model.pojo.properties.TokenProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Security 核心配置类
 * 开启控制权限至Controller
 *
 * @author Exrickx
 */
@Slf4j
@Configuration
@EnableWebSecurity
@EnableConfigurationProperties({TokenProperties.class, IgnoredUrlsProperties.class, CaptchaProperties.class})
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private TokenProperties tokenProperties;

    @Autowired
    private CaptchaProperties captchaProperties;

    @Autowired
    private IgnoredUrlsProperties ignoredUrlsProperties;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationSuccessHandler successHandler;

    @Autowired
    private AuthenticationFailHandler failHandler;

//    @Autowired
//    private MyFilterSecurityInterceptor myFilterSecurityInterceptor;

    @Autowired
    private RedisService redisTemplate;

    @Autowired
    private SecurityFactory securityFactory;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean
    GrantedAuthorityDefaults grantedAuthorityDefaults() {
        // Remove the ROLE_ prefix
        return new GrantedAuthorityDefaults("");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 过滤静态资源
        String[] urls = ignoredUrlsProperties.getUrls().toArray(new String[ignoredUrlsProperties.getUrls().size()]);
        web.ignoring().antMatchers(urls);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http
//            .formLogin().loginProcessingUrl("/auth/login")
//                .loginPage("/api/portal/auth/needLogin").permitAll()
//            .successHandler(successHandler)
//            .failureHandler(failHandler)
//            .and()//禁用csrf和session
//            .csrf().disable()
//            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//            .and()// 其他的需要认证访问
//            .authorizeRequests()
//            .anyRequest().authenticated()
//            .and()//权限拒绝处理
//            .exceptionHandling().accessDeniedHandler(accessDeniedHandler)
//            .and()//增加了过滤器
//            //  .addFilterBefore(myFilterSecurityInterceptor, FilterSecurityInterceptor.class)
//            .addFilterBefore(new ImageValidateFilter(captchaProperties, redisTemplate), UsernamePasswordAuthenticationFilter.class)
//            .addFilter(new JWTAuthenticationFilter(authenticationManager, tokenProperties, redisTemplate, securityFactory));
        String []patterns = ignoredUrlsProperties.getPath().toArray(new String[0]);
        http.csrf().disable()
                .authorizeRequests().antMatchers(patterns).permitAll()
                .anyRequest().authenticated()
                .and().formLogin()
                .loginPage("/auth/toLogin").permitAll()
                .loginProcessingUrl("/login").permitAll()
                .successHandler(successHandler)
                .failureHandler(failHandler)
                .and().exceptionHandling().accessDeniedHandler(new RestAccessDeniedHandler())// PS:因为framework中已经对AccessDeniedException异常做了处理，此处不生效
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilterBefore(new ImageValidateFilter(captchaProperties, redisTemplate), UsernamePasswordAuthenticationFilter.class)
                .addFilter(new JWTAuthenticationFilter(authenticationManager, tokenProperties, redisTemplate,securityFactory));

    }
}
