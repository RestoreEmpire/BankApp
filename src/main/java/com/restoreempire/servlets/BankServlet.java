package com.restoreempire.servlets;


import com.restoreempire.model.Bank;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name="bank",urlPatterns={"/banks"})
public class BankServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ArrayList<ArrayList<String>> banksStringify = new ArrayList<>();
        
        ArrayList<Bank> banks = Bank.getAll();
        for (Bank bank : banks) {
            var bankDict = new ArrayList<String>();
            bankDict.add(String.valueOf(bank.getId()));
            bankDict.add(bank.getName());
            banksStringify.add(bankDict);
        } // хранить данные нунжно в контексте сессии
        String[] keys = new String[]{"ID", "Name"};
        request.setAttribute("keys", keys);
        request.setAttribute("values", banksStringify);
        request.setAttribute("title", "Banks");


        getServletContext().getRequestDispatcher("/show.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("delete") != null) {
            int id = Integer.valueOf(req.getParameter("delete"));
            Bank bank = new Bank();
            bank.read(id);
            bank.delete();
        }
        resp.sendRedirect("/banks");
    }

}
