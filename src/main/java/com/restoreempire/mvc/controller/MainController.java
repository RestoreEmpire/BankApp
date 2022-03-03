package com.restoreempire.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class MainController {

    @GetMapping("/")
    public String index() {
        return "index";
    }
    
}
