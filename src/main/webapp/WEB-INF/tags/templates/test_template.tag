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

<header id="pageheader" class="header">
    <h1 class="text-primary text-center">
        <jsp:invoke fragment="header"/>
    </h1>
</header>

<div id="body">
    <div class="container">
        <jsp:doBody/>
    </div>
</div>