<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
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
	<title>Identity Search</title>
</head>
<body>
  <jsp:include page="_header.jsp"></jsp:include>
	
  <div class="container">
	<c:if test="${not empty msg}">
		  <div class="alert alert-info" role="alert">${msg}</div>
	</c:if>
	<h2>Identity Search</h2>
	<p> PUT HERE A BACK LINK</p>
	<div class="panel panel-default">
  	  <div class="panel-heading">
        <h3 class="panel-title">Search Criteria</h3>
      </div>
      <div class="panel-body">	
		<form method="POST" action="identityList">
          <div class="row">
	  		<div class="col-sm-1">
        		<label for="sel1" >Field:</label>
        	</div>
        	<div class="col-sm-3">
  				<select class="col-sm-4 form-control" id="field" name="field" onchange="checkSelected(this);">
    				<option value="any">Any</option>
				    <option value="dispName">Display Name</option>
 				    <option value="email">Email</option>
    				<option value="birthday">Birthday</option>
	  			</select>
		  	</div>
		  	<div class="col-sm-3">
		  		<input id="search"  name="search" type="text" class="form-control" placeholder="Birthday format: YYYY-MM-dd" />
  		   </div>
  		   <div class="col-sm-3">
  		     <input class="btn btn-sample pull-left" type="submit" value="Search">
  		   </div>
  		   </div>
        </form>
      </div>
    </div>
	<div class="panel panel-default">
  	  <div class="panel-heading">
        <h3 class="panel-title">Search Results</h3>
      </div>
      <div class="panel-body">
        <table class="table table-striped">
			<thead>
				<tr>
					<th>Display Name</th>
					<th>Email</th>
					<th>Birthday</th>
					<th>Address</th>
					<th>Action</th>
				</tr>
			</thead>

			<c:forEach var="identity" items="${identities}">
			  <tr>
				<td>${identity.displayName}</td>
				<td>${identity.email}</td>
				<td><fmt:formatDate pattern = "yyyy-MM-dd" value = "${identity.birthdate}"></fmt:formatDate></td>
				<td>
					<c:if test= "${fn:length(identity.addresses) gt 0}">
						<spring:url value="/users/${identity.uid}/addr" var="userAddr" />
						<button class="btn btn-info"
                                          onclick="location.href='${userUrl}'">See address</button>
					</c:if>
				</td>
				<td>
				  <spring:url value="/users/${identity.uid}" var="userUrl" />
				  <spring:url value="/users/${identity.uid}/delete" var="deleteUrl" />
				  <spring:url value="/users/${identity.uid}/update" var="updateUrl" />

				  <button class="btn btn-primary"
                                          onclick="location.href='${userUrl}'">Query</button>
				  <button class="btn btn-success"
                                          onclick="location.href='${updateUrl}'">Update</button>
				  <c:if test="${loggedUser.identity.uid ne identity.uid}">
				    <button class="btn btn-danger"
                                          onclick="this.disabled=true;post('${deleteUrl}')">Delete</button>
                  </c:if>
                </td>
			  </tr>
			</c:forEach>
		</table>
      </div>
    </div>
	
  </div>
  <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
  <!-- Include all compiled plugins (below), or include individual files as needed -->
  <script src="webjars/jquery/2.2.3/jquery.min.js"></script>
</body>
</html>