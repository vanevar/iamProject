<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE>
<html>
<head>
	<link rel="stylesheet" href="resources/css/bootstrap.min.css">         
	<script src="resources/js/bootstrap.min.js"></script> 
</head>
<body>
    <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
         
		<p class="navbar-brand">I AM System</p>
        </div>
        <c:if test="${not empty loggedUserDisplayName}">
			<p class="navbar-text navbar-right">${loggedUserDisplayName}</p>
	 	</c:if>
        <p class="navbar-text navbar-right">EPITA - 2017</p>
      </div>
    </nav>
</body>
</html>