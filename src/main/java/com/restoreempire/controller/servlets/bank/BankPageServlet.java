package com.restoreempire.controller.servlets.bank;


import java.io.IOException;

import com.restoreempire.dao.BankDao;
import com.restoreempire.dao.Dao;
import com.restoreempire.model.Bank;
import com.restoreempire.service.BankService;
import com.restoreempire.service.Service;
import com.restoreempire.service.validators.Validation;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name="bankPage",urlPatterns={"/banks/p"})
public class BankPageServlet extends HttpServlet {

    Dao<Bank> dao = new BankDao();
    Service<Bank> service = new BankService(dao);
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        if(!Validation.isNullOrEmpty(req.getParameter("id"))) {
            Bank bank = dao.read(Long.parseLong(req.getParameter("id")));
            getServletContext().setAttribute("bank", bank); // use this to prevent id substitution in the parameter
            req.setAttribute("title", bank.getName());
        }
        getServletContext().getRequestDispatcher("/page/bank.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Bank bank = (Bank) getServletContext().getAttribute("bank"); 
        Bank updating = service.setParams(req.getParameterMap());
        service.update(bank, updating);
        resp.sendRedirect("/banks");
    }
}

