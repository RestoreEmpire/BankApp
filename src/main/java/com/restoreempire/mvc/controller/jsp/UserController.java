package com.restoreempire.mvc.controller.jsp;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import com.restoreempire.mvc.model.User;
import com.restoreempire.mvc.repository.postgres.UserRepositorty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
    
    @Autowired
    private UserRepositorty repo;

    @GetMapping("/users")
    @ResponseBody
    public List<User> showUsers() { 
        var users = repo.findAll();
        return users;
    }

    @GetMapping("/users/create")
    public String showUserCreationForm(){
        return "user_form";
    }

    @PostMapping("/users/create")
    public String createNewUser(
        @RequestParam String surname,
        @RequestParam String firstname,
        @RequestParam(required=false) String middlename,
        @RequestParam LocalDate birthdate
    ){
        @Valid User user = new User(
            surname,
            firstname,
            middlename, 
            birthdate
        );
        repo.save(user);
        return "redirect:/users";         
    }
}
