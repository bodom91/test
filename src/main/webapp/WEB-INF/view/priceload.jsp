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
    <div class="col-md-3">
        <div class="hero-unit">
        <h4>Выбор города</h4>
            <form:form modelAttribute="citych" method="post" action="/priceload">
                    <form:select path="id" onchange='if(this.value != 0) { this.form.submit(); }'>
                        <c:forEach items="${city2}" var="i">
                                <option value="<c:out value="${i.getId()}"/>"><c:out value="${i.getDomain()}"/></option>
                        </c:forEach>
                    </form:select>
            </form:form>
        </div>
    </div>
    <div class="col-md-8">
        <h1>Goods Page</h1>
        <table class="table table-hover">
        <thead>
        <tr>
            <th>ID</th>
            <th>Domain</th>
            <th>State</th>
            <th>StateReason</th>
            <th>StReasonCpa</th>
        </tr>
        </thead>
            <tbody>
                <c:forEach items="${city2}" var="i">
                    <tr>
                        <td><c:out value="${i.getId()}"/></td>
                        <td><a href="http://<c:out value="${i.getDomain()}"/>"/><c:out value="${i.getDomain()}"/> </a></td>
                        <td><c:out value="${i.getState()}"/></td>
                        <td><c:out value="${i.getStateReasons()}"/></td>
                        <td><c:out value="${i.getStateReasonsCpa()}"/></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <br>Thank you for your support.</p>
    </div>
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
        <h3>Enter text</h3>
        <input type="button" value="OK" onclick="refresh()">
        <div id="result"></div>
    </div>
</div>
</body>
</html>