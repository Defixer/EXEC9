package com.hibernate.exercise6.utilities;

import com.hibernate.exercise6.model.*;
import com.hibernate.exercise6.dto.*;

import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;

public class EntityDTO{

	public Employee toEntity(EmployeeDTO employeeDTO){
		Employee employee = new Employee();
		Name nameInfo = new Name();
		Address addressInfo = new Address();
		OtherInfo otherInfo = new OtherInfo();
		Set<Contact> contacts = new HashSet();
		Set<Role> roles = new HashSet();
		
		NameDTO nameDTO = employeeDTO.getName();
		nameInfo.setTitle(nameDTO.getTitle());
		nameInfo.setFirstName(nameDTO.getFirstName());
		nameInfo.setMiddleName(nameDTO.getMiddleName());
		nameInfo.setLastName(nameDTO.getLastName());
		nameInfo.setSuffix(nameDTO.getSuffix());
		
		AddressDTO addressDTO = employeeDTO.getAddress();
		addressInfo.setStreetNo(addressDTO.getStreetNo());
		addressInfo.setBrgy(addressDTO.getBrgy());
		addressInfo.setCityMunicipality(addressDTO.getCityMunicipality());
		addressInfo.setZipcode(addressDTO.getZipcode());
		
		OtherInfoDTO otherInfoDTO = employeeDTO.getOtherInfo();
		otherInfo.setBirthday(otherInfoDTO.getBirthday());		
		otherInfo.setHireDate(otherInfoDTO.getHireDate());
		otherInfo.setGwa(otherInfoDTO.getGwa());
		otherInfo.setIsEmployed(otherInfoDTO.getIsEmployed());		
		
		if(employeeDTO.getContacts() != null){
			for(ContactDTO contactDTO: employeeDTO.getContacts()){
				Contact thisContact = new Contact();				
								
				thisContact.setId(contactDTO.getId());				
				thisContact.setContactDetails(contactDTO.getContactDetails());
				thisContact.setContactType(contactDTO.getContactType());
				contacts.add(thisContact);				
			}
			
			/*Set<ContactDTO> contactsDTO = employeeDTO.getContacts();
			
			for(Iterator contactDTOIterator = contactsDTO.iterator(); contactDTOIterator.hasNext();){
				Contact thisContact = new Contact();
				ContactDTO thisContactDTO = (ContactDTO)contactDTOIterator.next();
				
				String contactDetails = thisContactDTO.getContactDetails();
				String contactType = thisContactDTO.getContactType();
				//int id = thisContactDTO.getId();
				
				thisContact.setContactDetails(contactDetails);
				thisContact.setContactType(contactType);
				//thisContact.setId(id);
				contacts.add(thisContact);
			}*/			
		}
		
		if(employeeDTO.getRole() != null){
			for(RoleDTO roleDTO: employeeDTO.getRole()){
				Role thisRole = new Role();
				thisRole.setId(roleDTO.getId());
				thisRole.setRoleName(roleDTO.getRoleName());
				roles.add(thisRole);								
			}
			
		}
				
		employee.setId(employeeDTO.getId());		
		employee.setName(nameInfo);
		employee.setAddress(addressInfo);
		employee.setOtherInfo(otherInfo);
		employee.setRole(roles);
		employee.setContacts(contacts);
				
		return employee;
	}
	
	public EmployeeDTO toDTO(Employee employee){
		EmployeeDTO employeeDTO = new EmployeeDTO();
		NameDTO nameInfoDTO = new NameDTO();
		AddressDTO addressInfoDTO = new AddressDTO();
		OtherInfoDTO otherInfoDTO = new OtherInfoDTO();
		Set<ContactDTO> contactsDTO = new HashSet();
		Set<RoleDTO> rolesDTO = new HashSet();
		
		Name name = employee.getName();
		nameInfoDTO.setTitle(name.getTitle());
		nameInfoDTO.setFirstName(name.getFirstName());
		nameInfoDTO.setMiddleName(name.getMiddleName());
		nameInfoDTO.setLastName(name.getLastName());
		nameInfoDTO.setSuffix(name.getSuffix());

		Address address = employee.getAddress();
		addressInfoDTO.setStreetNo(address.getStreetNo());
		addressInfoDTO.setBrgy(address.getBrgy());
		addressInfoDTO.setCityMunicipality(address.getCityMunicipality());
		addressInfoDTO.setZipcode(address.getZipcode());
		
		OtherInfo otherInfo = employee.getOtherInfo();
		otherInfoDTO.setBirthday(otherInfo.getBirthday());
		otherInfoDTO.setHireDate(otherInfo.getHireDate());
		otherInfoDTO.setGwa(otherInfo.getGwa());
		otherInfoDTO.setIsEmployed(otherInfo.getIsEmployed());
		
		if(employee.getContacts() != null){
			for(Contact contact: employee.getContacts()){
				ContactDTO thisContactDTO = new ContactDTO();
				
				thisContactDTO.setId(contact.getId());
				thisContactDTO.setContactDetails(contact.getContactDetails());
				thisContactDTO.setContactType(contact.getContactType());
				
				contactsDTO.add(thisContactDTO);
			}
		}
		
		if(employee.getRole() != null){
			for(Role role: employee.getRole()){
				RoleDTO thisRoleDTO = new RoleDTO();
				thisRoleDTO.setId(role.getId());
				thisRoleDTO.setRoleName(role.getRoleName());
				rolesDTO.add(thisRoleDTO);
			}	
		}		
		
		employeeDTO.setId(employee.getId());
		employeeDTO.setName(nameInfoDTO);
		employeeDTO.setAddress(addressInfoDTO);
		employeeDTO.setOtherInfo(otherInfoDTO);
		employeeDTO.setContacts(contactsDTO);
		employeeDTO.setRole(rolesDTO);
		
		return employeeDTO;
	}
	
	public RoleDTO roleToDTO(Role rol){
		RoleDTO roleDTO = new RoleDTO();
		
		Set<EmployeeDTO> employeesDTO = new HashSet();
		
		Set<Employee> employees = rol.getEmployee();
		for(Iterator employeesIterator = employees.iterator(); employeesIterator.hasNext();){
			Employee employee = (Employee)employeesIterator.next();
			EmployeeDTO employeeDTO = new EmployeeDTO();
			
			employeeDTO = toDTO(employee);
			employeesDTO.add(employeeDTO);			
		}
		
		roleDTO.setId(rol.getId());		
		roleDTO.setRoleName(rol.getRoleName());
		roleDTO.setEmployee(employeesDTO);		
		
		return roleDTO;
	}	
}
