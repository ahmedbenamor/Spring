<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<h2>Create new account</h2>
	<sf:form id="details" method="post"
		action="${pageContext.request.contextPath}/createaccount"
		commandName="user">
		<table class="formtable">
			<tr>
				<td class="label">Username :</td>

				<td><sf:input class="control" path="username" name="username"
						type="text" /><div class="error"> <sf:errors path="username"></sf:errors></div>
				</td>
			</tr>

<tr>
				<td class="label">name :</td>

				<td><sf:input class="control" path="name" name="name"
						type="text" /><div class="error"> <sf:errors path="name"></sf:errors></div>
				</td>
			</tr>

			<tr>
				<td class="label">email :</td>

				<td><sf:input class="control" path="email" name="email"
						type="text" /><div class="error"> <sf:errors path="email"></sf:errors></div>
				</td>
			</tr>

			<tr>
				<td class="label">Password :</td>

				<td><sf:input id="password" class="control" path="password" name="password"
						type="password" /> <div class="error"><sf:errors path="password"></sf:errors></div>
				</td>
			</tr>
		<tr>
				<td class="label">Confirm password :</td>

				<td><input class="control" name="confirmpass" id="confirmpass"
						type="password" />
						<div id="matchpass"></div>
				</td>
			</tr>
			<tr>
				<td class="label"></td>

				<td><input class="control" value="Create account" type="submit" /></td>
			</tr>
		</table>
	</sf:form>
