package com.hibernate.exercise6.model;

import java.util.Date;
import javax.persistence.Embeddable;
import javax.persistence.Column;

@Embeddable
public class OtherInfo{
	@Column(name = "BIRTHDAY")
	private Date birthday;
	
	@Column(name = "HIRE_DATE")
	private Date hireDate;
	
	@Column(name = "GWA")
	private double gwa;
	
	@Column(name = "IS_EMPLOYED")
	private boolean isEmployed;
	
	public OtherInfo() {}
	
	public OtherInfo(Date birthday, Date hireDate, double gwa, boolean isEmployed){
		this.birthday = birthday;
		this.hireDate = hireDate;
		this.gwa = gwa;
		this.isEmployed = isEmployed;
	}
	
	
	public Date getBirthday(){
		return birthday;
	}
	public void setBirthday(Date birthday){
		this.birthday = birthday;
	}
	
	
	public Date getHireDate(){
		return hireDate;
	}
	public void setHireDate(Date hireDate){
		this.hireDate = hireDate;
	}
	
	
	public double getGwa(){
		return gwa;
	}
	public void setGwa(double gwa){
		this.gwa = gwa;
	}
	
	
	public boolean getIsEmployed(){
		return isEmployed;
	}
	public void setIsEmployed(boolean isEmployed){
		this.isEmployed = isEmployed;
	}	
}
