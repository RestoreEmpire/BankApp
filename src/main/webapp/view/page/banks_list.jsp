<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/view/template/header.jsp">
    <jsp:param name="title" value="${title}"/>
</jsp:include>
<div class="body">
    <div class="container">
        <table class="table">
            <thead>
                <tr>
                    <c:forEach items="${keys}" var="key">
                    <th scope="col">${key}</th>
                    </c:forEach>
                    <th scope="col"></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${banks}" var="bank">
                    <tr>
                        <td>${bank.id}</td>
                        <td>${bank.name}</td>
                        <td class="col-2">
                            <div class="row">
                                <form class="col-md-3" method="get">
                                    <button class="btn btn-primary" name="change" data-bs-toggle="tooltip" data-bs-placement="bottom" title="Change this entry" value="${bank.id}}">
                                        <i class="fa-solid fa-pen-to-square"></i>
                                    </button>
                                </form>
                                <form method="post" action="/bank/delete" class="col-md-3">
                                    <button class="btn btn-primary" name="id" data-bs-toggle="tooltip" data-bs-placement="bottom" title="Delete this entry" value="${bank.id}">
                                        <i class="fa-solid fa-trash-can"></i>
                                    </button>
                                </form>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <nav aria-label="Page navigation">
            <ul class="pagination">
                <c:forEach var = "i" begin = "1" end = "${pages}">
                    <li class="page-item"><a class="page-link" href="?&page=${i}">${i}</a></li>
                </c:forEach>        
            </ul>
        </nav>
    </div>
</div>



<jsp:include page="/view/template/footer.jsp"/>