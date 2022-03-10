package com.restoreempire.mvc.controller.jsp;

import com.restoreempire.mvc.model.Role;
import com.restoreempire.mvc.model.User;
import com.restoreempire.mvc.service.jsp.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthorizationController {

    @Autowired
    UserService service;

    @GetMapping("/registration")
    String registrationForm() {
        return "registration";
    }

    @PostMapping("/registration")
    String registerUser(@ModelAttribute User user, Model model) {
        User dbUser = service.findByUsername(user.getUsername());
        if (dbUser != null){
            model.addAttribute("message", "User is already exists");
            return "registration";
        }
        user.setActive(true);
        service.save(user);
        user.setRole(Role.USER);
        return "redirect:/login";
    }

}
