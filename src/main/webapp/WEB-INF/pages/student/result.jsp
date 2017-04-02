<%--
  Created by IntelliJ IDEA.
  User: koval
  Date: 05.02.2017
  Time: 22:43
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="templates" tagdir="/WEB-INF/tags/templates" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/pages/setup/setupMessages.jsp" %>

<templates:page_template>

    <jsp:attribute name="header">
        <h1 class="text-primary text-center"><fmt:message key="result.final" bundle="${lang}"/> ${mark}</h1>
    </jsp:attribute>

</templates:page_template>
