package com.thoughtworks.nho.service.impl;

import com.thoughtworks.nho.cofiguration.security.JWTUser;
import com.thoughtworks.nho.domain.Privilege;
import com.thoughtworks.nho.domain.Role;
import com.thoughtworks.nho.domain.User;
import com.thoughtworks.nho.dto.UserDTO;
import com.thoughtworks.nho.exception.UserExistedException;
import com.thoughtworks.nho.exception.UserNotExistException;
import com.thoughtworks.nho.repository.UserRepository;
import com.thoughtworks.nho.service.UserService;
import com.thoughtworks.nho.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<UserDTO> listUsers() {
        return userRepository.findAll().stream().map(user ->
                UserDTO.builder().id(user.getId()).name(user.getName())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public User create(User user) {
        user.setId(StringUtils.uuid());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (userRepository.findByName(user.getName()) != null) {
            throw new UserExistedException("User already exists.");
        }
        return userRepository.save(user);
    }

    @Override
    public User findByName(String username) {
        User user = userRepository.findByName(username);
        if (user == null) {
            throw new UserNotExistException(username);
        }
        return userRepository.findByName(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("Username does not exist.");
        }
//        Role role = user.getRole();
        return JWTUser.builder()
                .username(user.getName())
                .password(user.getPassword())
//                .role(user.getRole().getSymbol().name())
//                .privileges(role.getPrivileges().stream().map(Privilege::getSymbol).collect(toList()))
                .build();
    }

}
