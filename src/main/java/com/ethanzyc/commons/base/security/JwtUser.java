package com.ethanzyc.commons.base.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @author ethan
 * @date 2019/7/26 21:04
 */
public class JwtUser implements UserDetails {

    private final String id;
    private final String username;
    private final String password;
    private final String email;
    private final Collection<? extends GrantedAuthority> authorities;
//    private final Date lastPasswordResetDate;

    public JwtUser(
            String id,
            String username,
            String password,
            String email,
            Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
//        this.password = password;
        this.password = password;
        this.email = email;
        this.authorities = authorities;
//        this.lastPasswordResetDate = lastPasswordResetDate;
    }

    //返回分配给用户的角色列表
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        System.out.println("getAuthorities");
        return authorities;
    }

    @JsonIgnore
    public String getId() {
        System.out.println("getId");
        return id;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        System.out.println("getPassword");


        return password;
    }

    @Override
    public String getUsername() {
        System.out.println("getUsername");
        return username;
    }

    // 账户是否未过期
    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        System.out.println("isAccountNonExpired");
        return true;
    }

    // 账户是否未锁定
    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        System.out.println("isAccountNonLocked");
        return true;
    }

    // 密码是否未过期
    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        System.out.println("isCredentialsNonExpired");
        return true;
    }

    // 账户是否激活
    @JsonIgnore
    @Override
    public boolean isEnabled() {
        System.out.println("isEnabled");
        return true;
    }
}
