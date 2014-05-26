<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>


<table class="offers">
	<tr>
		<td>Name :</td>
		<td>Email :</td>
		<td>Offer :</td>
	</tr>
	<c:forEach var="offer" items="${offers}">
		<tr>
			<td><c:out value="${offer.user.name}"></c:out></td>
			<td><c:out value="${offer.user.email}"></c:out></td>
			<td><c:out value="${offer.text}"></c:out></td>
		</tr>

	</c:forEach>
</table>

<p></p>
<c:choose>
<c:when test="${hasOffers}">
<p>
	<a href="${pageContext.request.contextPath}/createoffer">Edit or delete your current Offer </a>
</p>
</c:when>
<c:otherwise>
<p>
	<a href="${pageContext.request.contextPath}/createoffer">Create a
		new Offer </a>
</p>
</c:otherwise>
</c:choose>





<sec:authorize access="hasRole('ROLE_ADMIN')">
	<p>
		<a href="<c:url value='/admin'/>">Admin </a>
	</p>
</sec:authorize>
