<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"  %>
<%@ page import="model.Book"%>
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

<body >
	<%
		ArrayList<Book> bookList = new ArrayList<Book>();
			if (request.getAttribute("books") != null) {
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
					<li><a href="allBookPro" class="active"><i
							class="fa fa-book fa-fw"></i> Books</a></li>
					<li><a href="allOrderPro"><i class="fa fa-reorder fa-fw"></i>
							Orders</a></li>
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
					<h1 class="page-header">Books</h1>
				</div>
			</div>
			<!-- /.row -->
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">
							add book
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
											<th>Title</th>
											<th>Author</th>
											<th>Price</th>
											<th>Publisher</th>
											<th>Date</th>
											<th>Image</th>
											<th>Stock</th>
											<th>Category</th>
											<th></th>
										</tr>
									</thead>
									<tbody>
										<%
											for (int i = 0; i < bookList.size(); i++) {
												Book book = bookList.get(i);
												SimpleDateFormat bartDateFormat = new SimpleDateFormat
										  				("yyyy-MM-dd"); 
										%>
										<tr>
										    <td valign="middle"><%=book.getId()%></td>
											<td><%=book.getTitle()%></td>
											<td><%=book.getAuthor()%></td>
											<td><%=book.getPrice()/100.0%></td>
											<td><%=book.getPublisher()%></td>
											<td><%=book.getDate()==null?null:bartDateFormat.format((Date)book.getDate()) %></td>
											<td align="center"><img src=<%="getPictureBookPro?title="+book.getTitle() %> width=100px/></td>
											<td><%=book.getStock()%></td>
											<td><%=book.getCategory()%></td>
											<td>
												<button class="btn btn-default delete" type="button"
													data-id="<%=book.getId()%>">
													<i class="fa fa-trash"></i>
												</button>
												<button class="btn btn-default edit" type="button"
													data-id="<%=book.getId()%>"
													data-title="<%=book.getTitle()%>"
													data-author="<%=book.getAuthor()%>"
													data-price="<%=book.getPrice()/100.0%>"
													data-publisher="<%=book.getPublisher()%>"
													data-date="<%=book.getDate()==null?null:bartDateFormat.format((Date)book.getDate())%>"
													data-image="<%=book.getImage()%>"
													data-stock="<%=book.getStock()%>"
													data-category="<%=book.getCategory()%>">
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
									<label>Title</label> <input onblur="checkInfo()" id="title_book" class="form-control" name="title">
								</div>
								<div class="form-group">
									<label>Author</label> <input onblur="checkInfo()" class="form-control" name="author">
								</div>
								<div class="form-group">
									<label>Price</label> <input onblur="checkInfo()" class="form-control" type="number"
										step="0.01" name="price">
								</div>
								<div class="form-group">
									<label>Publisher</label> <input onblur="checkInfo()" class="form-control"
										name="publisher">
								</div>
								<div class="form-group">
									<label>Date</label> <input onblur="checkInfo()" class="form-control" type="date"
										name="date">
								</div>
								<div class="form-group" hidden="true">
									<label>Image</label> <input onblur="checkInfo()" class="form-control"
										name="image">
								</div>
								<div class="form-group">
									<label>stock</label> <input onblur="checkInfo()" class="form-control" type="number"
										step="1" name="stock">
								</div>
								<div class="form-group">
									<label>category</label>
									<select onblur="checkInfo()" class="form-control" id="bookcategory">
										<option value="History">History</option>
										<option value="Literature">Literature</option>
										<option value="Art">Art</option>
										<option value="Science">Science</option>
									</select>
								</div>
							</form>
							<form id="form_picture" method="post" action="savePictureBookPro" enctype="multipart/form-data" >
								<div class="form-group" hidden="true">
									<label>Title</label> <input id="title_picture"class="form-control" name="title">
								</div>
								<div class="form-group">
									<label>Picture</label> <input id="imageFile" onblur="checkInfo()" type="file" class="form-control" name="imageFile">
								</div>
							</form>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<h4 id="checkInfoResult" hidden="true">uncomplete information</h4>
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary" id="save">Save</button>
				</div>
			</div>
		</div>
	</div>

	<script src="<%=path%>/bookstore/js/jquery.min.js"></script>
	<script src="<%=path%>/bookstore/js/bootstrap.min.js"></script>
	<script src="<%=path%>/bookstore/js/jquery.dataTables.min.js"></script>
	<script src="<%=path%>/bookstore/js/dataTables.bootstrap.min.js"></script>
	<script src="<%=path%>/bookstore/js/bookstore.js"></script>
	<script src="<%=path%>/bookstore/js/bootbox.min.js"></script>

	<script src="<%=path%>/bookstore/js/book.js"></script>

	<script>
		$(document).ready(function() {
			$('#dataTables').DataTable({
				responsive : true
			});
		});
	</script>

</body>

</html>

