<%@ include file="header.jsp" %>
<script>
var loginBtn = new sap.ui.commons.Button("btn");
loginBtn.setText("Login");
loginBtn.attachPress(function(){
	$("#login").submit();
});
loginBtn.placeAt("loginBtn");
</script>
<c:choose>
      <c:when test="${error ne null}">
<p style="color:red"><c:out value="${error}"></c:out></p>
      </c:when>
</c:choose>
<form id="login" method="post">
<label for="username">Username: </label><input type="text" name="username" id="username"><br>
<label for="password">Password: </label><input type="password" name="password" id="password">
<div id="loginBtn"></div>
</form>
<%@ include file="footer.jsp" %>