<%--
  Created by IntelliJ IDEA.
  User: koval
  Date: 22.01.2017
  Time: 19:05
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="templates" tagdir="/WEB-INF/tags/templates" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/pages/setup/setupMessages.jsp" %>

<c:url value="/tutor/quizzes/add" var="addQuizAction"/>

<c:set value="quiz_id" var="quizId"/>

<templates:page_template>

    <jsp:attribute name="header">
        <fmt:message key="addQuiz.title" bundle="${lang}"/>
    </jsp:attribute>

    <jsp:body>
        <div class="well">
            <form action="${addQuizAction}" method="POST" class="form-horizontal">
                <input type="hidden" name="command" value="quizAdd">

                <c:choose>
                    <c:when test="${empty errorMsgs['name'] and empty errorMsg}">
                        <div class="form-group">
                            <div class="col-xs-3">
                                <input type="text" class="form-control" id="${quizId}"
                                       name="quiz-name"
                                       placeholder="<fmt:message key="addQuiz.placeholder" bundle="${lang}"/>" required>
                            </div>
                            <div class="col-xs-3">
                                <button type="submit" class="btn btn-primary btn-md">
                                    <fmt:message key="addQuiz.add" bundle="${lang}"/>
                                </button>
                            </div>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="form-group has-error has-feedback">
                            <div class="col-xs-3">
                                <input type="text" class="form-control" id="${quizId}"
                                       value="${quiz.name}"
                                       name="quiz-name"
                                       placeholder="<fmt:message key="addQuiz.placeholder" bundle="${lang}"/>" required>
                            </div>
                            <div class="col-xs-3">
                                <button type="submit" class="btn btn-primary btn-md">
                                    <fmt:message key="addQuiz.add" bundle="${lang}"/>
                                </button>
                            </div>
                        </div>
                    </c:otherwise>
                </c:choose>

                <c:if test="${not empty errorMsgs['name']}">
                    <div class="col-xs-12">
                        <p class="text-danger">${errorMsgs['name']}</p>
                    </div>
                </c:if>
            </form>
        </div>
    </jsp:body>

</templates:page_template>
