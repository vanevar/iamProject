<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE>
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
<title>Oops</title>
</head>
<body>
	<jsp:include page="_header.jsp"></jsp:include>
	<h3>An error occurred</h3>
    
    <p style="color: red;">${errorString}</p>
    
</body>
</html>