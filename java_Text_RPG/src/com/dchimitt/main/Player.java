package com.dchimitt.main;
import java.util.Scanner;

// NOTE: @Override means inherited methods from abstract superclass differ
public class Player extends Character {
	
	public static Scanner in = new Scanner(System.in);
	
	// progressive skills that player can select in attack, abilities, magic, and defense
	public String[] atkUpgrades = {"Feeble", "Weak", "Novice", "Developed", "Competent", "Skilled", "Proficient", "Formidable", "Strong", "Mighty", "Powerful", "Herculean"};
	public String[] defUpgrades = {"Fragile", "Vulnerable", "Squishy", "Soft", "Resilient", "Sturdy", "Durable", "Tough", "Hardy", "Fortified", "Impenetrable", "Invincible"};
	public String[] abilityUpgrades = {};
	public String[] offensiveMagUpgrades = {};
	public String[] supportMagUpgrades = {};
	
	// variables to store number of skills added to each of the above options
	public int numAtkUpgrades, numAbilityUpgrades, numOffensiveMagUpgrades, numSupportMagUpgrades, numDefUpgrades;
	
	// Player constructor
	public Player (String name) {
		// super keyword to use the constructor from superclass
        // starting maximumHp is 100 and exp is 0
		super(name, 100, 15, 0); 
		this.numAtkUpgrades = 0;
		this.numAbilityUpgrades = 0;
		this.numOffensiveMagUpgrades = 0;
		this.numSupportMagUpgrades = 0;
		this.numDefUpgrades = 0;
		// allow player to choose two upgrades at the start of game
		chooseUpgrade();
		System.out.println("Please choose another upgrade: ");
		chooseUpgrade();
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
	
	// allows player to upgrade one path
	public void chooseUpgrade() {
		GameLogic.clearConsole();
		GameLogic.printHeader("Choose an upgrade:");
		System.out.println("(1) Attack damage increase - " + atkUpgrades[numAtkUpgrades]);
		System.out.println("(2) Defense increase - " + defUpgrades[numDefUpgrades]);
		System.out.println("(3) Ability selection - " + abilityUpgrades[numAbilityUpgrades]);
		System.out.println("(4) Offensive magic skill selection - " + offensiveMagUpgrades[numOffensiveMagUpgrades]);
		System.out.println("(5) Support magic skill selection - " + supportMagUpgrades[numSupportMagUpgrades]);
		
		// obtain input as player's upgrade choice
		int input = GameLogic.userInput("--> ", 5);
		GameLogic.clearConsole();
		
		// TO-DO: allow player to say wrong choice and add error-handling for choices other than Y/y, N/n
		if (input == 1) {
			GameLogic.printHeader("You chose " + atkUpgrades[numAtkUpgrades] + "!");
			numAtkUpgrades++;			
		}
		else if (input == 2) {
			GameLogic.printHeader("You chose " + defUpgrades[numDefUpgrades] + "!");
			numDefUpgrades++;
		}
		else if (input == 3) {
			GameLogic.printHeader("You chose " + abilityUpgrades[numAbilityUpgrades] + "!");
			numAbilityUpgrades++;
		}
		else if (input == 4) {
			GameLogic.printHeader("You chose " + offensiveMagUpgrades[numOffensiveMagUpgrades] + "!");
			numOffensiveMagUpgrades++;
		}
		else {
			GameLogic.printHeader("You chose " + supportMagUpgrades[numSupportMagUpgrades] + "!");
			numDefUpgrades++;
		}
		GameLogic.typeToContinue();
	}
}
