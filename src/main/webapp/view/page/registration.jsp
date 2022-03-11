<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/view/template/header.jsp">
    <jsp:param name="title" value="Client | Create"/>
</jsp:include>
<div class="body">
    <div class="container">
        <form class="was-validated" modelAttribute="user" method="post"> 
            <div class="mb-3">
                <label for="username" class="form-label">Username</label>
                <input type="text" class="form-control" id="username" name="username" required>
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">Password</label>
                <input type="password" class="form-control" id="password" name="password" required>
            </div>
            <div class="mb-3">
              <label for="clientSurnameInput" class="form-label">Surname</label>
              <input type="text" class="form-control" id="clientSurnameInput" name="surname" required>
            </div>
            <div class="mb-3">
                <label for="clientFirstNameInput" class="form-label">First name</label>
                <input type="text" class="form-control" id="clientFirstNameInput" name="firstName" required>
            </div>
            <div class="mb-3">
                <label for="clientMiddleName" class="form-label">Middle name</label>
                <input type="text" class="form-control" id="clientMiddleName" name="middleName">
            </div>
            <div class="mb-3 form-check">
                <input onclick='disableContent("clientMiddleName")' type="checkbox" class="form-check-input" id="clientNumberCheck" name="nomiddlename">
                <label class="form-check-label" for="middlenameCheck">There is no middle name</label>
              </div>
            <div class="mb-3">
                <label for="birthDate" class="form-label">Birth date</label>
                <input type="date" id="birthDate" name="birthDate"  required>
            </div>
            <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <button id="submit" type="submit" class="btn btn-primary">Submit</button>
          </form>
          <c:if test="${message}">
            <div class="invalid-feedback">${message}</div>
          </c:if>
    </div>
</div>
<jsp:include page="/view/template/footer.jsp"/>