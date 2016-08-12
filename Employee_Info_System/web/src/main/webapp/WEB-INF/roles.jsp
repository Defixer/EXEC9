<!-- EDITS THE VIEW ROLE HTML -->
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import = "com.hibernate.exercise6.dto.*"%>
<%@ page import = "java.io.*"%>
<%@ page import = "java.util.*"%>

<html>
	<head>
		<title>View Roles</title>
	</head>
	<body>
		<a href = ${pageContext.request.contextPath}/MainPage>Main Page<br/></a>
		<br>
		
		
		
		</br>
		<c:set var = "roles" value = "${roleList}"/>		
		<label><b>Role Information</b></label>	
		<table>			
			<c:forEach var = "role" items = "${roles}">
				<c:set var = "roleId" value = '${role.id}'/>
				<c:set var = "roleName" value = '${role.roleName}'/>				
				<c:set var = "employees" value = '${role.getEmployee()}'/>				
				<tr>
					<br/><br/><br/><b><c:out value = "${roleName}"/></b><br/><br/>
					<label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ID&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Employee Name</label><br/>
					<c:forEach var = "employee" items = "${employees}">
						<c:set var = "name" value = '${employee.getName()}'/>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<c:out value = "${employee.id}"/>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<c:out value = "${name.firstName}"/>
						<c:out value = "${name.middleName}"/>
						<c:out value = "${name.lastName}"/>
						<c:out value = "${name.suffix}"/><br/>
					</c:forEach>										
				</tr>						
			</c:forEach>			
		</table>	
		<br/><footer>
			<label>&copy August 2016</label><br/>
			<label>Mamen &#8482</label>
		</footer>
		<style>
			footer{
				text-align: center;
				font-size: 9;
			}
		</style>
	</body>
<html/>
