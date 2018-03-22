

function deal(){
    //implement the synchronous submission of two forms
    var id = $("#book_id").val();
	var stock = $("#book_stock").val();

    jQuery.ajax({
		url : 'dealBookPro',
		processData : true,
		dataType : "text",
		data : {
			id : id,
			stock : stock
		},
		success : function(data) {
			console.log(id);
		}
	});
	document.getElementById("cart_deal_form").submit();
}

function confirm_receipt(orderid,buttonid){
	 var date = new Date();
	    var seperator1 = "-";
	    var month = date.getMonth() + 1;
	    var strDate = date.getDate();
	    if (month >= 1 && month <= 9) {
	        month = "0" + month;
	    }
	    if (strDate >= 0 && strDate <= 9) {
	        strDate = "0" + strDate;
	    }
	    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate+ " ";
    jQuery.ajax({
		url : 'confirmReceiptOrderPro',
		processData : true,
		dataType : "text",
		data : {
			id : orderid,
			enddate : currentdate
		}
	});
	bootbox.alert({
		message : "Confirm Receipt successfully!"
	});
	document.getElementById("button_confirm"+buttonid).hidden=true;
}
