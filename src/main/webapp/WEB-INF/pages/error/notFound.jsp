<%--
  Created by IntelliJ IDEA.
  User: koval
  Date: 16.01.2017
  Time: 23:13
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="templates" tagdir="/WEB-INF/tags/templates" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/WEB-INF/pages/setup/setupMessages.jsp" %>

<templates:error_template title="page.error.notFound">

    <jsp:attribute name="header">
        <h1 class="text-muted text-center"><fmt:message key="page.error.notFound" bundle="${lang}"/></h1>
    </jsp:attribute>

</templates:error_template>

