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
			 <a href="certificate?action=certificatelist"><button type="button"
			class="btn btn-primary">Display
				All Certificates</button></a>


		</h2>
	</center>
	<a href="UserDashboard.html"><button type="button"
			class="btn btn-primary">Dashboard</button></a>
	<div align="center">
		<c:if test="${certificate != null}">
			<form action="certificate?action=certificateupdate" method="post">
		</c:if>
		<c:if test="${certificate == null}">
			<form action="certificate?action=certificateinsert" method="post">
		</c:if>
		<table border="1" cellpadding="5">

			<h2>
				<c:if test="${certificate != null}">
            			Edit Certificate
            		</c:if>
				<c:if test="${certificate == null}">
            			Add New Certificate
            		</c:if>
			</h2>

			<c:if test="${certificate != null}">
				<input type="hidden" name="id"
					value="<c:out value='${certificate.id}' />" />
			</c:if>
			<tr>
				<th>Certificate Year:</th>
				<td><input type="text" class="form-control" required name="year" size="45"
					value="<c:out value='${certificate.year}' />" /></td>
			</tr>
			<tr>
				<th>Certificate College:</th>
				<td><input type="text" class="form-control" required name="college" size="45"
					value="<c:out value='${certificate.college}' />" /></td>
			</tr>


			<tr>
				<td colspan="2" align="center"><button type="submit" class="btn btn-success">Save</button></td>
			</tr>
		</table>
		</form>
	</div>
</body>
</html>
