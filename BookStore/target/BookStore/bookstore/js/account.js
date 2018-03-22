$(function() {

	$("#save").click(function(e) {
		var username = $("input[name='username']").val();
		var password = $("input[name='password']").val();
		var role = "customer";
		console.log(username, password, role);

		
		jQuery.ajax({
			url : 'addUserPro',
			processData : true,
			dataType : "text",
			data : {
				username : username,
				password : password,
				role : role
			},
			success : function(data) {
				bootbox.alert({
					message : 'Registered! '
						+ 'PS: No change if this user already exists!',
					callback : function() {
						location.reload();
					}
				});
			}
		})

		$('#modal-register').modal('hide');
	});

	$("#register").click(function(e) {
		$('#modalTitle').html("Register");

		$("input[name='username']").val("");
		$("input[name='password']").val("");
		$("input[name='role']").val("");

		$("#save").attr("data-id", "");
		$('#modal-register').modal('show');
	});


});
