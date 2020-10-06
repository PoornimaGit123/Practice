<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript"
	src='<c:url value="/js/jquery-1.11.3.min.js"/>'></script>
<script type="text/javascript" src='<c:url value="/js/addTask.js"/>'></script>

<!-- <script src="http://code.jquery.com/jquery-1.11.3.min.js"></script> -->
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add task Page</title>
${errorMsg}
</head>
<body>

	<table>
		<form:form id="taskForm" commandName="taskForm" action="saveTask.action">
			<tr>
				<th>Assign Tasks</th>
				<th></th>
			</tr>
			<tr>
				<td>Project*</td>
				<td><%-- <form:select path="selectedProjectId" id="projectId">
						<form:option value="-1">Select Project</form:option>
						<c:forEach items="${projectMap}" var="project">
							<form:option value="${project.key}">${project.value}</form:option>
						</c:forEach>
					</form:select> --%>
					<form:select path="projectVO" id="projectId">
					<form:option value="-1" label="Select Project"/>
					<form:options items="${projectList}" itemValue="id" itemLabel="name" />
					</form:select>
					<form:errors path="projectVO"/></td>
			</tr>
			<tr>
				<td>Description*</td>
				<td><form:input path="description" />
				<form:errors path="description"/></td>
			</tr>
			<tr>
				<td>Start Date of Task[dd-mm-yyyy]*</td>
				<td><form:input path="startDate" />
				<form:errors path="startDate"/></td>
			</tr>
			<tr>
				<td>Due Date of Task[dd-mm-yyyy]*</td>
				<td><form:input path="dueDate" />
				<form:errors path="dueDate"/></td>
			</tr>
			<tr>
				<td>Who should do this*</td>
				<td><form:select path="projectEmployeeList" id="employeeList">
						<form:options items="${employeeList}" itemLabel="name"
							itemValue="id" />
					</form:select>
					<form:errors path="projectEmployeeList"/></td>
			</tr>
			<tr>
				<td><input type="submit" value="Add a Task" /></td>
				<td><button>Cancel</button></td>
			</tr>
		</form:form>
	</table>
</body>
</html>