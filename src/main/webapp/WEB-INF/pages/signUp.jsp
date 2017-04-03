<%--
  Created by IntelliJ IDEA.
  User: koval
  Date: 17.01.2017
  Time: 23:07
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="templates" tagdir="/WEB-INF/tags/templates" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/pages/setup/setupMessages.jsp" %>

<c:url value="/sign-up" var="signUpAction"/>

<templates:page_template>

    <jsp:attribute name="header">
        <fmt:message key="signUp.title" bundle="${lang}"/>
    </jsp:attribute>

    <jsp:body>
        <div class="well">
            <form action="${signUpAction}" method="POST" class="form-horizontal js-sign_up"
                  data-msg-repeat="${repeatPassword}">
                <input type="hidden" name="command" value="signUp"/>

                <div class="form-group">
                    <div class="row">
                        <label class="control-label col-xs-5" for="role"><fmt:message key="signUp.chooseRole"
                                                                                      bundle="${lang}"/></label>
                        <div class="col-xs-3">
                            <select class="form-control" id="role" name="role">
                                <c:forEach items="${roles}" var="role">
                                    <option value="${role}" ${role == user.role ? "selected" : ""}>
                                        <fmt:message key="signUp.${role}" bundle="${lang}"/>
                                    </option>
                                </c:forEach>
                            </select>
                        </div>

                        <c:if test="${not empty errorMsgs['role']}">
                            <div class="col-xs-12 center">
                                    ${errorMsgs['role']}
                            </div>
                        </c:if>
                    </div>
                </div>

                <div class="form-group">
                    <div class="row">
                        <label class="control-label col-xs-5" for="name"><fmt:message key="signUp.name"
                                                                                      bundle="${lang}"/></label>

                        <div class="col-xs-3">
                            <input type="text" pattern="${nameRegex}" class="form-control" id="name" name="name"
                                   value="${user.name}"
                                   placeholder="<fmt:message key="signUp.enterName" bundle="${lang}"/>" required>
                        </div>

                        <c:if test="${not empty errorMsgs['name']}">
                            <div class="col-xs-12 center">
                                    ${errorMsgs['name']}
                            </div>
                        </c:if>
                    </div>
                </div>

                <div class="form-group">
                    <div class="row">
                        <label class="control-label col-xs-5" for="surname"><fmt:message key="signUp.surname"
                                                                                         bundle="${lang}"/></label>

                        <div class="col-xs-3">
                            <input type="text" pattern="${surnameRegex}" class="form-control" id="surname"
                                   name="surname" value="${user.surname}"
                                   placeholder="<fmt:message key="signUp.enterSurname" bundle="${lang}"/>" required>
                        </div>

                        <c:if test="${not empty errorMsgs['surname']}">
                            <div class="col-xs-12 center">
                                    ${errorMsgs['surname']}
                            </div>
                        </c:if>
                    </div>
                </div>

                <div class="form-group">
                    <div class="row">
                        <label class="control-label col-xs-5" for="email"><fmt:message key="homePage.email"
                                                                                       bundle="${lang}"/></label>

                        <div class="col-xs-3">
                            <input type="text" pattern="${emailRegex}" class="form-control" id="email" name="email"
                                   value="${user.email}"
                                   placeholder="<fmt:message key="homePage.enterEmail" bundle="${lang}"/>" required>
                        </div>

                        <div class="col-xs-12">
                            <c:if test="${not empty errorMsgs['email']}">
                                <div class="col-xs-12 center">
                                        ${errorMsgs['email']}
                                </div>
                            </c:if>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <div class="row">
                        <label class="control-label col-xs-5" for="psw"><fmt:message key="homePage.password"
                                                                                     bundle="${lang}"/></label>

                        <div class="col-xs-3">
                            <input type="password" pattern="${passwordRegex}" class="form-control" id="psw"
                                   name="password"
                                   placeholder="<fmt:message key="homePage.enterPassword" bundle="${lang}"/>" required>
                        </div>

                        <div class="col-xs-12">
                            <c:if test="${not empty errorMsgs['password']}">
                                <div class="col-xs-12 center">
                                        ${errorMsgs['password']}
                                </div>
                            </c:if>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <div class="row">
                        <label class="control-label col-xs-5" for="confirm_psw">
                            <fmt:message key="signUp.repeatPassword" bundle="${lang}"/></label>

                        <div class="col-xs-3">
                            <input type="password" pattern="${passwordRegex}" class="form-control" id="confirm_psw"
                                   placeholder="<fmt:message key="homePage.enterPassword" bundle="${lang}"/>" required>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <div class="row">
                        <div class="col-xs-offset-5 col-xs-3">
                            <button type="submit" class="btn btn-info btn-block">
                                <fmt:message key="homePage.register" bundle="${lang}"/>
                            </button>
                            <button class="btn btn-link btn-block" id="back_btn">
                                <fmt:message key="signUp.back" bundle="${lang}"/>
                            </button>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </jsp:body>

</templates:page_template>
