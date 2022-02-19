package com.restoreempire.controller.servlets.client;


import java.io.IOException;

import com.restoreempire.dao.ClientDao;
import com.restoreempire.dao.Dao;
import com.restoreempire.model.Client;
import com.restoreempire.service.ClientService;
import com.restoreempire.service.Service;
import com.restoreempire.service.validators.Validation;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name="clientPage",urlPatterns={"/clients/p"})
public class ClientPageServlet extends HttpServlet {

    Dao<Client> dao = new ClientDao();
    Service<Client> service = new ClientService(dao);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        if(!Validation.isNullOrEmpty(req.getParameter("id"))) {
            Client client = new ClientDao().read(Long.parseLong(req.getParameter("id")));
            req.setAttribute("client", client);
            req.setAttribute("title", client.getClientNumber());
        }
        getServletContext().getRequestDispatcher("/page/client.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Client client = (Client) req.getAttribute("client");  
        service.update(client, service.setParams(req.getParameterMap()));
        getServletContext().removeAttribute("client");
        resp.sendRedirect("/clients");

    }
}