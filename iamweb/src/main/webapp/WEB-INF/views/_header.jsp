<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE>
<html>
<head>
	<link rel="stylesheet" href="resources/css/bootstrap.min.css">         
	<script src="resources/js/bootstrap.min.js"></script> 
</head>
<body>
    <spring:url value="/home" var="home" />
    <spring:url value="/logout" var="logout" />
    <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
         
		<a class="navbar-brand" href="${home}">I AM System</a>
        </div>
        <c:if test="${not empty loggedUser.identity.displayName}">
			
			<button class="btn btn-sample btn-xs navbar-btn navbar-right" onclick="location.href='${logout}'">
				<span class="glyphicon glyphicon-off" aria-hidden="true"></span>
			</button>
			<p class="navbar-text navbar-right">&nbsp;&nbsp;${loggedUser.identity.displayName}&nbsp;&nbsp;</p>
	 	</c:if>
        <p class="navbar-text navbar-right">EPITA - 2017</p>
      </div>
    </nav>
</body>
</html>