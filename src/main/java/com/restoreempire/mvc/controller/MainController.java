package com.restoreempire.mvc.controller;

import java.util.List;

import com.restoreempire.mvc.model.Bank;
import com.restoreempire.mvc.service.BankService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class MainController {

    @Autowired
    BankService service;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/mysql/create")
    public String showMysqlForm(){
        return "bank_form";
    }

    @GetMapping("/postgres/create")
    public String showPostgresForm(){
        return "bank_form";
    }

    @PostMapping("/mysql/create")
    public String createInMysql(@RequestParam String name){
        Bank bank = new Bank(name);
        service.saveToMysql(bank);
        return "redirect:/mysql/show";
    }

    @PostMapping("/postgres/create")
    public String createInPostgres(@RequestParam String name){
        Bank bank = new Bank(name);
        service.saveToPostgres(bank);
        return "redirect:/postgres/show";
    }

    @GetMapping("/mysql/show")
    @ResponseBody
    public List<Bank> mysqlShow(){
        return service.showAllBanksFromMysql();
    }
     
    @GetMapping("/postgres/show")
    @ResponseBody
    public List<Bank> postgresShow(){
        return service.showAllBanksFromPostgres();
    }
}