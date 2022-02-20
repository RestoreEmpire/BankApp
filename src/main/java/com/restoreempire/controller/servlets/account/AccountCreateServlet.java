package com.restoreempire.controller.servlets.account;

import java.io.IOException;
import java.util.List;

import com.restoreempire.dao.AccountDao;
import com.restoreempire.dao.BankDao;
import com.restoreempire.dao.ClientDao;
import com.restoreempire.dao.Dao;
import com.restoreempire.model.Account;
import com.restoreempire.model.Bank;
import com.restoreempire.model.Client;
import com.restoreempire.service.AccountService;
import com.restoreempire.service.Service;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet(name="accountCreate", urlPatterns = "/accounts/create")
public class AccountCreateServlet extends HttpServlet {
    
    Dao<Account> dao = new AccountDao();
    Service<Account> service = new AccountService(dao);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().removeAttribute("account"); // removing object from updating page servlet, because this servlet uses same jsp
        List<Bank> banks = new BankDao().getAll(); // list of banks. We need to display them because it is a foreign key
        List<Client> clients = new ClientDao().getAll(); // client is a foreign key too 
        req.setAttribute("banks", banks);
        req.setAttribute("clients", clients);
        getServletContext().getRequestDispatcher("/page/account.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            service.create(service.setParams(req.getParameterMap()));
            resp.sendRedirect("/accounts");
    }
}
