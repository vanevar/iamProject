<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    
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
  <div class="container">
	<jsp:include page="_header.jsp"></jsp:include>
	<div class="row">
	<div class="col-sm-12 jumbotron">
 		<h1>Welcome to the I am System! </h1>
  		<p><a class="btn btn-default btn-lg" href="#" role="button">Disconect</a></p>
	</div>
	</div>
	<div class="row">
	  <div class="col-sm-6 panel panel-default">
  		<div class="panel-body">
    		<h3>Identity Creation</h3>
  			<p>With this action you can create a new identity. Click the button to begin!</p>
			<a class="btn btn-default pull-left" href="#" role="button">Create</a>
  		</div>
	  </div>
	  <div class="col-sm-6 panel panel-default">
  		<div class="panel-body">
    		<h3>Identity Search</h3>
			<p>Search, update and delete an identity. </p>
			<a class="btn btn-default pull-left" href="#" role="button">Search</a>
  		</div>
	  </div>
  </div>
</body>
</html>