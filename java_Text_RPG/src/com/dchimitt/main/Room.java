package com.dchimitt.main;

public class Room extends Thing {
	private int n, s, w, e;
	
	public Room(String name, String description, int n, int s, int w, int e) {
		super(name, description);
		this.n = n;
		this.s = s;
		this.w = w;
		this.e = e;
	}
	
	public int getN() {
		return n;
	}
	
	public void setN(int n) {
		this.n = n;
	}
	
	public int getS() {
		return s;
	}
	
	public void setS(int s) {
		this.s = s;
	}
	
	public int getW() {
		return w;
	}
	
	public void setW(int w) {
		this.w = w;
	}
	
	public int getE() {
		return e;
	}
	
	public void setE() {
		this.e = e;
	}
}
