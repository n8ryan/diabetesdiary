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
<title>Data</title>
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
  			<h1>Data Entry</h1>
  			<h2 style="color: #008000">${message}</h2> 
		</div>


 	<%-- <form:form action = "updateStats" method="POST" class="col-md-12 col-md-offset-1" modelAttribute="updateStats">
		
		<!-- first row  -->
		<div class="row"> 
			<div class="col-md-3">
				
					<div class="form-group">
						<label for="activity" id='activity'>Type of Exercise</label>
						<form:input type="text" path="activity" 
							class="form-control" placeholder="Type of Exercise" 
							autofocus="true"></form:input>						
			</div>
			
			</div>


			
		<!-- button row -->
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

	</form:form>  --%>
	
	<form id="exer" method="post"  action="exercises">
                    
      <div class="contact-form">
              <div class="container">
                     
                     <div class="col-md-3">
                           <div class="form-group">
                              <label for="activity" id='activity'> Activity</label>
                              <input type=text id="activity" name="activity" class="form-control input-sm" placeholder="activity" > 
                           </div>
                           <div id='etl'>
                           
                           </div>
                     </div>
    
                     
                     <div class="col-md-3">
                           <div class="form-group">
                              <label for="duration" id='duration'>duration*</label>
                              <input type=text id="duration" name="duration" class="form-control input-sm" placeholder="duration" > 
                           </div>
                           
                     </div>
                     
             
                        <div class="col-md-8" >
                           	<div class="form-group">                       
                   				<button type="submit" name="submit" id="submit" class="btn btn-primary" >Submit</button>
                   				
                   			</div>
                         </div> 
                     </div>      
              </div>
                
           
    </form>


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