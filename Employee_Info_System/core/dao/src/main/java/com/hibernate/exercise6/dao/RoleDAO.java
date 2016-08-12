package com.hibernate.exercise6.dao;

import com.hibernate.exercise6.utilities.*;
import com.hibernate.exercise6.dto.RoleDTO;
import com.hibernate.exercise6.model.Role;

import java.util.List;
import java.util.ArrayList;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.HibernateException;

public class RoleDAO{
	private HibernateUtil hibernateUtil = new HibernateUtil();
	private EntityDTO entityDTO = new EntityDTO();
	private List<Role> roleList;		
	private Role thisRole;
		
		
	public List<RoleDTO> getRoleList(){
		SessionFactory factory = hibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		Transaction transaction = null;
		
		List<RoleDTO> roleListDTO = new ArrayList();
		
		try{
			transaction = session.beginTransaction();	
			roleList = session.createQuery("FROM Role").setCacheable(true).list();
			
			for(Role rol: roleList){
				roleListDTO.add(entityDTO.roleToDTO(rol));
			}
		}catch(HibernateException e){
			if(transaction != null){
				transaction.rollback();
			}
			e.printStackTrace();
		}finally{
			session.close();
		}
		
		return roleListDTO;		
	}		
	
	public Role getRoleFromDBByName(String roleName){
		SessionFactory factory = hibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		Transaction transaction = null;
		
		try{
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(Role.class);
			criteria.add(Restrictions.eq("roleName", roleName));
			thisRole = (Role)criteria.uniqueResult();			
			transaction.commit();
		}catch(HibernateException e){
			if(transaction != null){
				transaction.rollback();
			}
			e.printStackTrace();
		}finally{
			session.close();
		}
		
		return thisRole;
	}	
}
