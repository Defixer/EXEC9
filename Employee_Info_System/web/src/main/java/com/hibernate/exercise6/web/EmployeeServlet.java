package com.hibernate.exercise6.web;

import java.io.IOException;
import java.io.PrintWriter;

import com.hibernate.exercise6.model.Employee;
import com.hibernate.exercise6.service.*;
import com.hibernate.exercise6.dto.*;
import com.hibernate.exercise6.utilities.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class EmployeeServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private EmployeeService employeeService = new EmployeeService();
	private EntityDTO entityDTO = new EntityDTO();
	
	public EmployeeServlet(){
		super();
		System.out.println("EmployeeServlet constructor called");
	}
	
	public void init(ServletConfig config) throws ServletException{
		System.out.println("EmployeeServlet \"Init\" method called");
	}
	
	public void destroy(){
		System.out.println("EmployeeServlet \"Destroy\" method called");
		employeeService.terminateProgram();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String id = request.getParameter("id");
		
		if(id == null){
			id = "";
		}
		else{
			Integer employeeID = Integer.parseInt(id);
			Employee employee = employeeService.getEmployeeRecord(employeeID);
			EmployeeDTO employeeDTO = entityDTO.toDTO(employee);
			
			request.setAttribute("employee", employeeDTO);
			request.setAttribute("id", id);
		}
		
		HttpSession session = request.getSession(false);
		if(session != null){
			String uname = (String)session.getAttribute("uname");
			String emailId = (String)session.getAttribute("emailId");
		}
		
		request.getRequestDispatcher("/WEB-INF/person.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String id = request.getParameter("id");
		Integer employeeID = Integer.parseInt(id);
		employeeService.deleteEmployeeRecord(employeeID);
		response.sendRedirect(request.getContextPath()+ "/MainPage?delete=SUCCESS");
	}
}
