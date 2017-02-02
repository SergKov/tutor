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
        <fmt:message key="studentHomePage.welcome"/> ${student.name}
    </jsp:attribute>

    <jsp:body>
        <c:choose>
            <c:when test="${not empty quizzes}">

                <div class="row">
                    <div class="col-xs-offset-5 col-xs-5">
                        <label class="control-label" for="quiz">
                            <h3><fmt:message key="studentHomePage.chooseQuiz"/></h3>
                        </label>
                    </div>
                </div>

                <form action="/studentHomePage" method="POST">
                    <input type="hidden" name="controller" value="studentTest">

                    <div class="row">
                        <div class="col-xs-offset-5 col-xs-2">
                            <div class="form-group">
                                <select id="quiz" class="form-control" name="quiz">
                                    <c:forEach items="${quizzes}" var="quiz">
                                        <option value="${quiz.id}">
                                                ${quiz.name}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-xs-offset-5 col-xs-2">
                            <button class="btn btn-block btn-primary" id="start_btn">
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
            <div class="col-xs-offset-5 col-xs-2">
                <button class="btn btn-block btn-link" id="back_btn">
                    <fmt:message key="studentHomePage.back"/>
                </button>
            </div>
        </div>
    </jsp:body>

</templates:page_template>
