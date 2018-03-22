$(function() {

	$("#save").click(function(e) {
		var userid = $("#userid").val();
		var date = $("input[name='date']").val();
		var enddate = $("input[name='enddate']").val();
		var address = $("input[name='address']").val();
		var phone = $("input[name='phone']").val();
		console.log(userid, date, enddate, address, phone);

		var dataset = e.currentTarget.dataset;
		var id = dataset.id;

		if (id != "") { // Edit
			jQuery.ajax({
				url : 'updateOrderPro',
				processData : true,
				dataType : "text",
				data : {
					id : id,
					userid : userid,
					date : date,
					enddate : enddate,
					address : address,
					phone : phone
				},
				success : function(data) {
					console.log(id);
					bootbox.alert({
						message : 'Modify Successfully! '
							+ 'PS: No change if there are orderitems involving this order!',
					    callback : function() {
							location.reload();
						}
					});
				}
			});
		} else { // Add
			jQuery.ajax({
				url : 'addOrderPro',
				processData : true,
				dataType : "text",
				data : {
					userid : userid,
					date : date,
					enddate : enddate,
					address : address,
					phone : phone
				},
				success : function(data) {
					bootbox.alert({
						message : 'Add Successfully! '
							+ 'PS: No change if foreign key does not exist!',
						callback : function() {
							location.reload();
						}
					});
				}
			})
		}

		$('#modal').modal('hide');
	});

	$(".delete").click(function(e) {
		bootbox.confirm({
			buttons : {
				confirm : {
					label : 'Delete'
				},
				cancel : {
					label : 'Cancel'
				}
			},
			message : 'Sure to delete?',
			callback : function(result) {
				if (result) {

					var dataset = e.currentTarget.dataset;
					var id = dataset.id;
					jQuery.ajax({
						url : 'deleteOrderPro',
						processData : true,
						dataType : "text",
						data : {
							id : id
						},
						success : function(data) {
							console.log(id);
							bootbox.alert({
								message : 'Delete Successfully! '
									+ 'PS: No change if there are orderitems involving this order!',
							    callback : function() {
							       location.reload();
							    }
							});
						}
					});

				}
			}
		});
	});

	$("#add").click(function(e) {
		$('#modalTitle').html("Add");

		$("#userid").val("");
		$("input[name='date']").val("");
		$("input[name='enddate']").val("");
		$("input[name='address']").val("");
		$("input[name='phone']").val("");

		$("#save").attr("data-id", "");
		$('#modal').modal('show');
	});

	$(".edit").click(function(e) {
		$('#modalTitle').html("Edit");
		var dataset = e.currentTarget.dataset;
		var id = dataset.id;
		console.log(id);

		$("#userid").val(dataset.userid);
		$("input[name='date']").val(dataset.date);
		$("input[name='enddate']").val(dataset.enddate);
		$("input[name='address']").val(dataset.address);
		$("input[name='phone']").val(dataset.phone);

		$("#save").attr("data-id", dataset.id);
		$('#modal').modal('show');
	});

});
