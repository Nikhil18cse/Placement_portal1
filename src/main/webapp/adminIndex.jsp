<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<%@include file="css/style.jsp"%>
<title>Placement Management Application</title>
</head>
<body>
	<center>
		<h1>Placement Management</h1>
		<h2>
			<a href="Admin?action=Adminlist"><button type="button"
					class="btn btn-primary">Display All Admins</button></a>


		</h2>
	</center>
	<a href="AdminDashboard.html"><button type="button"
			class="btn btn-primary">Dashboard</button></a>
	<div align="center">
		<c:if test="${admin != null}">
			<form action="Admin?action=Adminupdate" method="post">
		</c:if>
		<c:if test="${admin == null}">
			<form action="Admin?action=Admininsert" method="post">
		</c:if>
		<table border="1" cellpadding="5">

			<h2>
				<c:if test="${admin != null}">
		            			Edit Admin
		            		</c:if>
				<c:if test="${admin == null}">
		            			Add New Admin
		            		</c:if>
			</h2>

			<c:if test="${admin != null}">
				<input type="hidden" name="id" value="<c:out value='${admin.id}' />" />
			</c:if>
			<tr>
				<th>Admin Name:</th>
				<td><input type="text" class="form-control" required
					name="name" size="45" value="<c:out value='${admin.name}' />" /></td>
			</tr>
			<tr>
				<th>Admin Password:</th>
				<td><input type="text" class="form-control" required
					name="password" size="45"
					value="<c:out value='${admin.password}' />" /></td>
			</tr>

			<tr>
				<td colspan="2" align="center">
					<button type="submit" class="btn btn-success">Save</button>
				</td>
			</tr>
		</table>
		</form>
		</form>
	</div>
</body>
</html>
