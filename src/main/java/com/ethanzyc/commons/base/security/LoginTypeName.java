package com.ethanzyc.commons.base.security;

import lombok.Data;

/**
 * @author ethan
 * @date 2019/9/5 09:08
 */
@Data
public class LoginTypeName {
    private String username;
    private LoginType loginType;
}
