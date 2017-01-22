<%--
  Created by IntelliJ IDEA.
  User: koval
  Date: 19.01.2017
  Time: 19:28
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="templates" tagdir="/WEB-INF/tags/templates" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/pages/setup/setupMessages.jsp" %>

<templates:page_template>

    <jsp:attribute name="header">
        <fmt:message key="studentHomePage.welcome"/> ${user.name}
    </jsp:attribute>

    <jsp:body>
        <c:choose>
            <c:when test="${not empty quizzes}">
                <div class="row">
                    <div class="col-xs-offset-5 col-xs-5">
                        <label class="control-label" for="spec">
                            <h3><fmt:message key="studentHomePage.chooseSpeciality"/></h3>
                        </label>
                    </div>
                </div>

                <form action="/studentHomePage" method="GET">
                    <input type="hidden" name="controller" value="chooseTest">

                    <div class="row">
                        <div class="col-xs-offset-5 col-xs-2">
                            <c:forEach items="${quizzes}" var="quiz">
                                <div class="form-group">
                                    <select id="spec" class="form-control" name="speciality">
                                        <option value="${quiz.id}">
                                                ${quiz.name}
                                        </option>
                                    </select>
                                </div>
                            </c:forEach>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-xs-offset-5 col-xs-5">
                            <button class="btn btn-primary" id="start_btn">
                                <fmt:message key="studentHomePage.start"/>
                            </button>
                        </div>
                    </div>
                </form>

            </c:when>
            <c:otherwise>
                <h1 class="text-muted text-center"><fmt:message key="studentHomePage.empty"/></h1>
                <h3 class="text-muted text-center"><fmt:message key="studentHomePage.tryLater"/></h3>
            </c:otherwise>
        </c:choose>

        <div class="row">
            <div class="col-xs-offset-5 col-xs-3">
                <button class="btn btn-link" id="back_btn">
                    <fmt:message key="studentHomePage.back"/>
                </button>
            </div>
        </div>
    </jsp:body>

</templates:page_template>
