package com.restoreempire.controller.servlets.account;


import java.io.IOException;

import com.restoreempire.dao.AccountDao;
import com.restoreempire.dao.Dao;
import com.restoreempire.model.Account;
import com.restoreempire.service.AccountService;
import com.restoreempire.service.Service;
import com.restoreempire.service.validators.Validation;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name="accountShow",urlPatterns={"/accounts"})
public class AccountShowServlet extends HttpServlet {

    private final Dao<Account> dao = new AccountDao();
    private final Service<Account> service = new AccountService(dao);
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if(!Validation.isNullOrEmpty(request.getParameter("change"))) {
            response.sendRedirect("/accounts/p?&id=" + request.getParameter("change"));
        }
        String pageIdParam = request.getParameter("page");
        int page = Validation.isNullOrEmpty(pageIdParam) ? 1 : Integer.parseInt(pageIdParam);
        int paginationElements = 10;
        int pages = service.getPageCount(paginationElements);
        var values = service.pagination(page, paginationElements);
        String[] keys = dao.getKeys();
        request.setAttribute("pages", pages);
        request.setAttribute("keys", keys);
        request.setAttribute("values", values);
        request.setAttribute("title", "Accounts");
        getServletContext().getRequestDispatcher("/page/show.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (!Validation.isNullOrEmpty(req.getParameter("delete"))) {
            int id = Integer.valueOf(req.getParameter("delete"));
            Dao<Account> dao = new AccountDao();
            Account account = dao.read(id);
            dao.delete(account);
            resp.sendRedirect("/accounts");
        }
        
    }

    
}
