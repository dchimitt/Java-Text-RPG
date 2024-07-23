package com.dchimitt.main;

public abstract class Character {
	// attributes that all players, enemies, and bosses must have
	public String name;
	public int maximumHp, currentHp, maximumMana, currentMana, exp;
	
	// Character constructor
	public Character(String name, int maximumHp, int maximumMana, int exp) {
		this.name = name;
		this.maximumHp = maximumHp;
		this.currentHp = maximumHp;
		this.maximumMana = maximumMana;
		this.currentMana = maximumMana;
		this.exp = exp;
	}
	
	// abstract methods
	public abstract int attack();
	public abstract int useMagic();
	public abstract int defend();
}
