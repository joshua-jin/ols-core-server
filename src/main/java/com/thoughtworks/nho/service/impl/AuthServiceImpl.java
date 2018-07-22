package com.thoughtworks.nho.service.impl;

import com.thoughtworks.nho.cofiguration.security.JWTUser;
import com.thoughtworks.nho.cofiguration.security.LoginRequestUser;
import com.thoughtworks.nho.cofiguration.security.RegisterRequestUser;
import com.thoughtworks.nho.domain.User;
import com.thoughtworks.nho.exception.InvalidCredentialException;
import com.thoughtworks.nho.exception.InvalidPasswordException;
import com.thoughtworks.nho.exception.InvalidUserException;
import com.thoughtworks.nho.exception.UserExistedException;
import com.thoughtworks.nho.repository.TokenAuthRepository;
import com.thoughtworks.nho.repository.UserRepository;
import com.thoughtworks.nho.service.AuthService;
import com.thoughtworks.nho.service.UserService;
import com.thoughtworks.nho.util.StringUtils;
import com.thoughtworks.nho.util.UserFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class AuthServiceImpl implements AuthService {
    private static final String PREFIX_BLACK_LIST = "SSJ-BLACKLIST-";

    @Value("${security.jwt.token-prefix:Bearer}")
    private String tokenPrefix;

    @Value("${security.jwt.header:Authorization}")
    private String header;

    @Value("${security.jwt.expiration-in-seconds}")
    private long expirationInSeconds;

    @Autowired
    private TokenAuthRepository authRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private UserService userService;

    @Override
    public JWTUser login(HttpServletResponse response, LoginRequestUser loginRequestUser) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestUser.getUsername(), loginRequestUser.getPassword()));
        JWTUser principal = (JWTUser) authenticate.getPrincipal();

        Map payload = StringUtils.readJsonStringAsObject(StringUtils.writeObjectAsJsonString(principal), Map.class);

        response.addHeader(header, String.join(" ", tokenPrefix,
                authRepository.generateToken(payload)));
        return principal;
    }

    @Override
    public void logout(HttpServletRequest request) {
        String token = extractToken(request);
        String key = PREFIX_BLACK_LIST + token;
        redisTemplate.opsForValue().set(key, token);
        redisTemplate.expire(key, expirationInSeconds, TimeUnit.SECONDS);
    }

    @Override
    public User register(RegisterRequestUser registerRequestUser) throws UserExistedException {
        User user = new User();
        user.setId(StringUtils.uuid());
        if (registerRequestUser.getUsername().isEmpty() ||
                registerRequestUser.getPassword().isEmpty() ||
                registerRequestUser.getUsername() == null ||
                registerRequestUser.getPassword() == null) {
            throw new InvalidUserException("username or password is empty!");
        }
        if (userRepository.findByName(user.getName()) != null) {
            throw new InvalidUserException("username or password is empty!");
        }

        user.setName(registerRequestUser.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequestUser.getPassword()));
        userRepository.save(user);
        return user;
    }

    @Override
    public JWTUser getAuthorizedJWTUser(HttpServletRequest request) {
        String payload = authRepository.extractAuthorizedPayload(extractToken(request));
        return StringUtils.readJsonStringAsObject(payload, JWTUser.class);
    }

    @Override
    public boolean hasJWTToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(header);
        return StringUtils.hasText(authorizationHeader) && authorizationHeader.startsWith(tokenPrefix);
    }

    @Override
    public boolean isTokenInBlackList(HttpServletRequest request) {
        String token = extractToken(request);
        return StringUtils.hasText(redisTemplate.opsForValue().get(PREFIX_BLACK_LIST + token));
    }

    private String extractToken(HttpServletRequest request) {
        if (!hasJWTToken(request)) {
            throw new InvalidCredentialException();
        }
        return request.getHeader(header).substring(tokenPrefix.length() + 1);
    }
}
