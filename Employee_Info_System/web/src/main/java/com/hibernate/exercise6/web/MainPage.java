package com.hibernate.exercise6.web;

import com.hibernate.exercise6.model.*;
import com.hibernate.exercise6.dto.*;
import com.hibernate.exercise6.service.*;
import com.hibernate.exercise6.utilities.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.DecimalFormat;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;
import java.util.HashSet;


public class MainPage extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private EmployeeService employeeService = new EmployeeService();
	private RoleService roleService = new RoleService();
	private EntityDTO entityDTO = new EntityDTO();
	private EmployeeDTO employeeDTO;
	
	public MainPage(){
		System.out.println("MainPage constructor called");
	}
	
	public void init(ServletConfig config) throws ServletException{
		System.out.println("MainPage \"Init\" method called");
	}
	
	public void destroy(){
		System.out.println("MainPage \"Destroy\" method called");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String searchCriteria = "";
		double lowCriteria = 0,
						highCriteria = 0;
						
		String sort = request.getParameter("sort");		
		String lastNameSearch = request.getParameter("lastNameSearch");
		String gwaSearch = request.getParameter("gwaSearch");
		String hireDateSearch = request.getParameter("hireDateSearch");
		
		try{
			if(sort == null){
				sort = "";
			}
			if(lastNameSearch == null){
				lastNameSearch = "";
			}
			if(gwaSearch == null){
				gwaSearch = "";
			}
			if(hireDateSearch == null){
				hireDateSearch = "";
			}
		
			String cancel = request.getParameter("cancel");
			System.out.println("Action!: " +sort);
		
			System.out.println("MainPage \"Service\" method(inherited) called");
			System.out.println("MainPage \"DoGet\" method called");
		
			List<EmployeeDTO> employeeDTOList = new ArrayList();		
					
			if(sort.equals("byLastName") && (lastNameSearch.equals("") && gwaSearch.equals("") && hireDateSearch.equals(""))){
				employeeDTOList = employeeService.getEmployeeByLastName();
			}
			else if(sort.equals("byGWA") && (lastNameSearch.equals("") && gwaSearch.equals("") && hireDateSearch.equals(""))){
				employeeDTOList = employeeService.getEmployeeByGWA();
			}
			else if(sort.equals("byDateHired") && (lastNameSearch.equals("") && gwaSearch.equals("") && hireDateSearch.equals(""))){
				employeeDTOList = employeeService.getEmployeeByHireDate();
			}				
			else if(sort.equals("")  & (lastNameSearch.equals("") && gwaSearch.equals("") && hireDateSearch.equals(""))){
				employeeDTOList = employeeService.getEmployeeById();
			}			
		
			if(lastNameSearch != null && !lastNameSearch.equals("")){
				searchCriteria = "%" + lastNameSearch + "%";
				employeeDTOList = employeeService.searchByLastName(searchCriteria);
			}
			else if(gwaSearch != null && !gwaSearch.equals("")){
				lowCriteria = Double.parseDouble(gwaSearch) - 0.01;
				highCriteria = Double.parseDouble(gwaSearch) + 0.01;
	
				employeeDTOList = employeeService.searchByGwa(lowCriteria, highCriteria);
			}
			else if(hireDateSearch != null && !hireDateSearch.equals("")){		
				employeeDTOList = employeeService.searchByHireDate(hireDateSearch);
			}
		
			employeeDTOList.forEach(System.out::print);
			response.setContentType("text/html");
			response.setStatus(HttpServletResponse.SC_OK);
			request.setAttribute("employees",employeeDTOList);
			request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
		}catch(NullPointerException e){
			e.printStackTrace();
		}					
	}	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		boolean isEmployed = false;
		
		try{
			System.out.println("POST METHOD!");
			String employeeId = request.getParameter("employeeId");
			String title = request.getParameter("title");
			String firstName = request.getParameter("firstName");
			String middleName = request.getParameter("middleName");
			String lastName = request.getParameter("lastName");
			String suffix = request.getParameter("suffix");
			
			String streetNo = request.getParameter("streetNo");
			String brgy = request.getParameter("brgy");
			String cityMunicipality = request.getParameter("cityMunicipality");
			String zipcode = request.getParameter("zipcode");
			
			String bday = request.getParameter("birthday");			
			String dateHired = request.getParameter("hireDate");			
			String gwaString = request.getParameter("gwa");				
			String employmentStatus = request.getParameter("isEmployed");
						
			String [] roles = request.getParameterValues("role");
			String [] contactsType = request.getParameterValues("contacts");
			String landlineValue = request.getParameter("landline");
			String mobileValue = request.getParameter("mobile");
			String emailValue = request.getParameter("email");
			String landlineId = request.getParameter("landlineId");
			String mobileId = request.getParameter("mobileId");
			String emailId = request.getParameter("emailId");
			
			employeeId = employeeId.isEmpty() ? "0":employeeId;
			emailId = emailId.isEmpty() ? "0":emailId;
			mobileId = mobileId.isEmpty() ? "0":mobileId;
			landlineId = landlineId.isEmpty() ? "0":landlineId;
			
			if(employmentStatus.equals("no")){
				dateHired = "9999/12/31";
			}
			
			boolean requiredFieldsMet = employeeService.checkRequiredFields(firstName, middleName, lastName, streetNo, brgy, cityMunicipality, zipcode, bday, dateHired, gwaString, employmentStatus);
			
			boolean dateIsValid = employeeService.checkDateValidity(bday, dateHired);
					
			if(requiredFieldsMet && dateIsValid){				
				Date birthday = employeeService.parseToDate(bday);
				/*if(dateHired.equals("")){
					dateHired = "9999/12/31";
				}*/
				Date hireDate = employeeService.parseToDate(dateHired);
				double gwa = Double.parseDouble(gwaString);			
				
				if(employmentStatus.equals("yes")){
					isEmployed = true;
				}
			
				NameDTO nameDTO = new NameDTO(title, firstName, middleName, lastName, suffix);
				AddressDTO addressDTO = new AddressDTO(streetNo, brgy, cityMunicipality, zipcode);
				OtherInfoDTO otherInfoDTO = new OtherInfoDTO(birthday, hireDate, gwa, isEmployed); 
			
				employeeDTO = new EmployeeDTO(nameDTO, addressDTO, otherInfoDTO);
			
				Set<ContactDTO> contactsDTO = new HashSet();
			
				for(String cTString: contactsType){
					ContactDTO contactDTO = new ContactDTO();
					contactDTO.setContactType(cTString);
				
					if(cTString.equals("landline")){
						contactDTO.setContactDetails(landlineValue);
						contactDTO.setId(Integer.valueOf(landlineId));					
					}
					else if(cTString.equals("mobile")){
						contactDTO.setContactDetails(mobileValue);
						contactDTO.setId(Integer.valueOf(mobileId));					
					}
					else if(cTString.equals("email")){
						contactDTO.setContactDetails(emailValue);
						contactDTO.setId(Integer.valueOf(emailId));				
					}				
					contactsDTO.add(contactDTO);
				}
			
				if(roles != null){
					Set<RoleDTO> rolesDTO = new HashSet();
			
					for(String roleName: roles){				
						Role role = roleService.getRoleFromDBByName(roleName);
						RoleDTO roleDTO = entityDTO.roleToDTO(role);				
						rolesDTO.add(roleDTO);
					}
					employeeDTO.setRole(rolesDTO);
				}
						
				employeeDTO.setContacts(contactsDTO);			
								
				if(employeeId.isEmpty() || employeeId.equals("0")){
					employeeService.createEmployeeRecord(employeeDTO);
				}
				else{
					employeeDTO.setId(Integer.valueOf(employeeId));
					employeeService.updateEmployeeRecord(employeeDTO);
				}
				
				response.sendRedirect(request.getContextPath() + "/MainPage?message=SUCCESS");
			}
			else{
				List<String> errors = new ArrayList();
				if(!requiredFieldsMet){
					errors.add("Required Fields Missing");
				}
				if(!dateIsValid){
					errors.add("Invalid Date Format");
				}
				
				request.setAttribute("errors", errors);
				request.setAttribute("employee", employeeDTO);
				request.getRequestDispatcher("/WEB-INF/person.jsp").forward(request, response);				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
