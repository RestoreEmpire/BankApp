package com.restoreempire.controller.servlets.bank;


import com.restoreempire.dao.BankDao;
import com.restoreempire.dao.Dao;
import com.restoreempire.model.Bank;
import com.restoreempire.processing.data.validators.Validation;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name="bankShow",urlPatterns={"/banks"})
public class BankShowServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String pageIdParam = request.getParameter("page");
        int pageId = Validation.isNullOrEmpty(pageIdParam) ? 1 : Integer.parseInt(pageIdParam);
        int rows = 5;
        List<List<String>> banksStringify = new ArrayList<>();
        List<Bank> banks = new BankDao().getAll();
        int pages = ((banks.size() - 1) / rows) + 1;
        for (int i = (pageId - 1) * rows; (i < pageId * rows) && (i < banks.size()); i++) {
            Bank bank = banks.get(i);
            var bankDict = new ArrayList<String>();
            bankDict.add(String.valueOf(bank.getId()));
            bankDict.add(bank.getName());
            banksStringify.add(bankDict);
        }

        String[] keys = new String[]{"ID", "Name"};
        request.setAttribute("pages", pages);
        request.setAttribute("keys", keys);
        request.setAttribute("values", banksStringify);
        request.setAttribute("title", "Banks");
        getServletContext().getRequestDispatcher("/page/show.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(!Validation.isNullOrEmpty(req.getParameter("change"))) {
            resp.sendRedirect("/banks/p?&id=" + req.getParameter("change"));
        }
        if (!Validation.isNullOrEmpty(req.getParameter("delete"))) {
            int id = Integer.valueOf(req.getParameter("delete"));
            Dao<Bank> dao = new BankDao();
            Bank bank = dao.read(id);
            dao.delete(bank);
            resp.sendRedirect("/banks");
        }
    }


}
