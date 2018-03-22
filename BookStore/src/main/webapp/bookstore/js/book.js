$(function() {

	$("#save").click(function(e) {
		var title = $("#title_book").val();
		var author = $("input[name='author']").val();
		var price = $("input[name='price']").val();
		var publisher = $("input[name='publisher']").val();
		var date = $("input[name='date']").val();
		var image = $("input[name='image']").val();
		var stock = $("input[name='stock']").val();
		var category = $("#bookcategory").val();
		console.log(title, author, price, publisher, date, image, stock ,category);

		$("#title_picture").val(title);
		
		var dataset = e.currentTarget.dataset;
		var id = dataset.id;

		if (id != "") { // Edit
			jQuery.ajax({
				url : 'updateBookPro',
				processData : true,
				dataType : "text",
				data : {
					id : id,
					title : title,
					author : author,
					price : price*100,
					publisher : publisher,
					date : date,
					image : image,
					stock : stock,
					category : category
				},
				success : function(data) {
					console.log(id);
					bootbox.alert({
						message : 'Modify Successfully! '
							+ 'PS: No change if there are orderitems involving this book!',
						callback : function() {
							location.reload();
						}
					});
				}
			});
		} else { // Add
			jQuery.ajax({
				url : 'addBookPro',
				processData : true,
				dataType : "text",
				data : {
					title : title,
					author : author,
					price : price*100,
					publisher : publisher,
					date : date,
					image : image,
					stock : stock,
					category : category
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
		$("#form_picture").submit();
		
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
						url : 'deleteBookPro',
						processData : true,
						dataType : "text",
						data : {
							id : id
						},
						success : function(data) {
							console.log(id);
							bootbox.alert({
								message : 'Delete Successfully! '
									+ 'PS: No change if there are orderitems involving this book!',
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

		$("#title_book").val("");
		$("input[name='author']").val("");
		$("input[name='price']").val("");
		$("input[name='publisher']").val("");
		$("input[name='date']").val("");
		$("input[name='image']").val("/bookstore/img/default.jpg");
		$("input[name='stock']").val("");
		$("#bookcategory").val("");

		$("#save").attr("data-id", "");
		$('#modal').modal('show');
	});

	$(".edit").click(function(e) {
		$('#modalTitle').html("Edit");
		var dataset = e.currentTarget.dataset;
		var id = dataset.id;
		console.log(id);

		$("#title_book").val(dataset.title);
		$("input[name='author']").val(dataset.author);
		$("input[name='price']").val(dataset.price);
		$("input[name='publisher']").val(dataset.publisher);
		$("input[name='date']").val(dataset.date);
		$("input[name='image']").val(dataset.image);
		$("input[name='stock']").val(dataset.stock);
		$("#bookcategory").val(dataset.category);

		$("#save").attr("data-id", dataset.id);
		$('#modal').modal('show');
	});

});

