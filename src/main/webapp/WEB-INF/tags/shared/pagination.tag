<%--
  Created by IntelliJ IDEA.
  User: koval
  Date: 02.04.2017
  Time: 0:39
  To change this template use File | Settings | File Templates.
--%>
<%@ tag description="Overall page template" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="action" type="java.lang.String" required="true" %>
<%@ attribute name="numberOfPages" type="java.lang.Integer" required="true" %>
<%@ attribute name="currentPage" type="java.lang.Integer" required="true" %>
<%@ attribute name="numberOfElements" type="java.lang.Integer" required="true" %>

<form action="${action}" method="GET">

    <input type="hidden" value="${currentPage}"/>

    <select name="show-elements" class="js-show-elements" onchange="submit">
        <option>5</option>
        <option>10</option>
        <option>15</option>
        <option>20</option>
        <option>25</option>
    </select>

    <ul class="pagination">
        <c:forEach var="i" begin="1" end="${numberOfPages}">
            <li>
                <button value="${i}" name="currentPage" class="center"
                        <c:if test="${currentPage eq i}">disabled</c:if>>
                        ${i}
                </button>
            </li>
        </c:forEach>
    </ul>
</form>
