package com.restoreempire.servlets.client;


import com.restoreempire.model.Bank;
import com.restoreempire.model.Client;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

@WebServlet(name="clientServlet",urlPatterns={"/clients/create"})
public class ClientCreateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        getServletContext().getRequestDispatcher("/create/client.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        //client-number=123124
        // &surname=12313
        // &firstname=123123
        // &middlename=123123
        // &birthdate=2000-01-01
        // &noclient-number=on&nomiddlename=on
        if (
            req.getParameter("surname") != null &&
            !req.getParameter("surname").isEmpty() && 
            req.getParameter("firstname") != null  &&
            !req.getParameter("firstname").isEmpty() && 
            req.getParameter("birthdate") != null &&
            !req.getParameter("birthdate").isEmpty()
            ) {
                Client client = new Client();
                client.setSurname(req.getParameter("surname"));
                client.setFirstName(req.getParameter("firstname"));
                client.setBirthDate(req.getParameter("birthdate"));
                if (
                    req.getParameter("middlename") != null &&
                    !req.getParameter("middlename").isEmpty()
                ) {
                    client.setMiddlename(req.getParameter("middlename"));
                }
                if (
                    req.getParameter("client-number") != null &&
                    !req.getParameter("client-number").isEmpty()
                ) {
                    client.setClientNumber(req.getParameter("client-number"));
                }
                else {
                    client.setRandClientNumber();
                }
                client.create();
        }
        resp.sendRedirect("/clients");
    }
}