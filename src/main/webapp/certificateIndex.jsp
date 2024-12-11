<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
            <a href="certificate?action=certificatelist"><button type="button"
			class="btn btn-primary">Display
				All Certificates</button></a>
        </h2>
    </center>
    <a href="AdminDashboard.html">
        <button type="button" class="btn btn-primary">Dashboard</button>
    </a>
    <div align="center">
        <c:if test="${user != null}">
            <form action="user?action=uupdate" method="post">
        </c:if>
        <c:if test="${user == null}">
            <form action="user?action=uinsert" method="post">
        </c:if>
        <table class="table table-bordered" cellpadding="5">
   

        <!-- Certificate Form -->
        <h2>Add New Certificate</h2>
        <form action="certificate?action=certificateinsert" method="post" enctype="multipart/form-data">
            <table class="table table-bordered" cellpadding="5">
                <tr>
                    <th>Year:</th>
                    <td><input type="text" name="year" required class="form-control" /></td>
                </tr>
                <tr>
                    <th>College:</th>
                    <td><input type="text" name="college" required class="form-control" /></td>
                </tr>
                <tr>
                    <th>Upload File:</th>
                    <td><input type="file" name="file" accept=".pdf,.png" required class="form-control" /></td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <button type="submit" class="btn btn-success">Submit</button>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</body>
</html>
