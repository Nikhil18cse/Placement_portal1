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
			<a href="college?action=list"><button type="button"
					class="btn btn-primary">Display All Colleges</button></a>

		</h2>
	</center>
	<a href="AdminDashboard.html"><button type="button"
			class="btn btn-primary">Dashboard</button></a>
	<div align="center">
		<c:if test="${college != null}">
			<form action="college?action=update" method="post">
		</c:if>
		<c:if test="${college == null}">
			<form action="college?action=insert" method="post">
		</c:if>
		<table border="1" cellpadding="5">
		
				<h2>
					<c:if test="${college != null}">
            			Edit College
            		</c:if>
					<c:if test="${college == null}">
            			Add New College
            		</c:if>
				</h2>
		
			<c:if test="${college != null}">
				<input type="hidden" name="id"
					value="<c:out value='${college.id}' />" />
			</c:if>
			<tr>
				<th>College Name:</th>
				<td><input type="text" class="form-control" required
					name="collegedmin" size="45"
					value="<c:out value='${college.collegedmin}' />" /></td>
			</tr>
			<tr>
				<th>Collegename:</th>
				<td><input type="text" class="form-control" required
					name="collegename" size="45"
					value="<c:out value='${college.collegename}' />" /></td>
			</tr>
			<tr>
				<th>College Location:</th>
				<td><input type="text" class="form-control" required
					name="location" size="45"
					value="<c:out value='${college.location}' />" /></td>
			</tr>

			<tr>
				<td colspan="2" align="center"><button type="submit"
						class="btn btn-success">Save</button></td>
			</tr>
		</table>
		</form>
	</div>
</body>
</html>
