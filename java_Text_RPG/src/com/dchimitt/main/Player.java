package com.dchimitt.main;
import java.util.*;
import java.io.Serializable;

// NOTE: @Override means inherited methods from abstract superclass differ
public class Player extends Character implements java.io.Serializable {
	
	public static Scanner in = new Scanner(System.in);
	
	private int lastLearnedAbilityIndex = -1;
	private int lastLearnedOffMagIndex = -1;
	private int lastLearnedSuppMagIndex = -1;
	
	public static enum Abilities {
		STRENGTH("Strength", "Placeholder", 3, 1, false),
		AGILITY("Agility", "Placeholder", 5, 3, false),
		ENDURANCE("Endurance", "Placeholder", 4, 5, false),
		REFLEXES("Reflexes", "Placeholder", 6, 8, false);
	
		// instance variables for each ability
		private final String abilityName;
		private final String abilityDescription;
		private final int abilityCooldown;
		private final int requiredAbilityLevel;
		private boolean learnedAbility;
		
		// Constructor
		Abilities(String abilityName, String abilityDescription, int abilityCooldown, int requiredLevel, boolean learnedAbility) {
			this.abilityName = abilityName;
			this.abilityDescription = abilityDescription;
			this.abilityCooldown = abilityCooldown;
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
		public boolean getLearnedAbility() {
			return learnedAbility;
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
	
	public static enum OffMagSpells {
		ICE_SPIKE("Ice Spike", "Placeholder", 3, 1, false),
		FIREBALL("Fireball", "Placeholder", 5, 3, false),
		TELEPORT("Teleport", "Placeholder", 12, 5, false),
		LIGHTNING_BOLT("Lightning Bolt", "Placeholder", 20, 8, false),
		METEOR_SHOWER("Meteor Shower", "Placeholder", 35, 11, false);
			
		// instance variables for each offensive spell
		private final String offMagName;
		private final String offMagDescription;
		private final int offMagManaCost;
		private final int requiredOffMagLevel;
		private boolean learnedOffMag;
		
		// Constructor
		OffMagSpells(String offMagName, String offMagDescription, int offMagManaCost, int requiredLevel, boolean learnedOffMag) {
			this.offMagName = offMagName;
			this.offMagDescription = offMagDescription;
			this.offMagManaCost = offMagManaCost;
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
		public boolean getLearnedOffMag() {
			return learnedOffMag;
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

	public static enum SuppMagSpells {
		CURE_POISON("Cure Poison", "Placeholder", 4, 1, false),
		BURSTING_LIGHT("Burst of Light", "Placeholder", 6, 3, false),
		INVISIBILITY("Invisibility", "Placeholder", 10, 5, false),
		INCREASE_SPELL_DAMAGE("Increase Spell Damage", "Placeholder", 12, 8, false);
			
		// instance variables for each defensive spell
		private final String suppMagName;
		private final String suppMagDescription;
		private final int suppMagManaCost;
		private final int requiredSuppMagLevel;
		private boolean learnedSuppMag;
		
		// Constructor
		SuppMagSpells(String suppMagName, String suppMagDescription, int suppMagManaCost, int requiredLevel, boolean learnedSuppMag) {
			this.suppMagName = suppMagName;
			this.suppMagDescription = suppMagDescription;
			this.suppMagManaCost = suppMagManaCost;
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
		public int getRequiredSuppMagLevel() {
			return requiredSuppMagLevel;
		}
		public boolean getLearnedSuppMag() {
			return learnedSuppMag;
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
	
	// Player constructor -- currentExp, expToLevel, gold, and currentAct are specific to player
	public Player (String name, int currentExp, int expToLevel, int gold, int currentAct, int playerMovementCounter) {
		// super keyword to use the constructor from superclass
        // params: name, str, dex, int, starting hp pool, starting mana pool, level
		super(name, 1, 1, 1, 16, 5, 1); 
		
		this.currentExp = currentExp;
		this.expToLevel = expToLevel;
		this.gold = gold;
		this.currentAct = currentAct;
		this.playerMovementCounter = playerMovementCounter;
	}

	@Override
	public int attack() {
		return (int) (Math.random() * (level / 2 + strength * 2) + dexterity * 2);
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
		return (int) (Math.random() * (level / 2 + strength * 2) + dexterity * 2);
	}
	
	// methods to access and modify player's movement counter (used for random encounters)
	public int getMovementCounter() {
		return playerMovementCounter;
	}
	public void resetMovementCounter() {
		playerMovementCounter = 0;
	}	
	public void incrementMovementCounter() {
		playerMovementCounter++;
	}
	
	public void pickThreeStats() {
		GameLogic.clearConsole();
		GameLogic.printHyphenHeader("You've earned three stat points! Type a number and enter three times to choose your upgrades: ");
		System.out.println("(1) Strength --> increases physical attack power, increases maximum health pool, and affects various abilities.");
		System.out.println("(2) Dexterity --> increases critical hit chance (150% damage on critical) and affects various abilities.");
		System.out.println("(3) Intelligence --> increases magical attack power and maximum mana pool.");
		
		// obtain input as player's three stat choices
		int firstStat = GameLogic.intUserInput("--> ", 3);	
		if (firstStat == 1) 
			strength++;
		else if (firstStat == 2)
			dexterity++;
		else
			intelligence++;
		
		System.out.println("You've picked your first stat point. You have two stat increases remaining: ");
		int secondStat = GameLogic.intUserInput("--> ", 3);	
		if (secondStat == 1) 
			strength++;
		else if (secondStat == 2)
			dexterity++;
		else
			intelligence++;
		
		System.out.println("You've chosen two stat points. Pick your last stat increase: ");
		int thirdStat = GameLogic.intUserInput("--> ", 3);
		if (thirdStat == 1) 
			strength++;
		else if (thirdStat == 2)
			dexterity++;
		else
			intelligence++;			
	}

	// allows player to choose an upgrade
	public void chooseUpgrade() {
	    while (true) {
	        GameLogic.clearConsole();
	        GameLogic.printHyphenHeader("Choose an upgrade. You will be able to read a description of your selection before making your final choice: ");
	        System.out.println("(1) Abilities --> Skills that utilize strength and dexterity to inflict damage. These have cooldowns.");
	        System.out.println("(2) Offensive Magic --> Spells that decimate your enemies. These consume mana.");
	        System.out.println("(3) Support Magic --> Spells with varying effects that support your character. These consume mana.");

	        int input = GameLogic.intUserInput("--> ", 3);
	        GameLogic.clearConsole();

	        boolean learned = false;
	        
	        // TODO: when player selects no, program always outputs "no new _ to learn at the moment", even if player could learn something
	        switch (input) {
	            case 1:
	                for (Abilities ability : Abilities.values()) 
	                    System.out.println(ability.getAbilityName() + ": " + ability.getAbilityDescription() + " - Level " + ability.requiredAbilityLevel + " required.");
	                
	                for (int i = lastLearnedAbilityIndex + 1; i < Abilities.values().length; i++) {
	                    Abilities ability = Abilities.values()[i];
	                    if (ability.canLearnAbility(level)) {
	                        System.out.println();
	                        System.out.println("Would you like to learn " + ability.getAbilityName() + "? (Y/N)");
	                        String response = in.nextLine().trim().toUpperCase();
	                        if (response.equals("Y")) {
	                            System.out.println("Learning " + ability.getAbilityName() + "...");
	                            ability.learnAbility();
	                            System.out.println(ability.getAbilityName() + " learned!");
	                            lastLearnedAbilityIndex = i;
	                            learned = true;
	                            break;
	                        } 
	                        else if (response.equals("N")) {
	                            break; 
	                        } 
	                        else {
	                            System.out.println("Invalid input, returning to selections.");
	                            GameLogic.typeToContinue();
	                            break; 
	                        }
	                    }
	                }
	                if (!learned) {
	                    System.out.println("No new abilities to learn at the moment.");
	                    GameLogic.typeToContinue();
	                }
	                break;

	            case 2:
	                for (OffMagSpells offSpell : OffMagSpells.values()) 
	                    System.out.println(offSpell.getOffMagName() + ": " + offSpell.getOffMagDescription() + " - Level " + offSpell.requiredOffMagLevel + " required.");
	                
	                for (int i = lastLearnedOffMagIndex + 1; i < OffMagSpells.values().length; i++) {
	                    OffMagSpells offSpell = OffMagSpells.values()[i];
	                    if (offSpell.canLearnOffMag(level)) {
	                        System.out.println();
	                        System.out.println("Would you like to learn " + offSpell.getOffMagName() + "? (Y/N)");
	                        String response = in.nextLine().trim().toUpperCase();
	                        if (response.equals("Y")) {
	                            System.out.println("Learning " + offSpell.getOffMagName() + "...");
	                            offSpell.learnOffMag();
	                            System.out.println(offSpell.getOffMagName() + " learned!");
	                            lastLearnedOffMagIndex = i;
	                            learned = true;
	                            break;
	                        } 
	                        else if (response.equals("N")) {
	                            break; 
	                        } 
	                        else {
	                            System.out.println("Invalid input, returning to selections.");
	                            GameLogic.typeToContinue();
	                            break; 
	                        }
	                    }
	                }
	                if (!learned) {
	                    System.out.println("No new offensive magic spells to learn at the moment.");
	                    GameLogic.typeToContinue();
	                }
	                break;

	            case 3:
	                for (SuppMagSpells suppSpell : SuppMagSpells.values()) 
	                    System.out.println(suppSpell.getSuppMagName() + ": " + suppSpell.getSuppMagDescription() + " - Level " + suppSpell.requiredSuppMagLevel + " required.");
	                
	                for (int i = lastLearnedSuppMagIndex + 1; i < SuppMagSpells.values().length; i++) {
	                    SuppMagSpells suppSpell = SuppMagSpells.values()[i];
	                    if (suppSpell.canLearnSuppMag(level)) {
	                        System.out.println();
	                        System.out.println("Would you like to learn " + suppSpell.getSuppMagName() + "? (Y/N)");
	                        String response = in.nextLine().trim().toUpperCase();
	                        if (response.equals("Y")) {
	                            System.out.println("Learning " + suppSpell.getSuppMagName() + "...");
	                            suppSpell.learnSuppMag();
	                            System.out.println(suppSpell.getSuppMagName() + " learned!");
	                            lastLearnedSuppMagIndex = i;
	                            learned = true;
	                            break;
	                        } 
	                        else if (response.equals("N")) {
	                            break; 
	                        } 
	                        else {
	                            System.out.println("Invalid input, returning to selections.");
	                            GameLogic.typeToContinue();
	                            break; 
	                        }
	                    }
	                }
	                if (!learned) {
	                    System.out.println("No new support magic spells to learn at the moment.");
	                    GameLogic.typeToContinue();
	                }
	                break;

	            default:
	                System.out.println("Invalid choice, please select again.");
	                break;
	        }
	        
	        if (learned) {
	            GameLogic.typeToContinue();
	            break; 
	        }
	    }
	}
}
