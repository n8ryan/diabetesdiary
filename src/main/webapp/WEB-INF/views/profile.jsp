<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.text.NumberFormat"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="java.io.*,java.util.*"%>
<%
	java.text.DateFormat df = new java.text.SimpleDateFormat("dd/MM/yyyy");
%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Title</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.min.css">
<!-- <script>
    window.history.forward();
    </script> 
     -->
<style>
	.orange {
    color: white;
    }
</style>
</head>
<body>
	<header style="background-color: #f4511e;">
		<div class="navigation">
			<div class="container">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target=".navbar-collapse.collapse">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
				</div>

				<div class="navbar-collapse collapse">
					<div class="menu">
						<ul class="nav nav-tabs" role="tablist">
							<li class="active"><a href="#">Profile</a></li>
							<li>
							<li role="presentation" class="pull-right">
								<form class="navbar-form navbar-right" role="search"
									action="LookupMembers" method="post">
									<div class="input-group add-on">
										<input type="hidden" name="id" value="${attribs.id}" /> <input
											type="text" class="form-control"
											placeholder="Find Members by Name" name="LookupMembers"
											id="LookupMembers" SIZE='20'>
										<div class="input-group-btn">
											<button class="btn btn-default" type="submit">
												<i class="glyphicon glyphicon-search"></i>
											</button>
										</div>
									</div>
								</form>
							</li>

							<li class="pull-right orange"><a href="logout" id="logout">${attribs.firstName}
									| Logout</a></li>


						</ul>
					</div>
				</div>
			</div>
		</div>
	</header>

	<div class="container">


		<form id="viewCharts" method="post" action="viewCharts">
			<input type="hidden" name="id" value="${attribs.id}">
		</form>

		<form id="statEntry" method="post" action="statEntry">
			<input type="hidden" name="id" value="${attribs.id}">
		</form>

		<form id="userEdits" method="post" action="userEdits">
			<input type="hidden" name="id" value="${attribs.id}">
		</form>

		<div>
			<h1 style="color: #000">Welcome to Diabetes Daily</h1>

			<%-- <p style="color: #008000">${msg}</p>
			<h2 style="color: #008000">${message}</h2>
			<h2 style="color: #008000">${success}</h2>
			<h2 style="color: #FF0000">${error}</h2>
			<h2 style="color: #008000">${email}</h2>
			<h2 style="color: #008000">${myname}</h2> --%>

		</div>



		<div class="row">
			<div class="col-md-6">
				<table class="table table-hover table-bordered">
					<tr style="background-color: #f4511e;">
						<td>Member</td>
						<td><span class="fa fa-calendar"
							style="font-size: 18px; color: white; padding-top: 2px;">
								Details <%=df.format(new java.util.Date())%>
						</span></td>
					</tr>
					<tr>
						<td>Name</td>
						<td><span style="color: red" class="glyphicon glyphicon-user"></span>
							<c:out value="${attribs.firstName}" /> <c:out
								value="${attribs.lastName}" /></td>
					</tr>
					<tr>
						<td>Contact Info</td>
						<td><span style="color: red" class="fa fa-phone fa"></span> <c:out
								value="${attribs.phone}" /> <span style="color: red"
							class="fa fa-envelope fa"></span> <c:out value="${attribs.email}" /></td>
					</tr>

					<tr>
						<td>Location</td>
						<td><span style="color: red" class="glyphicon glyphicon-home"></span>
							<c:out value="${attribs.city}" /> <c:out
								value="${attribs.state}" />
					</tr>
				</table>
			</div>


		</div>

		<a class="btn btn-danger" id="edata"
			onclick="document.forms['userEdits'].submit()">Update Profile </a>
			
		<a class="btn btn-danger" id="stats"
			onclick="document.forms['statEntry'].submit()">Stats Entry</a>
			
		<a class="btn btn-danger" id="charts"
			onclick="document.forms['viewCharts'].submit()">View Charts</a>
		

	</div>
	<br>
	<div id="show" class="collapse">
		<%@include file="getpswd.jsp"%>
	</div>
</body>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

<!-- <footer
	style="background-color: #ff6600; position: fixed; bottom: 0; width: 100%;">
	<div class="col-md-5 col-md-offset-5">
		<div class="menu">
			<ul class="nav nav-tabs" role="tablist">
				<li><a href="index">Home</a></li>
				<li><a href="about">About Us</a></li>
				<li><a href="contact">Contact Us</a></li>
				<li><a href="signups">Sign up</a></li>
				<li><a href="login">login</a></li>
				<li><a href="services">Look-Ups</a></li>

			</ul>

		</div>
	</div>

</footer> -->

</html>
