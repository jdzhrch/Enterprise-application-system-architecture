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