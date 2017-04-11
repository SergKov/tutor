<%--
  Created by IntelliJ IDEA.
  User: koval
  Date: 10.04.2017
  Time: 1:37
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="templates" tagdir="/WEB-INF/tags/templates" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/pages/setup/setupMessages.jsp" %>

<c:url var="resultsAction" value="/student/results"/>

<templates:page_template>

    <jsp:body>
        <c:choose>
            <c:when test="${not empty results}">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th>
                            <fmt:message key="results.quiz.name"/>
                        </th>
                        <th>
                            <fmt:message key="results.quiz.author"/>
                        </th>
                        <th>
                            <fmt:message key="results.mark"/>
                        </th>
                        <th>
                            <fmt:message key="results.date"/>
                        </th>
                    </tr>
                    </thead>

                    <tbody>
                    <c:forEach items="${results}" var="result">
                        <tr>
                            <td>
                                    <c:out value="${result.quiz.name}"/>
                            </td>
                            <td>
                                    <c:out value="${result.quiz.user.name}"/>
                            </td>
                            <td>
                                    <c:out value="${result.mark}"/>
                            </td>
                            <td>
                                    <c:out value="${result.creationDateTime}"/>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

                <shared:pagination action="${resultsAction}"
                                   currentPage="${pagination.currentPage}"
                                   numberOfPages="${pagination.numberOfPages}"
                                   showElements="${pagination.showElements}"/>

                <button class="btn btn-link btn-block" id="back_btn">
                    <fmt:message key="signUp.back"/>
                </button>
            </c:when>
            <c:otherwise>
                <h1 class="text-muted text-center"><fmt:message key="results.empty"/></h1>
            </c:otherwise>
        </c:choose>
    </jsp:body>

</templates:page_template>
