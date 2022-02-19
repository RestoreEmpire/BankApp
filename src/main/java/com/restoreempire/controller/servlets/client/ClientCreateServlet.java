package com.restoreempire.controller.servlets.client;


import java.io.IOException;

import com.restoreempire.dao.ClientDao;
import com.restoreempire.dao.Dao;
import com.restoreempire.model.Client;
import com.restoreempire.service.ClientService;
import com.restoreempire.service.Service;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name="clientCreate",urlPatterns={"/clients/create"})
public class ClientCreateServlet extends HttpServlet {
    
    Dao<Client> dao = new ClientDao();
    Service<Client> service = new ClientService(dao);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        getServletContext().removeAttribute("client");
        request.setAttribute("title", "Create | client");
        getServletContext().getRequestDispatcher("/page/client.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        service.create(service.setParams(req.getParameterMap()));
        resp.sendRedirect("/clients");
    }
}