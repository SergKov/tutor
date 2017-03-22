<%--
  Created by IntelliJ IDEA.
  User: koval
  Date: 27.01.2017
  Time: 1:24
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="templates" tagdir="/WEB-INF/tags/templates" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/pages/setup/setupMessages.jsp" %>

<c:url value="/tutor/quizzes/questions/add" var="addQuestionAction"/>
<c:url value="/resource/img/plus.ico" var="plusAnswerIcon"/>
<c:url value="/resource/img/minus.ico" var="minusAnswerIcon"/>

<templates:page_template>

    <jsp:attribute name="header">
        <fmt:message key="addQuestion.title"/>
    </jsp:attribute>

    <jsp:body>
        <form action="${addQuestionAction}" method="POST" class="form-horizontal">
            <input type="hidden" name="command" value="questionAdd"/>

            <div class="row">
                <label for="question"><fmt:message key="addQuestion.question"/></label>
            </div>

            <div class="row">
                <div class="form-group">
                    <textarea class="form-control question-border" rows="3" id="question" name="questionText"
                              required>${questionText}</textarea>
                </div>
            </div>

            <div class="row">
                <div class="col-xs-8">
                    <span><fmt:message key="addQuestion.answer"/></span>
                </div>
                <div class="col-xs-1 col-xs-offset-2">
                    <span><fmt:message key="addQuestion.type"/> </span>
                </div>
            </div>

            <div class="row answers">
                <div class="answer">
                    <div class="col-xs-8">
                        <input type="text" class="form-control" id="answer" name="answerText[]" required/>
                    </div>

                    <div class="col-xs-2 col-xs-offset-2">
                        <select id="answerType" name="answerType[]" class="form-control" required>
                            <c:forEach items="${answerTypes}" var="type">
                                <option value="${type}">
                                    <fmt:message key="addQuestion.${type}"/>
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </div>


            <div class="row answers-manipulate">
                <div class="col-xs-1 col-xs-offset-10">
                    <img id="plusAnswer" src="${plusAnswerIcon}" width="25px" height="25px">
                </div>

                <div class="col-xs-1">
                    <img id="minusAnswer" src="${minusAnswerIcon}" width="25px" height="25px">
                </div>
            </div>


            <p>
            <div class="row">
                <div class="col-xs-4 col-xs-offset-10">
                    <button type="submit" class="btn btn-primary btn-md">
                        <fmt:message key="addQuestion.add"/>
                    </button>
                </div>
            </div>
            </p>
        </form>
    </jsp:body>

</templates:page_template>
