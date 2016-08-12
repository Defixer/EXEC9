package com.hibernate.exercise6.model;

import javax.persistence.Embeddable;
import javax.persistence.Column;

@Embeddable
public class Address{
	@Column(name = "STREET_NO", nullable = false, length = 50)
	private String streetNo;

	@Column(name = "BRGY", nullable = false, length = 50)
	private String brgy;

	@Column(name = "CITY_MUNICIPALITY", nullable = false, length = 250)
	private String cityMunicipality;
	
	@Column(name = "ZIPCODE", nullable = false, length = 50)
	private String zipcode;
									
	public Address() {}
	
	public Address(String streetNo, String brgy, String cityMunicipality, String zipcode){
		this.streetNo = streetNo;
		this.brgy = brgy;
		this.cityMunicipality = cityMunicipality;
		this.zipcode = zipcode;
	}
	
	
	public String getStreetNo(){
		return streetNo;
	}
	public void setStreetNo(String streetNo){
		this.streetNo = streetNo;
	}
	
	
	public String getBrgy(){
		return brgy;
	}
	public void setBrgy(String brgy){
		this.brgy = brgy;
	}
	
	
	public String getCityMunicipality(){
		return cityMunicipality;
	}
	public void setCityMunicipality(String cityMunicipality){
		this.cityMunicipality = cityMunicipality;
	}
	
	
	public String getZipcode(){
		return zipcode;
	}
	public void setZipcode(String zipcode){
		this.zipcode = zipcode;
	}
}
