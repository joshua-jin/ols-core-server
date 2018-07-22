package com.thoughtworks.nho.api;

import com.thoughtworks.nho.cofiguration.security.JWTUser;
import com.thoughtworks.nho.cofiguration.security.LoginRequestUser;
import com.thoughtworks.nho.cofiguration.security.RegisterRequestUser;
import com.thoughtworks.nho.domain.User;
import com.thoughtworks.nho.exception.UserExistedException;
import com.thoughtworks.nho.repository.UserRepository;
import com.thoughtworks.nho.service.AuthService;
import com.thoughtworks.nho.service.UserService;
import com.thoughtworks.nho.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/authentication")
public class AuthenticationController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;


    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void logout(HttpServletRequest request) {
        authService.logout(request);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public JWTUser login(@RequestBody LoginRequestUser loginRequestUser, HttpServletResponse response) {
        return authService.login(response, loginRequestUser);
    }


    @PostMapping("register")
    @ResponseStatus(HttpStatus.CREATED)
    public User register(@RequestBody RegisterRequestUser registerRequestUser){
        return authService.register(registerRequestUser);
    }

    @PostMapping("exist")
    @ResponseStatus(HttpStatus.OK)
    public String checkIfUserExist(@RequestBody User user){
        if (userRepository.findByName(user.getName()) != null) {
            return "400";
        }
        return "200";

    }
}
