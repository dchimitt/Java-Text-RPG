package com.dchimitt.main;

public abstract class Character {
	// attributes that all players, enemies, and bosses must have
	public String name;
	public int strength, dexterity, intelligence, maximumHp, currentHp, maximumMana, currentMana, level, currentExp, expToLevel;
	
	// Character constructor
	public Character(String name, int strength, int dexterity, int intelligence, int maximumHp, int maximumMana, int level) {
		this.name = name;
		this.strength = strength;
		this.dexterity = dexterity;
		this.intelligence = intelligence;
		this.maximumHp = maximumHp;
		this.currentHp = maximumHp;
		this.maximumMana = maximumMana;
		this.currentMana = maximumMana;
		this.level = level;
	}
	
	// abstract methods
	public abstract int attack();
	public abstract int useAbility();
	public abstract int useMagic();
	public abstract int defend();
}
