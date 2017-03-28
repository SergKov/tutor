<%--
  Created by IntelliJ IDEA.
  User: koval
  Date: 16.01.2017
  Time: 23:03
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="templates" tagdir="/WEB-INF/tags/templates" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/pages/setup/setupMessages.jsp" %>

<c:url value="/" var="signInAction"/>
<c:url value="/sign-up" var="signUpHref"/>

<templates:page_template>

    <jsp:attribute name="header">
        <fmt:message key="homePage.title"/>
    </jsp:attribute>

    <jsp:body>
        <div class="well">
            <form action="${signInAction}" method="POST" class="form-horizontal">
                <div class="row">
                    <input type="hidden" name="command" value="studentSignIn">

                    <label class="control-label col-xs-5" for="email"><fmt:message
                            key="homePage.login"/></label>

                    <c:choose>
                        <c:when test="${empty errorMsg}">
                            <div class="form-group">
                                <div class="col-xs-3">
                                    <input type="text" pattern="${emailRegex}" class="form-control" id="email"
                                           name="email"
                                           placeholder="<fmt:message key="homePage.enterEmail"/>" required>
                                </div>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="form-group has-error has-feedback">
                                <div class="col-xs-3">
                                    <input type="text" pattern="${emailRegex}" class="form-control" id="email"
                                           name="email"
                                           value="${email}"
                                           placeholder="<fmt:message key="homePage.enterEmail"/>" required>
                                </div>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>


                <div class="row">
                    <label class="control-label col-xs-5" for="psw"><fmt:message
                            key="homePage.password"/></label>

                    <c:choose>
                        <c:when test="${empty errorMsg}">
                            <div class="form-group">
                                <div class="col-xs-3">
                                    <input type="password" pattern="${passwordRegex}" class="form-control" id="psw"
                                           name="password" placeholder="<fmt:message key="homePage.enterPassword"/>"
                                           required>
                                </div>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="form-group has-error has-feedback">
                                <div class="col-xs-3">
                                    <input type="password" pattern="${passwordRegex}" class="form-control" id="psw"
                                           name="password" placeholder="<fmt:message key="homePage.enterPassword"/>"
                                           required>
                                </div>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>

                <div class="row">
                    <div class="col-xs-offset-7 col-xs-5">
                        <button type="submit" class="btn btn-info btn-md"><fmt:message
                                key="homePage.submit"/></button>
                    </div>
                </div>

                <div class="row">
                    <p class="text-center"><a href="${signUpHref}"><fmt:message key="homePage.register"/></a>
                    </p>
                </div>
            </form>
        </div>
    </jsp:body>

</templates:page_template>