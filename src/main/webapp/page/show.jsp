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
                            <form method="post">
                                <button class="btn btn-primary" name="change" value="${row[0]}">
                                    <i class="fa-solid fa-pen-to-square"></i>
                                </button>
                                <button class="btn btn-primary" name="delete" value="${row[0]}">
                                    <i class="fa-solid fa-trash-can"></i>
                                </button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<jsp:include page="../template/footer.jsp"/>