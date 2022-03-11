<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
          crossorigin="anonymous">
          <link rel="stylesheet" href="/view/static/style.css">
          <!-- <link rel="stylesheet" href="/static/fontawesome-free-6.0.0-web/css/all.css"> -->
    <% String title = request.getParameter("title"); %>
    
    <title><%= title %> </title>
</head>
<header class="p-3 bg-dark text-white">
    <div class="container">
        <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
            <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                <li><a href="/" class="nav-link px-1 text-white">Home</a></li>
            </ul>
            <ul class="nav md-2">
                <sec:authorize access="isAuthenticated()">
                    <li><a href="/logout" class="nav-link px-1 text-white">Logout</a></li>
                </sec:authorize>
            </ul>
        </div>
    </div>
</header>