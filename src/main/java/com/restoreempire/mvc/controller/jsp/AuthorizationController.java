package com.restoreempire.mvc.controller.jsp;

import java.security.Principal;

import javax.validation.Valid;

import com.mysql.cj.x.protobuf.MysqlxSession.AuthenticateOk;
import com.restoreempire.mvc.UsernameAlreadyExistsException;
import com.restoreempire.mvc.model.Role;
import com.restoreempire.mvc.model.User;
import com.restoreempire.mvc.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

@Controller
@Validated
public class AuthorizationController {

    @Autowired
    UserService service;

    @Autowired
    PasswordEncoder passwordEncoder;
    
    @Autowired
    OAuth2AuthorizedClientService authorizedClientService;

    
    // @GetMapping("/login")
    // String loginForm() {
    // 	return "login";
    // }

    @GetMapping("/principal")
    @ResponseBody
    Principal currentUser(Principal principal) {
        return principal;
    }

    @GetMapping("/oauth-client")
    @ResponseBody
    ResponseEntity<?> oauthClient(Authentication authentication) {
        OAuth2AuthorizedClient client = authorizedClientService.loadAuthorizedClient("github", authentication.getName());
        return ResponseEntity.ok().body(client);

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
    String registerUser(@Valid @ModelAttribute User user, Authentication authentication, Model model) {

        if (authentication != null) {
            OAuth2AuthorizedClient client = authorizedClientService.loadAuthorizedClient(
                "github",
                authentication.getName()
            );
            user.setGithubId(client.getPrincipalName());  
        }
        try {
            service.register(user);
            return "redirect:/login";
        } catch (UsernameAlreadyExistsException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        
    }

}
