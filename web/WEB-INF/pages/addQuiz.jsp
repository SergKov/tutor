<%--
  Created by IntelliJ IDEA.
  User: koval
  Date: 22.01.2017
  Time: 19:05
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="templates" tagdir="/WEB-INF/tags/templates" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/WEB-INF/pages/setup/setupMessages.jsp" %>

<templates:page_template>

    <jsp:attribute name="header">
        <fmt:message key="addQuiz.title"/>
    </jsp:attribute>

    <jsp:body>
        <div class="row">
            <div class="well">
                <form action="/tutor/quizzes/addQuiz" method="POST" class="form-horizontal">
                    <input type="hidden" name="controller" value="addQuiz">
                    <div class="form-group">

                        <div class="col-xs-3">
                            <input type="text" class="form-control" id="quiz_id"
                                   value="${quizName}"
                                   name="quiz" placeholder="<fmt:message key="addQuiz.placeholder"/>" required>
                        </div>

                        <div class="col-xs-3">
                            <button type="submit" class="btn btn-primary btn-md">
                                <fmt:message key="addQuiz.add"/>
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </jsp:body>

</templates:page_template>
