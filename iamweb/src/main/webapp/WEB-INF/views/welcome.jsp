<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	
    <!-- Bootstrap -->
    <link href="resources/css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap-Theme -->
    <link rel="stylesheet" href="resources/css/bootstrap-theme.min.css">
    <!-- Custom styles for this template -->
    <link href="resources/css/iam.css" rel="stylesheet">
<title>Welcome to IAM System</title>
</head>
<body>
  <spring:url value="/identityForm" var="create" />
  <spring:url value="/identityList" var="search" />
  <jsp:include page="_header.jsp"></jsp:include>
					  				  
  <div class="container">
	<div class="row">
	<div class="col-sm-12 jumbotron">
 		<h1>Welcome to the I am System! </h1>
	</div>
	</div>
	<div class="row">
	  <div class="col-sm-6 panel panel-default">
  		<div class="panel-body">
    		<h3>Identity Creation</h3>
  			<p>With this action you can create a new identity. Click the button to begin!</p>
			<a class="btn btn-sample pull-left" href="${create}" role="button">Create</a>
  		</div>
	  </div>
	  <div class="col-sm-6 panel panel-default">
  		<div class="panel-body">
    		<h3>Identity Search</h3>
			<p>Search, update and delete an identity. </p>
			<a class="btn btn-sample pull-left" href="${search}" role="button">Search</a>
  		</div>
	  </div>
    </div>
  </div>
  <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
  <!-- Include all compiled plugins (below), or include individual files as needed -->
  <script src="webjars/jquery/2.2.3/jquery.min.js"></script>
</body>
</html>