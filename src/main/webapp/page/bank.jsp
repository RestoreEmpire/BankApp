<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../template/head.jsp">
    <jsp:param name="title" value="${title}"/>
</jsp:include>
<div class="body">
    <div class="container">
        <form class="was-validated" method="post"> 
            <div class="mb-3">
              <label for="bankNameInput" class="form-label">Bank name</label> 
              <input type="text" class="form-control" id="bankNameInput"  name="name" value="${bank.name}" required >
              <div class="invalid-feedback">Required field.</div>
            </div>
            <button type="submit" class="btn btn-primary" id="submit" >Submit</button>
          </form>
    </div>
</div>
<jsp:include page="../template/footer.jsp"/>