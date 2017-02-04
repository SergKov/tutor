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

<c:url value="/test" var="testAction"/>

<templates:page_template>

    <jsp:attribute name="header">
        <c:out value=" ${quiz.name} !"/>
    </jsp:attribute>

    <jsp:body>
        <form action="${testAction}" method="POST">
            <input type="hidden" name="controller" value="testPage"/>

            <textarea class="form-control question-border" rows="3" id="question" disabled>
                <c:out value="${question.text}"/>
            </textarea>

            <c:forEach items="${question.answers}" var="answer">
                <div class="row">
                    <br/>
                    <div class="col-xs-10">
                        <c:out value="${answer.text}"/>
                    </div>
                    <div class="col-xs-2">
                        <input type="checkbox" value="" name="answerText[]">
                    </div>
                </div>
            </c:forEach>

            <ul class="pagination">
                <c:forEach var="i" begin="1" end="${fn:length(quiz.questions)}">
                    <li>
                        <button name="questionNumber" value="${i}"><c:out value="${i}"/></button>
                    </li>
                </c:forEach>
            </ul>
        </form>
    </jsp:body>

</templates:page_template>
