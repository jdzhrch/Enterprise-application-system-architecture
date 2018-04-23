<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="model.Book"%>
<%@ page import="model.User"%>
<%@ page import="model.Order"%>
<%@ page import="model.Orderitem"%>
<%@ page import="model.BookCategoryPermission"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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


<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
  <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
  <![endif]-->
</head>

<body id="top">
<%if(request.getAttribute("language")!=null){ %>
<fmt:setLocale value="${language}" />
<%}else{ %>
<fmt:setLocale value="en" />
<%} %>
<fmt:setBundle basename="internationalize.HomepageResourceBundle" var="lang" />
	<%
		ArrayList<Book> bookList = new ArrayList<Book>();
		if (request.getAttribute("books") != null) {
			bookList = (ArrayList<Book>) request.getAttribute("books");
		}
		User account = (User) session.getAttribute("account");

		Order cart = (Order) session.getAttribute("cart");
	%>
	<header id="home">
		<nav>
			<div class="container-fluid">
				<div class="row">
					<div
						class="col-md-8 col-md-offset-2 col-sm-8 col-sm-offset-2 col-xs-8 col-xs-offset-2">

						<ul>
							<li><a href="#intro"><fmt:message key="Home" bundle="${lang}"/> <span class="indicator"><i
										class="fa fa-angle-right"></i></span></a></li>
							<li><a href="#books"><fmt:message key="Books" bundle="${lang}"/>  <span class="indicator"><i
										class="fa fa-angle-right"></i></span></a></li>
							<li><a href="bookstore/jsp/chatroom.jsp"><fmt:message key="Chatroom" bundle="${lang}"/>  <span class="indicator"><i
										class="fa fa-angle-right"></i></span></a></li>
							<%
								if (account != null) {
									if (account.getRole().equals("admin")) {
							%>
							<li><a href="allUserPro"><fmt:message key="Backstage" bundle="${lang}"/> <span
									class="indicator"><i class="fa fa-angle-right"></i></span></a></li>
							<%
								}
							%>

							<form id="cart_form" role="form" action="cartUserPro"
								method="post" hidden>
								<div class="form-group">
									<input class="form-control" name="id"
										value=<%="" + account.getId()%>>
								</div>
								<div class="form-group">
									<input class="form-control" name="username"
										value=<%=account.getUsername()%>>
								</div>
								<div class="form-group">
									<input class="form-control" name="password"
										value=<%=account.getPassword()%>>
								</div>
								<div class="form-group">
									<input class="form-control" name="role"
										value=<%=account.getRole()%>>
								</div>

							</form>
							<li><a href="#" onclick="document:cart_form.submit();"><fmt:message key="MyCart" bundle="${lang}"/><span class="indicator"><i
										class="fa fa-angle-right"></i></span>
							</a></li>
							<%
								}
							%>
							<li>
								<form id="search_form" role="form" action="searchBookPro"
									method="post">
									<div class="form-group">
										<fmt:message key="BookSearch" bundle="${lang}"/>
										<div class="input-group">
											<input class="form-control" name="title"> <span
												class="input-group-addon btn btn-primary"
												onclick="document:search_form.submit();"><fmt:message key="search" bundle="${lang}"/></span>
										</div>

									</div>
								</form>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</nav>
		<section class="hero" id="intro">
		<a href="englishhomePro" ><fmt:message key="English" bundle="${lang}"/></a>
		<a href="frenchhomePro" ><fmt:message key="French" bundle="${lang}"/></a>
			<div class="container">
				<div class="row">
					<%
						if (account == null) {
					%>
					<div
						style="width: 300px; height: auto; float: left; display: inline">
						<a href="#" data-toggle="modal" data-target="#modal-register"><fmt:message key="Register" bundle="${lang}"/>
						</a>/ <a href="#" data-toggle="modal" data-target="#modal-login"><fmt:message key="Login" bundle="${lang}"/>
						</a>
					</div>
					<%
						} else {
					%>
					<div
						style="width: 300px; height: auto; float: left; display: inline">
						<fmt:message key="Username" bundle="${lang}"/> : <a href="#"><%=account.getUsername()%></a>
						&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message key="Role" bundle="${lang}"/> : <a href="#"><%=account.getRole()%></a>
						<br /> <a href="logoutUserPro"
							style="color: #666; font-size: 20px;"><fmt:message key="Logout" bundle="${lang}"/></a>

					</div>
					<%
						}
					%>
					<div class="col-md-12 text-right navicon"
						style="width: 300px; height: auto; float: right; display: inline">
						<a id="nav-toggle" class="nav_slide_button" href="#"><span></span></a>
					</div>
				</div>

				<div class="row">
					<div class="col-md-8 col-md-offset-2 text-center inner">
						<h1 class="animated fadeInDown">
							<a href="HomepagePro"><fmt:message key="BOOKSTORE" bundle="${lang}"/></a><span></span>
						</h1>
						<p class="animated fadeInUp delay-05s">By Richard Zhang</p>
					</div>
				</div>

			</div>
			</div>
		</section>
	</header>


	<section class=" features text-center section-padding" id="books">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<%
						//search results
						if (request.getAttribute("issearch") != null) {
					%>
					<h1 class="arrow"><fmt:message key="HereAreYourSearchResult" bundle="${lang}"/></h1>
					<%
						} else {
					%>
					<h1 class="arrow"><fmt:message key="HereAreTheBooksInThisStore" bundle="${lang}"/></h1>
					<%
						}
					%>
					<div class="features-wrapper">
						<%
							for (int i = 0; i < bookList.size(); i++) {
								Book book = bookList.get(i);
								if (book.getStock() > 0) {
						%>
						<%
							if (cart != null) {
						%>
						<div class="modal fade" id=<%="modal" + i%> tabindex="-1"
							role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal">
											<span aria-hidden="true">&times;</span><span class="sr-only"><fmt:message key="Close" bundle="${lang}"/></span>
										</button>
										<h4 class="modal-title" id="modalTitle"><fmt:message key="AddIntoYourCart" bundle="${lang}"/></h4>
									</div>
									<div class="modal-body">
										<div class="row">
											<div class="col-lg-12">
												<form id=<%="add_orderitem_form" + i%> role="form"
													action="addOrderitemPro" method="post">
													<div class="form-group">
														<label><fmt:message key="BookTitle" bundle="${lang}"/></label> <input class="form-control"
															value=<%=book.getTitle()%> disabled />
													</div>
													<div class="form-group">
														<!-- 此处value必须用""+int类型 因为jsp上本身需要的是字符串 -->
														<input class="form-control" id=<%="orderitem_orderid" + i%>
															name="orderid" value=<%="" + cart.getId()%> hidden>
													</div>
													<div class="form-group">
														<input class="form-control" id=<%="orderitem_bookid" + i%>
															name="bookid" value=<%="" + book.getId()%> hidden>
													</div>
													<label><fmt:message key="amount" bundle="${lang}"/></label>
													<div class="form-group">
														<input class="form-control" id=<%="orderitem_amount" + i%>
															name="amount" value="please input an positive integer"
															onfocus="if (value =='please input an positive integer'){value =''}"
															onblur=<%="numberCheck(this," + i + ")"%>>
													</div>
												</form>
											</div>
										</div>
									</div>
									<div class="modal-footer">
										<button id=<%="add_button" + i%> type="button"
											class="btn btn-primary" onclick=<%="javascript:add(" + i + ")"%>><fmt:message key="Add" bundle="${lang}"/></button>
									</div>
								</div>
							</div>
						</div>

						<%
							}
						%>
						<div class="col-md-4  delay-04s">
							<div>
								<img onclick=<%="showDetails(" + book.getId() + ")"%>
									src=<%="getPictureBookPro?title=" + book.getTitle()%>
									width=200px />
							</div>
							<h2><%=book.getTitle()%></h2>
							<p><%="by " + book.getAuthor()%></p>
							<p><%="¥ " + book.getPrice() / 100.0%></p>
							<%
								if (cart != null) {
							%>
							<button class="btn btn-default"
								onclick=<%="$(modal" + i + ").modal('show');"%>><fmt:message key="addintomycart" bundle="${lang}"/></button>
							<%
								}
							%>
						</div>

						<%
							}
							}
						%>
						<div class="clearfix"></div>
					</div>
				</div>
			</div>
		</div>
	</section>

	<!-- /#wrapper register-->

	<div class="modal fade" id="modal-register" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title"><fmt:message key="Register" bundle="${lang}"/></h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-lg-12">
							<form role="form" action="registerUserPro" method="post">
								<div class="form-group">
									<label><fmt:message key="Username" bundle="${lang}"/></label> <input class="form-control"
										name="username" onblur="checkUsername(this.value)">
									<p id="checkUsernameResult">
								</div>
								<div class="form-group">
									<label><fmt:message key="Password" bundle="${lang}"/></label> <input class="form-control"
										name="password">
								</div>
								<div class="form-group">
									<label><fmt:message key="Role" bundle="${lang}"/></label> <input class="form-control" name="role"
										value="customer" readonly="true">
								</div>
								<div class="modal-footer">
									<input id="register_button" class="btn btn-primary"
										type="submit" value="register">
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- /#wrapper login-->

	<div class="modal fade" id="modal-login" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only"><fmt:message key="Close" bundle="${lang}"/></span>
					</button>
					<h4 class="modal-title"><fmt:message key="Login" bundle="${lang}"/></h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-lg-12">
							<form role="form" action="loginUserPro" method="post">
								<div class="form-group">
									<label><fmt:message key="Username" bundle="${lang}"/></label> <input class="form-control"
										name="username">
								</div>
								<div class="form-group">
									<label><fmt:message key="Password" bundle="${lang}"/></label> <input class="form-control"
										type="password" name="password">
								</div>
								<div class="modal-footer">
									<input class="btn btn-primary" type="submit" value="login">
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- /#wrapper details-->

	<div class="modal fade" id="modal-details" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only"><fmt:message key="Close" bundle="${lang}"/></span>
					</button>
					<h4 id="details_tt" class="modal-title"><fmt:message key="Details" bundle="${lang}"/></h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-lg-12">
							<form role="form">
								<div class="form-group">
									<label><fmt:message key="Title" bundle="${lang}"/></label>
									<p id="details_title">
								</div>
								<div class="form-group">
									<label><fmt:message key="Author" bundle="${lang}"/></label>
									<p id="details_author">
								</div>
								<div class="form-group">
									<label><fmt:message key="Price" bundle="${lang}"/></label>
									<p id="details_price">
								</div>
								<div class="form-group">
									<label><fmt:message key="Publisher" bundle="${lang}"/></label>
									<p id="details_publisher">
								</div>
								<div class="form-group">
									<label><fmt:message key="StockAmount" bundle="${lang}"/></label>
									<p id="details_stock">
								</div>
								<div class="form-group">
									<label><fmt:message key="Category" bundle="${lang}"/></label>
									<p id="details_category">
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="<%=path%>/bookstore/js/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="<%=path%>/bookstore/js/waypoints.min.js"></script>
	<script src="<%=path%>/bookstore/js/bootstrap.min.js"></script>
	<script src="<%=path%>/bookstore/js/scripts.js"></script>
	<script src="<%=path%>/bookstore/js/jquery.flexslider.js"></script>
	<script src="<%=path%>/bookstore/js/modernizr.js"></script>
	<script src="<%=path%>/bookstore/js/bootbox.min.js"></script>
	<script src="<%=path%>/bookstore/js/homepage.js"></script>
	<script>
function numberCheck(t,i){
    var num = t.value;
    var re=/^\d*$/;
	document.getElementById("add_button"+i).disabled=false;
    if(!re.test(num)){
        isNaN(parseInt(num))?t.value=0:t.value=parseInt(num);
		document.getElementById("add_button"+i).disabled=true;
    }
    if(num <= 0){
		document.getElementById("add_button"+i).disabled=true;
    }
}
</script>
</body>
</html>