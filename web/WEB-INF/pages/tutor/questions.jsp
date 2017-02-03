<%--
  Created by IntelliJ IDEA.
  User: koval
  Date: 24.01.2017
  Time: 19:41
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="templates" tagdir="/WEB-INF/tags/templates" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/pages/setup/setupMessages.jsp" %>

<c:url value="/tutor/quizzes/questions/add" var="addQuestionAction"/>
<c:url value="/tutor/quizzes/questions" var="seeQuestionAction"/>
<c:url value="/tutor/quizzes/questions" var="removeQuestionAction"/>

<templates:page_template>

    <jsp:attribute name="header">
        <fmt:message key="questionPage.welcome"/><c:out value=" ${tutor.name} !"/>
    </jsp:attribute>

    <jsp:body>

        <c:choose>
            <c:when test="${not empty questions}">
                <c:forEach items="${questions}" var="question">
                    <div class="row">
                        <div class="col-xs-6 col-xs-offset-2">
                            <c:set var="shortText" value="${fn:substring(question.text, 0, 50)}"/>
                                <c:out value="${shortText}"/>
                        </div>

                        <div class="col-xs-1">
                            <form action="${seeQuestionAction}" method="GET" class="form-horizontal">
                                <input type="hidden" name="question-id" value="${question.id}">

                                <input type="image" id="question" class="see_btn"
                                       src="/resource/img/see.ico" width="25px" height="25px">
                            </form>
                        </div>

                        <div class="col-xs-2">
                            <form action="${seeQuestionAction}" method="POST" class="form-horizontal js-remove-btn"
                                  data-remove-btn="<fmt:message key="quizzes.delete.confirm"/>">
                                <input type="hidden" name="question-id" value="${question.id}">

                                <input id="question_remove" class="remove_btn" type="image"
                                       src="/resource/img/delete.ico" width="25px" height="25px"
                                       name="controller" value="questionRemove">
                            </form>
                        </div>
                    </div>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <h1 class="text-muted text-center"><fmt:message key="questionPage.text"/></h1>
            </c:otherwise>
        </c:choose>

        <div class="row">
            <div class="col-xs-1 col-xs-offset-11">
                <a href="${addQuestionAction}" id="add_question">
                    <img src="/resource/img/plus.ico" width="25px" height="25px">
                </a>
            </div>
        </div>
    </jsp:body>

</templates:page_template>
