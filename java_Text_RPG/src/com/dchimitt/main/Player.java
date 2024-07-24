package com.dchimitt.main;
import java.util.Scanner;

// NOTE: @Override means inherited methods from abstract superclass differ
public class Player extends Character {
	
	public static Scanner in = new Scanner(System.in);
	
	public enum Abilities {
		STRENGTH("Strength", 1, false),
		AGILITY("Agility", 3, false),
		ENDURANCE("Endurance", 5, false),
		REFLEXES("Reflexes", 8, false);
		
		// instance variables for each ability
		private final String abilityName;
		private final int requiredAbilityLevel;
		private boolean learnedAbility;
		
		// Constructor
		Abilities(String abilityName, int requiredLevel, boolean learnedAbility) {
			this.abilityName = abilityName;
			this.requiredAbilityLevel = requiredLevel;
			this.learnedAbility = learnedAbility;
		}
		
		// getter methods
		public String getAbilityName() {
			return abilityName;
		}		
		public int getRequiredAbilityLevel() {
			return requiredAbilityLevel;
		}
		
		// check if ability can be learned
		public boolean canLearnAbility(int playerLevel) {
			return !learnedAbility && playerLevel >= requiredAbilityLevel;
		}
		// mark the ability as learned
		public void learnAbility() {
			learnedAbility = true;
		}
	}
	
	public enum OffMagSpells {
		ICE_SPIKE("Ice Spike", 1, false),
		FIREBALL("Fireball", 3, false),
		TELEPORT("Teleport", 5, false),
		LIGHTNING_BOLT("Lightning Bolt", 8, false),
		METEOR_SHOWER("Meteor Shower", 11, false);
			
		// instance variables for each offensive spell
		private final String offMagName;
		private final int requiredOffMagLevel;
		private boolean learnedOffMag;
		
		// Constructor
		OffMagSpells(String offMagName, int requiredLevel, boolean learnedOffMag) {
			this.offMagName = offMagName;
			this.requiredOffMagLevel = requiredLevel;
		}
		
		// getter methods
		public String getOffMagName() {
			return offMagName;
		}		
		public int getRequiredOffMagLevel() {
			return requiredOffMagLevel;
		}
		// check if offensive spell can be learned
		public boolean canLearnOffMag(int playerLevel) {
			return !learnedOffMag && playerLevel >= requiredOffMagLevel;
		}
		// mark the offensive spell as learned
		public void learnOffMag() {
			learnedOffMag = true;
		}
	}
	/*  EXAMPLE OF HOW TO USE ENUM:
	 *  // Accessing enum constants
        System.out.println("Defensive Magic Spells:");
        for (DefMagSpells spell : DefMagSpells.values()) {
            System.out.println(spell.getSpellName() + " - Required Level: " + spell.getRequiredLevel());
        }

        // Example usage
        DefMagSpells chosenSpell = DefMagSpells.PROTECT;
        int playerLevel = 17; // Example player level

        if (playerLevel >= chosenSpell.getRequiredLevel()) {
            System.out.println("Player casts " + chosenSpell.getSpellName() + "!");
            // Add casting logic here
        } else {
            System.out.println("Player needs to reach level " + chosenSpell.getRequiredLevel() +
                               " to cast " + chosenSpell.getSpellName() + ".");
        }
	 */
	
	public enum DefMagSpells {
		SHIELD("Shield", 1, false),
		PROTECT("Fireball", 3, false),
		BARRIER("Teleport", 5, false),
		ABSORB("Lightning Bolt", 8, false);
			
		// instance variables for each defensive spell
		private final String defMagName;
		private final int requiredDefMagLevel;
		private boolean learnedDefMag;
		
		// Constructor
		DefMagSpells(String defMagName, int requiredLevel, boolean learnedDefMag) {
			this.defMagName = defMagName;
			this.requiredDefMagLevel = requiredLevel;
			this.learnedDefMag = learnedDefMag;
		}
		
		// getter methods
		public String getDefMagName() {
			return defMagName;
		}		
		public int getRequiredDefMagLevel() {
			return requiredDefMagLevel;
		}
		
		// check if defensive spell can be learned
		public boolean canLearnDefMag(int playerLevel) {
			return !learnedDefMag && playerLevel >= requiredDefMagLevel;
		}
		// mark the defensive spell as learned
		public void learnDefMag() {
			learnedDefMag = true;
		}
	}	
	
	// Player constructor
	public Player (String name) {
		// super keyword to use the constructor from superclass
        // params: name, str, dex, int, starting hp pool, starting mana pool, level, current exp, exp to level
		super(name, 1, 1, 1, 16, 5, 1, 0, 12); 

		// allow player to choose three stats and one ability, offensive spell, or support spell at the start of game
		pickThreeStats();
		// chooseUpgrade();
	}

	@Override
	public int attack() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public int useAbility() {
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
	
	public void pickThreeStats() {
		GameLogic.clearConsole();
		GameLogic.printHeader("You've earned three stat points! Type a number and enter three times to choose your upgrades: ");
		System.out.println("(1) Strength --> increases physical attack power, increases maximum health pool, and affects various abilities.");
		System.out.println("(2) Dexterity --> increases critical hit chance (150% damage on critical) and affects various abilities.");
		System.out.println("(3) Intelligence --> increases magical attack power and maximum mana pool.");
		
		// obtain input as player's three stat choices
		int firstStat = GameLogic.userInput("--> ", 3);
		if (firstStat == 1) 
			strength++;
		else if (firstStat == 2)
			dexterity++;
		else
			intelligence++;
		
		System.out.println("You've picked your first stat point. You have two upgrades remaining: ");
		int secondStat = GameLogic.userInput("--> ", 3);
		if (secondStat == 1) 
			strength++;
		else if (secondStat == 2)
			dexterity++;
		else
			intelligence++;
		
		System.out.println("You've chosen two stat points. Pick your last: ");
		int thirdStat = GameLogic.userInput("--> ", 3);
		if (thirdStat == 1) 
			strength++;
		else if (thirdStat == 2)
			dexterity++;
		else
			intelligence++;
		
		System.out.println("You've chosen your last stat point.");
		GameLogic.typeToContinue();				
	}
	
	/*
	// allows player to upgrade one path
	public void chooseUpgrade() {
		GameLogic.clearConsole();
		GameLogic.printHeader("Choose an upgrade: ");
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
			numSupportMagUpgrades++;
		}
		GameLogic.typeToContinue();
	}
	*/
}
