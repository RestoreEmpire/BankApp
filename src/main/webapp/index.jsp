<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="template/head.jsp">
    <jsp:param name="title" value="Index"/>
</jsp:include>

    <div class="body">
        <div class="container list-group">
            <a href='/banks' class="list-group-item list-group-item-action">
                Banks
                <object>
                    <a class="float-end btn btn-primary" href="/banks/create">Create new</a>
                </object>
            </a>                                  
            <a href='/clients' class="list-group-item list-group-item-action">
                Clients
                <object>
                    <a class="float-end btn btn-primary" href="/clients/create">Create new</a>
                </object>
            </a>
            <a href='/accounts' class="list-group-item list-group-item-action">
                Accounts
                <object>
                    <a class="float-end btn btn-primary" href="/accounts/create">Create new</a>
                </object>
            </a>
        </div>
    </div>
</div>

<jsp:include page="template/footer.jsp"/>