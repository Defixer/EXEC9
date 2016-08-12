package com.hibernate.exercise6.dto;

import java.util.Date;
import java.text.DecimalFormat;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class OtherInfoDTO{
	private Date birthday,
								hireDate;
	private double gwa;
	private boolean isEmployed;
	private String gwaString;
	
	public OtherInfoDTO() {}
	
	public OtherInfoDTO(Date birthday, Date hireDate, double gwa, boolean isEmployed){
		this.birthday = birthday;
		this.hireDate = hireDate;
		this.gwa = gwa;
		this.isEmployed = isEmployed;
	}
	
	public String dateToString(Date day){
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		
		return dateFormat.format(day);
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
	
	public String getGwaString(double gwa){
		DecimalFormat twoDecimalPlace = new DecimalFormat("0.00");
		
		return String.valueOf(twoDecimalPlace.format(gwa));
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
