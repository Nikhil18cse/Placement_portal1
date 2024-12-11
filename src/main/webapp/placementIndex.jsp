<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head><%@include file="css/style.jsp"%>

<title>Placement Management Application</title>
</head>
<body>
	<center>
		<h1>Placement Management</h1>
		<h2>
			 <a href="placement?action=placementlist"><button type="button"
			class="btn btn-primary">Display
				All Placements</button></a>

		</h2>
	</center><a href="AdminDashboard.html"><button type="button"
			class="btn btn-primary">Dashboard</button></a>
	<div align="center">
		<c:if test="${placement != null}">
			<form action="placement?action=placementupdate" method="post">
		</c:if>
		<c:if test="${placement == null}">
			<form action="placement?action=placementinsert" method="post">
		</c:if>
		<table border="1" cellpadding="5">
			
				<h2>
					<c:if test="${placement != null}">
            			Edit Placement
            		</c:if>
					<c:if test="${placement == null}">
            			Add New Placement
            		</c:if>
				</h2>
			
			<c:if test="${placement != null}">
				<input type="hidden" name="id" value="<c:out value='${placement.id}' />" />
			</c:if>
			<tr>
				<th>Placement Name:</th>
				<td><input type="text"  class="form-control" required name="name" size="45"
					value="<c:out value='${placement.name}' />" /></td>
			</tr>
			<tr>
				<th>Placement College:</th>
				<td><input type="text" class="form-control" required name="college" size="45"
					value="<c:out value='${placement.college}' />" /></td>
			</tr>
			<tr>
				<th>Placement Date:</th>
				<td><input type="text" class="form-control" required name="date" size="45"
					value="<c:out value='${placement.date}' />" /></td>
			</tr>
			<tr>
				<th>Placement Qualification:</th>
				<td><input type="text" class="form-control" required name="qualification" size="45"
					value="<c:out value='${placement.qualification}' />" /></td>
			</tr>
			<tr>
				<th>Placement Year:</th>
				<td><input type="text" class="form-control" required name="year" size="45"
					value="<c:out value='${placement.year}' />" /></td>
			</tr>

			<tr>
				<td colspan="2" align="center"><button type="submit" class="btn btn-success">Save</button></td>
			</tr>
		</table>
		</form>
	</div>
</body>
</html>
