package com.springsecurityjwt.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springsecurityjwt.models.User;


public interface UserRepository extends JpaRepository<User, Integer>{
    User findByUserName(String userName);

}
