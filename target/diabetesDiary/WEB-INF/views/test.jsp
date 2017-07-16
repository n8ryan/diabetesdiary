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
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Test</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.min.css">
<body>
	<header style="background-color: #DAF7A6;">
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
							<li class="active"><a href="#">Look-Ups</a></li>
							<li><a href="index">Home</a></li>
							<li><a href="about">About Us</a></li>
							<li><a href="contact">Contact Us</a></li>
							<li><a href="signups">Sign up</a></li>
							<li><a href="login">login</a></li>
							<li role="presentation" class="small">
								<form class="navbar-form navbar-right" role="search"
									action="lookupArtist" method="post">
									<div class="input-group add-on">
										<input type="text" class="form-control"
											placeholder="Search Members" name="lookupArtist"
											id="lookupArtist" SIZE='13'>
										<div class="input-group-btn">
											<button class="btn btn-default" type="submit">
												<i class="glyphicon glyphicon-search"></i>
											</button>
										</div>
									</div>
								</form>
							</li>

							<li class="pull-right">
								<form class="navbar-form navbar-right" role="search"
									action="lookupcity" method="post">
									<div class="input-group add-on">
										<input type="text" class="form-control"
											placeholder="Find Members By City or Zip" name="lookupcity"
											id="Lookupcity" SIZE='22'> <input type="hidden"
											name="id" value="-1">
										<div class="input-group-btn">
											<button class="btn btn-default" type="submit">
												<i class="glyphicon glyphicon-search"
													title="Lookup Members by City or Zip"></i>
											</button>
										</div>
									</div>
								</form>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>

	</header>
	<div class="container">

		<div>
			<h1 style="color: #008000">Welcome to wellness club</h1>
			<h3 style="color: #008000">${msg}</h3>
			<h2 style="color: #008000">${message}</h2>
			<h2 style="color: #008000">${success}</h2>
			<h2 style="color: #FF0000">${error}</h2>

		</div>
		<div></div>
	</div>



	<div class="col-lg-6">
		<form id="test" action="testpost" method="post"
			class="bs-example form-horizontal">
			<fieldset>
				<legend>wellness club login </legend>
				<br>

				<div class="form-group">
					<label for="email" class="col-lg-3 control-label">enter
						your details?</label>
					<div class="col-lg-9">
						<input type="text" class="form-control" name="emailAddress"
							id="emailAddress" placeholder="what is my email" /> <input
							type="text" class="form-control" name="fname" id="fname"
							placeholder="what is my name" />
					</div>
				</div>



				<div class="col-lg-9 col-lg-offset-3">
					<button class="btn btn-primary">Submit</button>
					<a href="index" class="btn btn-primary" role="button">Cancel</a>
				</div>
			</fieldset>
		</form>
	</div>
	<!-- 	@SuppressWarnings("rawtypes") -->
	<!-- 	public List<UserLogin> lookupCity(String cityOrZip) { -->
	<!-- 		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);  -->

	<!--        	final String sql  = "SELECT m.* FROM wellness.users m WHERE m.city like '%"+ cityOrZip +"%'" -->
	<!--        			+ " UNION " -->
	<!--        			+ "SELECT m.* FROM wellness.users m WHERE m.zip like '%"+ cityOrZip +"%'"; -->

	<!--    		List<UserLogin> memberList = jdbcTemplate.query(sql, new BeanPropertyRowMapper( UserLogin.class ));						 -->
	<!--    		return memberList; -->

	<!-- 	} -->
</body>
<footer
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

</footer>
</html>