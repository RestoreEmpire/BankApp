<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="template/head.jsp">
    <jsp:param name="title" value="Index"/>
</jsp:include>

    <div class="body">
        <div class="container ">
            <button onclick="location.href='/banks'" type="button" class="list-group-item list-group-item-action">Banks</button>
            <button onclick="location.href='/clients'" type="button" class="list-group-item list-group-item-action">Clients</button>
            <button onclick="location.href='/accounts'" type="button" class="list-group-item list-group-item-action">Accounts</button>
        </div>
    </div>
</div>

<jsp:include page="template/footer.jsp"/>