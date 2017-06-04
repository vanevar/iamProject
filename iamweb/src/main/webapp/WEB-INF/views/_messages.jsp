<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
  <link rel="stylesheet" href="resources/css/bootstrap.min.css">         
  <script src="resources/js/bootstrap.min.js"></script> 
</head>
<body>
  <c:if test="${not empty error_msg}">
	<div class="alert alert-danger" role="alert">${error_msg}</div>
  </c:if>
  <c:if test="${not empty succ_msg}">
	<div class="alert alert-success" role="alert">${succ_msg}</div>
  </c:if>
  <c:if test="${not empty war_msg}">
	<div class="alert alert-warning" role="alert">${war_msg}</div>
  </c:if>
  <c:if test="${not empty msg}">
	<div class="alert alert-info" role="alert">${msg}</div>
  </c:if>
</body>
</html>