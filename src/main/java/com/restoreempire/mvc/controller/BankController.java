package com.restoreempire.mvc.controller;

import java.util.Optional;

import javax.persistence.Entity;

import com.restoreempire.mvc.model.Bank;
import com.restoreempire.mvc.repository.mysql.MysqlBankRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Transactional
public class BankController {

    // @Autowired
    // private MysqlBankRepository bankRepository;

    // @GetMapping("/banks")
    // public String bank(Model model){
    //     var banks = bankRepository.findAll();
    //     model.addAttribute("keys", new String[] {"id", "name"});
    //     model.addAttribute("banks",banks);
    //     model.addAttribute("title", "Banks");
    //     return "banks_list";
    // }

    // @GetMapping("/banks/create")
    // public String getBankCreationForm(Model model){
    //     model.addAttribute("title", "Add bank");
    //     return "bank_form";
    // }

    // @PostMapping("/banks/create")
    // public String createNewBank(@RequestParam String name){
    //     Bank bank = new Bank(name);
    //     bankRepository.save(bank);
    //     return "redirect:/";
    // }

    // @GetMapping("/banks/{id}")
    // @ResponseBody
    // public Bank bankById(@PathVariable Long id){
    //     var bank = bankRepository.findById(id).orElse(null);
    //     return bank;
    // }

    // @GetMapping("/banks/entity/{id}")
    // public ResponseEntity<?> rep(@PathVariable Long id) {
    //     var body = bankRepository.findById(id).orElse(null);
    //     var etag = "12313123123";
    //     if( body == null) 
    //         return ResponseEntity.status(404).build();
    //     else
    //         return ResponseEntity.ok().eTag(etag).body(body);
    // }

    // @PostMapping("/bank/delete")
    // public String delete(@RequestParam Long id){
    //     bankRepository.deleteById(id);
    //     return "redirect:/banks";
    // }
}
