package com.restoreempire.mvc.service;


import com.restoreempire.mvc.UsernameAlreadyExistsException;
import com.restoreempire.mvc.model.Role;
import com.restoreempire.mvc.model.User;
import com.restoreempire.mvc.repository.postgres.UserRepositorty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService{
    
    @Autowired
    UserRepositorty userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    private void save(User user){
        userRepository.save(user);
    }

    public void register(User user) throws UsernameAlreadyExistsException { 
        if (findByUsername(user.getUsername()) != null)
            throw new UsernameAlreadyExistsException("User already exists");
        user.setRole(new Role("User"));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(true);
        save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User findByGithubId(String githubId) {
        return userRepository.findByGithubId(githubId);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username + " not found");
        }
        return user;
    }

}
