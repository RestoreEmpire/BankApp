package com.restoreempire.controller.servlets.account;

import java.io.IOException;
import java.util.List;

import com.restoreempire.dao.AccountDao;
import com.restoreempire.dao.BankDao;
import com.restoreempire.dao.ClientDao;
import com.restoreempire.dao.Dao;
import com.restoreempire.exceptions.ValidationException;
import com.restoreempire.model.Account;
import com.restoreempire.model.Bank;
import com.restoreempire.model.Client;
import com.restoreempire.service.AccountService;
import com.restoreempire.service.Service;
import com.restoreempire.service.validators.Validation;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet(name="accountUpdate", urlPatterns = "/accounts/p")
public class AccountPageServlet extends HttpServlet {

    Dao<Account> dao = new AccountDao();
    Service<Account> service = new AccountService(dao);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Bank> banks = new BankDao().getAll(); // list of banks. We need to display them because it is a foreign key
        List<Client> clients = new ClientDao().getAll(); // client is a foreign key too 
        req.setAttribute("banks", banks);
        req.setAttribute("clients", clients);
        if(!Validation.isNullOrEmpty(req.getParameter("id"))){ 
            Account account = dao.read(Long.parseLong(req.getParameter("id")));
            getServletContext().setAttribute("account", account); // use this to prevent id substitution in the parameter
            req.setAttribute("title", account.getAccountNumber()); 
            req.setAttribute("defaultBank", new BankDao().read(account.getBankId()));
            req.setAttribute("defaultClient", new ClientDao().read(account.getClientId()));
        }
        getServletContext().getRequestDispatcher("/page/account.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ValidationException {
        Account account = (Account) getServletContext().getAttribute("account");
        service.update(account, service.setParams(req.getParameterMap()));
        resp.sendRedirect("/accounts");

    }
}
