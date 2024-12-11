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
        <h2>
        	
        	<a href="student?action=studentlist"><button type="button"
			class="btn btn-primary">Display All Students</button></a>
        	
        </h2>
	</center><a href="AdminDashboard.html"><button type="button"
			class="btn btn-primary">Dashboard</button></a>
    <div align="center">
		<c:if test="${student != null}">
			<form action="student?action=studentupdate" method="post">
        </c:if>
        <c:if test="${student == null}">
			<form action="student?action=studentinsert" method="post">
        </c:if>
        <table border="1" cellpadding="5">

            	<h2>
            		<c:if test="${student != null}">
            			Edit Student
            		</c:if>
            		<c:if test="${student == null}">
            			Add New Student
            		</c:if>
            	</h2>
   
        		<c:if test="${student != null}">
        			<input type="hidden" name="id" value="<c:out value='${student.id}' />" />
        		</c:if>            
            <tr>
                <th>Student Name: </th>
                <td>
                	<input type="text" class="form-control" required name="name" size="45"
                			value="<c:out value='${student.name}' />"
                		/>
                </td>
            </tr>
            <tr>
                <th>Student College: </th>
                <td>
                	<input type="text" class="form-control" required name="college" size="45"
                			value="<c:out value='${student.college}' />"
                	/>
                </td>
            </tr>
            <tr>
                <th>Student Roll: </th>
                <td>
                	<input type="text" class="form-control" required name="roll" size="45"
                			value="<c:out value='${student.roll}' />"
                	/>
                </td>
            </tr>
            <tr>
                <th>Student Qualification: </th>
                <td>
                	<input type="text" class="form-control" required name="qualification" size="45"
                			value="<c:out value='${student.qualification}' />"
                	/>
                </td>
            </tr>
            <tr>
                <th>Student Course: </th>
                <td>
                	<input type="text" class="form-control" required name="course" size="45"
                			value="<c:out value='${student.course}' />"
                	/>
                </td>
            </tr>
            <tr>
                <th>Student Year: </th>
                <td>
                	<input type="text" class="form-control" required name="year" size="45"
                			value="<c:out value='${student.year}' />"
                	/>
                </td>
            </tr>
            <tr>
                <th>Student Certificate: </th>
                <td>
                	<input type="text" class="form-control" required name="certificate" size="45"
                			value="<c:out value='${student.certificate}' />"
                	/>
                </td>
            </tr>
            <tr>
                <th>Student Hallticket Number: </th>
                <td>
                	<input type="text" class="form-control" required name="hallTicketNo" size="15"
                			value="<c:out value='${student.hallTicketNo}' />"
                	/>
                </td>
            </tr>
            <tr>
            	<td colspan="2" align="center">
            		<button type="submit" class="btn btn-success">Save</button>
            	</td>
            </tr>
        </table>
        </form>
    </div>	
</body>
</html>
