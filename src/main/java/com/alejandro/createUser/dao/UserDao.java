package com.alejandro.createUser.dao;

import com.alejandro.createUser.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long>{
    User findByUsername(String username);
}
