<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../template/head.jsp">
    <jsp:param name="title" value="Bank | Create"/>
</jsp:include>
<div class="body">
    <div class="container">
        <form method="post"> 
            <div class="mb-3">
              <label for="bankNameInput" class="form-label">Bank name</label>
              <input type="text" class="form-control" id="bankNameInput"  name="name">
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
          </form>
    </div>
</div>
<jsp:include page="../template/footer.jsp"/>