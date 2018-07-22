package com.thoughtworks.nho.exceptionhandle;

import com.thoughtworks.nho.result.ErrorResult;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Component
@ControllerAdvice
public class RestfulExceptionHandler {

    /**
     * 登陆用户不存在处理
     *
     * @return
     */
    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResult loginExceptionHandle() {
        return ErrorResult.build(400,"用户名或密码不存在");
    }
}
