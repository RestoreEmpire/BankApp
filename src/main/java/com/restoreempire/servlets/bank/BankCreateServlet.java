package com.restoreempire.servlets.bank;


import com.restoreempire.model.Bank;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name="bankCreate",urlPatterns={"/banks/create"})
public class BankCreateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        getServletContext().getRequestDispatcher("/create/bank.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("name") != null && !req.getParameter("name").isEmpty()){
            String name = req.getParameter("name");
            Bank bank = new Bank(name);
            bank.create();
        }
        resp.sendRedirect("/banks");
    }
}