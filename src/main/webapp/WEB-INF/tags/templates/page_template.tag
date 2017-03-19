<%@ tag description="Overall page template" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ attribute name="header" fragment="true" %>
<fmt:setBundle basename="messages.base"/>
<html>
<head>
    <title>${title}</title>
    <link rel="icon" href="/resource/img/title.ico">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="/resource/js/main.js" defer></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <link href="/resource/css/main.css" rel="stylesheet" type="text/css">
</head>
<body>

<c:url value="/sign-out" var="signOutHref"/>

<header id="pageheader">
    <h1 class="text-primary text-center">
        <jsp:invoke fragment="header"/>
    </h1>

    <c:if test="${not empty student}">
        <div class="dropdown">
            <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">${student.name}
                <span class="caret"></span></button>
            <ul class="dropdown-menu">
                <li>
                    <a href="${signOutHref}">
                        <fmt:message key="signOut.signOut"/>
                    </a>
                </li>
            </ul>
        </div>
    </c:if>

    <c:if test="${not empty tutor}">
        <div class="dropdown">
            <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">${tutor.name}
                <span class="caret"></span></button>
            <ul class="dropdown-menu">
                <li>
                    <a href="${signOutHref}">
                        <fmt:message key="signOut.signOut"/>
                    </a>
                </li>
            </ul>
        </div>
    </c:if>
</header>

<div id="body">
    <div class="container">
        <div class="row alert alert-danger" <c:if test="${empty errorMsg}"> style="display: none;" </c:if>
             id="errorMsg"> ${errorMsg}
        </div>
        <jsp:doBody/>
    </div>
</div>
