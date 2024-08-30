package com.dchimitt.main;
import java.io.Serializable;

public class Room extends Thing implements Serializable {
	private int n, s, w, e;
	private boolean isTown;
	
	public Room(String name, String description, int n, int s, int e, int w) {
		super(name, description);
		this.n = n;
		this.s = s;
		this.e = e;
		this.w = w;
		this.isTown = name.toLowerCase().contains("town");
	}
	
	public int getNorth() {
		return n;
	}
	
	public void setNorth(int n) {
		this.n = n;
	}
	
	public int getSouth() {
		return s;
	}
	
	public void setSouth(int s) {
		this.s = s;
	}
	
	public int getEast() {
		return e;
	}
	
	public void setEast(int e) {
		this.e = e;
	}
	
	public int getWest() {
		return w;
	}
	
	public void setWest(int w) {
		this.w = w;
	}
	
	public boolean isTown() {
		return isTown;
	}
	
	//TODO: add boolean method isSafeRoom variable and method to this class in order to reduce redundancy in code
}
