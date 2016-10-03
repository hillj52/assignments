$(document).ready(function() {
	$(".edit-student-div").hide();
	$(".add-student-div").hide();
	$("#edit-return").click(function() {
		$(".table-div").show();
		$(".edit-student-div").hide();
	});
	$("#add-return").click(function() {
		$(".table-div").show();
		$(".add-student-div").hide();
	});
	$.ajax({url:"http://localhost:8080/student"}).then(function(data) {
			for(var i=0;i<data.length;i++) {
				var studentId = data[i].id;
				var item = $("<tr><td><a class='student"+data[i].id+
					"' href='#' data-student-id='"+studentId+"'>"+data[i].lastName+", "+
					data[i].firstName+"</a></td><td>"+data[i].sat+"</td><td>"+data[i].gpa+
					"</td><td><button class='edit"+studentId+"' data-student-id='"+studentId+
					"'><span class='glyphicon glyphicon-pencil'></span></button>"+
					"<button class='delete"+studentId+"' data-student-id='"+studentId+
					"'><span class='glyphicon glyphicon-remove'></span></button></td></tr>");
				$("#student-list").append(item);
				$(".edit"+data[i].id).click(function() { 
					var id = $(this).data("student-id");
					$.ajax({url:"http://localhost:8080/student/"+id}).then(function(data) {
						$(".table-div").hide();
						$(".edit-student-div").show();
						$("#student-id").val(data.id);
						$("#student-first-name").val(data.firstName);
						$("#student-last-name").val(data.lastName);
						$("#student-sat").val(data.sat);
						$("#student-gpa").val(data.gpa);
					});
				});
				$(".delete"+data[i].id).click(function() {
					var id = $(this).data("student-id");
					$.ajax({
						url:"http://localhost:8080/student/delete/"+id,
						type:"DELETE",
						success : function() {
							location.reload(true);
						}
					});
				});
			}
    });
	$("#update").click(function() {
		var data = {
			id:$("#student-id").val(),
			firstName:$("#student-first-name").val(),
			lastName:$("#student-last-name").val(),
			sat:$("#student-sat").val(),
			gpa:$("#student-gpa").val()
			};
		$.ajax({
			url:"http://localhost:8080/student/update",
			type:"PUT",
			"data":JSON.stringify(data),
			crossorigin: true,
			contentType: "application/json",
			dataType   : "json",
			success : function() {
				location.reload(true);
			}
			});
	});
	$("#add-student").click(function() {
		$(".table-div").hide();
		$(".edit-student-div").hide();
		$(".add-student-div").show();
	});
	$("#save").click(function() {
		var data = {
			firstName:$("#add-student-first-name").val(),
			lastName:$("#add-student-last-name").val(),
			sat:$("#add-student-sat").val(),
			gpa:$("#add-student-gpa").val()
			};
		$.ajax({
			url:"http://localhost:8080/student/add",
			type:"POST",
			"data":JSON.stringify(data),
			crossorigin: true,
			contentType: "application/json",
			dataType   : "json",
			success : function() {
				location.reload(true);
			}
			});
	});
});
