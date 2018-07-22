package com.thoughtworks.nho.util;


import com.thoughtworks.nho.cofiguration.security.JWTUser;
import com.thoughtworks.nho.domain.Privilege;
import com.thoughtworks.nho.domain.User;

import java.util.stream.Collectors;
public class UserFactory {
    public static JWTUser fromUser(User user) {
        JWTUser jwtUser = new JWTUser();
        jwtUser.setUsername(user.getName());
//        jwtUser.setRole(user.getRole().getSymbol().name());
//        jwtUser.setPrivileges(user.getRole().getPrivileges().stream().map(Privilege::getSymbol).collect(Collectors.toList()));
        return jwtUser;
    }
}
