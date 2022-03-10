<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/view/template/header.jsp">
    <jsp:param name="title" value="Login"/>
</jsp:include>
<div class="body">
    <form action="/login" method="post">
        <div class="mb-3">
          <label for="username" class="form-label">Username</label>
          <input type="text" class="form-control" id="username" name="username">
        </div>
        <div class="mb-3">
          <label for="exampleInputPassword1" class="form-label">Password</label>
          <input type="password" class="form-control" id="password" name="password">
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
      </form>
</div>
<jsp:include page="/view/template/footer.jsp"/>
