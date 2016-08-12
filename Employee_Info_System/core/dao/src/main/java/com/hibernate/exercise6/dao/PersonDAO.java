package com.hibernate.exercise6.dao;

import com.hibernate.exercise6.utilities.*;
import com.hibernate.exercise6.model.*;
import com.hibernate.exercise6.dto.*;

import java.util.List;
import java.util.ArrayList;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class PersonDAO{
	private HibernateUtil hibernateUtil = new HibernateUtil();
	private EntityDTO entityDTO = new EntityDTO();
	private Employee employee;
	private Criteria criteria;
	private Query query;
	private List<Employee> employeeList;
	
	public Employee getEmployeeRecord(Integer employeeID){
		SessionFactory factory = hibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		Transaction transaction = null;
		
		try{
			transaction = session.beginTransaction();
			employee = (Employee)session.get(Employee.class, employeeID);
			
		}catch(HibernateException e){
			if(transaction != null){
				transaction.rollback();
			}
			e.printStackTrace();
		}finally{
			session.close();
		}
		return employee;		
	}
	
	public List<EmployeeDTO> getEmployeeListByCriteria(){
		SessionFactory factory = hibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		Transaction transaction = null;
		
		try{
			transaction = session.beginTransaction();
			criteria = session.createCriteria(Employee.class);
			criteria.addOrder(Order.asc("nameInfo.lastName"));
			employeeList = criteria.list();			
			transaction.commit();
		}catch(HibernateException e){
			if(transaction != null){
				transaction.rollback();
			}
			e.printStackTrace();
		}finally{
			session.close();
		}
		
		List<EmployeeDTO> employeeListDTO = entityListToDTOList(employeeList);
		
		return employeeListDTO;
	}
	
	public List<EmployeeDTO> getEmployeeListByQuery(){
		SessionFactory factory = hibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		Transaction transaction = null;
		
		try{
			transaction = session.beginTransaction();
			employeeList = session.createQuery("FROM Employee ORDER BY id").setCacheable(true).list();
		}catch(HibernateException e){
			if(transaction != null){
				transaction.rollback();
			}
			e.printStackTrace();
		}finally{
			session.close();
		}
		
		List<EmployeeDTO> employeeListDTO = entityListToDTOList(employeeList);
		
		return employeeListDTO;
	}
	
	public List<EmployeeDTO> getEmployeeListByHqlQuery(String hql){
		SessionFactory factory = hibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		Transaction transaction = null;
		
		try{
			transaction = session.beginTransaction();
			query = session.createQuery(hql).setCacheable(true);
			employeeList = query.list();
			transaction.commit();
		}catch(HibernateException e){
			if(transaction != null){
				transaction.rollback();
			}
			e.printStackTrace();
		}finally{
			session.close();
		}
		
		List<EmployeeDTO> employeeListDTO = entityListToDTOList(employeeList);
		
		return employeeListDTO;
	}
	
	public List<EmployeeDTO> searchByLastName(String searchCriteria){
		SessionFactory factory = hibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		
		List<Employee> employeeList = session.createCriteria(Employee.class)
		.add(Restrictions.like("nameInfo.lastName", searchCriteria))
		.addOrder(Order.asc("nameInfo.lastName"))
		.list();
		
		List<EmployeeDTO> employeeListDTO = entityListToDTOList(employeeList);
		
		return employeeListDTO;
	}
	
	public List<EmployeeDTO> searchByGwa(double lowCriteria, double hiCriteria){
		SessionFactory factory = hibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		
		List<Employee> employeeList = session.createCriteria(Employee.class)
		.add(Restrictions.between("otherInfo.gwa", lowCriteria, hiCriteria))
		.list();
		
		List<EmployeeDTO> employeeListDTO = entityListToDTOList(employeeList);
		
		return employeeListDTO;
	}
	
	public List<EmployeeDTO> searchByHireDate(String tempSearchCriteria){
		SessionFactory factory = hibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		
		String hql = "FROM Employee E WHERE E.otherInfo.hireDate = '" + tempSearchCriteria + "'";
		Query query = session.createQuery(hql);
		List<Employee> employeeList = query.list();
		
		List<EmployeeDTO> employeeListDTO = entityListToDTOList(employeeList);
		
		return employeeListDTO;
	}
	
	public List<EmployeeDTO> entityListToDTOList(List<Employee> employeeList){
		List<EmployeeDTO> employeeListDTO = new ArrayList<>();
		for(Employee emp: employeeList){
			employeeListDTO.add(entityDTO.toDTO(emp));
		}
		
		return employeeListDTO;
	}
	
	public void addEmployeeToDB(EmployeeDTO employeeDTO){	
		SessionFactory factory = hibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		Transaction transaction = null;
		try{
			transaction = session.beginTransaction();
			Employee employee = entityDTO.toEntity(employeeDTO);
			session.save(employee);
			transaction.commit();
			System.out.println("\t\tRecord successfully created");
		}catch(HibernateException e){
			if(transaction != null){
				transaction.rollback();
			}
			e.printStackTrace();
		}finally{
			session.close();
		}
	}
	
	public void updateEmployeeRecordToDB(EmployeeDTO employeeDTO){
		SessionFactory factory = hibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		Transaction transaction = null;
		
		try{
			transaction = session.beginTransaction();
			Employee employee = entityDTO.toEntity(employeeDTO);
			System.out.println("Employee returned");
			session.update(employee);
			transaction.commit();		
		}catch(HibernateException e){
			if(transaction != null){
				transaction.rollback();
			}
			e.printStackTrace();
		}finally{
			session.close();
		}		
	}
	
	public void deleteEmployeeRecord(Integer employeeID){
		SessionFactory factory = hibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		Transaction transaction = null;
		
		try{
			transaction = session.beginTransaction();	
			Employee employee = (Employee)session.get(Employee.class, employeeID);		
			session.delete(employee);		
			transaction.commit();
			System.out.println("\t\tRecord successfully deleted");			
		}catch(HibernateException e){
			if(transaction != null){
				transaction.rollback();
			}
			e.printStackTrace();
		}finally{
			session.close();
		}
	}
	
	public void terminateProgram(){
		HibernateUtil.closeSessionFactory();
	}
}
