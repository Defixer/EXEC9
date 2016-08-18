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

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class EditEmployeeController extends MultiActionController{
	private EmployeeService employeeService;
	private EntityDTO entityDTO;
	
	public void setService(EmployeeService employeeService){
		this.employeeService = employeeService;
	}
	
	public ModelAndView loadEditor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		ModelAndView model = new ModelAndView("employee");
		
		String id = request.getParameter("id");
		
		if(id == null){
			id = "";
		}
		else{
			Employee employee = employeeService.getEmployeeRecord(Integer.parseInt(id));
			EmployeeDTO employeeDTO = entityDTO.toDTO(employee);
			OtherInfoDTO otherInfoDTO = employeeDTO.getOtherInfo();
			model.addObject("employee", employee);
			model.addObject("employeeID", id);
			model.addObject("bday", otherInfoDTO.getBirthday());
			
			employeeDTO.getContacts().stream.forEach(
				c->{
					model.addObject(c.getContactType().toString() + "Checked", true);
					model.addObject(c.getContactType().toString() + "Value", c.getContactDetails());
					model.addObject(c.getContactType().toString() + "Id", c.getId());
				}
			);
			
			employeeDTO.getRole().stream().forEach(
				r->{
					model.addObject(r.getRoleName() + "Type", r.getRoleName());
				}
			);
		}
		
		List<String> roleList = new ArrayList();
		roles.add("Software Developer");
		roles.add("Quality Assurance Engineer");
		roles.add("Infrastructure Engineer");
		roles.add("Finance Officer");
		roles.add("Human Resources Officer");
		
		model.addObject("roles", roleList);
		
		List<String> contactList = new ArrayList();
		contactList.add("landline");
		contactList.add("mobile");
		contactList.add("email");
		
		model.addObject("contactList", contactList);
		
		return model;		
	}
	
	public ModelAndView edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		return loadEdit(request, response);
	}
	
	public ModelAndView add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		return loadEditor(request, response);
	}
	
	public ModelAndView success(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		ModelAndView model = new ModelAndView("index");
		return model;
	}
	
	public ModelAndView update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		return save(request, response);s
	}
	
	public ModelAndView save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		ModelAndView model = new ModelAndView("employee");
		
		System.out.println("POST METHOD");
		
		try{
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
				
				model = new ModelAndView("redirect:/ListPeople?");
				List message =  new ArrayList();
				
				if(employeeId.isEmpty() || employeeId.equals("0")){
					employeeService.createEmployeeRecord(employeeDTO);
					message.add("Employee Record Created Successfully\nName: " +
												employeeDTO.getName());
				}
				else{
					employeeDTO.setId(Integer.valueOf(employeeId));
					employeeService.updateEmployeeRecord(employeeDTO);
					message.add("Employee Record Updated Successfully\nName: " +
												employeeDTO.getName());
				}
				
				model.addObject("message", message);
				
				List<EmployeeDTO> employeeDTOList = employeeService.getEmployeeById();
				
				model.addObject("employees", employeeDTOList);				
			}
			else{
				List<String> errors = new ArrayList();
				if(!requiredFieldsMet){
					errors.add("Required Fields Missing");
				}
				if(!dateIsValid){
					errors.add("Invalid Date Format");
				}
			
				String id = request.getParameter("employeeID");
			
				model.addObject("errors", errors);
				model.addObject("employeeID", id);
				model.addObject("employee", employeeDTO);			
			
				List<String> roleList = new ArrayList();
				roles.add("Software Developer");
				roles.add("Quality Assurance Engineer");
				roles.add("Infrastructure Engineer");
				roles.add("Finance Officer");
				roles.add("Human Resources Officer");
		
				model.addObject("roles", roleList);
		
				List<String> contactList = new ArrayList();
				contactList.add("landline");
				contactList.add("mobile");
				contactList.add("email");
		
				model.addObject("contactList", contactList);
			
				if(employeeDTO.getContacts() != null){
					for(ContactDTO c: employeeDTO.getContacts()){
						model.addObject(c.getContactType().toString() + "Checked", true);
						model.addObject(c.getContactType().toString() + "Value", c.getContactDetails());
						model.addObject(c.getContactType().toString() + "Id", c.getId());
					}
				}
			
				if(employeeDTO.getRole() != null){
					for(RoleDTO r: employeeDTO.getRole()){
						model.addObject(r.getRoleName() + "Type", r.getRoleName());
					}
				}		
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	
		return model;	
	}
}
