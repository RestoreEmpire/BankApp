package com.restoreempire.servlets.bank;


import com.restoreempire.model.Bank;
import com.restoreempire.processing.data.validators.Validation;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name="bankCreate",urlPatterns={"/banks/create/"})
public class BankCreateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        req.setAttribute("title", "Bank | Create");
        getServletContext().getRequestDispatcher("/create/bank.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!Validation.isNullOrEmpty(req.getParameter("name"))){
            String name = req.getParameter("name");
            Bank bank = new Bank(name);
            bank.create();
        }
        resp.sendRedirect("/banks");
    }
}