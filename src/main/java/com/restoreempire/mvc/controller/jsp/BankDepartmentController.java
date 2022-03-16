package com.restoreempire.mvc.controller.jsp;

import javax.validation.Valid;

import com.restoreempire.mvc.model.BankDepartment;
import com.restoreempire.mvc.service.BankDepartmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/bank-departments")
public class BankDepartmentController {

    @Autowired
    private BankDepartmentService service;


    @GetMapping
    public String getBankDepartments(Model model){
        var departments = service.findAll();
        model.addAttribute("keys", new String[] {"id", "name"});
        model.addAttribute("departments", departments);
        model.addAttribute("title", "Bank departments");
        return "departments_list";
    }

    @GetMapping("/create")
    public String getBankCreationForm(Model model){
        model.addAttribute("title", "Add new department");
        return "department_create";
    }

    @PostMapping("create")
    public String createNewBank( @ModelAttribute @Valid BankDepartment department){
        service.save(department);
        return "redirect:/";
    }

    @GetMapping("{id}")
    public String bankById(@PathVariable Long id, Model model){
        var department = service.findById(id);
        model.addAttribute("department", department);
        return "department_page";
    }

    @GetMapping("/entity/{id}")
    public ResponseEntity<?> rep(@PathVariable Long id) {
        var body = service.findById(id);
        if( body == null) 
            return ResponseEntity.status(404).build();
        else
            return ResponseEntity.ok().body(body);
    }

    @PostMapping("/delete")
    public String delete(@RequestParam Long id){
        service.delete(id);
        return "redirect:/banks";
    }
}
