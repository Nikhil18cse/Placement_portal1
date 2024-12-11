<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head><%@include file="css/style.jsp"%>

	<title>Placement Management Application</title>
</head>
<body>
	<center>
		<h1>Placement Management</h1>
        
	</center><a href="AdminDashboard.html"><button type="button"
			class="btn btn-primary">Dashboard</button></a>
    <div align="center" class="table">
        <table border="1" cellpadding="5" class="table-bodered">
       <h2>List of Colleges</h2>
            <tr>
                <th scope="col" >ID</th>
                <th scope="col" >CollegeAdmin</th>
                <th scope="col" >College name</th>
                <th scope="col" >Location</th>
                <th scope="col">Operations</th>
            </tr>
            <c:forEach var="college" items="${listCollege}">
                <tr>
                    <td><c:out value="${college.id}" /></td>
                    <td><c:out value="${college.collegedmin}" /></td>
                    <td><c:out value="${college.collegename}" /></td>
                    <td><c:out value="${college.location}" /></td>
                    <td>
                    	<a href="college?action=edit&id=<c:out value='${college.id}' />"><button type="button"
			class="btn btn-warning">Edit</button></a>
                    	&nbsp;&nbsp;&nbsp;&nbsp;
                    	<a href="college?action=delete&id=<c:out value='${college.id}' />"><button type="button"
			class="btn btn-danger">Delete</button></a>                    	
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>	<br>
    <h2 align="center">
        	<a href="college?action=new"><button type="button"
			class="btn btn-info">Add New College</button></a>
        	
        	
        </h2>
</body>
</html>
