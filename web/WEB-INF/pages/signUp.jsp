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

<templates:page_template>

    <jsp:attribute name="header">
        <fmt:message key="signUp.title"/>
    </jsp:attribute>

    <jsp:body>
        <div class="well">
            <form action="/signUp" method="POST" class="form-horizontal js-sign_up" data-msg-repeat="${repeatPassword}">
                <div class="row">
                    <input type="hidden" name="controller" value="signUp"/>

                    <div class="form-group">
                        <label class="control-label col-xs-5" for="role"><fmt:message key="signUp.chooseRole"/></label>

                        <div class="col-xs-3">
                            <select class="form-control" id="role" name="role">
                                <c:forEach items="${roles}" var="role">
                                    <option value="${role}">
                                        <fmt:message key="signUp.${role}"/>
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group">
                        <label class="control-label col-xs-5" for="name"><fmt:message key="signUp.name"/></label>

                        <div class="col-xs-3">
                            <input type="text" pattern="${nameRegex}" class="form-control" id="name" name="name"
                                   value="${user.name}" placeholder="<fmt:message key="signUp.enterName"/>" required>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group">
                        <label class="control-label col-xs-5" for="surName"><fmt:message key="signUp.surname"/></label>

                        <div class="col-xs-3">
                            <input type="text" pattern="${surnameRegex}" class="form-control" id="surName"
                                   name="surname" value="${user.surname}"
                                   placeholder="<fmt:message key="signUp.enterSurname"/>" required>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group">
                        <label class="control-label col-xs-5" for="email"><fmt:message key="homePage.email"/></label>

                        <div class="col-xs-3">
                            <input type="text" pattern="${emailRegex}" class="form-control" id="email" name="email"
                                   value="${user.email}"
                                   placeholder="<fmt:message key="homePage.enterEmail"/>" required>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group">
                        <label class="control-label col-xs-5" for="psw"><fmt:message key="homePage.password"/></label>

                        <div class="col-xs-3">
                            <input type="password" pattern="${passwordRegex}" class="form-control" id="psw"
                                   name="password" placeholder="<fmt:message key="homePage.enterPassword"/>" required>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group">
                        <label class="control-label col-xs-5" for="confirm_psw"><fmt:message
                                key="signUp.repeatPassword"/></label>

                        <div class="col-xs-3">
                            <input type="password" pattern="${passwordRegex}" class="form-control" id="confirm_psw"
                                   name="password" placeholder="<fmt:message key="homePage.enterPassword"/>" required>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group">
                        <div class="col-xs-offset-5 col-xs-3">
                            <button type="submit" class="btn btn-info btn-block">
                                <fmt:message key="homePage.register"/>
                            </button>
                            <button class="btn btn-link btn-block" id="back_btn">
                                <fmt:message key="signUp.back"/>
                            </button>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </jsp:body>

</templates:page_template>
