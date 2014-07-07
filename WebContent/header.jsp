<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Library</title>
<script id="sap-ui-bootstrap"
  src="https://sapui5.hana.ondemand.com/resources/sap-ui-core.js"
  data-sap-ui-theme="sap_goldreflection"
  data-sap-ui-libs="sap.ui.commons"
></script>
</head>
<body>
<c:choose>
      <c:when test="${visitor.isLoggedIn()}">
You are logged in as <c:out value="${visitor.getUsername()}"></c:out> (<a href="/<c:out value="${urlPrefix}"></c:out>Logout">Logout</a>) | <a href="/<c:out value="${urlPrefix}Books"></c:out>">Books</a> | 
<a href="/<c:out value="${urlPrefix}books/Add"></c:out>">Add Book</a> | <a href="/<c:out value="${urlPrefix}users"></c:out>">Users</a> | <a href="/<c:out value="${urlPrefix}users/Add"></c:out>">Add User</a> | 
<a href="/<c:out value="${urlPrefix}booking"></c:out>">Booking</a>
      </c:when>
      <c:otherwise>
<a href="/<c:out value="${urlPrefix}"></c:out>Login">Login</a>
      </c:otherwise>
</c:choose>