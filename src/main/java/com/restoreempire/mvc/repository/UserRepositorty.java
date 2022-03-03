package com.restoreempire.mvc.repository;

import com.restoreempire.mvc.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositorty extends JpaRepository<User, Long> {
    
}
