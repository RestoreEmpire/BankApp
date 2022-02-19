<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../template/head.jsp">
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
                <c:forEach items="${values}" var="row">
                    <tr>
                        <c:forEach items="${row}" var="item">
                            <td>${item}</td>
                        </c:forEach>
                        <td class="col-2">
                            <div class="row">
                                <form class="col-md-3" method="get">
                                    <button class="btn btn-primary" name="change" data-bs-toggle="tooltip" data-bs-placement="bottom" title="Change this entry" value="${row[0]}">
                                        <i class="fa-solid fa-pen-to-square"></i>
                                    </button>
                                </form>
                                <form class="col-md-3" method="post">
                                    <button class="btn btn-primary" name="delete" data-bs-toggle="tooltip" data-bs-placement="bottom" title="Delete this entry" value="${row[0]}">
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



<jsp:include page="../template/footer.jsp"/>