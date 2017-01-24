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


<templates:page_template>

<jsp:attribute name="header">
    <fmt:message key="tutorHomePage.hello"/> ${tutor.name} !
</jsp:attribute>

    <jsp:body>

        <div class="row">
            <c:choose>
                <c:when test="${quizzes ne null}">
                    <c:forEach items="${quizzes}" var="quiz">
                        <form action="/tutor/quizzes" method="POST" class="form-horizontal">
                            <input type="hidden" name="quizId" value="${quiz.id}">

                            <div class="col-xs-6 col-xs-offset-2">
                                <div class="form-group">
                                    <input id="speciality_name" type="text" class="form-control"
                                           name="quizName"
                                           value="${quiz.name}">
                                </div>
                            </div>

                            <div class="col-xs-1">
                                <input id="speciality_questions" type="image" src="/resource/img/see.ico"
                                       width="25px"
                                       height="25px"
                                       name="controller" value="quizQuestions">
                            </div>

                            <div class="col-xs-2">
                                <input id="speciality_remove" class="remove_btn" type="image"
                                       src="/resource/img/delete.ico" width="25px" height="25px"
                                       name="controller" value="quizRemove">
                            </div>
                        </form>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <h1 class="text-muted text-center"><fmt:message key="quizPage.text"/></h1>
                </c:otherwise>
            </c:choose>
        </div>

        <div class="row">
            <a href="/tutor/quizzes/addQuiz" id="add_quiz" class="col-xs-1 col-xs-offset-10">
                <img src="/resource/img/plus.ico" width="25px" height="25px">
            </a>
        </div>
        
    </jsp:body>

</templates:page_template>
