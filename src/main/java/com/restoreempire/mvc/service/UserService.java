package com.restoreempire.mvc.service;

import com.restoreempire.mvc.model.User;
import com.restoreempire.mvc.repository.postgres.UserRepositorty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    UserRepositorty userRepo;

    public UserService(UserRepositorty userRepo) {
        this.userRepo = userRepo;
    }

    public User findByUsername(String username){
        return userRepo.findByUsername(username);
    }

    public void save(User user){
        user.setActive(true);
        userRepo.save(user);
    }

}
