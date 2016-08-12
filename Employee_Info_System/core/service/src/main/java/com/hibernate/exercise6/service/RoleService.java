package com.hibernate.exercise6.service;

import com.hibernate.exercise6.dao.RoleDAO;
import com.hibernate.exercise6.dto.RoleDTO;
import com.hibernate.exercise6.model.Role;
import java.util.List;

public class RoleService{
	private RoleDAO roleDAO = new RoleDAO();
	
	public List<RoleDTO> getRoleList(){
		List<RoleDTO> roleListDTO = roleDAO.getRoleList();
		
		return roleListDTO;
	}
	
	public Role getRoleFromDBByName(String roleName){
		Role thisRole = roleDAO.getRoleFromDBByName(roleName);
		
		return thisRole;
	}
}
