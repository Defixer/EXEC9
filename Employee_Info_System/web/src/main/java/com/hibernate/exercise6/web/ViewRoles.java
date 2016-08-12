package com.hibernate.exercise6.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import com.hibernate.exercise6.service.*;
import com.hibernate.exercise6.dto.*;
import com.hibernate.exercise6.model.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class ViewRoles extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private RoleService roleService = new RoleService();
	private final String TAB_SPACES = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	
	public ViewRoles(){
		System.out.println("ViewRoles constructor called");
	}
	
	public void init(ServletConfig config) throws ServletException{
		System.out.println("ViewRoles \"Init\" method called");
	}
	
	public void destroy(){
		System.out.println("ViewRoles \"Destroy\" method called");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
			System.out.println("View Roles \"Service\" method(inherited) called");
			System.out.println("View Roles \"DoGet\" method called");
			
			List<RoleDTO> rolesDTOList = roleService.getRoleList();		
			
			response.setContentType("text/html");
			response.setStatus(HttpServletResponse.SC_OK);
			request.setAttribute("roleList", rolesDTOList);
			request.getRequestDispatcher("/WEB-INF/roles.jsp").forward(request, response);
	}
}
