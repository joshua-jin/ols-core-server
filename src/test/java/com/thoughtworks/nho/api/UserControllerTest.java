package com.thoughtworks.nho.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.nho.domain.Privilege;
import com.thoughtworks.nho.domain.Role;
import com.thoughtworks.nho.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserControllerTest extends BaseControllerTest {

    @Test
    void should_create_user() throws Exception {
        loginWithUser("future_star", Privilege.Symbol.CREATE_USER);
        Privilege privilege = Privilege.builder().name("创建用户").symbol(Privilege.Symbol.CREATE_USER).build();

        User user = User.builder().name("new_future_star").password("123456")
//                .role(Role.builder().privileges(Arrays.asList(privilege)).name("管理员").symbol(Role.Symbol.SYSTEM_ADMIN).build())
                .build();
        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(user)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.username").value("new_future_star"));
    }

}