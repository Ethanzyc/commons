package com.ethanzyc.commons.base.security;

import java.io.Serializable;

/**
 * @author ethan
 * @date 2019/7/26 22:55
 */
public class JwtAuthenticationResponse implements Serializable {

    private static final long serialVersionUID = 1250166508152483573L;

    private final String token;

    public JwtAuthenticationResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }
}
