
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<html>
<head>

<%
	String path = request.getContextPath();
%>
   <meta charset="UTF-8" />
   <title>Highcharts æç¨ | èé¸æç¨(runoob.com)</title>
   <script src="<%=path%>/bookstore/js/jquery.min.js"></script>
   <script src="<%=path%>/bookstore/js/highcharts.js"></script>
</head>
<body>
<div id="container" style="width: 550px; height: 400px; margin: 0 auto"></div>
<script language="JavaScript">
$(document).ready(function() {
	var xAxis = {
		       categories: []
		   };

		   var yAxis = {
		      title: {
		         text: 'Numbers of book sold'
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
		         name: 'London',
		         data: []
		      }
		   ];

		   var json = {};

		   json.xAxis = xAxis;
		   json.yAxis = yAxis;
		   json.tooltip = tooltip;
		   json.legend = legend;
		   json.series = series;

		   $('#container').highcharts(json);
});
</script>
</body>
</html>
