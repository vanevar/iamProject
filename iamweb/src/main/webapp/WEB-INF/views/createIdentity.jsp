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
<!--     <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/css/bootstrap-datepicker3.css" rel="stylesheet"> -->
	<title>Create Identity</title>
</head>
<body>
	<jsp:include page="_header.jsp"></jsp:include>
	<div class="container">
	  <jsp:include page="_messages.jsp"></jsp:include>
	  <h2>Identity Creation</h2>
	  <div class="panel panel-default">
  	    <div class="panel-heading">
          <h3 class="panel-title">Create Identity</h3>
        </div>
        <div class="panel-body">	
          <form method="POST" action="doCreateIdentity">
            <div class="form-group ">
  			  <label class="control-label requiredField" for="name">
     		    Display Name: <span class="asteriskField"> *</span>
      		  </label>
      		  <input class="form-control" id="displayName" name="displayName" type="text" />
     		</div>
     		<div class="form-group ">
      		  <label class="control-label " for="email">
       			E-mail: 
      	  	  </label>
      		  <input class="form-control" id="email" name="email" type="text" />
     		</div>
     		<div class="form-group ">
      		  <label class="control-label " for="date">
       			Birthday:
      		  </label>
      		  <input class="form-control" id="date" name="date" placeholder="YYYY-MM-DD" type="text" />
     		</div>
     		<div class="form-group">
      		  <div>
       			<button class="btn btn-sample " name="submit" type="submit">
        			Save
       			</button>
       		  </div>
       		</div>
          </form>
        </div>
	  </div>
	</div>	
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="webjars/jquery/2.2.3/jquery.min.js"></script>
    <!--  
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.9.0/moment-with-locales.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/js/bootstrap-datepicker.min.js"></script>
    <script>
    $(document).ready(function(){
      var date_input=$('input[name="date"]'); //our date input has the name "date"
      var options={
        format: 'yyyy/mm/dd',
        todayHighlight: true,
        autoclose: true,
      };
      date_input.datepicker(options);
    })
  </script>
  -->
</body>
</html>