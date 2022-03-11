package com.restoreempire.mvc.controller.jsp;

import javax.validation.Valid;

import com.restoreempire.mvc.model.Role;
import com.restoreempire.mvc.model.User;
import com.restoreempire.mvc.service.jsp.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Validated
public class AuthorizationController {

    @Autowired
    UserService service;

    // @GetMapping("/login")
	// String loginForm() {
	// 	return "login";
	// }

    // @PostMapping("/login")
    // String login(@Valid @ModelAttribute User user, Model model) {
    //     User dbUser = service.findByUsername(user.getUsername());
    //     if(dbUser == null){
    //         model.addAttribute("message", "User not exists");
    //         return "login";
    //     }
    //     if(!user.getPassword().equals(dbUser.getPassword())){
    //         model.addAttribute("message", "Wrong password");
    //         return "login";
    //     }
    //     UsernamePasswordAuthenticationToken authReq
    //     = new UsernamePasswordAuthenticationToken(user, );
    //     Authentication auth = authManager.authenticate(authReq);
    //     SecurityContext sc = SecurityContextHolder.getContext();
    //     sc.setAuthentication(auth);
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
        service.save(user);
        return "redirect:/login";
    }

}
