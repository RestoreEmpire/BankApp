package com.restoreempire.servlets.account;

import java.io.IOException;
import java.util.ArrayList;

import com.restoreempire.model.Account;
import com.restoreempire.model.Bank;
import com.restoreempire.model.Client;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet(name="accountCreate", urlPatterns = "/accounts/create")
public class AccountCreateServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<Bank> banks = Bank.getAll();
        ArrayList<Client> clients = Client.getAll();
        req.setAttribute("banks", banks);
        req.setAttribute("clients", clients);
        getServletContext().getRequestDispatcher("/create/account.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // account-number=123&bank=1&client=3&funds=123
        if (
            req.getParameter("bank") != null && 
            !req.getParameter("bank").isEmpty() &&
            req.getParameter("client") != null &&
            !req.getParameter("client").isEmpty()
        ){
            Account account = new Account();
            account.setBankId(Long.parseLong(req.getParameter("bank")));
            account.setClientId(Long.parseLong(req.getParameter("client")));
            if (
                req.getParameter("funds") != null &&
                !req.getParameter("funds").isEmpty()
            ) {
                String funds = req.getParameter("funds");
                account.setFunds(funds);
            }
            if (
                req.getParameter("account-number") != null &&
                !req.getParameter("account-number").isEmpty()
            ){
                account.setAccountNumber(req.getParameter("account-number"));
            }
            else{
                account.setRandomAccountNumber();
            }
        
            account.create();
        }
        resp.sendRedirect("/accounts");
    }
}
