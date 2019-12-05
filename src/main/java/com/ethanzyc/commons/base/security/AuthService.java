package com.ethanzyc.commons.base.security;

/**
 * @author ethan
 * @date 2019/7/26 22:42
 */
public interface AuthService {
    User register(User userToAdd);

    String login(String username, String password);

    String refresh(String oldToken);
}
