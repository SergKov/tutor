<%--
  Created by IntelliJ IDEA.
  User: koval
  Date: 31.03.2017
  Time: 21:09
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="templates" tagdir="/WEB-INF/tags/templates" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/pages/setup/setupMessages.jsp" %>

<c:url value="/student/update-password" var="updatePasswordAction"/>

<templates:page_template>

    <jsp:attribute name="header">
        <fmt:message key="updatePassword.title" bundle="${lang}"/>
    </jsp:attribute>

    <jsp:body>
        <div class="well">
            <form action="${updatePasswordAction}" method="POST" class="form-horizontal">
                <input type="hidden" name="command" value="studentUpdatePassword">

                <div class="form-group">
                    <div class="row">
                        <label class="control-label col-xs-5" for="oldPsw"><fmt:message
                                key="updatePassword.oldPassword" bundle="${lang}"/></label>

                        <div class="col-xs-3">
                            <input type="password" pattern="${passwordRegex}" class="form-control" id="oldPsw"
                                   name="oldPassword"
                                   placeholder="<fmt:message key="updatePassword.enterOldPassword" bundle="${lang}"/>"
                                   required>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <div class="row">
                        <label class="control-label col-xs-5" for="newPsw"><fmt:message
                                key="updatePassword.newPassword" bundle="${lang}"/></label>

                        <div class="col-xs-3">
                            <input type="password" pattern="${passwordRegex}" class="form-control" id="newPsw"
                                   name="newPassword"
                                   placeholder="<fmt:message key="updatePassword.enterNewPassword" bundle="${lang}"/>"
                                   required>
                        </div>

                        <div class="col-xs-12">
                            <c:if test="${not empty errorMsgs['newPassword']}">
                                <div class="col-xs-12 center">
                                    <p class="text-danger">${errorMsgs['newPassword']}</p>
                                </div>
                            </c:if>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <div class="row">
                        <label class="control-label col-xs-5" for="confirm_psw"><fmt:message
                                key="updatePassword.repeatNewPassword" bundle="${lang}"/></label>

                        <div class="col-xs-3">
                            <input type="password" pattern="${passwordRegex}" class="form-control" id="confirm_psw"
                                   placeholder="<fmt:message key="updatePassword.enterNewPassword" bundle="${lang}"/>"
                                   required>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-xs-offset-5 col-xs-3">
                        <button type="submit" class="btn btn-info btn-block">
                            <fmt:message key="updatePassword.edit" bundle="${lang}"/>
                        </button>

                        <button class="btn btn-link btn-block" id="back_btn">
                            <fmt:message key="updatePassword.back" bundle="${lang}"/>
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </jsp:body>
</templates:page_template>