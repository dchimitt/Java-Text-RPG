package com.dchimitt.main;

public class Room extends Thing implements java.io.Serializable {
	private int n, s, w, e;
	
	public Room(String name, String description, int n, int s, int e, int w) {
		super(name, description);
		this.n = n;
		this.s = s;
		this.e = e;
		this.w = w;
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
}
