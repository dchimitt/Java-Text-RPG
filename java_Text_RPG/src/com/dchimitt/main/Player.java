package com.dchimitt.main;
import java.util.Scanner;

// NOTE: @Override means inherited methods from abstract superclass differ
public class Player extends Character {
	
	public static Scanner in = new Scanner(System.in);
	
	private int lastLearnedAbilityIndex = -1;
	private int lastLearnedOffMagIndex = -1;
	private int lastLearnedSuppMagIndex = -1;
	
	public enum Abilities {
		STRENGTH("Strength", "Placeholder", 1, false),
		AGILITY("Agility", "Placeholder", 3, false),
		ENDURANCE("Endurance", "Placeholder", 5, false),
		REFLEXES("Reflexes", "Placeholder", 8, false);
		
		// instance variables for each ability
		private final String abilityName;
		private final String abilityDescription;
		private final int requiredAbilityLevel;
		private boolean learnedAbility;
		
		// Constructor
		Abilities(String abilityName, String abilityDescription, int requiredLevel, boolean learnedAbility) {
			this.abilityName = abilityName;
			this.abilityDescription = abilityDescription;
			this.requiredAbilityLevel = requiredLevel;
			this.learnedAbility = learnedAbility;
		}
		
		// getter methods
		public String getAbilityName() {
			return abilityName;
		}		
		public String getAbilityDescription() {
			return abilityDescription;
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
		ICE_SPIKE("Ice Spike", "Placeholder", 1, false),
		FIREBALL("Fireball", "Placeholder", 3, false),
		TELEPORT("Teleport", "Placeholder", 5, false),
		LIGHTNING_BOLT("Lightning Bolt", "Placeholder", 8, false),
		METEOR_SHOWER("Meteor Shower", "Placeholder", 11, false);
			
		// instance variables for each offensive spell
		private final String offMagName;
		private final String offMagDescription;
		private final int requiredOffMagLevel;
		private boolean learnedOffMag;
		
		// Constructor
		OffMagSpells(String offMagName, String offMagDescription, int requiredLevel, boolean learnedOffMag) {
			this.offMagName = offMagName;
			this.offMagDescription = offMagDescription;
			this.requiredOffMagLevel = requiredLevel;
			this.learnedOffMag = learnedOffMag;
		}
		
		// getter methods
		public String getOffMagName() {
			return offMagName;
		}		
		public String getOffMagDescription() {
			return offMagDescription;
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

	public enum SuppMagSpells {
		SHIELD("Shield", "Placeholder", 1, false),
		PROTECT("Fireball", "Placeholder", 3, false),
		BARRIER("Teleport", "Placeholder", 5, false),
		ABSORB("Lightning Bolt", "Placeholder", 8, false);
			
		// instance variables for each defensive spell
		private final String suppMagName;
		private final String suppMagDescription;
		private final int requiredSuppMagLevel;
		private boolean learnedSuppMag;
		
		// Constructor
		SuppMagSpells(String suppMagName, String suppMagDescription, int requiredLevel, boolean learnedSuppMag) {
			this.suppMagName = suppMagName;
			this.suppMagDescription = suppMagDescription;
			this.requiredSuppMagLevel = requiredLevel;
			this.learnedSuppMag = learnedSuppMag;
		}
		
		// getter methods
		public String getSuppMagName() {
			return suppMagName;
		}		
		public String getSuppMagDescription() {
			return suppMagDescription;
		}
		public int getRequiredDefMagLevel() {
			return requiredSuppMagLevel;
		}
		
		// check if defensive spell can be learned
		public boolean canLearnSuppMag(int playerLevel) {
			return !learnedSuppMag && playerLevel >= requiredSuppMagLevel;
		}
		// mark the defensive spell as learned
		public void learnSuppMag() {
			learnedSuppMag = true;
		}
	}	
	
	// Player constructor
	public Player (String name) {
		// super keyword to use the constructor from superclass
        // params: name, str, dex, int, starting hp pool, starting mana pool, level, current exp, exp to level
		super(name, 1, 1, 1, 16, 5, 1, 0, 12); 

		// allow player to choose three stats and one ability, offensive spell, or support spell at the start of game
		pickThreeStats();
		chooseUpgrade();
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
	
	// allows player to choose an upgrade
	public void chooseUpgrade() {
		GameLogic.clearConsole();
		GameLogic.printHeader("Choose an upgrade. You will be able to read a description of your selection before making your final choice: ");
		System.out.println("(1) Abilities --> Skills that utilize strength and dexterity to inflict damage. These have cooldowns.");
		System.out.println("(2) Offensive Magic --> Spells that decimate your enemies. These consume mana.");
		System.out.println("(3) Support Magic --> Spells with varying effects that support your character. These consume mana.");
		
		// obtain input as player's upgrade choice
		int input = GameLogic.userInput("--> ", 3);
		GameLogic.clearConsole();
		
        if (input == 1) {
        	for (Abilities abilities : Abilities.values()) 
        		System.out.println(abilities.getAbilityName() + ": " + abilities.getAbilityDescription() + " - Level " + abilities.requiredAbilityLevel + " required.");
            for (int i = lastLearnedAbilityIndex + 1; i < Abilities.values().length; i++) {
                Abilities ability = Abilities.values()[i];
                if (ability.canLearnAbility(level)) {
                    System.out.println("Would you like to learn " + ability.getAbilityName() + "? (Y/N)");
                    String response = in.nextLine().trim().toUpperCase();
                    if (response.equals("Y")) {
                        System.out.println("Learning " + ability.getAbilityName() + "...");
                        ability.learnAbility();
                        System.out.println(ability.getAbilityName() + " learned!");
                        lastLearnedAbilityIndex = i; // Update last learned index
                        GameLogic.typeToContinue();
                        return;
                    }
                    else if (response.equals("N")) 
                    	chooseUpgrade();
                    else {
                    	System.out.println("Invalid input, returning to selections.");
                    	GameLogic.typeToContinue();
                    	chooseUpgrade();
                    }
                }
            }
            System.out.println("No new abilities to learn at the moment.");
            GameLogic.typeToContinue();
        }
        else if (input == 2) {
        	for (OffMagSpells offSpell : OffMagSpells.values()) 
        		System.out.println(offSpell.getOffMagName() + ": " + offSpell.getOffMagDescription() + " - Level " + offSpell.requiredOffMagLevel + " required.");
            for (int i = lastLearnedOffMagIndex + 1; i < OffMagSpells.values().length; i++) {
                OffMagSpells offSpell = OffMagSpells.values()[i];
                if (offSpell.canLearnOffMag(level)) {
                    System.out.println("Would you like to learn " + offSpell.getOffMagName() + "? (Y/N)");
                    String response = in.nextLine().trim().toUpperCase();
                    if (response.equals("Y")) {
                        System.out.println("Learning " + offSpell.getOffMagName() + "...");
                        offSpell.learnOffMag();
                        System.out.println(offSpell.getOffMagName() + " learned!");
                        lastLearnedOffMagIndex = i; // Update last learned index
                        GameLogic.typeToContinue();
                        return;
                    }
                    else if (response.equals("N")) 
                    	chooseUpgrade();
                    else {
                    	System.out.println("Invalid input, returning to selections.");
                    	GameLogic.typeToContinue();
                    	chooseUpgrade();
                    }
                }
            }
            System.out.println("No new offensive magic spells to learn at the moment.");
            GameLogic.typeToContinue();
        }
		else {
			for (SuppMagSpells suppSpell : SuppMagSpells.values()) 
				System.out.println(suppSpell.getSuppMagName() + ": " + suppSpell.getSuppMagDescription() + " - Level " + suppSpell.requiredSuppMagLevel + " required.");
            for (int i = lastLearnedSuppMagIndex + 1; i < SuppMagSpells.values().length; i++) {
                SuppMagSpells suppSpell = SuppMagSpells.values()[i];
                if (suppSpell.canLearnSuppMag(level)) {
                    System.out.println("Would you like to learn " + suppSpell.getSuppMagName() + "? (Y/N)");
                    String response = in.nextLine().trim().toUpperCase();
                    if (response.equals("Y")) {
                        System.out.println("Learning " + suppSpell.getSuppMagName() + "...");
                        suppSpell.learnSuppMag();
                        System.out.println(suppSpell.getSuppMagName() + " learned!");
                        lastLearnedSuppMagIndex = i; // Update last learned index
                        GameLogic.typeToContinue();
                        return;
                    }
                    else if (response.equals("N")) 
                    	chooseUpgrade();
                    else {
                    	System.out.println("Invalid input, returning to selections.");
                    	GameLogic.typeToContinue();
                    	chooseUpgrade();
                    }
                }
            }
            System.out.println("No new support magic spells to learn at the moment.");
            GameLogic.typeToContinue();
		}
	}
}
