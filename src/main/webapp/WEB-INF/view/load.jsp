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
        <h4>Choose city</h4>
        <form:form method="post" enctype="multipart/form-data" action="/loadprice" id="my" modelAttribute="citych">
        <form:select path="id">
                <c:forEach items="${city2}" var="i">
                    <option
                            <c:if test="${i.getId() == citych.getId()}"> selected </c:if>
                            value="<c:out value="${i.getId()}"/>">
                        <c:out value="${i.getDomain().substring(0,i.getDomain().indexOf(\".\"))}"/>
                    </option>
                </c:forEach>
        </form:select>
    </div>
    <div class="col-md-6">

            File to upload:
        <input type="file" name="file"><br/>
        <button form="my" type="submit" value="Upload" />
            Press here to upload the file!
            <p>${filename}</p>
    </div>
    </form:form>
</div>
</body>