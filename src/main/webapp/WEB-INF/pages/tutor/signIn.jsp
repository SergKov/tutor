<%--
  Created by IntelliJ IDEA.
  User: koval
  Date: 21.01.2017
  Time: 13:06
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="templates" tagdir="/WEB-INF/tags/templates" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/pages/setup/setupMessages.jsp" %>

<c:url value="/tutor" var="tutorAction"/>
<c:url value="/sign-up" var="signUpHref"/>

<c:set value="email" var="idEmail"/>
<c:set value="pwd" var="pwd"/>

<templates:page_template>

    <jsp:attribute name="header">
        <fmt:message key="homePage.title"/>
    </jsp:attribute>

    <jsp:body>
        <div class="well">
            <form action="${tutorAction}" method="POST" class="form-horizontal">
                <input type="hidden" name="command" value="tutorSignIn">

                <div class="row">
                    <label class="control-label col-xs-5" for="${email}"><fmt:message key="tutorSignIn.email"/></label>
                    <c:choose>
                        <c:when test="${empty errorMsg}">
                            <div class="form-group">
                                <div class="col-xs-3">
                                    <input type="text" pattern="${emailRegex}" class="form-control" id="${idEmail}"
                                           name="email"
                                           placeholder="<fmt:message key="tutorSignIn.enterEmail"/>"
                                           required>
                                </div>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="form-group has-error has-feedback">
                                <div class="col-xs-3">
                                    <input type="text" pattern="${emailRegex}" class="form-control" id="${idEmail}"
                                           name="email"
                                           value="${email}"
                                           placeholder="<fmt:message key="tutorSignIn.enterEmail"/>"
                                           required>
                                </div>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>

                <div class="row">
                    <label class="control-label col-xs-5" for="${pwd}"><fmt:message
                            key="tutorSignIn.password"/></label>

                    <c:choose>
                        <c:when test="${empty errorMsg}">
                            <div class="form-group">
                                <div class="col-xs-3">
                                    <input type="password" pattern="${passwordRegex}" class="form-control" id="${pwd}"
                                           name="password"
                                           placeholder="<fmt:message key="tutorSignIn.enterPassword"/>"
                                           required>
                                </div>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="form-group has-error has-feedback">
                                <div class="col-xs-3">
                                    <input type="password" pattern="${passwordRegex}" class="form-control" id="${pwd}"
                                           name="password"
                                           placeholder="<fmt:message key="tutorSignIn.enterPassword"/>"
                                           required>
                                </div>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>

                <div class="row">
                    <div class="col-xs-offset-5 col-xs-3 login-form-btn-block">
                        <button type="submit" class="btn btn-info btn-md"><fmt:message
                                key="homePage.submit"/></button>
                        <a href="${signUpHref}"><fmt:message key="homePage.register"/></a>
                    </div>
                </div>
            </form>
        </div>
    </jsp:body>

</templates:page_template>

