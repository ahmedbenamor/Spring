<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<sf:form method="post" action="${pageContext.request.contextPath}/docreate" commandName="offer">
<script type="text/javascript">
function ondelete(event){
	
	var doDelete = confirm("Are you sure you want to delete this offer ? ");
	if(doDelete == false)
		{
		event.preventDefault();
		}
}
function onready()
{
	
	$("#delete").click(ondelete);
	
	}
$(document).ready(onready);
</script>
<sf:input path="id" name="id" type="hidden"/>
	<table class="formtable">
		

		<tr>
			<td class="label">Your offer :</td>

			<td><sf:textarea class="control" path="text" name="text"
					rows="10" cols="10"></sf:textarea> <sf:errors path="text"
					cssClass="error"></sf:errors></td>
		</tr>
		<tr>
			<td class="label"></td>

			<td><input class="control" value="Save offer" type="submit" /></td>
			
		</tr>
		<c:if test="${offer.id != 0}">
		<tr>
			<td class="label"></td>

			<td>&nbsp;</td>
			
		</tr>
		
		<tr>
			<td class="label"></td>

			<td><input class="control delete" id="delete" name="delete" value="Delete offer" type="submit" /></td>
			
		</tr>
		</c:if>
	</table>
	
	</sf:form>