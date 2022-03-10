package com.restoreempire.mvc.service.jsp;

import com.restoreempire.mvc.model.User;
import com.restoreempire.mvc.repository.postgres.UserRepositorty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepositorty userRepo;

    public User findByUsername(String username){
        return userRepo.findByUsername(username);
    }

    public void save(User user){
        userRepo.save(user);
    }
}
