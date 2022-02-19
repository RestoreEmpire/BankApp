package com.restoreempire.controller.servlets.bank;


import java.io.IOException;

import com.restoreempire.dao.BankDao;
import com.restoreempire.dao.Dao;
import com.restoreempire.model.Bank;
import com.restoreempire.service.BankService;
import com.restoreempire.service.Service;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name="bankCreate",urlPatterns={"/banks/create"})
public class BankCreateServlet extends HttpServlet {
    
    Dao<Bank> dao = new BankDao();
    Service<Bank> service = new BankService(dao);
    

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        getServletContext().removeAttribute("bank");
        req.setAttribute("title", "Bank | Create");
        getServletContext().getRequestDispatcher("/page/bank.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            service.create(service.setParams(req.getParameterMap()));
            resp.sendRedirect("/banks");    
    }
}