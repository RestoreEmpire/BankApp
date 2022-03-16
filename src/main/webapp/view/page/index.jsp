<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/view/template/header.jsp">
    <jsp:param name="title" value="Index"/>
</jsp:include>

    <div class="body">
        <div class="container list-group">
            <a href='/bank-departments' class="list-group-item list-group-item-action">
                Bank departments
                <object>
                    <a class="float-end btn btn-primary" href="/bank-departments/create">Create new</a>
                </object>
            </a>                                  
        </div>
    </div>
</div>

<jsp:include page="/view/template/footer.jsp"/>