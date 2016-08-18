package com.hibernate.exercise6.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import com.hibernate.exercise6.service.*;
import com.hibernate.exercise6.dto.*;
import com.hibernate.exercise6.model.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class RolesController extends MultiActionController{
	private RoleService roleService;
	
	public void setService(RoleService roleService){
		this.roleService = roleService;
	}
	
	public ModelAndView viewRoles(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		List<RoleDTO> roleDTOList = roleService.getRoleList();
		ModelAndView model = new ModelAndView("viewRoles");
		model.addObject("roles", roleNames);
		return model;
	}
	
	public ModelAndView test(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int roleID = Integer.parseInt(request.getParameter("roleID"));
		List<RoleDTO> roleDTOList = roleService.getRoleList();
		ModelAndView model = new ModelAndView("test");
		model.addObject("roles", roleDTOList);
		model.addObject("id", roleID);
		return model;
	}
}
