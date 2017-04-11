<%--
  Created by IntelliJ IDEA.
  User: koval
  Date: 29.01.2017
  Time: 21:48
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="templates" tagdir="/WEB-INF/tags/templates" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/WEB-INF/pages/setup/setupMessages.jsp" %>

<c:url value="/student/test" var="testAction"/>

<templates:page_template>

    <jsp:body>
        <form action="${testAction}" method="POST" class="question-student-form" id="question-form">
            <input type="hidden" name="command" value="answerSave"/>
            <input type="hidden" name="question-number" value="${currentQuestion}"/>

            <div class="text-border question-block" id="test">
                    ${testQuestion.question.text}
            </div>

            <div class="answer-block clearfix">
                <c:forEach items="${testQuestion.question.answers}" var="answer">
                    <label class="answer-single">
                        <input type="checkbox" value="${answer.id}" name="chosenAnswersId[]" class="answer-single__input"
                               <c:if test="${fn:contains(testQuestion.answers, answer)}">checked</c:if>
                        > <span class="answer-text">${answer.text}</span>
                    </label>
                </c:forEach>
            </div>

        </form>

        <div class="question-form__btn-block clearfix">
            <button type="submit" id="save_btn" class="btn btn-success btn-md answer-submit-btn" form="question-form">
                <fmt:message key="test.answer.save"/>
            </button>

            <button type="submit" id="finish_btn" class="btn btn-danger btn-md" form="close-form" form="question-form">
                <fmt:message key="test.submit"/>
            </button>
        </div>

        <form action="${testAction}" method="GET">
            <ul class="pagination custom-pagination">
                <c:forEach var="i" begin="1" end="${fn:length(test)}">
                    <li>
                        <button name="question-number" value="${i}">${i}</button>
                    </li>
                </c:forEach>
            </ul>
        </form>

        <form action="${testAction}" method="POST" class="form-horizontal js-finish-btn" id="close-form"
              data-finish-btn="<fmt:message key="test.confirm.finish"/>">
            <input type="hidden" name="command" value="testEnd">
        </form>
    </jsp:body>

</templates:page_template>
