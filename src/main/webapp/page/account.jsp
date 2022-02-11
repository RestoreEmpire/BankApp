<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../template/head.jsp">
    <jsp:param name="title" value="Client | Create"/>
</jsp:include>

<div class="body">
    <div class="container">
        <form class="was-validated" method="post"> 
            <!--    private long id;
                    private String accountNumber;
                    private Long bankId;
                    private Long clientId;
                    private BigDecimal funds = BigDecimal.ZERO;
                    
                    private final static String tableName = "account"; -->

            <div class="mb-3">
                <label for="accountNumber" class="form-label">Account number</label>
                <input type="text" class="form-control" id="accountNumber" name="account-number" value="${account.accountNumber}">
            </div>
            <div class="mb-3 form-check">
                <input onclick='disableContent("accountNumber")' type="checkbox" class="form-check-input" id="accountNumberCheck" name="no-account-number">
                <label class="form-check-label" for="accountNumberCheck">Set it automatically</label>
              </div>
            <div class="mb-3">
                <label for="bankSelect" class="form-label">Bank</label>
                <select class="form-select" aria-label="Select" id="bankSelect" name="bank">
                    <option value="${defaultBank.id}" selected>${defaultBank.id} ${defaultBank.name}</option>
                    <c:forEach var="bank" items="${banks}">
                        <option value="${bank.id}">${bank.id} ${bank.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="mb-3">
                <label for="clientSelect" class="form-label">Client</label>
                <select name="client" id="clientSelect" class="form-select">
                    <option value="${defaultClient}" selected>${defaultClient.clientNumber} ${defaultClient}</option>
                    <c:forEach var="client" items="${clients}">
                        <option value="${client.id}">${client.clientNumber} ${client}</option>
                    </c:forEach>
                </select>
            </div>

            
            <div class="mb-3">
                <label for="funds" class="form-label">funds</label>
                <input type="text" class="form-control" id="funds" name="funds" value="${account.funds}">
            </div>
            

            <button type="submit" class="btn btn-primary">Submit</button>
          </form>
    </div>
</div>

<jsp:include page="../template/footer.jsp"/>