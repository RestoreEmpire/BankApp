package com.restoreempire.controller.servlets.account;


import com.restoreempire.dao.AccountDao;
import com.restoreempire.dao.Dao;
import com.restoreempire.model.Account;
import com.restoreempire.processing.data.validators.Validation;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name="accountShow",urlPatterns={"/accounts"})
public class AccountShowServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<List<String>> values = new ArrayList<>();
        List<Account> accounts = new AccountDao().getAll();
        for (Account account : accounts) {
            var dict = new ArrayList<String>();
            dict.add(String.valueOf(account.getId()));
            dict.add(account.getAccountNumber());
            dict.add(account.getBankId().toString());
            dict.add(account.getClientId().toString());
            dict.add(account.getFunds().toString());
            values.add(dict);
        } // хранить данные нунжно в контексте сессии
        String[] keys = new String[]{"ID", "Account Number", "Bank","Client", "Funds"};
        request.setAttribute("keys", keys);
        request.setAttribute("values", values);
        request.setAttribute("title", "Accounts");

        getServletContext().getRequestDispatcher("/page/show.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(!Validation.isNullOrEmpty(req.getParameter("change"))) {
            resp.sendRedirect("/accounts/p?&id=" + req.getParameter("change"));
        }
        if (!Validation.isNullOrEmpty(req.getParameter("delete"))) {
            int id = Integer.valueOf(req.getParameter("delete"));
            Dao<Account> dao = new AccountDao();
            Account account = dao.read(id);
            dao.delete(account);
            resp.sendRedirect("/accounts");
        }
        
    }

    
}
