<%--
  Created by IntelliJ IDEA.
  User: koval
  Date: 03.02.2017
  Time: 22:21
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="templates" tagdir="/WEB-INF/tags/templates" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/WEB-INF/pages/setup/setupMessages.jsp" %>

<templates:page_template>

    <jsp:attribute name="header">
        <h1 class="text-muted text-center"><fmt:message key="serverError"/></h1>
    </jsp:attribute>

</templates:page_template>
