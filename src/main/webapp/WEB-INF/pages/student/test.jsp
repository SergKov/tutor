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
        <form action="${testAction}" method="POST">
            <input type="hidden" name="command" value="saveAnswer"/>
            <input type="hidden" name="question-number" value="${currentQuestion}"/>

            <div class="form-control question-border col-xs-8" id="test">
                <c:out value="${testQuestion.question.text}"/>
            </div>

            <c:forEach items="${testQuestion.question.answers}" var="answer">
                <div class="row">
                    <br/>
                    <div class="col-xs-10">
                        <c:out value="${answer.text}"/>
                    </div>
                    <div class="col-xs-2">
                        <input type="checkbox" value="${answer.id}" name="chosenAnswersId[]"
                               <c:if test="${fn:contains(testQuestion.answers, answer)}">checked</c:if>
                        >
                    </div>
                </div>
            </c:forEach>

            <button type="submit" id="save_btn" class="col-xs-2 btn btn-info btn-md">
                <fmt:message key="test.answer.save"/>
            </button>
        </form>

        <form action="${testAction}" method="GET">
            <ul class="pagination">
                <c:forEach var="i" begin="1" end="${fn:length(test)}">
                    <li>
                        <button name="question-number" value="${i}"><c:out value="${i}"/></button>
                    </li>
                </c:forEach>
            </ul>
        </form>

        <form action="${testAction}" method="POST" class="form-horizontal js-finish-btn"
              data-finish-btn="<fmt:message key="test.confirm.finish"/>">
            <input type="hidden" name="command" value="endTest">

            <div class="col-xs-5">
                <button type="submit" id="finish_btn" class="btn btn-danger btn-md">
                    <fmt:message key="test.submit"/>
                </button>
            </div>
        </form>
    </jsp:body>

</templates:page_template>
