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
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/WEB-INF/pages/setup/setupMessages.jsp" %>

<c:url value="/tutor/quizzes/questions/add" var="addQuestionAction"/>
<c:url value="/resource/img/plus.ico" var="plusAnswerIcon"/>
<c:url value="/resource/img/minus.ico" var="minusAnswerIcon"/>

<c:set value="text" var="text"/>

<templates:page_template>

    <jsp:attribute name="header">
        <fmt:message key="addQuestion.title"/>
    </jsp:attribute>

    <jsp:body>
        <form action="${addQuestionAction}" method="POST" class="form-horizontal">
            <input type="hidden" name="command" value="questionAdd"/>

            <div class="row">
                <label for="${text}"><fmt:message key="addQuestion.text"/></label>
            </div>

            <div class="row">
                <c:choose>
                    <c:when test="${empty errorMsgs['text']}">
                        <div class="form-group">
                            <textarea class="form-control text-border" rows="3" id="${text}" name="questionText"
                                      required>${fn:escapeXml(question.text)}</textarea>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="form-group has-error has-feedback">
                            <textarea class="form-control text-border" rows="3" id="${text}" name="questionText"
                                      required>${fn:escapeXml(question.text)}</textarea>

                            <div class="col-xs-12 center">
                                <p class="text-danger">${errorMsgs['text']}</p>
                            </div>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>

            <div class="row">
                <div class="col-xs-8">
                    <span><fmt:message key="addQuestion.answer"/></span>
                </div>
                <div class="col-xs-1 col-xs-offset-2">
                    <span><fmt:message key="addQuestion.type"/></span>
                </div>
            </div>

            <div class="row answers">
                <div class="answer clearfix">
                    <div class="col-xs-8">
                        <input type="text" class="form-control" id="answer" name="answerText[]"
                               value="<c:out value="" escapeXml="false"/>" required/>

                        <div class="color-xs-12 center">
                            <p class="text-danger">${errorMsgs['answersText']}</p>
                        </div>
                    </div>

                    <div class="col-xs-2 col-xs-offset-2">
                        <select id="answerType" name="answerType[]" class="form-control" required>
                            <c:forEach items="${answerTypes}" var="type">
                                <option value="${type}">
                                    <fmt:message key="addQuestion.${type}"/>
                                </option>
                            </c:forEach>
                        </select>

                        <div class="color-xs-12 center">
                            <p class="text-danger">${errorMsgs['answersType']}</p>
                        </div>
                    </div>
                </div>
            </div>

            <div class="answers-manipulate">
                <img id="plusAnswer" src="${plusAnswerIcon}" width="25px" height="25px"
                     class="answers-manipulate__elem"/>
                <img id="minusAnswer" src="${minusAnswerIcon}" width="25px" height="25px"
                     class="answers-manipulate__elem"/>

                <button type="submit" class="btn btn-primary btn-md answers-manipulate__elem">
                    <fmt:message key="addQuestion.add"/>
                </button>
            </div>
        </form>
    </jsp:body>

</templates:page_template>
