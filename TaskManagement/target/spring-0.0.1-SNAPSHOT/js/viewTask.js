$(document).ready(function(){
	$("#taskDetails").hide();
	$("#projectId").change(function(){
		var projectId = $('#projectId').val();
		if(projectId == '100'){
			url='getTaskDetailsForAllTheProjects.action?projectId='+projectId;
		}else{
			url = 'getTaskDetailsForProject.action?projectId='+projectId;
		}
		$.ajax({
			url: url,
			type: "GET",
			contentType: "application/json;charset=utf-8",
			success:function(response){
				//console.log(response);
				var json = $.parseJSON(response);
				console.log(json);
				$("#taskDetails").show();
				$.each(json,function(key,value){
					$("#taskDetails").html('Project:'+key+'<br /><br />');
					$("#taskDetails").append('Task Description :'+value.taskDescription+'<br />');
					$("#taskDetails").append('Task Start Date :'+value.taskStartDate+'<br />');
					$("#taskDetails").append('Task End Date :'+value.taskEndDate+'<br />');
					var empList = value.employeesList;
					$('#taskDetails').append('<table border="1"><tr><th>MID</th><th>Employee Name</th></tr></table>');
					var table = $('#taskDetails').children();
					 for(i=0;i<empList.length;i++){
					    table.append( '<tr><td>' + empList[i].id + '</td><td>' + empList[i].name + '</td></tr>' );
					}
					});
			},error:function(error){
				console.log(error);
			}
			});
		});
	});