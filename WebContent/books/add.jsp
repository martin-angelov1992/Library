<%@ include file="../header.jsp" %>
<script type="text/javascript">
var addBookBtn = new sap.ui.commons.Button("btn");
addBookBtn.setText("Add Book");
addBookBtn.attachPress(function(){
	$("#add-book").submit();
});
addBookBtn.placeAt("add-book-btn");
</script>
<c:choose>
<c:when test="${created ne null}">
<p style="color:green">Book added successfully</p>
</c:when>
</c:choose>
<form method="post" id="add-book">
<table>
	<tr><td><label for="name">Name: </label></td><td><input type="text" name="name" id="name"></td></tr>
	<tr><td><label for="author">Author: </label></td><td><input type="text" name="author" id="author"></td></tr>
	<tr><td><label for="description">Description: </label></td><td><input type="text" name="description" id="description"></td></tr>
	<tr><td><label for="amount">Amount: </label></td><td><input type="text" name="amount" id="amount" value="1"></td></tr>
</table>
	<div id="add-book-btn"></div>
</form>
<%@ include file="../footer.jsp" %>