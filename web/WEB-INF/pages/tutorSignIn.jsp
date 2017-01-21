<%--
  Created by IntelliJ IDEA.
  User: koval
  Date: 21.01.2017
  Time: 13:06
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
        <div class="well">
            <form action="/tutor" method="POST" class="form-horizontal">
                <input type="hidden" name="controller" value="tutorSignIn">

                <div class="row">
                    <div class="form-group">
                        <label class="control-label col-xs-5" for="email"><fmt:message key="tutorSignIn.email"/></label>

                        <div class="col-xs-3">
                            <input type="text" pattern="${emailRegex}" class="form-control" id="email" name="email"
                                   placeholder="<fmt:message key="tutorSignIn.enterEmail"/>" required>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group">
                        <label class="control-label col-xs-5" for="psw"><fmt:message
                                key="tutorSignIn.password"/></label>

                        <div class="col-xs-3">
                            <input type="password" pattern="${passwordRegex}" class="form-control" id="psw"
                                   name="password" placeholder="<fmt:message key="tutorSignIn.enterPassword"/>"
                                   required>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-xs-offset-7 col-xs-5">
                        <button type="submit" class="btn btn-info btn-md"><fmt:message key="homePage.submit"/></button>
                    </div>
                </div>

                <div class="row">
                    <p class="text-center"><a href="/signUp"><fmt:message key="homePage.register"/></a></p>
                </div>
            </form>
        </div>
    </jsp:body>

</templates:page_template>

