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

<c:url value="/settings" var="updateAction"/>

<templates:page_template>

    <jsp:attribute name="header">
        <fmt:message key="settings.title"/>
    </jsp:attribute>

    <jsp:body>
        <div class="well">
            <form action="${updateAction}" method="POST" class="form-horizontal">
                <input type="hidden" name="command" value="studentUpdate">

                <div class="form-group">
                    <div class="row">
                        <label class="control-label col-xs-5" for="email"><fmt:message key="settings.email"/></label>

                        <div class="col-xs-3">
                            <input type="text" pattern="${emailRegex}" class="form-control" id="email" name="email"
                                   placeholder="<fmt:message key="settings.enterEmail"/>" value="${user.email}"
                                   required>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <div class="row">
                        <label class="control-label col-xs-5" for="oldPsw"><fmt:message
                                key="settings.oldPassword"/></label>

                        <div class="col-xs-3">
                            <input type="password" pattern="${passwordRegex}" class="form-control" id="oldPsw"
                                   name="password" placeholder="<fmt:message key="settings.enterOldPassword"/>" required>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <div class="row">
                        <label class="control-label col-xs-5" for="newPsw"><fmt:message
                                key="settings.newPassword"/></label>

                        <div class="col-xs-3">
                            <input type="password" pattern="${passwordRegex}" class="form-control" id="newPsw"
                                   name="password" placeholder="<fmt:message key="settings.enterNewPassword"/>" required>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <div class="row">
                        <label class="control-label col-xs-5" for="confirm_psw"><fmt:message
                                key="settings.repeatNewPassword"/></label>

                        <div class="col-xs-3">
                            <input type="password" pattern="${passwordRegex}" class="form-control" id="confirm_psw"
                                   name="password"
                                   placeholder="<fmt:message key="settings.enterNewPassword"/>" required>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-xs-offset-5 col-xs-3">
                        <button type="submit" class="btn btn-info btn-block">
                            <fmt:message key="settings.edit"/>
                        </button>

                        <button class="btn btn-link btn-block" id="back_btn">
                            <fmt:message key="settings.back"/>
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </jsp:body>
</templates:page_template>