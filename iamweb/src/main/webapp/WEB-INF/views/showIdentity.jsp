<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html >
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
    <title>Identity Details</title>
</head>
<body>
	<jsp:include page="_header.jsp"></jsp:include>
	<div class="container">
	  <jsp:include page="_messages.jsp"></jsp:include>
	  <h2>Identity Details</h2>
	  <div class="panel panel-default">
  	    <div class="panel-heading">
          <h3 class="panel-title">Identity</h3>
        </div>
        <div class="panel-body">	
        	<div class="row">
        	  <label class="col-sm-2">ID</label>
		      <div class="col-sm-10">${identity.uid}</div>
        	</div>
        	<div class="row">
        	  <label class="col-sm-2">Display Name</label>
		      <div class="col-sm-10">${identity.displayName}</div>
        	</div>
        	<div class="row">
        	  <label class="col-sm-2">Email</label>
		      <div class="col-sm-10">${identity.email}</div>
        	</div>
        	<div class="row">
        	  <label class="col-sm-2">Birthday</label>
		      <div class="col-sm-10"><fmt:formatDate pattern = "yyyy-MM-dd" value = "${identity.birthdate}"></fmt:formatDate></div>
        	</div>
        </div>
      </div>
      <!-- ADDRESSES -->
      <div class="panel panel-default">
  	    <div class="panel-heading">
          <h3 class="panel-title">Addresses</h3>
        </div>
        <div class="panel-body">	
          <table class="table table-striped">
			<thead>
				<tr>
					<th>Address</th>
					<th>City</th>
					<th>Postal Code</th>
					<th>Country</th>
				</tr>
			</thead>

			<c:forEach var="address" items="${identity.addresses}">
			  <tr>
				<td>${address.firstLine}</td>
				<td>${address.city}</td>
				<td>${address.postalCode}</td>
				<td>${address.country}</td>
				<!-- 
				<td>
				  <spring:url value="/users/${identity.uid}" var="userUrl" />
				  <spring:url value="/delete?id=${identity.uid}" var="deleteUrl" />
				  <spring:url value="/update?id=${identity.uid}" var="updateUrl" />

				  <button class="btn btn-primary" onclick="location.href='${userUrl}'">
				  	<span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>
				  </button>
				  <button class="btn btn-success" onclick="location.href='${updateUrl}'">
				    <span class="glyphicon glyphicon-edit" aria-hidden="true"></span>
				  </button>
				  <c:if test="${loggedUser.identity.uid ne identity.uid}">
				    <button class="btn btn-danger" onclick="location.href='${deleteUrl}'">
				      <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
				    </button>
                  </c:if>
                </td>
                 -->
			  </tr>
			</c:forEach>
		</table>
        </div>
      </div>
    </div>
</body>
</html>