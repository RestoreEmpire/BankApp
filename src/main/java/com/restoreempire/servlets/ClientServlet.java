package com.restoreempire.servlets;


import com.restoreempire.model.Bank;
import com.restoreempire.model.Client;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

@WebServlet(name="client",urlPatterns={"/clients"})
public class ClientServlet extends HttpServlet {

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
        if (req.getParameter("delete") != null) {
            int id = Integer.valueOf(req.getParameter("delete"));
            Client client = new Client();
            client.read(id);
            client.delete();        
        }
        resp.sendRedirect("/clients");
    }

}
