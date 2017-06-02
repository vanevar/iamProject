<%@ page session="false"%>
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
   <title>Login Page</title>
</head>
<body>    
    <div class="container">
      
      <jsp:include page="_header.jsp"></jsp:include>
	
	  <div class="row gap"></div>
      <div class="col col-md-2">&nbsp;&nbsp;</div>
      <div class="col col-md-2">&nbsp;&nbsp;</div>
      
      <div class="col col-md-5 login-form">
	    <c:if test="${not empty msg}">
		  <div class="alert alert-danger" role="alert">${msg}</div>
	    </c:if>
        <div class="wrap">
          <form method="POST" action="authenticate">
<!--             <div class="text-center cus-text">Login</div> -->
              <div class="input-group">
                <span class="input-group-addon" id="username-logo">
                  <i class="glyphicon glyphicon-user"></i> 
                </span>
			    <input id="loginInput"  name="login" type="text" class="form-control" placeholder="Enter login" 
			    	aria-describedby="username-logo"  />
              </div>
              <div class="input-group">
                <span class="input-group-addon" id="password-logo">
                  <i class="glyphicon glyphicon-lock"></i>
                </span>
                <input type="password" class="form-control" placeholder="Password" aria-describedby="password-logo"
                	id="passwordInput" name="password" />
             </div>
             <input class="btn btn-sample pull-right" type="submit" value="Login">
             <div class="smallgap"></div>
            </form>
          </div>
        </div>   
      </div>

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="webjars/jquery/2.2.3/jquery.min.js"></script>
</body>
</html>