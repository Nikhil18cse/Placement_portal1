<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head><%@include file="css/style.jsp"%>

<title>Placement Management Application</title>
</head>
<body>
	<center>
		<h1>Placement Management</h1>
	</center>
	<a href="AdminDashboard.html"><button type="button"
			class="btn btn-primary">Dashboard</button></a>
	<div align="center" class="table">
		<table border="1" cellpadding="5" class="table-bodered">
			<h2>List of Students</h2>
			<tr>
				<th scope="col">ID</th>
				<th scope="col">Name</th>
				<th scope="col">College</th>
				<th scope="col">Roll</th>
				<th scope="col">Qualification</th>
				<th scope="col">Course</th>
				<th scope="col">Year</th>
				<th scope="col">Certificate</th>
				<th scope="col">hallTicketNo</th>
				<th scope="col">Operations</th>
			</tr>
			<c:forEach var="student" items="${listStudent}">
				<tr>
					<td><c:out value="${student.id}" /></td>
					<td><c:out value="${student.name}" /></td>
					<td><c:out value="${student.college}" /></td>
					<td><c:out value="${student.roll}" /></td>
					<td><c:out value="${student.qualification}" /></td>
					<td><c:out value="${student.course}" /></td>
					<td><c:out value="${student.year}" /></td>
					<td><c:out value="${student.certificate}" /></td>
					<td><c:out value="${student.hallTicketNo}" /></td>
					<td><a
						href="student?action=studentedit&id=<c:out value='${student.id}' />"><button type="button"
			class="btn btn-warning">Edit</button></a>
						&nbsp;&nbsp;&nbsp;&nbsp; <a
						href="student?action=studentdelete&id=<c:out value='${student.id}' />"><button type="button"
			class="btn btn-danger">Delete</button></a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<br>
	<h2 align="center">
		<a href="student?action=studentnew"><button type="button"
				class="btn btn-info">Add New Student</button></a>


	</h2>
</body>
</html>
