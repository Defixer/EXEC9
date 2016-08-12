package com.hibernate.exercise6.model;

import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.MappedSuperclass;
import javax.persistence.GenerationType;
import org.hibernate.annotations.GenericGenerator;

@MappedSuperclass
public abstract class BaseEntity{	

	@Id
	@GenericGenerator(name = "mamen", strategy = "increment")
	@GeneratedValue(generator = "mamen")
	@Column(name = "id", insertable = false ,nullable = false, updatable = false)
	private int id;
	
	
	public int getId(){
		return id;
	}	
	public void setId(int id){
		this.id = id;
	}
}
