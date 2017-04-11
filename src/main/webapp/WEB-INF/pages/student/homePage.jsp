<%--
  Created by IntelliJ IDEA.
  User: koval
  Date: 19.01.2017
  Time: 19:28
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="templates" tagdir="/WEB-INF/tags/templates" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shared" tagdir="/WEB-INF/tags/shared" %>
<%@ include file="/WEB-INF/pages/setup/setupMessages.jsp" %>

<c:url value="/student" var="homePageAction"/>

<templates:page_template>

    <jsp:attribute name="header">
        <fmt:message key="studentHomePage.welcome"/> ${student.name} !
    </jsp:attribute>

    <jsp:body>
        <c:choose>
            <c:when test="${not empty quizzes}">
                <div class="row">
                    <h3 class="text-center"><fmt:message key="studentHomePage.chooseQuiz"/></h3>
                </div>

                <form action="${homePageAction}" method="POST" class="js-start-btn"
                      data-start-btn="<fmt:message key="homePage.ready.confirm"/>">
                    <input type="hidden" name="command" value="testStart"/>

                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th>
                                <fmt:message key="homePage.quiz"/>
                            </th>
                            <th>
                                <fmt:message key="homePage.author"/>
                            </th>
                        </tr>
                        </thead>

                        <tbody>
                        <c:forEach items="${quizzes}" var="quiz">
                            <tr>
                                <td>
                                        ${quiz.name}
                                </td>
                                <td>
                                        ${quiz.user.name}
                                </td>
                                <td>
                                    <div class="radio">
                                        <input type="radio" name="quiz-id" value="${quiz.id}">
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>

                    <div class="row">
                        <div class="col-xs-offset-5 col-xs-2">
                            <button class="btn btn-block btn-primary" id="start_btn">
                                <fmt:message key="studentHomePage.start"/>
                            </button>
                        </div>
                    </div>
                </form>

                <shared:pagination action="${homePageAction}"
                                   currentPage="${pagination.currentPage}"
                                   numberOfPages="${pagination.numberOfPages}"
                                   showElements="${pagination.showElements}"/>
            </c:when>
            <c:otherwise>
                <h1 class="text-muted text-center"><fmt:message key="studentHomePage.empty"/></h1>
                <h3 class="text-muted text-center"><fmt:message key="studentHomePage.tryLater"/></h3>
            </c:otherwise>
        </c:choose>
    </jsp:body>

</templates:page_template>
