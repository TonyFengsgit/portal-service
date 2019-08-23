package com.suitfit.portal.model.pojo.auth;

import com.suitfit.portal.model.pojo.consts.CommonConstant;
import com.suitfit.portal.model.pojo.dto.AuthModel;
import com.suitfit.portal.model.pojo.dto.RoleModel;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Exrickx
 */
@Data
public class AuthUser implements UserDetails {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String username;
    private String password;
    private Integer state;
    private List<RoleModel> roles;

    public AuthUser(AuthModel user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.state = user.getState();
        this.roles = user.getRoles();
    }

    /**
     * 添加用户拥有角色
     *
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorityList = new ArrayList<>();
        roles.stream().forEach(role -> {
            role.getPermissions().stream().forEach(permission -> {
                authorityList.add(new SimpleGrantedAuthority(permission.getName()));
            });
        });
        return authorityList;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    /**
     * 账户是否过期
     *
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 是否禁用
     *
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return CommonConstant.USER_STATUS_LOCK.equals(this.getState()) ? false : true;
    }

    /**
     * 密码是否过期
     *
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 是否启用
     *
     * @return
     */
    @Override
    public boolean isEnabled() {
//        return CommonConstant.USER_STATUS_NORMAL.equals(this.getState()) ? true : false;
        return true;
    }
}