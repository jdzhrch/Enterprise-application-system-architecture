<%@ page import="java.util.ArrayList"%>
<%@ page import="model.Book"%>
<%@ page import="model.Order"%>
<%@ page import="model.Orderitem"%>
<%@ page import="model.User"%>
<%@ page import="model.User"%>
<%@ page import="javax.security.auth.Subject"%>
<%@ page import="javax.security.auth.login.LoginContext"%>
<%@ page import="jaas.SimplePrincipal"%>
<%@ page import="jaas.SimpleCallbackHandler"%>
<%@ page import="java.util.Set"%>
<%@ page import="java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<title>BookStore</title>

<%
	String path = request.getContextPath();
%>
<link href="<%=path%>/bookstore/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=path%>/bookstore/css/dataTables.bootstrap.css"
	rel="stylesheet">
<link href="<%=path%>/bookstore/css/dataTables.responsive.css"
	rel="stylesheet">
<link href="<%=path%>/bookstore/css/bookstore.css" rel="stylesheet">
<link href="<%=path%>/bookstore/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
</head>

<body>
	<%
	boolean authResult = false;
	/*利用jaas的simpleLoginModule做核验*/
	User account = (User) session.getAttribute("account");
	if (account != null) {
		System.out.println(account.getRole());
		System.setProperty("java.security.auth.login.config",
				"E:/GitHub/proj/Enterprise application system architecture/BookStore/src/main/java/jaas/jaas.conf");

		LoginContext context = new LoginContext("Login1",
				new SimpleCallbackHandler(account.getUsername(), account.getPassword().toCharArray()));
		context.login();
		Subject subject = context.getSubject();
		SimplePrincipal simplePrincipal = new SimplePrincipal("role", "admin");
		if (subject.getPrincipals().contains(simplePrincipal)) {
			context.logout();
			authResult = true;
			System.out.println("login success");
		}else{
			authResult = false;
			System.out.println("login fail");
		}
		context.logout();
	} else{
		authResult = false;
		System.out.println("account=null");
	}
	
	if(authResult == false){
		%>
		<section class="hero" id="hero">
		<div class="container">
			<div class="row">
				<div class="col-md-8 col-md-offset-2 text-center inner">
					<h1 class="animated fadeInDown">Only admin can access<span></span>
					</h1>
					<p class="animated fadeInUp delay-05s">
						<button class="btn btn-primary" onclick="window.history.back()">Back</button>
					</p>
				</div>
			</div>

		</div>
		</section>
	<%
	}else{
		
		ArrayList<Order> orderList = new ArrayList<Order>();
		if (request.getAttribute("orders") != null) {
		    orderList = (ArrayList<Order>) request.getAttribute("orders");
		}
		ArrayList<User> userList = new ArrayList<User>();
		if(request.getAttribute("users") != null) {
			userList = (ArrayList<User>) request.getAttribute("users");	
		}
		ArrayList<Book> bookList = new ArrayList<Book>();
		if(request.getAttribute("books") != null) {
			bookList = (ArrayList<Book>) request.getAttribute("books");
		}
	%>
	<div id="wrapper">
		<!-- Navigation -->
		<nav class="navbar navbar-default navbar-static-top" role="navigation"
			style="margin-bottom: 0">

		<div class="navbar-header">
			<a class="navbar-brand" href="HomepagePro">BookStore</a>
		</div>

		<div class="navbar-default sidebar" role="navigation">
			<div class="sidebar-nav navbar-collapse">
				<ul class="nav" id="side-menu">
					<li><a href="allUserPro"><i class="fa fa-user fa-fw"></i>
							Users</a></li>
					<li><a href="allBookPro"><i class="fa fa-book fa-fw"></i>
							Books</a></li>
					<li><a href="allOrderPro" class="active"><i
							class="fa fa-reorder fa-fw"></i> Orders</a></li>
					<li><a href="allOrderitemPro"><i
							class="fa fa-table fa-fw"></i> Orderitems</a></li>
					<li><a href="allByBookTotalStatisticPro"><i
							class="fa fa-signal fa-fw"></i> Sales Statistics</a></li>
				</ul>
			</div>
			<!-- /.sidebar-collapse -->
		</div>
		<!-- /.navbar-static-side --> </nav>

		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">Orders</h1>
				</div>
			</div>
			<!-- /.row -->
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">
							add order
							<button class="btn btn-default" type="button" id="add">
								<i class="fa fa-plus"></i>
							</button>
						</div>
						<!-- /.panel-heading -->
						<div class="panel-body">
							<div class="dataTable_wrapper">
								<table class="table table-striped table-bordered table-hover"
									id="dataTables">
									<thead>
										<tr>
											<th>ID</th>
											<th>Userid</th>
											<th>Date</th>
											<th>Orderitem ID List</th>
											<th>Sum</th>
											<th>End Date</th>
											<th>address</th>
											<th>phone</th>
											<th></th>
										</tr>
									</thead>
									<tbody>
										<%
											for (int i = 0; i < orderList.size(); i++) {
												Order order = orderList.get(i);
												Set<Orderitem> orderitems = order.getOrderitems();
												ArrayList<String> orderitemStr = new ArrayList<String>();
											    int sum = 0;
																												
												Iterator iterator = orderitems.iterator();     
												while(iterator.hasNext()){
													Orderitem item = (Orderitem)iterator.next();
													orderitemStr.add(item.getId()+"");

													sum += item.getOrderitemPrice();
												}
										%>
										<tr>
											<td><%=order.getId()%></td>
											<td><%=order.getUserid()%></td>
											<td><%=order.getDate()%></td>
											<td><%=orderitemStr%></td>
											<td><%=sum/100.0%></td>
											<td><%=order.getEnddate()%></td>
											<td><%=order.getAddress()%></td>
											<td><%=order.getPhone()%></td>
											<td>
												<button class="btn btn-default delete" type="button"
													data-id="<%=order.getId()%>">
													<i class="fa fa-trash"></i>
												</button>
												<button class="btn btn-default edit" type="button"
													data-id="<%=order.getId()%>"
													data-userid="<%=order.getUserid()%>"
													data-date="<%=order.getDate()%>"
													data-enddate="<%=order.getEnddate()%>"
													data-address="<%=order.getAddress()%>"
													data-phone="<%=order.getPhone()%>">
													<i class="fa fa-edit"></i>
												</button>
											</td>	
										</tr>
										<%
											}
										%>
									</tbody>
								</table>
							</div>
						</div>
						<!-- /.panel-body -->
					</div>
					<!-- /.panel -->
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->
		</div>
		<!-- /#page-wrapper -->
	</div>
	<!-- /#wrapper -->

	<div class="modal fade" id="modal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="modalTitle"></h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-lg-12">
							<form role="form">
								<div class="form-group">
									<label>Userid</label>
									<select class="form-control" id="userid">
										<%
											for (int i = 0; i < userList.size(); i++) {
												User user = userList.get(i);
										%>
										<option value="<%=user.getId()%>"><%=user.getId()%></option>
										<%
											}
										%>
									</select>
								</div>
								<div class="form-group">
									<label>Date</label> <input class="form-control" type="date"
										name="date">
								</div>
								<div class="form-group">
									<label>End Date</label> <input class="form-control" type="date"
										name="enddate">
								</div>
								<div class="form-group">
									<label>Address</label> <input class="form-control"
										name="address">
								</div>
								<div class="form-group">
									<label>Phone</label> <input class="form-control"
										name="phone">
								</div>
							</form>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary" id="save">Save</button>
				</div>
			</div>
		</div>
	</div>
<%} %>
	<script src="<%=path%>/bookstore/js/jquery.min.js"></script>
	<script src="<%=path%>/bookstore/js/bootstrap.min.js"></script>
	<script src="<%=path%>/bookstore/js/jquery.dataTables.min.js"></script>
	<script src="<%=path%>/bookstore/js/dataTables.bootstrap.min.js"></script>
	<script src="<%=path%>/bookstore/js/bookstore.js"></script>
	<script src="<%=path%>/bookstore/js/bootbox.min.js"></script>

	<script src="<%=path%>/bookstore/js/order.js"></script>

	<script>
		$(document).ready(function() {
			$('#dataTables').DataTable({
				responsive : true
			});
		});
	</script>

</body>

</html>

