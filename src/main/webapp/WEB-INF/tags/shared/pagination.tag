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
<%@ attribute name="showElements" type="java.lang.Integer" required="true" %>

<form action="${action}" method="GET" class="center">
    <ul class="pagination custom-pagination">
        <c:forEach var="i" begin="1" end="${numberOfPages}">
            <li>
                <button value="${i}" name="current-page" class="center"
                        <c:if test="${currentPage eq i}">disabled</c:if>>
                        ${i}
                </button>
            </li>
        </c:forEach>
    </ul>
</form>

<form action="${action}" method="GET" class="center">
    <select name="show-elements" class="js-show-elements">
        <option <c:if test="${showElements eq 5}">selected</c:if>>5</option>
        <option <c:if test="${showElements eq 10}">selected</c:if>>10</option>
        <option <c:if test="${showElements eq 15}">selected</c:if>>15</option>
        <option <c:if test="${showElements eq 20}">selected</c:if>>20</option>
        <option <c:if test="${showElements eq 25}">selected</c:if>>25</option>
    </select>
</form>
