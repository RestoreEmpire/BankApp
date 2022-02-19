package com.restoreempire.controller.servlets.bank;


import java.io.IOException;

import com.restoreempire.dao.BankDao;
import com.restoreempire.dao.Dao;
import com.restoreempire.model.Bank;
import com.restoreempire.service.BankService;
import com.restoreempire.service.Service;
import com.restoreempire.service.validators.Validation;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name="bankShow",urlPatterns={"/banks"})
public class BankShowServlet extends HttpServlet {

    private final Dao<Bank> dao= new BankDao();
    private final Service<Bank> service = new BankService(dao);

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if(!Validation.isNullOrEmpty(request.getParameter("change"))) {
            response.sendRedirect("/banks/p?&id=" + request.getParameter("change"));
        }
        else{
        String pageIdParam = request.getParameter("page");
        int page = Validation.isNullOrEmpty(pageIdParam) ? 1 : Integer.parseInt(pageIdParam);
        int rows = 10;
        int pages = service.getPageCount(rows);
        var keys = dao.getKeys();
        var values = service.pagination(page, rows);  
        request.setAttribute("pages", pages);
        request.setAttribute("keys", keys);
        request.setAttribute("values", values);
        request.setAttribute("title", "Banks");
        getServletContext().getRequestDispatcher("/page/show.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (!Validation.isNullOrEmpty(req.getParameter("delete"))) {
            int id = Integer.valueOf(req.getParameter("delete"));
            Bank bank = dao.read(id);
            dao.delete(bank);
            resp.sendRedirect("/banks");
        }
    }


}
