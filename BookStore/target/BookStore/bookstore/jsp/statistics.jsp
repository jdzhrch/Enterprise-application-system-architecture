<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"  %>
<%@ page import="model.Statistic"%>
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
		ArrayList<Statistic> statisticList = new ArrayList<Statistic>();
			if (request.getAttribute("statistics") != null) {
				statisticList = (ArrayList<Statistic>) request.getAttribute("statistics");
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
					<li><a href="allBookPro"><i
							class="fa fa-book fa-fw"></i> Books</a></li>
					<li><a href="allOrderPro"><i class="fa fa-reorder fa-fw"></i>
							Orders</a></li>
					<li><a href="allOrderitemPro"><i
							class="fa fa-table fa-fw"></i> Orderitems</a></li>
					<li><a href="allByBookTotalStatisticPro" class="active"><i
							class="fa fa-signal fa-fw"></i> Sales Statistics</a></li>
				</ul>
			</div>
			<!-- /.sidebar-collapse -->
		</div>
		<!-- /.navbar-static-side --> </nav>

		<div id="page-wrapper" id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">Sales Statistics</h1>
				</div>
			</div>
			<!-- /.row -->
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default" id="panel">
						<div class="panel-heading">
							sorted by
							<button class="btn btn-default" type="button" onclick="allByBook()">
								Book
							</button>
							<button class="btn btn-default" type="button" onclick="allByUser()">
								User
							</button>
							<button class="btn btn-default" type="button" onclick="allByStartDate()">
								Start date
							</button>
							<button class="btn btn-default" type="button" onclick="allByEndDate()">
								End date
							</button>
							<button class="btn btn-default" type="button" onclick="allByCategory()">
								Book Category
							</button>
						</div>
						<!-- /.panel-heading -->
						<div class="panel-body" id="panel-body">
							<div class="dataTable_wrapper"id="dataTable_wrapper">
								<table class="table table-striped table-bordered table-hover"
									id="dataTables">
									<thead>
										<tr>
										    <th id="criteria">Book Id</th>
											<th>Number of books sold</th>
											<th>Total Sales Amount</th>
										</tr>
									</thead>
									<tbody id="tbody">
										<%
											for (int i = 0; i < statisticList.size(); i++) {
												Statistic statistic = statisticList.get(i);
										%>
										<tr>
										    <td valign="middle"><%=statistic.getCriteria()%></td>
											<td><%=statistic.getNumber()%></td>
											<td><%=statistic.getAmount()%></td>
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
	
<div id="container1" style="width: 550px; height: 400px; margin: 0 auto"></div>
<div id="container2" style="width: 550px; height: 400px; margin: 0 auto"></div>
	<script src="<%=path%>/bookstore/js/jquery.min.js"></script>
	<script src="<%=path%>/bookstore/js/bootstrap.min.js"></script>
	<script src="<%=path%>/bookstore/js/jquery.dataTables.min.js"></script>
	<script src="<%=path%>/bookstore/js/dataTables.bootstrap.min.js"></script>
	<script src="<%=path%>/bookstore/js/bookstore.js"></script>
	<script src="<%=path%>/bookstore/js/bootbox.min.js"></script>

	<script src="<%=path%>/bookstore/js/statistic.js"></script>
	<script src="<%=path%>/bookstore/js/highcharts.js"></script>

	<script>
		$(document).ready(function() {
			$('#dataTables').DataTable({
				responsive : true
			});
		});
	</script>
	<script type="text/javascript">
	function activate() {
		$('#dataTables').DataTable({
			responsive : true
		});
	};
	function appendTable(data,criteria) {  
	              var panel_body = document.createElement("div");
	              panel_body.className = "panel-body";
	              panel_body.id = "panel-body";
	              var div = document.createElement("div");  
	              div.className = "dataTable_wrapper";
	              //init thead
	              var table = document.createElement("table");  
	              table.className = "table table-striped table-bordered table-hover";
	              table.id = "dataTables";
	              var thead = document.createElement("thead");  
	              var tr = document.createElement("tr");  
	              
	              var td1 = document.createElement("th");  
	              td1.innerHTML = criteria;  
	              tr.appendChild(td1);  
	              var td2 = document.createElement("th"); 
	              td2.innerHTML = "Number of books sold";  
	              tr.appendChild(td2);  
	              var td3 = document.createElement("th"); 
	              td3.innerHTML = "Total Sales Amount";  
	              tr.appendChild(td3);  
	              
	              thead.appendChild(tr);
	              table.appendChild(thead);  
	              //init tbody
	              var tbody = document.createElement("tbody");  
	              for ( var tableRowNo = 0; tableRowNo < data.length; tableRowNo++) {  
	                        var tr = document.createElement("tr");  
	                        var cell1 = document.createElement("td");  
	                        cell1.innerHTML = data[tableRowNo]["criteria"];  
	                        tr.appendChild(cell1);  
	                        var cell2 = document.createElement("td");  
	                        cell2.innerHTML = data[tableRowNo]["number"];  
	                        tr.appendChild(cell2);  
	                        var cell3 = document.createElement("td");  
	                        var amount = data[tableRowNo]["amount"];
	                        cell3.innerHTML = amount;  
	                        tr.appendChild(cell3);  
	                        tbody.appendChild(tr);  
	             }  
	             table.appendChild(tbody);
	             div.appendChild(table);
	             panel_body.appendChild(div);
	             document.getElementById("panel").removeChild(document.getElementById("panel-body"));
	             document.getElementById("panel").appendChild(panel_body);
	             var page_wrapper = document.getElementById("page-wrapper");
	             document.getElementById("wrapper").removeChild(document.getElementById("page-wrapper"));
	             document.getElementById("wrapper").appendChild(page_wrapper);
	             activate();
	}

	function allByBook(){
		jQuery.ajax({
			url : 'allByBookStatisticPro',
			processData : true,
			dataType : "json",
			success : function(data) {
				appendTable(data,"Book id");
				showBookNumChart(data,'Book id');
				showTotalAmount(data,'Book id');
			}
		});
	}
	function allByUser(){
		jQuery.ajax({
			url : 'allByUserStatisticPro',
			processData : true,
			dataType : "json",
			success : function(data) {
				appendTable(data,"User id");
				showBookNumChart(data,'User id');
				showTotalAmount(data,'User id');
			}
		});
	}
	function allByStartDate(){
		jQuery.ajax({
			url : 'allByStartDateStatisticPro',
			processData : true,
			dataType : "json",
			success : function(data) {
				appendTable(data,"Start Date");
				showBookNumChart(data,'Start Date');
				showTotalAmount(data,'Start Date');
			}
		});
	}
	function allByEndDate(){
		jQuery.ajax({
			url : 'allByEndDateStatisticPro',
			processData : true,
			dataType : "json",
			success : function(data) {
				appendTable(data,"End Date");
				showBookNumChart(data,'End Date');
				showTotalAmount(data,'End Date');
			}
		});
	}
	function allByCategory(){
		jQuery.ajax({
			url : 'allByCategoryStatisticPro',
			processData : true,
			dataType : "json",
			success : function(data) {
				appendTable(data,"Category");
				showBookNumChart(data,'Category');
				showTotalAmount(data,'Category');
			}
		});
	}
	function showBookNumChart(data,xAxisTitle){
		var title = {
			       text: 'Number of books sold'   
			   };
		var xAxis = {
				title: {
			         text: xAxisTitle
			      },
				categories: []
	    };
		for ( var tableRowNo = 0; tableRowNo < data.length; tableRowNo++) {  
			xAxis['categories'].push(data[tableRowNo]["criteria"]);
		}
		var yAxis = {
		      title: {
		         text: 'Number of books sold'
		      },
		      plotLines: [{
		         value: 0,
		         width: 1,
		         color: '#808080'
		      }]
		};   
		var tooltip = {
				valueSuffix: ''
	    }
		var legend = {
	      layout: 'vertical',
	      align: 'right',
	      verticalAlign: 'middle',
	      borderWidth: 0
	   };
	   var series =  [
	      {
	         name: 'Number of books sold',
	         data: []
	      }
	   ];
	   for ( var tableRowNo = 0; tableRowNo < data.length; tableRowNo++) {  
		   series[0]['data'].push(data[tableRowNo]["number"]);
	   }
		
	   var json = {};

	   json.title = title;
	   json.xAxis = xAxis;
	   json.yAxis = yAxis;
	   json.tooltip = tooltip;
	   json.legend = legend;
	   json.series = series;

	   $('#container1').highcharts(json);
	}
	function showTotalAmount(data,xAxisTitle){
		var title = {
			       text: 'Total Sales Amount'   
			   };
		var xAxis = {
				title: {
			         text: xAxisTitle
			      },
				categories: []
	    };
		for ( var tableRowNo = 0; tableRowNo < data.length; tableRowNo++) {  
			xAxis['categories'].push(data[tableRowNo]["criteria"]);
		}
		var yAxis = {
		      title: {
		         text: 'Total Sales Amount'
		      },
		      plotLines: [{
		         value: 0,
		         width: 1,
		         color: '#808080'
		      }]
		};   
		var tooltip = {
				valueSuffix: ''
	    }
		var legend = {
	      layout: 'vertical',
	      align: 'right',
	      verticalAlign: 'middle',
	      borderWidth: 0
	   };
	   var series =  [
	      {
	         name: 'Total Sales Amount',
	         data: []
	      }
	   ];
	   for ( var tableRowNo = 0; tableRowNo < data.length; tableRowNo++) {  
		   series[0]['data'].push(data[tableRowNo]["amount"]);
	   }
		
	   var json = {};

	   json.title = title;
	   json.xAxis = xAxis;
	   json.yAxis = yAxis;
	   json.tooltip = tooltip;
	   json.legend = legend;
	   json.series = series;

	   $('#container2').highcharts(json);
	}
	</script>


</body>

</html>