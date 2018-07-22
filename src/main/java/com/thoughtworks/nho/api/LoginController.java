package com.thoughtworks.nho.api;

import com.thoughtworks.nho.cofiguration.security.JWTUser;
import com.thoughtworks.nho.cofiguration.security.LoginRequestUser;
import com.thoughtworks.nho.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/user")
public class LoginController {


    @Autowired
    private AuthService authService;


    /**
     * 登陆
     *
     * @param loginRequestUser
     * @param response
     * @return
     */
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public JWTUser login(@RequestBody LoginRequestUser loginRequestUser, HttpServletResponse response) {
        return authService.login(response, loginRequestUser);
    }
}
