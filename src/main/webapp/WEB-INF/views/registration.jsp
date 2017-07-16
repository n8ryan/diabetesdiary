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
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Sign Up</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.min.css">
<script>
	
</script>

<style>
/* Tooltip container */
.tooltip {
    position: relative;
    display: inline-block;
    border-bottom: 1px dotted black; /* If you want dots under the hoverable text */
}

/* Tooltip text */
.tooltip .tooltiptext {
    visibility: hidden;
    width: 120px;
    background-color: black;
    color: #fff;
    text-align: center;
    padding: 5px 0;
    border-radius: 6px;
 
    /* Position the tooltip text - see examples below! */
    position: absolute;
    z-index: 1;
}

/* Show the tooltip text when you mouse over the tooltip container */
.tooltip:hover .tooltiptext {
    visibility: visible;
}
  body {
      font: 400 15px Lato, sans-serif;
      line-height: 1.8;
      color: #818181;
  }
  h2 {
      font-size: 24px;
      text-transform: uppercase;
      color: #303030;
      font-weight: 600;
      margin-bottom: 30px;
  }
  h4 {
      font-size: 19px;
      line-height: 1.375em;
      color: #303030;
      font-weight: 400;
      margin-bottom: 30px;
  }  
  
  .center {
  	text-align: center;
  }  
  
  .right {
  	text-align: right;
  } 
   
  .jumbotron {
      background-color: #f4511e;
      color: #fff;
      font-family: Montserrat, sans-serif;
  }
  .container-fluid {
      padding: 60px 50px;
  }
  .bg-grey {
      background-color: #f6f6f6;
  }
  .logo-small {
      color: #f4511e;
      font-size: 50px;
  }
  .logo {
      color: #f4511e;
      font-size: 300px;
  }
  .thumbnail {
      padding: 0 0 15px 0;
      border: none;
      border-radius: 0;
  }
  .thumbnail img {
      width: 100%;
      height: 100%;
      margin-bottom: 10px;
  }
  .carousel-control.right, .carousel-control.left {
      background-image: none;
      color: #f4511e;
  }
  .carousel-indicators li {
      border-color: #f4511e;
  }
  .carousel-indicators li.active {
      background-color: #f4511e;
  }
  .item h4 {
      font-size: 19px;
      line-height: 1.375em;
      font-weight: 400;
      font-style: italic;
      margin: 70px 0;
  }
  .item span {
      font-style: normal;
  }
  .panel {
/*       border: 1px solid #f4511e;  */
/*       border-radius:0 !important; */
	  margin: auto;
      transition: box-shadow 0.5s;
  }
  .panel:hover {
      box-shadow: 5px 0px 40px rgba(0,0,0, .2);
  }
  .panel-footer .btn:hover {
      border: 1px solid #f4511e;
      background-color: #fff !important;
      color: #f4511e;
  }
  .panel-heading {
      color: #fff !important;
      background-color: #f4511e !important;
      padding: 25px;
      border-bottom: 1px solid transparent;
      border-top-left-radius: 0px;
      border-top-right-radius: 0px;
      border-bottom-left-radius: 0px;
      border-bottom-right-radius: 0px;
  }
  .panel-footer {
      background-color: white !important;
  }
  .panel-footer h3 {
      font-size: 32px;
  }
  .panel-footer h4 {
      color: #aaa;
      font-size: 14px;
  }
  .panel-footer .btn {
      margin: 15px 0;
      background-color: #f4511e;
      color: #fff;
  }
  .navbar {
      margin-bottom: 0;
      background-color: #f4511e;
      z-index: 9999;
      border: 0;
      font-size: 12px !important;
      line-height: 1.42857143 !important;
      letter-spacing: 4px;
      border-radius: 0;
      font-family: Montserrat, sans-serif;
  }
  .navbar li a, .navbar .navbar-brand {
      color: #fff !important;
  }
  .navbar-nav li a:hover, .navbar-nav li.active a {
      color: #f4511e !important;
      background-color: #fff !important;
  }
  .navbar-default .navbar-toggle {
      border-color: transparent;
      color: #fff !important;
  }
  footer .glyphicon {
      font-size: 20px;
      margin-bottom: 20px;
      color: #f4511e;
  }
  .slideanim {visibility:hidden;}
  .slide {
      animation-name: slide;
      -webkit-animation-name: slide;
      animation-duration: 1s;
      -webkit-animation-duration: 1s;
      visibility: visible;
  }
	  /* Tooltip container */
	.tooltip {
	    position: relative;
	    display: inline-block;
	    border-bottom: 1px dotted black; /* If you want dots under the hoverable text */
	}
	
	/* Tooltip text */
	.tooltip .tooltiptext {
	    visibility: hidden;
	    width: 120px;
	    background-color: black;
	    color: #fff;
	    text-align: center;
	    padding: 5px 0;
	    border-radius: 6px;
	 
	    /* Position the tooltip text - see examples below! */
	    position: absolute;
	    z-index: 1;
	}
	
	/* Show the tooltip text when you mouse over the tooltip container */
	.tooltip:hover .tooltiptext {
	    visibility: visible;
	}
  @keyframes slide {
    0% {
      opacity: 0;
      transform: translateY(70%);
    } 
    100% {
      opacity: 1;
      transform: translateY(0%);
    }
  }
  @-webkit-keyframes slide {
    0% {
      opacity: 0;
      -webkit-transform: translateY(70%);
    } 
    100% {
      opacity: 1;
      -webkit-transform: translateY(0%);
    }
  }
  @media screen and (max-width: 768px) {
    .col-sm-4 {
      text-align: center;
      margin: 25px 0;
    }
    .btn-lg {
        width: 100%;
        margin-bottom: 35px;
    }
  }
  @media screen and (max-width: 480px) {
    .logo {
        font-size: 150px;
    }
  }
  
</style>
</head>
<body>
<nav class="navbar navbar-default navbar-fixed-top">
  <div class="container">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav navbar-right">
							<li><a href="index">HOME</a></li>
<!-- 							<li><a href="about">About Us</a></li> -->
<!-- 							<li><a href="contact">Contact Us</a></li> -->
<!-- 							<li class="active"><a href="signups">Sign up</a></li> -->
<!-- 							<li><a href="services">Look-Ups</a></li> -->
<!-- 							<li><a href="login">login</a></li> -->
      </ul>
    </div>
  </div>
</nav>


		<div class="jumbotron text-center">
  			<h1>Registration</h1> 
		</div>


	<form:form method="POST" modelAttribute="registration"
		class="col-md-12 col-md-offset-1">
		
		<!-- first row  -->
		<div class="row"> 
			<div class="col-md-3" title="Also used as your &#13;username at sign-in.">
				<spring:bind path="email">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<label for="email" id='email'> Email Address*</label>
						<form:input type="text" path="email" class="form-control"
							placeholder="*Email Address" autofocus="true"></form:input>
						<form:errors class="text-danger" path="email"></form:errors>
					</div>
				</spring:bind>
			</div>
			
			<div class="col-md-3">
				<spring:bind path="password">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<label for="password" id='password'> Password*</label>
						<form:input type="text" path="password" class="form-control"
							placeholder="*Password" autofocus="true"></form:input>
						<form:errors class="text-danger" path="password"></form:errors>
					</div>
				</spring:bind>
			</div>

			<div class="col-md-3">
				<spring:bind path="passwordConfirm">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<label for="passwordConfirm" id='passwordConfirm'>Confirm Password*</label>
						<form:input type="text" path="passwordConfirm"
							class="form-control" placeholder="*Confirm Password "
							autofocus="true"></form:input>
						<form:errors class="text-danger" path="passwordConfirm"></form:errors>
					</div>
				</spring:bind>
			</div>
		</div>
		<!-- end row   -->
		
		<!-- 2nd row -->
		<div class="row">
			<div class="col-md-3">
				<spring:bind path="firstName">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<label for="firstName" id='firstName'>First Name*</label>
						<form:input type="text" path="firstName" 
							class="form-control" placeholder="First Name" 
							autofocus="true"></form:input>
						<form:errors class="text-danger" path="firstName"></form:errors>
					</div>
				</spring:bind>
			</div>

			<div class="col-md-3">
				<spring:bind path="lastName">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<label for="lastName" id='lastName'>Last Name*</label>
						<form:input type="text" path="lastName" 
							class="form-control" placeholder="Last Name" 
							autofocus="true"></form:input>
						<form:errors class="text-danger" path="lastName"></form:errors>
					</div>
				</spring:bind>
			</div>

		
			<div class="col-md-1" title="Location data is used &#13;for mapping and stats.">
				<spring:bind path="city">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<label for="city" id='city'> City*</label>
						<form:input type="text" path="city" class="form-control"
							placeholder="*Enter City" autofocus="true"></form:input>
						<form:errors class="text-danger" path="city"></form:errors>
					</div>
				</spring:bind>
			</div>

			<div class="col-md-1" title="Location data is used &#13;for mapping and stats.">
				<spring:bind path="state">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<label for="state" id='state'> *State</label>
						<form:select path="state" name="state"
							class="form-control input-sm">
							<option value="">Select State</option>
							<c:forEach items="${states}" var="state">
								<option value="${state}">${state.id}</option>
							</c:forEach>
						</form:select>
						<form:errors class="text-danger" path="state"></form:errors>
					</div>
				</spring:bind>
			</div>

			<div class="col-md-1" title="Only used for texting &#13;reminders and advice">
				<spring:bind path="phone">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<label for="phone" id='phone'> Telephone*</label>
						<form:input type="text" path="phone" class="form-control"
							placeholder="Telephone" autofocus="true"></form:input>
						<form:errors class="text-danger" path="phone"></form:errors>
					</div>
				</spring:bind>
			</div>

		</div>
		<!-- end row   -->
		
		<!-- third row -->
		<div class="row">
			<div class="col-md-3" title="Your id for forum posts &#13;chatrooms, articles, etc.">
				<spring:bind path="userName">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<label for="userName" id='userName'>Nickname*</label>
						<form:input type="text" path="userName" class="form-control"
							placeholder="Nickname" autofocus="true" tooltip="texttip1"></form:input>
						<form:errors class="text-danger" path="userName"></form:errors>
					</div>
				</spring:bind>
			</div>

			<div class="col-md-1" title="Used for calculating &#13;health risks and &#13;optimum goals">
				<spring:bind path="age">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<label for="age" id='age'> Age*</label>
						<form:input type="text" path="age" class="form-control"
							placeholder="*Age" autofocus="true"></form:input>
						<form:errors class="text-danger" path="age"></form:errors>
					</div>
				</spring:bind>
			</div>
			
			<div class="col-md-1" title="Used for calculating &#13;health risks and &#13;optimum goals">
				<spring:bind path="height">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<label for="height" id='height'> Height*</label>
						<form:input type="text" path="height" class="form-control"
							placeholder="height" autofocus="true"></form:input>
						<form:errors class="text-danger" path="height"></form:errors>
					</div>
				</spring:bind>
			</div>
			
			<div class="col-md-1" title="Used for calculating &#13;health risks and &#13;optimum goals">
				<spring:bind path="sex">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<label for="sex" id='sex'> Sex*</label>
						<form:input type="text" path="sex" class="form-control"
							placeholder="sex" autofocus="true"></form:input>
						<form:errors class="text-danger" path="sex"></form:errors>
					</div>
				</spring:bind>
			</div>
			
			<div class="col-md-1 right" title="Check none, either, or both.">
				<br>Would you like to receive:
			</div>
			
			<div class="col-md-1" title="Check if you would like &#13;to receive advice &#13; and reminder texts">
				<spring:bind path="receivetexts">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<label for="texts" id='texts'>Texts</label>
						<form:checkbox path="receivetexts" class="form-control input-sm"
							placeholder="texts" autofocus="true"></form:checkbox>
						<form:errors class="text-danger" path="receivetexts"></form:errors>
					</div>
				</spring:bind>
			</div>

			<div class="col-md-1" title="Check if you would like &#13;to receive advice &#13; and reminder emails">
				<spring:bind path="receiveemails">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<label for="emails" id='emails'>Emails</label>
						<form:checkbox path="receiveemails" class="form-control"
							placeholder="emails" autofocus="true"></form:checkbox>
						<form:errors class="text-danger" path="receiveemails"></form:errors>
					</div>
				</spring:bind>
			</div>

		</div>
		<!-- end row   -->
 		
		<!-- fourth row -->
		<div class="row">
		</div>
		<div class="row">
			<div class="col-md-4">
				<div class="input-group-btn">
					<button class="btn btn-danger" type="submit">Submit</button>
					<a class="btn btn-danger" href="index">Cancel</a>
				</div>
			</div>
		</div>

	</form:form>


	<!-- <footer
		style="background-color: #ff6600; position: relative; bottom: 0; width: 100%;">
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

</body>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script>
	$(document).ready(function() {
		$('#errorslname').css('color', '#FF0000');
		$('#errorsfname').css('color', '#FF0000');
		$('#errorspassword').css('color', '#FF0000');
		$('#errorsemailAddress').css('color', '#FF0000');
		$('#errorspasswordConfirm').css('color', '#FF0000');
	});
</script>
</html>