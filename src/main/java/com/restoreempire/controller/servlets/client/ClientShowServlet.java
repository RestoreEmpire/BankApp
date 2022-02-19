package com.restoreempire.controller.servlets.client;


import java.io.IOException;

import com.restoreempire.dao.ClientDao;
import com.restoreempire.dao.Dao;
import com.restoreempire.model.Client;
import com.restoreempire.service.BaseService;
import com.restoreempire.service.ClientService;
import com.restoreempire.service.validators.Validation;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name="clientShow",urlPatterns={"/clients"})
public class ClientShowServlet extends HttpServlet {

    private final Dao<Client> dao = new ClientDao();
    private final BaseService<Client> service = new ClientService(dao);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if(!Validation.isNullOrEmpty(request.getParameter("change"))) {
            response.sendRedirect("/clients/p?&id=" + request.getParameter("change"));
        }
        String pageIdParam = request.getParameter("page");
        int page = Validation.isNullOrEmpty(pageIdParam) ? 1 : Integer.parseInt(pageIdParam);
        int paginationElements = 10;
        int pages = service.getPageCount(paginationElements);
        var values = service.pagination(page, paginationElements);
        String[] keys = dao.getKeys();
        request.setAttribute("pages", pages);
        request.setAttribute("keys", keys);
        request.setAttribute("values", values);
        request.setAttribute("title", "Clients");
        getServletContext().getRequestDispatcher("/page/show.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!Validation.isNullOrEmpty(req.getParameter("delete"))) {
            int id = Integer.valueOf(req.getParameter("delete"));
            Client client = dao.read(id);
            dao.delete(client);        
            resp.sendRedirect("/clients");
        }
        
    }

}
