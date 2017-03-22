<%--
  Created by IntelliJ IDEA.
  User: koval
  Date: 21.01.2017
  Time: 20:56
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="templates" tagdir="/WEB-INF/tags/templates" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/pages/setup/setupMessages.jsp" %>

<c:url value="/tutor/quizzes" var="quizzesAction"/>
<c:url value="/tutor/quizzes/add" var="addQuizHref"/>
<c:url value="/resource/img/see.ico" var="seeQuestionsIcon"/>
<c:url value="/resource/img/delete.ico" var="deleteQuizIcon"/>
<c:url value="/resource/img/plus.ico" var="plusQuestionIcon"/>

<templates:page_template>

<jsp:attribute name="header">
    <fmt:message key="quizPage.hello"/><c:out value=" ${tutor.name} !"/>
</jsp:attribute>

    <jsp:body>
        <c:choose>
            <c:when test="${not empty quizzes}">
                <c:forEach items="${quizzes}" var="quiz">
                    <div class="row">
                        <div class="col-xs-6 col-xs-offset-2">
                            <div class="form-group">
                                <input id="quiz_name" type="text" class="form-control"
                                       name="quiz-name" value="${quiz.name}" readonly="readonly">
                            </div>
                        </div>

                        <div class="col-xs-1">
                            <form action="${quizzesAction}" method="GET" class="form-horizontal">
                                <input type="hidden" name="quiz-id" value="${quiz.id}">

                                <input type="image" id="question" class="see_btn"
                                       src="${seeQuestionsIcon}" width="25px" height="25px">
                            </form>
                        </div>

                        <div class="col-xs-2">
                            <form action="${quizzesAction}" method="POST" class="form-horizontal js-remove-btn"
                                  data-remove-btn="<fmt:message key="quizzes.confirm"/>">
                                <input type="hidden" name="quiz-id" value="${quiz.id}">

                                <input id="quiz_remove" class="remove_btn" type="image"
                                       src="${deleteQuizIcon}" width="25px" height="25px"
                                       name="command" value="quizRemove">
                            </form>
                        </div>
                    </div>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <h1 class="text-muted text-center"><fmt:message key="quizPage.text"/></h1>
            </c:otherwise>
        </c:choose>

        <div class="row">
            <a href="${addQuizHref}" id="add_quiz" class="col-xs-1 col-xs-offset-10">
                <img src="${plusQuestionIcon}" width="25px" height="25px">
            </a>
        </div>

    </jsp:body>

</templates:page_template>
