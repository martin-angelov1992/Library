<%@ include file="header.jsp" %>
<table border="1">
<tr><td>Name</td><td>Author</td><td>Description</td><td>Amount</td><td>Add to user</td><<td>Edit</td>/tr>
<c:forEach var="book" items="${books}">
	<tr>
		<td>${fn:escapeXml(book.getName())}</td>
		<td>${fn:escapeXml(book.getAuthor())}</td>
		<td>${fn:escapeXml(book.getDescription())}</td>
		<td><c:out value="${book.getAvailableCount()}" />/<c:out value="${book.getTotalCount()}" /></td>
		<td><a href="/<c:out value="${urlPrefix}" />?Booking?bookId=<c:out value="${book.getId()}" />">booking</a></td>
	</tr>
</c:forEach>
</table>
<c:choose>
      <c:when test="${page ne 1}">
<a href="/<c:out value="${urlPrefix}books" />">first</a>
<a href="/<c:out value="${urlPrefix}books?page=${page-1}" />"><< previous</a>
      </c:when>
</c:choose>
<%@ include file="footer.jsp" %>