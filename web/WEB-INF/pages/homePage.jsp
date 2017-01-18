<%--
  Created by IntelliJ IDEA.
  User: koval
  Date: 16.01.2017
  Time: 23:03
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="templates" tagdir="/WEB-INF/tags/templates" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/WEB-INF/pages/setup/setupMessages.jsp" %>

<templates:page_template>

    <jsp:attribute name="header">
        <fmt:message key="homePage.title"/>
    </jsp:attribute>

    <jsp:body>
        <div class="row">
            <div class="well">
                <form action="/" method="POST" class="form-horizontal">
                    <input type="hidden" name="controller" value="studentSignIn">

                    <div class="form-group">
                        <label class="control-label col-xs-5" for="email"><fmt:message key="homePage.login"/></label>

                        <div class="col-xs-3">
                            <input type="text" pattern="${emailRegex}" class="form-control" id="email" name="email"
                                   placeholder="<fmt:message bundle="${bundle}" key="homePage.enterEmail"/>" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-xs-5" for="psw"><fmt:message key="homePage.password"/>:</label>

                        <div class="col-xs-3">
                            <input type="password" pattern="${passwordRegex}" class="form-control" id="psw"
                                   name="password" placeholder="<fmt:message key="homePage.enterPassword"/>" required>
                        </div>
                    </div>
                    <div class="col-xs-offset-7 col-xs-5">
                        <button type="submit" class="btn btn-info btn-md"><fmt:message key="homePage.submit"/></button>
                    </div>
                    <p class="text-center"><a href="/signUp"><fmt:message key="homePage.register"/></a></p>
                </form>
            </div>
        </div>
    </jsp:body>

</templates:page_template>