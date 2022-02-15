package com.restoreempire.controller.servlets.bank;


import com.restoreempire.dao.BankDao;
import com.restoreempire.exceptions.ValidationException;
import com.restoreempire.model.Bank;
import com.restoreempire.service.validators.Validation;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name="bankPage",urlPatterns={"/banks/p"})
public class BankPageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        if(!Validation.isNullOrEmpty(req.getParameter("id"))) {
            Bank bank = new BankDao().read(Long.parseLong(req.getParameter("id")));
            getServletContext().setAttribute("bank", bank);
            req.setAttribute("title", bank.getName());
        }
        getServletContext().getRequestDispatcher("/page/bank.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Bank bank = (Bank) getServletContext().getAttribute("bank"); 
        if (!Validation.isNullOrEmpty(req.getParameter("name"))){
            Bank replace = new Bank(req.getParameter("name"));
            replace.setId(bank.getId());
            new BankDao().update(bank, replace);
            resp.sendRedirect("/banks");
        }
        else throw new ValidationException("Wrong form input");
        
    }
}

