package com.ethanzyc.commons.base.security;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author ethan
 * @date 2019/7/26 21:09
 */
@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        LoginTypeName loginTypeName = JSON.parseObject(s, LoginTypeName.class);

        if (StringUtils.equals("ethan", loginTypeName.getUsername())) {

            User user = User.builder().id("1")
                    .email("zhuy@123.ci")
//                    .password("fff")
                    .password(new BCryptPasswordEncoder().encode("abc"))
                    // visitor 是无效的，必须ROLE_作为前缀
                    .roles(Lists.newArrayList("ROLE_admin", "visitor"))
                    .username("ethan")
                    .build();
            return JwtUserFactory.create(user);
        }

        throw new UsernameNotFoundException(String.format("No user found with username '%s'.", s));
    }
}
