package com.restoreempire.servlets.client;


import com.restoreempire.model.Bank;
import com.restoreempire.model.Client;
import com.restoreempire.processing.data.validators.Validation;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name="clientPage",urlPatterns={"/clients/p"})
public class ClientPageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        if(!Validation.isNullOrEmpty(req.getParameter("id"))) {
            Client client = new Client(Long.parseLong(req.getParameter("id")));
            getServletContext().setAttribute("client", client);
            req.setAttribute("title", client.getClientNumber());
        }
        getServletContext().getRequestDispatcher("/create/client.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Client retClient = (Client) getServletContext().getAttribute("client");
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
                client.setId(retClient.getId());
                retClient.update(client);
        }
        resp.sendRedirect("/clients");
    }
}