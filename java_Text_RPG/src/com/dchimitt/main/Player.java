package com.dchimitt.main;

// NOTE: @Override means inherited methods from abstract superclass differ
public class Player extends Character {
	
	// progressive skills that player can select in attack, magic, and defense
	public String[] atkUpgrades = {};
	public String[] abilityUpgrades = {};
	public String[] offensiveMagUpgrades = {};
	public String[] defensiveMagUpgrades = {};
	public String[] defUpgrades = {};
	
	// Player constructor
	public Player (String name) {
		// super keyword to use the constructor from superclass
        // starting maximumHp is 100 and exp is 0
		super(name, 100, 0); 
	}

	@Override
	public int attack() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int useMagic() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int defend() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
