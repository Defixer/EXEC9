package com.hibernate.exercise6.dto;

public abstract class UniqueIdDTO{	
	private int idDTO;
	
	public int getId(){
		return idDTO;
	}	
	public void setId(int idDTO){
		this.idDTO = idDTO;
	}
}
