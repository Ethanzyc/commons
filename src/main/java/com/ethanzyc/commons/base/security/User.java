package com.ethanzyc.commons.base.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author ethan
 * @date 2019/7/26 21:02
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String id;

    private String username;

    private String password;
    private String email;
    //    private Date lastPasswordResetDate;
    private List<String> roles;
}
