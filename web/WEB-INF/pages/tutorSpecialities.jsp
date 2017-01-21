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
    <fmt:message key="tutorHomePage.hello"/> ${worker.name} !
</jsp:attribute>

    <jsp:body>
        <c:if test="${not empty specialities}">
            <c:forEach items="${specialities}" var="speciality">
                <form action="/tutor/specialities" method="GET" class="form-horizontal">
                    <input type="hidden" name="specialityId" value="${speciality.id}">

                    <div class="row">
                        <div class="col-xs-6 col-xs-offset-2">
                            <div class="form-group">
                                <input id="speciality_name" type="text" class="form-control"
                                       name="specialityName"
                                       value="${speciality.name}">
                            </div>
                        </div>

                        <div class="col-xs-1">
                            <input id="speciality_questions" type="image" src="/resource/img/see.ico"
                                   width="25px"
                                   height="25px"
                                   name="controller" value="specialityQuestions">
                        </div>
                    </div>
                </form>
            </c:forEach>
        </c:if>

        <div class="row">
            <a href="/tutor/specialities" name="addSpeciality" class="col-xs-1 col-xs-offset-10">
                <img src="/resource/img/plus.ico" width="25px" height="25px">
            </a>
        </div>
    </jsp:body>

</templates:page_template>
