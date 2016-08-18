package com.hibernate.exercise6.web;

import java.io.IOException;
import java.io.PrintWriter;

import com.hibernate.exercise6.model.Employee;
import com.hibernate.exercise6.service.*;
import com.hibernate.exercise6.dto.*;
import com.hibernate.exercise6.utilities.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.web.servlet.view.RedirectView;

public class EmployeeController extends MultiActionController{
	private EmployeeService employeeService;
	private EntityDTO entityDTO;
	
	public void setService(EmployeeService employeeService){
		this.employeeService = employeeService;
	}
	
	public ModelAndView listEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		ModelAndView model = new ModelAndView("index");
		
		String sort = request.getParameter("sort");
		
		if(sort == null){
			sort = "";
		}
		
		try{
			List<EmployeeDTO> employeeDTOList = new ArrayList();
			
			if(sort.equals("byLastName")){
				employeeDTOList = employeeService.getEmployeeByLastName();
			}
			else if(sort.equals("byGWA")){
				employeeDTOList = employeeService.getEmployeeByGWA();
			}
			else if(sort.equals("byDateHired")){
				employeeDTOList = employeeService.getEmployeeByHireDate();
			}
			else if(sort.equals("")){
				employeeDTOList = employeeService.getEmployeeById();
			}
			
			personDTOList.forEach(System.out::print);
			model.addObject("employees", employeeDTOList);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return model;
	}
	
	public ModelAndView deleteEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		ModelAndView model = new ModelAndView("index");
		String id = request.getParameter("id");
		Employee employee = employeeService.getEmployeeRecord(Integer.parseInt(id));
		EmployeeDTO employeeDTO = entityDTO.toDTO(employee);
		
		List message = new ArrayList();
		message.add("Employee Record Deleted Successfully" +
								"\nID: " + id + "\t" + employeeDTO.getName());
		employeeService.deleteEmployeeRecord(Integer.parseInt(id));
		model.addObject("message", message);
		List<EmployeeDTO> employeeDTOList = new Arraylist();
		employeeDTOList = employeeService.getEmployeeById();
		model.addObject("employees", employeeDTOList);
		
		return model;
	}
}
