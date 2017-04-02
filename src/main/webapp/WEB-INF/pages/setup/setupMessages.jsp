<%--
  Created by IntelliJ IDEA.
  User: koval
  Date: 17.01.2017
  Time: 16:24
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<fmt:setLocale value="${pageContext.request.locale.language}"/>
<fmt:setBundle basename="messages.base" var="lang"/>
<fmt:setBundle basename="attribute.base" var="attribute"/>