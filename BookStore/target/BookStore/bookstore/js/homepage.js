function checkUsername(username){
	jQuery.ajax({
		url : "checkUsernameUserPro",
		processData : true,
		dataType : "text",
		data : {
			username : username
		},
		success : function(data) {
			document.getElementById("checkUsernameResult").innerHTML=data;
			if(data == "this user already exists"){
				document.getElementById("register_button").disabled=true;
			}
			else{
				document.getElementById("register_button").disabled=false;
			}
		}
	});
}

function add(form_id){
    var orderid = $("#orderitem_orderid"+form_id).val();
		var bookid = $("#orderitem_bookid"+form_id).val();
		var amount = $("#orderitem_amount"+form_id).val();
		console.log(orderid,bookid,amount);

	    jQuery.ajax({
			url : 'addOrderitemPro',
			processData : true,
			dataType : "text",
			data : {
				orderid : orderid,
			bookid : bookid,
			amount : amount
			},
			success : function(data) {
				console.log(id);
			}
		});
	bootbox.alert({
		message : 'Add Successfully! '
			+ 'PS: No change if store is not enough!',
		callback : function() {
			location.reload();
		}
	});
		$('#modal'+form_id).modal('hide');
}

function numberCheck(t){
    var num = t.value;
    var re=/^\d*$/;
	document.getElementById("add_button").disabled=false;
    if(!re.test(num)){
        isNaN(parseInt(num))?t.value=0:t.value=parseInt(num);
		document.getElementById("add_button").disabled=true;
    }
    if(num <= 0){
		document.getElementById("add_button").disabled=true;
    }
}

function showDetails(book_id){
    
	    jQuery.ajax({
			url : 'showDetailsBookPro',
			processData : true,
			dataType : "json",
			data : {
				id : book_id
			},
			success : function(data) {
				document.getElementById("details_title").innerHTML = data.title;
				document.getElementById("details_author").innerHTML = data.author;
				document.getElementById("details_category").innerHTML = data.category;
				document.getElementById("details_price").innerHTML = "¥"+data.price/100.00;
				document.getElementById("details_publisher").innerHTML = data.publisher;
				document.getElementById("details_stock").innerHTML = data.stock;
				$('#modal-details').modal('show');
			}
		});
}