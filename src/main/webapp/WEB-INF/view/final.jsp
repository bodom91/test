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
<title>Login Page</title>
</head>
<body>
<div class="container">
    <p class="alert alert-success" role="alert">Congratulations , you have been Registered! Login, please.</p>
    <label>
        <p class="message"><a href="<c:url value="/"/>">Log In</a></p>
    </label>
</div>
</body>
</html>