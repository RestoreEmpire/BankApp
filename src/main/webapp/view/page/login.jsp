<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/view/template/header.jsp">
    <jsp:param name="title" value="Login"/>
</jsp:include>
<div class="body">
  <div class="container">
    <form method="post" action="/login">
        <div class="mb-3">
          <label for="username" class="form-label">Username</label>
          <input type="text" class="form-control" id="username" name="username">
        </div>
        <div class="mb-3">
          <label for="password" class="form-label">Password</label>
          <input type="password" class="form-control" id="password" name="password">
        </div>
        <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <button type="submit" class="btn btn-primary">Submit</button>
      </form>
      <a href="/oauth2/authorization/github">Login via GitHub</a>
    </div>
</div>
<jsp:include page="/view/template/footer.jsp"/>
