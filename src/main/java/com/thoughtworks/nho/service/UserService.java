package com.thoughtworks.nho.service;

import com.thoughtworks.nho.domain.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    User create(User user);

    User findByName(String username);
}
