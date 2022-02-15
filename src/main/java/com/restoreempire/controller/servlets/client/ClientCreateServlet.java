package com.restoreempire.controller.servlets.client;


import java.io.IOException;

import com.restoreempire.dao.ClientDao;
import com.restoreempire.dao.Dao;
import com.restoreempire.exceptions.ValidationException;
import com.restoreempire.model.Client;
import com.restoreempire.processing.data.validators.Validation;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name="clientCreate",urlPatterns={"/clients/create"})
public class ClientCreateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        getServletContext().removeAttribute("client");
        request.setAttribute("title", "Create | client");
        getServletContext().getRequestDispatcher("/page/client.jsp").forward(request, response);
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
            !Validation.isNullOrEmpty(req.getParameter("surname")) &&          
            !Validation.isNullOrEmpty(req.getParameter("firstname")) && 
            !Validation.isNullOrEmpty(req.getParameter("birthdate"))
            ) {
                Client client = new Client();
                client.setSurname(req.getParameter("surname"));
                client.setFirstName(req.getParameter("firstname"));
                client.setBirthDate(req.getParameter("birthdate"));
                if (
                    !Validation.isNullOrEmpty(req.getParameter("middlename"))
                ) {
                    client.setMiddlename(req.getParameter("middlename"));
                }
                if (
                    !Validation.isNullOrEmpty(req.getParameter("client-number"))
                ) {
                    client.setClientNumber(req.getParameter("client-number"));
                }
                else {
                    client.setRandClientNumber();
                }
                Dao<Client> dao = new ClientDao();
                dao.create(client);
                resp.sendRedirect("/clients");
        } else throw new ValidationException("Wrong form input");
        
    }
}