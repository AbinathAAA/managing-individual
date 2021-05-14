<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@page import="model.Project"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="Views/bootstrap.min.css">

<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/project.js"></script>
<title>Project Management</title>
</head>
<body>
<h1>Project Management</h1>

<form id="formItem" name="formItem" method="post" action="project.jsp">
  Name:
 <input id="Name" name="Name" type="text"
 class="form-control form-control-sm">
 <br>  Description:
 <input id="Description" name="Description" type="text"
 class="form-control form-control-sm">
 <br>  Language:
 <input id="Language" name="Language" type="text"
 class="form-control form-control-sm">
 <br>  Devname:
 <input id="Devname" name="Devname" type="text"
 class="form-control form-control-sm">

 <br>  Price:
 <input id="Price" name="Price" type="text"
 class="form-control form-control-sm">
 <br>
<br>
<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
<input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>


<br>
<div id="divItemsGrid">
<%

Project Obj = new Project();
 out.print(Obj.readProject());
%>
</div>
</body>
</html>