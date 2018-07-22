package com.thoughtworks.nho.cofiguration.security;


import lombok.*;

import javax.persistence.Entity;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequestUser {
    private String username;
    private String password;
}
