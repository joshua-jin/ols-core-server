package com.thoughtworks.nho.repository;


import com.thoughtworks.nho.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User findByName(String name);

}
