<!-- EDITS THE ADD NEW PERSON HTML-->
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import = "com.hibernate.exercise6.dto.*"%>
<%@ page import = "java.io.*"%>
<%@ page import = "java.util.*"%>

<html>
	<head>
		<title>Person</title>
	</head>
	<body>
		<div>
			<div class = "row">
				<c:forEach var = "errors" items = "${errors}">
					<div class = "column column-6" align = "center"><span style = "color:red">${errors}</span>					
					</div>					
				</c:forEach>
				
				<c:set var = "employee" value = "${employee}"/>
				<c:set var = "name" value = "${employee.getName()}"/>
				<c:set var = "address" value = "${employee.getAddress()}"/>
				<c:set var = "otherInfo" value = "${employee.getOtherInfo()}"/>
				<a href = "${pageContext.request.contextPath}/MainPage">Main Page</a><p></p>
				
				<form action = "${pageContext.request.contextPath}/MainPage" method = "post">
					<input type = "hidden" name = "employeeId" value = "${employee.id}">
					<fmt:formatDate pattern = "yyyy/MM/dd" value = "${otherInfo.birthday}" var = "birthday"/>
					<fmt:formatDate pattern = "yyyy/MM/dd" value = "${otherInfo.hireDate}" var = "hireDate"/>
					<div class = "column column-6" align = "center">
					<fieldset style = "padding:1%">
						<legend>Full Name</legend>
						<table>
							<div>
								<tr>
									<td>
										Title
									</td>
									<td>
										<input type = "text" name = "title" value = "${name.title}">
									</td>
								</tr>
								<tr>
									<td>
										<span class = "required">*</span></div>First Name
									</td>
									<td>
										<input type = "text" name = "firstName" value = "${name.firstName}">
									</td>
								</tr>
								<tr>
									<td>
										<span class = "required">*</span></div>Middle Name
									</td>
									<td>
										<input type = "text" name = "middleName" value = "${name.middleName}">
									</td>
								</tr>
								<tr>
									<td>
										<span class = "required">*</span></div>Last Name
									</td>
									<td>
										<input type = "text" name = "lastName" value = "${name.lastName}">
									</td>
								</tr>
								<tr>
									<td>
										Suffix
									</td>
									<td>
										<input type = "text" name = "suffix" value = "${name.suffix}">
									</td>
								</tr>								
							</div>
						</table>						
					</fieldset>				
					</div>
					
						<div class = "column column-6" align = "center">
						<fieldset style = "padding:1%">
							<legend>Address</legend>
							<table>
								<div>
									<tr>
										<td>
											<span class = "required">*</span></div>Street No
										</td>
										<td>
											<input type = "text" name = "streetNo" value = "${address.streetNo}">
										</td>
									</tr>
									<tr>
										<td>
											<span class = "required">*</span></div>Barangay
										</td>
										<td>
											<input type = "text" name = "brgy" value = "${address.brgy}">
										</td>
									</tr>
									<tr>
										<td>
											<span class = "required">*</span></div>City/Municipality
										</td>
										<td>
											<input type = "text" name = "cityMunicipality" value = "${address.cityMunicipality}">
										</td>
									</tr>
									<tr>
										<td>
											<span class = "required">*</span></div>Zipcode
										</td>
										<td>
											<input type = "text" name = "zipcode" value = "${address.zipcode}">
										</td>
									</tr>
								</div>
							</table>
						</fieldset>
					</div>	
					
					<div class = "column column-6" align = "center">
						<fieldset style = "padding:1%">
							<legend>Other Information</legend>							
							<table>
								<div>
									<tr>
										<td>
											<span class = "required">*</span></div>Birthday
										</td>
										<td>
											<input type = "text" name = "birthday" value = "${otherInfo.dateToString(otherInfo.getBirthday())}" placeholder = "YYYY/MM/DD">
										</td>
									</tr>
									<tr>
										<td>
											<span class = "required">*</span>Employed
										</td>
										<td>
											<c:if test = "${otherInfo.getIsEmployed() == 'true'}">
												<c:set var = "radioButtonTrue" value = "checked"/>
											</c:if>
											<c:if test = "${otherInfo.getIsEmployed() == 'false'}">
												<c:set var = "radioButtonFalse" value = "checked"/>
											</c:if>
											<input type = "radio" name = "isEmployed" value = "yes" ${radioButtonTrue}>Yes
											<input type = "radio" name = "isEmployed" value = "no" ${radioButtonFalse}>Not Employed
										</td>
										<td>
											<c:if test = "${otherInfo.dateToString(otherInfo.getHireDate()) == '9999/12/31'}">
												<c:set var = "hireDateValue" value = ""/>
											</c:if>
											<c:if test = "${otherInfo.dateToString(otherInfo.getHireDate()) != '9999/12/31'}">
												<c:set var = "hireDateValue" value = "${otherInfo.dateToString(otherInfo.getHireDate())}"/>
											</c:if>		
											<tr>									
												<td>
													<span class = "required">*</span>Hire Date
												</td>
												<td>
													<input type = "text" name = "hireDate" value = "${hireDateValue}" placeholder = "(if employed) YYYY/MM/DD">
												</td>
											</tr>
										</td>
									</tr>
									<tr>
										<td>
											<span class = "required">*</span></div>GWA
										</td>
										<td>
											<input type = "text" name = "gwa" value = "${otherInfo.getGwaString(otherInfo.getGwa())}">
										</td>
									</tr>
								</div>
							</table>
						</fieldset>					
					</div>
					
					<div class = "column column-6" align = "center">
						<fieldset style = "padding:1%">
							<legend>Contact</legend>
							<table>
								<div>
									<c:set var = "landlineChecked" value = ""/>
									<c:set var = "mobileChecked" value = ""/>
									<c:set var = "emailChecked" value = ""/>
									
									<c:set var = "landlineValue" value = ""/>
									<c:set var = "mobileValue" value = ""/>
									<c:set var = "emailValue" value = ""/>
									
									<c:set var = "landlineId" value = ""/>
									<c:set var = "mobileId" value = ""/>
									<c:set var = "emailId" value = ""/>
									
									<c:forEach var = "contact" items = "${employee.getContacts()}">
										<c:if test = "${contact.contactType == 'landline'}">
											<c:set var = "landlineChecked" value = "checked"/>
											<c:set var = "landlineValue" value = "${contact.contactDetails}"/>
											<c:set var = "landlineId" value = "${contact.id}"/>										
										</c:if>
										
										<c:if test = "${contact.contactType == 'mobile'}">
											<c:set var = "mobileChecked" value = "checked"/>
											<c:set var = "mobileValue" value = "${contact.contactDetails}"/>
											<c:set var = "mobileId" value = "${contact.id}"/>
										</c:if>
										
										<c:if test = "${contact.contactType == 'email'}">
											<c:set var = "emailChecked" value = "checked"/>
											<c:set var = "emailValue" value = "${contact.contactDetails}"/>
											<c:set var = "emailId" value= "${contact.id}"/>
										</c:if>										
									</c:forEach>
									<tr>
										<td>
											<input type = "checkbox" onclick = "document.getElementById('landline').disabled=!this.checked;" name = "contacts" value = "landline" ${landlineChecked}/>
										</td>
										<td>
											Landline
										</td>
										<td>
											<input type = "hidden" name = "landlineId" value = "${landlineId}">
											<input type = "text" name = "landline" id = "landline" value = "${landlineValue}">
										</td>
									</tr>

									<tr>
										<td>
											<input type = "checkbox" onclick = "document.getElementById('mobile').disabled=!this.checked;" name = "contacts" value = "mobile" ${mobileChecked}/>
										</td>
										<td>
											Mobile
										</td>
										<td>
											<input type = "hidden" name = "mobileId" value = "${mobileId}">
											<input type = "text" name = "mobile" id = "mobile" value = "${mobileValue}">
										</td>
									</tr>
									
									<tr>
										<td>
											<input type = "checkbox" onclick = "document.getElementById('email').disabled=!this.checked;" name = "contacts" value = "email" ${emailChecked}/>
										</td>
										<td>
											Email
										</td>
										<td>
											<input type = "hidden" name = "emailId" value = "${emailId}">
											<input type = "text" name = "email" id = "email" value = "${emailValue}">
										</td>
									</tr>
								</div>
							</table>
						</fieldset>
					</div>	
					<div class = "column column-6" align = "center">
						<fieldset style = "padding:1%">
							<legend>Roles<font size = "2" color = "red"> (if employed)</font></legend>
							<table>						
								<c:set var = "sdChecked" value = ""/>
								<c:set var = "qaeChecked" value = ""/>
								<c:set var = "ieChecked" value = ""/>
								<c:set var = "hroChecked" value = ""/>
								<c:set var = "foChecked" value = ""/>
							
								<c:forEach var = "role" items = "${employee.getRole()}">
									<c:if test = "${role.roleName.equals('Software Developer')}">
										<c:set var = "sdChecked" value = "checked"/>
									</c:if>										
									<c:if test = "${role.roleName.equals('Quality Assurance Engineer')}">
										<c:set var = "qaeChecked" value = "checked"/>
									</c:if>
									<c:if test = "${role.roleName.equals('Infrastructure Engineer')}">
										<c:set var = "ieChecked" value = "checked"/>
									</c:if>
									<c:if test = "${role.roleName.equals('Human Resources Officer')}">
										<c:set var = "hroChecked" value = "checked"/>
									</c:if>
									<c:if test = "${role.roleName.equals('Finance Officer')}">
										<c:set var = "foChecked" value = "checked"/>
									</c:if>
								</c:forEach>
								<td>
									<input type = "checkbox" name = "role" value = "Software Developer" ${sdChecked}/>Software Developer<br/>
									<input type = "checkbox" name = "role" value = "Quality Assurance Engineer" ${qaeChecked}/>Quality Assurance Engineer<br/>
									<input type = "checkbox" name = "role" value = "Infrastructure Engineer" ${ieChecked}/>Infrastructure Engineer<br/>
									<input type = "checkbox" name = "role" value = "Human Resources Officer" ${hroChecked}/>Human Resources Officer<br/>
									<input type = "checkbox" name = "role" value = "Finance Officer" ${foChecked}/>Finance Officer<br/>
								</td>
							</table>
						</fieldset>
					</div>		
					
					<div align = "center">
						<c:choose>
							<c:when test = "${empty id}">
								<input type = "submit" value = "Add" align = "center"/>
							</c:when>
							<c:otherwise>
								<p></p>
								<input type = "submit" value = "Update" align = "center"/>
							</c:otherwise>
						</c:choose>
					</div>
				</form>
			</div>
			<div align = "center">
				<form action = "${pageContext.request.contextPath}/MainPage">
					<input type = "submit" value = "Cancel"/>
				</form>
			</div>
		</div>
		<br/><footer>
			<label>&copy August 2016</label><br/>
			<label>Mamen &#8482</label>
		</footer>
		<style>
			footer{
				text-align: center;
				font-size: 9;
			}
			.required{
				color:red;
			}
		</style>
	</body>
</html>
