package com.hibernate.exercise6.service;

import com.hibernate.exercise6.model.Employee;
import com.hibernate.exercise6.dto.EmployeeDTO;
import com.hibernate.exercise6.dao.PersonDAO;

import java.util.List;
import java.util.Date;
import java.util.Collections;
import java.text.ParseException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class EmployeeService{
	private PersonDAO personDAO = new PersonDAO();
	
	public Employee getEmployeeRecord(Integer employeeID){
		Employee employee = personDAO.getEmployeeRecord(employeeID);
		
		return employee;
	}
	
	public List<EmployeeDTO> getEmployeeByLastName(){
		List<EmployeeDTO> employeeListDTO = personDAO.getEmployeeListByCriteria();
		
		return employeeListDTO;
	}
	
	public List<EmployeeDTO> getEmployeeByGWA(){
		List<EmployeeDTO> employeeListDTO = personDAO.getEmployeeListByQuery();
		Collections.sort(employeeListDTO, EmployeeDTO.employeeGwaComparator);
			
		return employeeListDTO;
	}
	
	public List<EmployeeDTO> getEmployeeByHireDate(){
		String hql = "FROM Employee E ORDER BY E.otherInfo.hireDate";
		List<EmployeeDTO> employeeListDTO = personDAO.getEmployeeListByHqlQuery(hql);
		
		return employeeListDTO;
	}
	
	public List<EmployeeDTO> getEmployeeById(){
		List<EmployeeDTO> employeeListDTO = personDAO.getEmployeeListByQuery();
				
		return employeeListDTO;
	}
	
	public List<EmployeeDTO> searchByLastName(String searchCriteria){
		List<EmployeeDTO> employeeListDTO = personDAO.searchByLastName(searchCriteria);
		
		return employeeListDTO;
	}
	
	public List<EmployeeDTO> searchByGwa(double lowCriteria, double hiCriteria){
		List<EmployeeDTO> employeeListDTO = personDAO.searchByGwa(lowCriteria, hiCriteria);
		
		return employeeListDTO;
	}
	
	public List<EmployeeDTO> searchByHireDate(String tempSearchCriteria){
		List<EmployeeDTO> employeeListDTO = personDAO.searchByHireDate(tempSearchCriteria);
		
		return employeeListDTO;
	}
	
	public void createEmployeeRecord(EmployeeDTO employeeDTO){
		personDAO.addEmployeeToDB(employeeDTO);
	}
	
	public void updateEmployeeRecord(EmployeeDTO employeeDTO){
		personDAO.updateEmployeeRecordToDB(employeeDTO);
	}
	
	public void deleteEmployeeRecord(Integer employeeID){
		
		personDAO.deleteEmployeeRecord(employeeID);
	}
	
	public boolean checkRequiredFields(String firstName, String middleName, String lastName, String streetNo, String brgy, String cityMunicipality, String zipcode, String bday, String dateHired, String gwaString, String employmentStatus){
		boolean requiredFieldsMet = true;
		
		try{
			if(firstName.isEmpty() | 
					middleName.isEmpty() | 
					streetNo.isEmpty() | 
					brgy.isEmpty() | 
					cityMunicipality.isEmpty() | 
					zipcode.isEmpty()){
				
				requiredFieldsMet = false;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return requiredFieldsMet;
	}
	
	public boolean checkDateValidity(String bday, String dateHired){
		boolean dateIsValid = false;
		
		try{
			Date birthday = parseToDate(bday);
			Date hireDate = parseToDate(dateHired);
			dateIsValid = true;
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return dateIsValid;
	}
	
	public Date parseToDate(String day) throws ParseException{
		final String DATE_PATTERN = "yyyy/MM/dd";
		DateFormat dayFormatter = new SimpleDateFormat(DATE_PATTERN);
		Date date =  dayFormatter.parse(day);
		
		return date;
	}	
	
	public void terminateProgram(){
		personDAO.terminateProgram();
	}
}
