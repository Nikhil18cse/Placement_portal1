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
        <h2>List of Placements</h2>
            <tr>
                <th scope="col">ID</th>
                <th scope="col">Name</th>
                <th scope="col">college</th>
                <th scope="col">Date</th>
                <th scope="col">Qualification</th>
                <th scope="col">Year</th>
                <th scope="col">Operations</th>
                
            </tr>
            <c:forEach var="placement" items="${listPlacement}">
                <tr>
                    <td><c:out value="${placement.id}" /></td>
                    <td><c:out value="${placement.name}" /></td>
                    <td><c:out value="${placement.college}" /></td>
                    <td><c:out value="${placement.date}" /></td>
                    <td><c:out value="${placement.qualification}" /></td>
                    <td><c:out value="${placement.year}" /></td>
                    <td>
                    	<a href="placement?action=placementedit&id=<c:out value='${placement.id}' />"><button type="button"
			class="btn btn-warning">Edit</button></a>
                    	&nbsp;&nbsp;&nbsp;&nbsp;
                    	<a href="placement?action=placementdelete&id=<c:out value='${placement.id}' />"><button type="button"
			class="btn btn-danger">Delete</button></a>                    	
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>	
    <br>
        <h2 align="center">
        	<a href="placement?action=placementnew"><button type="button"
			class="btn btn-info">Add New Placement</button></a>
        	
        	
        </h2>
</body>
</html>
