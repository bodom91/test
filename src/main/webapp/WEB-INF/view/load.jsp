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
        Load Price
    </title>
</head>
<body>
<div class="container">
    <header >
        <h1>Load Price</h1>
    </header>
    <div class="navbar-wrapper">
        <div class="container">
            <div class="navbar navbar-inverse navbar-static-top" role="navigation">
                <div class="container">
                    <div class="navbar-collapse collapse">
                        <ul class="nav navbar-nav">
                            <li class="active"><a href="<c:url value="/homepage"/>">Homepage</a></li>
                            <li><a href="<c:url value="/priceload"/>">Priceload</a></li>
                            <li><a href="<c:url value="/load"/>">LoadPrice</a></li>
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

    <div class="col-md-3">

    </div>
    <div class="col-md-6">
        <table class="table table-striped table-bordered">
            <thead>
                <tr>
                    <th>City</th>
                    <th>Fileload</th>
                    <th>Submit</th>
                    <th>Status</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach items="${city2}" var="i">
                <form:form method="post" action="loadprice" enctype="multipart/form-data">
                <tr>
                    <input name="city" type="hidden" value="${i.getId()}"/>
                    <td><c:out value="${i.getDomain().substring(0,i.getDomain().indexOf(\".\"))}"></c:out></td>
                    <td><input type="file" name="file"><br/></td>
                    <td><input type="submit" value="Upload" class="btn btn-primary"/></td>
                    <td>No</td>
                </tr>
                </form:form>
            </c:forEach>
            </tbody>
            </table>
    </div>
</div>
</body>