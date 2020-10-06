$(document).ready(function(){
	$("#projectId").change(function(){
		var projectId = $('#projectId').val();
		$.ajax({
			url: 'getEmployeeListForProject.action?projectId='+projectId,
			type: "GET",
			success:function(response){
				console.log(response);
				$("#employeeList").empty();
				$.each(response,function(key,value){
					$("#employeeList").append('<option value="'+value.id+'">'+value.name+'</option>');
				});
			},error:function(error){
				console.log(error);
			}
			});
		});
	});
