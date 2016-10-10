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
<div class="container">
    <form:form method="post" modelAttribute="check" action="/city">
<div class="col-md-2">
    <script type="text/javascript">
        function refresh() {
            $.ajax(
                    {
                        url:"ajaxRefreshCity",
                        success: function (data) {
                            $("#result").html(data);
                        }
                    }
            );
        }
    </script>
    <h3>Refresh City</h3>
    <input type="button" value="OK" onclick="refresh()">
    <div class=".m-x-auto" id="result"></div>
</div>
<div class="col-md-8">
    <h1>City Page</h1>
    <table class="table table-striped table-bordered">
        <thead>
        <tr>
            <th>#</th>
            <th>ID</th>
            <th>Domain</th>
            <th>State</th>
            <th>StateReason</th>
            <th>StReasonCpa</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${city}" var="i">
            <tr>
                <td>
                    <input type="checkbox" name="check" value="${i.getId()}">
                </td>
                <td><c:out value="${i.getId()}"/></td>
                <td><a href="http://<c:out value="${i.getDomain()}"/>" target="_blank"/><c:out value="${i.getDomain()}"/> </a></td>
                <td><c:out value="${i.getState()}"/></td>
                <td><c:out value="${i.getStateReasons()}"/></td>
                <td><c:out value="${i.getStateReasonsCpa()}"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <br>Thank you for your support.</p>
</div>
    <div class="col-md-1">
        <p><button class="btn btn-primary" type="submit"/>Add City</p>
    </div>
    </form:form>
</div>