<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


	<table class="offers">
	<tr><td>Name :</td><td>Email :</td> <td>Offer :</td></tr>
	<c:forEach var="offer" items="${offers}">
	<tr>
	<td><c:out value="${offer.user.name}"></c:out></td>
	<td><c:out value="${offer.user.email}"></c:out></td>
	<td><c:out value="${offer.text}"></c:out></td>
	</tr>
		
	</c:forEach>
	</table>
