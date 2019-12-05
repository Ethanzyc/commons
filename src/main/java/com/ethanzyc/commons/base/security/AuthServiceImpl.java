package com.ethanzyc.commons.base.security;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author ethan
 * @date 2019/7/26 22:42
 */
@Service
public class AuthServiceImpl implements AuthService {

    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;
    private JwtTokenUtil jwtTokenUtil;
//    private UserRepository userRepository;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    public AuthServiceImpl(
            AuthenticationManager authenticationManager,
            @Qualifier("jwtUserDetailsServiceImpl") UserDetailsService userDetailsService,
            JwtTokenUtil jwtTokenUtil) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    public User register(User userToAdd) {
        final String username = userToAdd.getUsername();
//        if(userRepository.findByUsername(username)!=null) {
//            return null;
//        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        final String rawPassword = userToAdd.getPassword();
        userToAdd.setPassword(encoder.encode(rawPassword));
//        userToAdd.setLastPasswordResetDate(new Date());
        userToAdd.setRoles(Lists.newArrayList("ROLE_admin"));
//        return userRepository.insert(userToAdd);
        return userToAdd;
    }

    @Override
    public String login(String username, String password) {

        LoginTypeName loginTypeName = new LoginTypeName();
        loginTypeName.setLoginType(LoginType.PC);
        loginTypeName.setUsername(username);
        String s = JSON.toJSONString(loginTypeName);

        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(s, password);
        final Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        final UserDetails userDetails = userDetailsService.loadUserByUsername(s);
        final String token = jwtTokenUtil.generateToken(userDetails);
        return token;
    }

    @Override
    public String refresh(String oldToken) {
        final String token = oldToken.substring(tokenHead.length());
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
//        if (jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())){
        return jwtTokenUtil.refreshToken(token);
//        }
//        return null;
    }
}
