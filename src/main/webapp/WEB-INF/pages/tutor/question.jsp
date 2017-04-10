<%--
  Created by IntelliJ IDEA.
  User: koval
  Date: 27.01.2017
  Time: 2:27
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="templates" tagdir="/WEB-INF/tags/templates" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/pages/setup/setupMessages.jsp" %>

<templates:page_template>

    <jsp:attribute name="header">
        <fmt:message key="Question" bundle="${lang}"/>
    </jsp:attribute>

    <jsp:body>

        <div class="row">
            <label for="text"><fmt:message key="addQuestion.text" bundle="${lang}"/></label>
        </div>

        <div class="row">
            <div class="form-group">
                <textarea class="form-control text-border" rows="3" id="text" disabled>
                        ${question.text}
                </textarea>
            </div>
        </div>

        <div class="row">
            <div class="col-xs-8">
                <span><fmt:message key="addQuestion.answer" bundle="${lang}"/></span>
            </div>

            <div class="col-xs-1 col-xs-offset-2">
                <span><fmt:message key="addQuestion.type" bundle="${lang}"/></span>
            </div>
        </div>

        <c:forEach items="${question.answers}" var="answer">
            <div class="row">
                <div class="col-xs-8">
                    <input class="form-control" value="${answer.text}" readonly/>
                </div>

                <div class="col-xs-2 col-xs-offset-2">
                    <input class="form-control" value="<fmt:message key="question.${answer.type}" bundle="${lang}"/>"
                           readonly/>
                </div>
            </div>
        </c:forEach>

        <div class="row">
            <div class="col-xs-3 col-xs-offset-10">
                <button class="btn btn-link btn-block" id="back_btn">
                    <fmt:message key="text.back" bundle="${lang}"/>
                </button>
            </div>
        </div>
    </jsp:body>

</templates:page_template>
