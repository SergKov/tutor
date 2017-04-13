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
<%@ taglib prefix="shared" tagdir="/WEB-INF/tags/shared" %>
<%@ include file="/WEB-INF/pages/setup/setupMessages.jsp" %>

<c:url value="/tutor/quizzes" var="quizzesAction"/>
<c:url value="/tutor/quizzes/add" var="addQuizHref"/>
<c:url value="/resource/img/see.ico" var="seeQuestionsIcon"/>
<c:url value="/resource/img/delete.ico" var="deleteQuizIcon"/>
<c:url value="/resource/img/active.ico" var="activeQuizIcon"/>
<c:url value="/resource/img/edit.ico" var="editQuizIcon"/>
<c:url value="/resource/img/plus.ico" var="plusQuestionIcon"/>

<c:set value="quiz_name" var="quizNameId"/>

<templates:page_template>

<jsp:attribute name="header">
    <fmt:message key="quizPage.hello"/> ${tutor.name} !
</jsp:attribute>

    <jsp:body>
        <c:choose>
            <c:when test="${not empty quizzes}">
                <c:forEach items="${quizzes}" var="quiz">
                    <c:set var="isNotActive" value="${quiz.active eq null or not quiz.active}"/>
                    <c:set var="emtyErrorMsgs" value="${empty errorMsgs['name'] and empty errorMsg}"/>

                    <div class="row quizzes-page-block js-quiz-block">
                        <div class="col-xs-5 col-xs-offset-2">
                            <c:choose>
                                <c:when test="${isNotActive}">
                                    <c:choose>
                                        <c:when test="${emtyErrorMsgs}">
                                            <div class="form-group">
                                                <input id="${quizNameId}" type="text"
                                                       class="form-control js-input-source"
                                                       name="quiz-name"
                                                       value="<c:out value="${quiz.name}"/>"/>
                                            </div>
                                        </c:when>
                                        <c:otherwise>
                                            <c:choose>
                                                <c:when test="${quizForm.id ne quiz.id}">
                                                    <div class="form-group">
                                                        <input id="${quizNameId}" type="text"
                                                               class="form-control js-input-source"
                                                               name="quiz-name"
                                                               value="<c:out value="${quiz.name}"/>"/>
                                                    </div>
                                                </c:when>
                                                <c:otherwise>
                                                    <div class="form-group has-error has-feedback">
                                                        <input id="${quizNameId}" type="text"
                                                               class="form-control js-input-source"
                                                               name="quiz-name"
                                                               value="<c:out value="${quizForm.name}"/>"/>
                                                    </div>

                                                    <div class="col-xs-12 center">
                                                        <p class="text-danger">${errorMsgs['name']}</p>
                                                    </div>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:otherwise>
                                    </c:choose>
                                </c:when>
                                <c:otherwise>
                                    <div class="form-group">
                                        <input id="${quizNameId}" type="text" class="form-control"
                                               name="quiz-name" value="<c:out value="${quiz.name}"/>"
                                               readonly="readonly">
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </div>

                        <div class="col-xs-1">
                            <form action="${quizzesAction}" method="GET" class="form-horizontal">
                                <input type="hidden" name="quiz-id" value="${quiz.id}">

                                <input type="image" id="text" class="see_btn"
                                       src="${seeQuestionsIcon}" width="25px" height="25px">
                            </form>
                        </div>

                        <c:if test="${isNotActive}">
                            <div class="col-xs-1">
                                <form action="${quizzesAction}" method="POST" class="form-horizontal">
                                    <input type="hidden" name="quiz-id" value="${quiz.id}"/>
                                    <input type="hidden" name="quiz-name" class="js-input-target"/>

                                    <input id="quiz_update" type="image" src="${editQuizIcon}" width="25px"
                                           height="25px"
                                           name="command" value="quizUpdate">
                                </form>
                            </div>
                        </c:if>

                        <div class="col-xs-1">
                            <form action="${quizzesAction}" method="POST" class="form-horizontal js-remove-btn"
                                  data-remove-btn="<fmt:message key="quizzes.confirm"/>">
                                <input type="hidden" name="quiz-id" value="${quiz.id}">

                                <input id="quiz_remove" class="remove_btn" type="image"
                                       src="${deleteQuizIcon}" width="25px" height="25px"
                                       name="command" value="quizRemove">
                            </form>
                        </div>

                        <c:if test="${isNotActive}">
                            <div class="col-xs-1">
                                <form action="${quizzesAction}" method="POST" class="form-horizontal">
                                    <input type="hidden" name="quiz-id" value="${quiz.id}">

                                    <input id="quiz_active" type="image" src="${activeQuizIcon}" width="25px"
                                           height="25px"
                                           name="command" value="quizActive">
                                </form>
                            </div>
                        </c:if>
                    </div>
                </c:forEach>

                <shared:pagination action="${quizzesAction}"
                                   currentPage="${pagination.currentPage}"
                                   numberOfPages="${pagination.numberOfPages}"
                                   showElements="${pagination.showElements}"/>
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
