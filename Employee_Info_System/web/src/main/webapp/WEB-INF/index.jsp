<!-- EDITS THE MAIN PAGE HTML-->
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import = "com.hibernate.exercise6.dto.*"%>
<%@ page import = "com.hibernate.exercise6.model.*"%>
<%@ page import = "java.io.*"%>
<%@ page import = "java.util.*"%>

<html>
	<head>
		<title>Main Page</title>
	</head>
	<body>
		<div>
			<div class = "row">
				<div class = "column column-6" align = "left">
					<a href = ${pageContext.request.contextPath}/MainPage>Main Page</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<a href = "${pageContext.request.contextPath}/EmployeeServlet">Add New Person</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<a href = "${pageContext.request.contextPath}/ViewRoles">View Roles<p><!--inserts a blank paragraph acting as newline--></p></a>
					
					<form action = "${pageContext.request.contextPath}/MainPage" method = "get">
						<label>List Employees:<br/></label>
						<input type = "radio" name = "sort" value = "byLastName">By Last Name<br>
						<input type = "radio" name = "sort" value = "byGWA">By GWA<br>
						<input type = "radio" name = "sort" value = "byDateHired">By Date Hired<br/>
						<input type = "submit" value = "Sort"/>						
					</form>
					
					<form action = "${pageContext.request.contextPath}/MainPage" method = "get">
						<input type = "text" name = "lastNameSearch" placeholder = "Last Name"/>					
						<input type = "text" name = "gwaSearch" placeholder = "GWA"/>
						<input type = "text" name = "hireDateSearch" placeholder = "Hire Date (YYYY-MM-DD)"/>
						<input type = "submit" name = "search" value = "Search"/><p></p>
					</form>
					
					<table>
						<tr>
							<th>ID</th>
							<th>Full Name</th>
							<th>Address</th>
							<th>GWA</th>
							<th>Birthday</th>
							<th>Employment Status</th>
							<th>Contact Information</th>
							<th>Actions</th>
						</tr>
						<c:set var = "employees" value = '${employees}'/>
						<c:forEach var = "employee" items = "${employees}">
							<c:set var = "employeeId" value = '${employee.id}'/>
							<c:set var = "name" value = '${employee.getName()}'/>
							<c:set var = "address" value = '${employee.getAddress()}'/>
							<c:set var = "otherInfo" value = '${employee.getOtherInfo()}'/>
							<c:set var = "gwa" value = '${otherInfo.getGwa()}'/>
							<c:set var = "gwaString" value = '${otherInfo.getGwaString(gwa)}'/>
							<c:set var = "birthday" value = '${otherInfo.getBirthday()}'/>
							<c:set var = "hireDate" value = '${otherInfo.getHireDate()}'/>
							<c:set var = "roles" value = '${employee.getRole()}'/>
							<c:set var = "contacts" value = '${employee.getContacts()}'/>
							<c:set var = "isEmployed" value = '${otherInfo.getIsEmployed()}'/>
							
							<tr>								
								<td><c:out value = "${employeeId}"/></td>
								<td>
									<c:out value = "${name.title}"/>
									<c:out value = "${name.firstName}"/>	
									<c:out value = "${name.middleName}"/>		
									<c:out value = "${name.lastName}"/>	
									<c:out value = "${name.suffix}"/>			
								</td>
								<td>
									<c:out value = "${address.streetNo}"/>
									<c:out value = "${address.brgy}"/>
									<c:out value = "${address.cityMunicipality}"/>
									<c:out value = "${address.zipcode}"/>
								</td>
								<td><c:out value = "${gwaString}"/></td>
								<td><fmt:formatDate dateStyle = "long" value = "${birthday}"/></td>
								<td>
									<c:choose>
										<c:when test = "${isEmployed}">
											<b>Hire Date:</b>
											<br/>
											<fmt:formatDate dateStyle = "long" value = "${hireDate}"/><br/><p></p>
											<b>Roles:</b>
											<br/>
											<c:forEach var = "role" items = '${roles}'>
												<c:out value = "${role.roleName}"/>
												<br/>												
											</c:forEach>
										</c:when>
										<c:otherwise>
											NOT EMPLOYED
										</c:otherwise>
									</c:choose>
								</td>
								<td>
									<c:forEach var = "contact" items = '${contacts}'>
										<b>
											<c:out value = "${contact.getContactType()}"/>
										</b>
										: <!--simple separator-->
										<c:out value = "${contact.getContactDetails()}"/>
										<br/>
									</c:forEach>
									<c:if test = "${contacts.isEmpty()}">
										NO CONTACTS AVAILABLE
									</c:if>
									<br/>
								</td>
								<td>
									<form class = "buttons" action = "${pageContext.request.contextPath}/EmployeeServlet" method= "get">
										<input type = "hidden" name = "id" value = '${employeeId}'>
										<input type = "submit" value = "Edit"/>						
									</form>
									<form class = "buttons" action = "${pageContext.request.contextPath}/EmployeeServlet" method = "post">
										<input type = "hidden" name = "id" value = "${employeeId}">
										<input type = "submit" onclick = "return confirm('WARNING!!! : Do you really want to delete employee record of \n\t\t\t\t ${name.firstName} ${name.middleName} ${name.lastName}?')" value = "Delete"/>
									</form>									
								</td>
							</tr>
						</c:forEach>
					</table>					
				</div>
			</div>
		</div>
		<br/><footer>
			<label>&copy August 2016</label><br/>
			<label>Mamen &#8482</label>
		</footer>
		<style>
			table{
				border-collapse: collapse;
				width: 100%;
			}			
			table, th, td{
				border: 1px solid black;
			}
			td{
				text-align: center;
			}
			footer{
				text-align: center;
				font-size: 9;
			}
			.buttons{
				align-items: center;
			}
		</style>
	</body>
</html>
