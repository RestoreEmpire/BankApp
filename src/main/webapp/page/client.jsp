<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../template/head.jsp">
    <jsp:param name="title" value="Client | Create"/>
</jsp:include>
<div class="body">
    <div class="container">
        <form class="was-validated" method="post"> 
            <div class="mb-3 ">
                <label for="clientNumber" class="form-label">Client number</label>
                <input type="text" class="form-control" id="clientNumber" name="client-number"  value="${client.clientNumber}" required>
            </div>
            <div class="mb-3 form-check">
                <input onclick='disableContent("clientNumber")' type="checkbox" class="form-check-input" id="clientNumberCheck" name="noclient-number">
                <label class="form-check-label" for="clientNumberCheck">Set it automatically</label>
              </div>
            <div class="mb-3">
              <label for="clientSurnameInput" class="form-label">Surname</label>
              <input type="text" class="form-control" id="clientSurnameInput" name="surname" value="${client.surname}" required>
            </div>
            <div class="mb-3">
                <label for="clientFirstNameInput" class="form-label">First name</label>
                <input type="text" class="form-control" id="clientFirstNameInput" name="firstname" value="${client.firstName}" required>
            </div>
            <div class="mb-3">
                <label for="clientMiddleName" class="form-label">Middle name</label>
                <input type="text" class="form-control" id="clientMiddleName" name="middlename" value="${client.middlename}" required>
            </div>
            <div class="mb-3 form-check">
                <input onclick='disableContent("clientMiddleName")' type="checkbox" class="form-check-input" id="clientNumberCheck" name="nomiddlename" required>
                <label class="form-check-label" for="middlenameCheck">There is no middle name</label>
              </div>
            <div class="mb-3">
                <label for="birthDate" class="form-label">Birth date</label>
                <input type="date" id="birthDate" name="birthdate"  value="${client.birthDate}" required>
            </div>

            <button id="submit" type="submit" class="btn btn-primary">Submit</button>
          </form>
    </div>
</div>
<jsp:include page="../template/footer.jsp"/>