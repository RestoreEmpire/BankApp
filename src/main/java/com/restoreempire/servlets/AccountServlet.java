package com.restoreempire.servlets;


import com.restoreempire.model.Account;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name="account",urlPatterns={"/accounts"})
public class AccountServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ArrayList<ArrayList<String>> values = new ArrayList<>();
        ArrayList<Account> accounts = Account.getAll();
        for (Account account : accounts) {
            var dict = new ArrayList<String>();
            dict.add(String.valueOf(account.getId()));
            dict.add(account.getAccountNumber());
            dict.add(account.getBankId().toString());
            dict.add(account.getClientId().toString());
            dict.add(account.getFunds().toString());
            values.add(dict);
        } // хранить данные нунжно в контексте сессии
        String[] keys = new String[]{"ID", "Account Number", "Client", "Bank", "Funds"};
        request.setAttribute("keys", keys);
        request.setAttribute("values", values);
        request.setAttribute("title", "Accounts");

        getServletContext().getRequestDispatcher("/show.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("delete") != null) {
            int id = Integer.valueOf(req.getParameter("delete"));
            Account account = new Account();
            account.read(id);
            account.delete();
        }
        resp.sendRedirect("/accounts");
    }

}
