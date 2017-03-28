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

<templates:page_template>

    <jsp:attribute name="header">
        <fmt:message key="addQuiz.title"/>
    </jsp:attribute>

    <jsp:body>
        <div class="well">
            <form action="${addQuizAction}" method="POST" class="form-horizontal">
                <input type="hidden" name="command" value="quizAdd">

                <c:choose>
                    <c:when test="${empty errorMsgs['name'] and empty errorMsg}">
                        <div class="form-group">
                            <div class="col-xs-3">
                                <input type="text" class="form-control" id="quiz_id"
                                       pattern="${quizRegex}"
                                       name="quiz-name" placeholder="<fmt:message key="addQuiz.placeholder"/>" required>
                            </div>
                            <div class="col-xs-3">
                                <button type="submit" class="btn btn-primary btn-md">
                                    <fmt:message key="addQuiz.add"/>
                                </button>
                            </div>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="form-group has-error has-feedback">
                            <div class="col-xs-3">
                                <input type="text" class="form-control" id="quiz_id"
                                       value="${quiz.name}" pattern="${quizRegex}"
                                       name="quiz-name" placeholder="<fmt:message key="addQuiz.placeholder"/>" required>
                            </div>
                            <div class="col-xs-3">
                                <button type="submit" class="btn btn-primary btn-md">
                                    <fmt:message key="addQuiz.add"/>
                                </button>
                            </div>
                        </div>
                    </c:otherwise>
                </c:choose>

                <c:if test="${not empty errorMsgs['name']}">
                    <div class="col-xs-12">
                        <c:out value="${errorMsgs['name']}"/>
                    </div>
                </c:if>
            </form>
        </div>
    </jsp:body>

</templates:page_template>
