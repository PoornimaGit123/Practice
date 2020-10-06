<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View All Tasks Page</title>
<script type="text/javascript"
	src='<c:url value="/js/jquery-1.11.3.min.js"/>'></script>
<script type="text/javascript" src='<c:url value="/js/viewTask.js"/>'></script>
</head>
<body>
<p>View Tasks</p>
${errorMsg}
<br />
Filter By Project
&nbsp;&nbsp; 
<select id="projectId">
<option value="-1" label="Select Project"/>
<option value="100" label="All Projects"/>
<c:forEach items="${projectList}" var="project">
	<option value="${project.id}">${project.name}</option>
</c:forEach>
</select>
<br />
<br />
<div id="taskDetails">

</div>




</body>
</html>