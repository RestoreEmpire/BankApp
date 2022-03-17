package com.restoreempire.mvc.controller.jsp;

import java.security.Principal;

import javax.validation.Valid;

import com.mysql.cj.x.protobuf.MysqlxSession.AuthenticateOk;
import com.restoreempire.mvc.model.Role;
import com.restoreempire.mvc.model.User;
import com.restoreempire.mvc.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Validated
public class AuthorizationController {

    UserService service;

    PasswordEncoder passwordEncoder;
    
    public AuthorizationController(UserService service, PasswordEncoder passwordEncoder) {
        this.service = service;
        this.passwordEncoder = passwordEncoder;
    }
    

    // @GetMapping("/login")
    // String loginForm() {
    // 	return "login";
    // }

    @GetMapping("/current")
    @ResponseBody
    Principal currentUser(Principal principal) {
        return principal;
    }

    // @PostMapping("/login")
    // String login(@Valid @ModelAttribute User user, Model model) {
    //     Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    //     if(authentication.isAuthenticated()) {
    //         model.addAttribute("message", "User not exists");
    //         return "index";
    //     }
    //     User dbUser = service.findByUsername(user.getUsername());
    //     if(dbUser == null){
    //         model.addAttribute("message", "User not exists");
    //         return "login";
    //     }
    //     if(!user.getPassword().equals(dbUser.getPassword())){
    //         model.addAttribute("message", "Wrong password");
    //         return "login";
    //     }
    //     return "redirect:/";
    // }

    @GetMapping("/registration")
    String registrationForm() {
        return "registration";
    }

    @PostMapping("/registration")
    String registerUser(@Valid @ModelAttribute User user, Model model) {
        User dbUser = service.findByUsername(user.getUsername());
        if (dbUser != null){
            model.addAttribute("message", "User is already exists");
            return "registration";
        }
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        service.save(user);
        return "redirect:/login";
    }

}
