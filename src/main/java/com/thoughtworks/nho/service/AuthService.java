package com.thoughtworks.nho.service;


import com.thoughtworks.nho.cofiguration.security.JWTUser;
import com.thoughtworks.nho.cofiguration.security.LoginRequestUser;
import com.thoughtworks.nho.cofiguration.security.RegisterRequestUser;
import com.thoughtworks.nho.domain.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AuthService {
    void logout(HttpServletRequest authorization);

    JWTUser getAuthorizedJWTUser(HttpServletRequest request);

    boolean hasJWTToken(HttpServletRequest request);

    boolean isTokenInBlackList(HttpServletRequest request);

    JWTUser login(HttpServletResponse response, LoginRequestUser loginRequestUser);
    User register(RegisterRequestUser registerRequestUser);
}
