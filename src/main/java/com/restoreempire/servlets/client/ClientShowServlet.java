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
import java.util.ArrayList;
import java.util.HashMap;

@WebServlet(name="clientShow",urlPatterns={"/clients"})
public class ClientShowServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ArrayList<ArrayList<String>> clientsStringify = new ArrayList<>();
        ArrayList<Client> clients = Client.getAll();
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
        getServletContext().getRequestDispatcher("/show.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(!Validation.isNullOrEmpty(req.getParameter("change"))) {
            resp.sendRedirect("/clients/p?&id=" + req.getParameter("change"));
        }
        if (!Validation.isNullOrEmpty(req.getParameter("delete"))) {
            int id = Integer.valueOf(req.getParameter("delete"));
            Client client = new Client();
            client.read(id);
            client.delete();        
            resp.sendRedirect("/clients");
        }
        
    }

}
