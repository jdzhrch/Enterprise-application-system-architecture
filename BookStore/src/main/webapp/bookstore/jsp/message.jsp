<%@ page import="java.util.ArrayList"%>
<%@ page import="model.Book"%>
<%@ page import="model.User"%>
<%@ page import="model.Order"%>
<%@ page import="model.Orderitem"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>BookStore</title>

<%
String path = request.getContextPath();
%>
<link href="<%=path%>/bookstore/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=path%>/bookstore/css/font-awesome.min.css"
	rel="stylesheet">
<link href="<%=path%>/bookstore/css/flexslider.css" rel="stylesheet">
<link href="<%=path%>/bookstore/css/styles.css" rel="stylesheet">
<link href="<%=path%>/bookstore/css/queries.css" rel="stylesheet">
<link href="<%=path%>/bookstore/css/animate.css" rel="stylesheet">

<style>
</style>
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
  <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
  <![endif]-->
</head>
<body>
	<%
	String error = (String)request.getAttribute("errorMsg");
	if(error!=""){
	%>
	<section class="hero" id="hero">
		<div class="container">
			<div class="row">
				<div class="col-md-8 col-md-offset-2 text-center inner">
					<h1 class="animated fadeInDown"><%= error %><span></span>
					</h1>
					<p class="animated fadeInUp delay-05s">
						<button class="btn btn-primary" onclick="window.history.back()">Back</button>
					</p>
				</div>
			</div>

		</div>
		<%
	}
	%>
	</section>
</body>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="<%=path%>/bookstore/js/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="<%=path%>/bookstore/js/waypoints.min.js"></script>
<script src="<%=path%>/bookstore/js/bootstrap.min.js"></script>
<script src="<%=path%>/bookstore/js/scripts.js"></script>
<script src="<%=path%>/bookstore/js/jquery.flexslider.js"></script>
<script src="<%=path%>/bookstore/js/modernizr.js"></script>
<script src="<%=path%>/bookstore/js/bootbox.min.js"></script>

<script src="<%=path%>/bookstore/js/account.js"></script>
</body>
</html>