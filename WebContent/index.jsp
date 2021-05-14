
<%@page import="com.Project"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
 
<link rel="stylesheet" href="Views/bootstrap.min.css">
<link rel="stylesheet" href="Views/bootstrap.min.js">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/jquery-3.2.1.js"></script>
<script src="Components/Projects.js"></script>
<meta charset="ISO-8859-1">
<title>Project service</title>
</head>
<body>
	
<div class="container">
<div class="row">
	
	<div class="col-8">
	<form id="formItem" name="formItem">
	
			 name: 
			<input id="name" name="name" type="text" 
			 class="form-control form-control-sm">
			 
			<br> cost: 
			<input id="cost" name="cost" type="text" 
			 class="form-control form-control-sm">
			 
			<br> duration: 
			<input id="duration" name="duration" type="text" 
			 class="form-control form-control-sm">
			 
			<br> author: 
			<input id="author" name="author" type="text" 
			 class="form-control form-control-sm">
			<br>
			
			<input id="btnSave" name="btnSave" type="button" value="Save" 
			 class="btn btn-primary">
			<input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value="">
			</form>
			<div id="alertSuccess" class="alert alert-success"></div>
			<div id="alertError" class="alert alert-danger"></div>
			
			<br>
			<div id="divItemsGrid">
			<%
				Project itemObj = new Project ();
				out.print(itemObj.readProject());
			%>	
			</div>
			
			
			
			
	
		</div>

	</div>

</div>
	
</body>
</html>