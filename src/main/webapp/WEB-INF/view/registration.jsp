<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<spring:url value="/resources/css/signin.css" var="startertemplate"/>
<spring:url value="/resources/css/bootstrap.min.css" var="start"/>
<link href="${start}" rel="stylesheet">
<link href="${startertemplate}" rel="stylesheet" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Registration Page</title>
</head>
<body>
<div class="container">
<form:form method="POST" commandName="user" action="final" class = "form-signin">
	<h2 class="form-signin-heading">Registration form</h2>
	<form:input path="name" class = "form-control" placeholder="Name" required = "required"/>
	<form:input path="password" class = "form-control" placeholder="Password" required = "required"/>
	<form:input path="email" class = "form-control" placeholder="email" required = "required" type = "email"/>
	<button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
	<label>
		<p class="message">Registered? <a href="<c:url value="/"/>">Log In</a></p>
	</label>
</form:form>
</div>
</body>
</html>