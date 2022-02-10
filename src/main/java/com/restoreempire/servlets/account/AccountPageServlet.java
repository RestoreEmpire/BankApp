package com.restoreempire.servlets.account;

import java.io.IOException;
import java.util.ArrayList;

import com.restoreempire.model.Account;
import com.restoreempire.model.Bank;
import com.restoreempire.model.Client;
import com.restoreempire.processing.data.validators.Validation;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet(name="accountPage", urlPatterns = "/accounts/p")
public class AccountPageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<Bank> banks = Bank.getAll();
        ArrayList<Client> clients = Client.getAll();
        req.setAttribute("banks", banks);
        req.setAttribute("clients", clients);
        if(!Validation.isNullOrEmpty(req.getParameter("id"))){
            Account account = new Account(Long.parseLong(req.getParameter("id")));
            getServletContext().setAttribute("account", account);
            
            req.setAttribute("title", account.getAccountNumber());
            req.setAttribute("defaultBank", new Bank(account.getBankId()));
            req.setAttribute("defaultClient", new Client(account.getClientId()));
        }
        getServletContext().getRequestDispatcher("/create/account.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account retAccount = (Account) getServletContext().getAttribute("account");
        if (
            !Validation.isNullOrEmpty(req.getParameter("bank")) &&
            !Validation.isNullOrEmpty(req.getParameter("client"))
        ){
            Account account = new Account();
            account.setBankId(Long.parseLong(req.getParameter("bank")));
            account.setClientId(Long.parseLong(req.getParameter("client")));
            if (!Validation.isNullOrEmpty(req.getParameter("funds"))
            ) {
                String funds = req.getParameter("funds");
                account.setFunds(funds);
            }
            if (
                !Validation.isNullOrEmpty(req.getParameter("account-number"))
            ){
                account.setAccountNumber(req.getParameter("account-number"));
            }
            else{
                account.setRandomAccountNumber();
            }
            account.setId(retAccount.getId());
            retAccount.update(account);
            
        }
        resp.sendRedirect("/accounts");
    }
}
