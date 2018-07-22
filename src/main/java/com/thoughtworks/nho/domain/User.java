package com.thoughtworks.nho.domain;

import com.thoughtworks.nho.util.StringUtils;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;

@Table(name = "t_user")
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
public class User {
    @Id
    private String id;

    @Column(unique = true)
    private String name;

    private String password;

//    @Column(name = "real_name")
//    private String realName;

//    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinColumn(name = "role")
//    private Role role;

    public User() {
        id = StringUtils.uuid();
    }
}
