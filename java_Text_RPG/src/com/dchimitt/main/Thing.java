package com.dchimitt.main;

public class Thing implements java.io.Serializable {
	// superclass Thing to extend to all objects used
	
	private String name;
	private String description;
	
	public Thing(String name, String description) {
		this.name = name;
		this.description = description;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
}
