package com.restoreempire.mvc.controller.rest;

import java.util.List;

import javax.validation.Valid;

import com.restoreempire.mvc.model.BankDepartment;
import com.restoreempire.mvc.service.rest.BankDepartmentRestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/v1/bank-departments")
public class BankRestController {

    @Autowired
    private BankDepartmentRestService service;

    @GetMapping
    List<BankDepartment> findAll() {
        return service.findAllDepartments();
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void create(@Valid @RequestBody BankDepartment dep)  {
        service.save(dep);
    }

    @GetMapping("/{id}")
    BankDepartment bankDepartmentById(@PathVariable Long id){
        return service.findById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    BankDepartment updateBankDepartment(@PathVariable Long id, @Valid  @RequestBody BankDepartment entity) throws MethodArgumentNotValidException{
        return service.update(entity, id);
    }

    @DeleteMapping("/{id}")
    void deleteDepartment(@PathVariable Long id){
        service.delete(id);
    }



}
