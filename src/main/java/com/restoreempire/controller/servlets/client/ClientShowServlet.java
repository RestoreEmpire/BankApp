package com.restoreempire.controller.servlets.client;


import com.restoreempire.dao.ClientDao;
import com.restoreempire.dao.Dao;
import com.restoreempire.model.Client;
import com.restoreempire.processing.data.validators.Validation;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name="clientShow",urlPatterns={"/clients"})
public class ClientShowServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<List<String>> clientsStringify = new ArrayList<>();
        List<Client> clients = new ClientDao().getAll();
        for (Client client: clients) {
            var dict = new ArrayList<String>();
            dict.add(String.valueOf(client.getId()));
            dict.add(client.getClientNumber());
            dict.add(client.getSurname());
            dict.add(client.getFirstName());
            dict.add(client.getMiddlename());
            dict.add(client.getBirthDate().toString());
            clientsStringify.add(dict);
        } // хранить данные нунжно в контексте сессии
        String[] keys = new String[]{"ID", "Client Number", "Surname", "First Name", "Middle name", "Birth date"};
        request.setAttribute("keys", keys);
        request.setAttribute("values", clientsStringify);
        request.setAttribute("title", "Clients");
        getServletContext().getRequestDispatcher("/page/show.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(!Validation.isNullOrEmpty(req.getParameter("change"))) {
            resp.sendRedirect("/clients/p?&id=" + req.getParameter("change"));
        }
        if (!Validation.isNullOrEmpty(req.getParameter("delete"))) {
            int id = Integer.valueOf(req.getParameter("delete"));
            Dao<Client> dao = new ClientDao();
            Client client = dao.read(id);
            dao.delete(client);        
            resp.sendRedirect("/clients");
        }
        
    }

}
