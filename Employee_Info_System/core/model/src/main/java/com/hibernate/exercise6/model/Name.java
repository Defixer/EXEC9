package com.hibernate.exercise6.model;

import javax.persistence.Embeddable;
import javax.persistence.Column;

@Embeddable
public class Name{

	@Column(name = "TITLE", nullable = true, length = 50)
	private String title;
	
	@Column(name = "FIRST_NAME", nullable = false, length = 250)
	private String firstName;
	
	@Column(name = "MIDDLE_NAME", nullable = true, length = 250)
	private String middleName;
	
	@Column(name = "LAST_NAME", nullable = false, length = 250)
	private String lastName;
	
	@Column(name = "SUFFIX", nullable = true, length = 50)
	private String suffix;
	
	
	public Name() {}
	
	public Name(String title, String firstName, String middleName, String lastName, String suffix){
		this.title = title;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.suffix = suffix;
	}
	
	
	public String getTitle(){
		return title;
	} 
	public void setTitle(String title){
		this.title = title;
	}
	
	
	public String getFirstName(){
		return firstName;
	}
	public void setFirstName(String firstName){
		this.firstName = firstName;
	}
	
	
	public String getMiddleName(){
		return middleName;
	}
	public void setMiddleName(String middleName){
		this.middleName = middleName;
	}
	
	
	public String getLastName(){
		return lastName;
	}
	public void setLastName(String lastName){
		this.lastName = lastName;
	}
	
	
	public String getSuffix(){
		return suffix;
	}
	public void setSuffix(String suffix){
		this.suffix = suffix;
	}
}
