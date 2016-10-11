<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <spring:url value="resources/css/bootstrap.min.css" var="bootstrap"/>
    <spring:url value="/resources/css/starter-template.css" var="startertemplate"/>
    <link href="${bootstrap}" rel="stylesheet" />
    <link href="${startertemplate}" rel="stylesheet" />
    <script type = text/javascript src = "resources/js/jquery-3.1.1.min.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset = UTF-8">
    <title>
        Price load
    </title>
</head>
<body>
<div class="container">
    <header>
        <h1>Price loader</h1>
    </header>
    <div class="navbar-wrapper">
        <div class="container">
            <div class="navbar navbar-inverse navbar-static-top" role="navigation">
                <div class="container">
                    <div class="navbar-collapse collapse">
                        <ul class="nav navbar-nav">
                            <li class="active"><a href="<c:url value="/homepage"/>">Homepage</a></li>
                            <li><a href="<c:url value="/priceload"/>">Priceload</a></li>
                            <li><a href="<c:url value="/statistic"/>">Statistic</a></li>
                            <li><a href="<c:url value="/city"/>">City</a></li>
                            <li><a href="<c:url value="/info"/>">Info</a></li>
                            <li><a href="<c:url value="/contact"/>">Contact</a></li>
                        </ul>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>
<form:form method="post" action="/priceload" modelAttribute="page">
<div class="container">
    <div class="col-md-2">
        <div class="hero-unit">
        <h4>Choose city</h4>
            <form:form modelAttribute="citych" method="post" action="/priceload">
                    <form:select path="id" onchange='if(this.value != 0) { this.form.submit(); }'>
                        <c:forEach items="${city2}" var="i">
                                <option
                                        <c:if test="${i.getId() == citych.getId()}"> selected </c:if>
                                         value="<c:out value="${i.getId()}"/>">
                                    <c:out value="${i.getDomain().substring(0,i.getDomain().indexOf(\".\"))}"/>
                                </option>
                        </c:forEach>
                    </form:select>
            </form:form>
        </div>
    </div>
    <div class="col-md-8 col-md-push-0">
        <h1>Goods Page number ${page}</h1>
        <table class="table table-striped table-bordered">
        <thead>
        <tr>
            <th>id</th>
            <th>name</th>
            <th>bid</th>
            <th>cbid</th>
        </tr>
        </thead>
            <tbody>
                <c:forEach items="${goods}" var="good">
                    <tr>
                        <td><c:out value="${good.getId()}"/></td>
                        <td><a href="<c:out value="${good.getUrl()}"/>" target="_blank"/><c:out value="${good.getName()}"/> </a></td>
                        <td><c:out value="${good.getBid()}"/></td>
                        <td><c:out value="${good.getCbid()}"/></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <div class="col-md-6 col-md-offset-1">
            <div class="col-md-3">
                <h5>${pages} Pages..</h5>
            </div>
            <div class="col-xs-4">
                <input type="text" name="page" class="form-control" placeholder="Page"/>
            </div>
            <div class="col-md-1">
                <p><button type="submit" class="btn"/>Go</p>
            </div>
        </div>
    </div>
</div>
</form:form>
<footer class="footer">
<div class="container">
    <span class="text-muted">Thank you for your support!</span>
</div>
</footer>
</body>
</html>