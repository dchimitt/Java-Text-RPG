package com.dchimitt.main;

import gearAndItems.PlayerGear;
import java.util.Scanner;
import java.util.EnumMap;
import java.util.Map;
import java.util.Random;
import java.io.Serializable;

// NOTE: @Override means inherited methods from abstract superclass differ
public class Player extends Character implements Serializable {
	public static Scanner in = new Scanner(System.in);
	public static PlayerGear playerGear = new PlayerGear();
	private static final Random RANDOM = new Random(); // single instance of random to use for all damage/defense calculations
	
	// define EnumMaps for learned states of abilities and spells
	private EnumMap<Abilities, Boolean> learnedAbilities;
	private EnumMap<OffMagSpells, Boolean> learnedOffensiveSpells;
	private EnumMap<SuppMagSpells, Boolean> learnedSupportSpells;
	
	private static int lastLearnedAbilityIndex = -1;
	private static int lastLearnedOffMagIndex = -1;
	private static int lastLearnedSuppMagIndex = -1;
	
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
		
		// create EnumMaps for learned states of abilities and spells, then initialize all values to not learned
		learnedAbilities = new EnumMap<>(Abilities.class);
		learnedOffensiveSpells = new EnumMap<>(OffMagSpells.class);
		learnedSupportSpells = new EnumMap<>(SuppMagSpells.class);		
		for (Abilities ability : Abilities.values()) 
			learnedAbilities.put(ability, false);
		for (OffMagSpells offSpell : OffMagSpells.values())
			learnedOffensiveSpells.put(offSpell, false);
		for (SuppMagSpells suppSpell : SuppMagSpells.values())
			learnedSupportSpells.put(suppSpell, false);
	}
	
	// getter and setter methods for player's currently equipped gear
	public PlayerGear getPlayerGear() {
		return playerGear;
	}	
	public void setPlayerGear(PlayerGear gear) {
		this.playerGear = gear;
	}
	
	public int getPlayerStat(String statType) {
		switch (statType) {
			case "strength":
				return AdventureGame.getPlayer().strength;
			case "dexterity":
				return AdventureGame.getPlayer().dexterity;
			case "intelligence":
				return AdventureGame.getPlayer().intelligence;
			default:
				return 0;
		}
	}
	
	// getters and setters for learned states of abilities and spells
	public boolean isAbilityLearned(Abilities ability) {
		return learnedAbilities != null && learnedAbilities.getOrDefault(ability, false);
	}
	public void setAbilityLearned(Abilities ability, boolean learned) {
		if (learnedAbilities != null) {
			learnedAbilities.put(ability, learned);
		}
	}
	
	public boolean isOffensiveSpellLearned(OffMagSpells offSpell) {
		return learnedOffensiveSpells != null && learnedOffensiveSpells.getOrDefault(offSpell, false);
	}
	public void setOffensiveSpellLearned(OffMagSpells offSpell, boolean learned) {
		if (learnedOffensiveSpells != null) {
			learnedOffensiveSpells.put(offSpell, learned);
		}
	}
	
	public boolean isSupportSpellLearned(SuppMagSpells suppSpell) {
		return learnedSupportSpells != null && learnedSupportSpells.getOrDefault(suppSpell, false);
	}
	public void setSupportSpellLearned(SuppMagSpells suppSpell, boolean learned) {
		if (learnedSupportSpells != null) {
			learnedSupportSpells.put(suppSpell, learned);
		}
	}
	
	// getters for learned abilities and spells
	public EnumMap<Abilities, Boolean> getLearnedAbilities() {
        return learnedAbilities;
    }
    public EnumMap<OffMagSpells, Boolean> getLearnedOffensiveSpells() {
        return learnedOffensiveSpells;
    }
    public EnumMap<SuppMagSpells, Boolean> getLearnedSupportSpells() {
        return learnedSupportSpells;
    }
    
    // getters and setters for last learned index of enums
    public int getLastLearnedAbilityIndex() {
    	return lastLearnedAbilityIndex;
    }
    public void setLastLearnedAbilityIndex(int index) {
    	lastLearnedAbilityIndex = index;
    }
    
    public int getLastLearnedOffMagIndex() {
    	return lastLearnedOffMagIndex;
    }
    public void setLastLearnedOffMagIndex(int index) {
    	lastLearnedOffMagIndex = index;
    }
    
    public int getLastLearnedSuppMagIndex() {
    	return lastLearnedSuppMagIndex;
    }
    public void setLastLearnedSuppMagIndex(int index) {
    	lastLearnedSuppMagIndex = index;
    }
	
	// inherited methods from Character superclass
	@Override
	// TODO: possibly using same calculation for every enemy in game as well, with stat and level adjustments as needed. Putting formula explanation here, shortened elsewhere
	public int attack() {
		final double STRENGTH_WEIGHT = 0.75;
		final double DEXTERITY_WEIGHT = 0.5;
		final double INTELLIGENCE_WEIGHT = 0.2;
		
		// base damage
		double baseDamage = (AdventureGame.getPlayer().strength * STRENGTH_WEIGHT) + (AdventureGame.getPlayer().dexterity * DEXTERITY_WEIGHT) +
																				     (AdventureGame.getPlayer().intelligence * INTELLIGENCE_WEIGHT);
		
		// level factor
		double levelFactor = 1 + (level/10.0);
		double damage = baseDamage * levelFactor;
		
		// add a random factor (between -5% and +5%)
		double randomFactor = (RANDOM.nextDouble() * 0.1 - 0.05) * baseDamage;
		damage += randomFactor;
		
		return (int) Math.round(damage);
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

	// allows player to choose an upgrade at start of game and every odd level
	// TODO: allow player to back out of menu and choose upgrade later if they do not want to make a selection right away
	public void chooseUpgrade() {
		boolean playerMadeSelection = false; 
		
		while (!playerMadeSelection) {
			GameLogic.clearConsole();
		    GameLogic.printHyphenHeader("Choose an upgrade. You will be able to read a description of your selection before making your final choice: ");
		    System.out.println("(1) Abilities --> Skills that utilize strength and dexterity to inflict damage. These have cooldowns.");
		    System.out.println("(2) Offensive Magic --> Spells that decimate your enemies. These consume mana.");
		    System.out.println("(3) Support Magic --> Spells with varying effects that support your character. These consume mana.");

		    int input = GameLogic.intUserInput("--> ", 3);
		    GameLogic.clearConsole();
	        
		    // TODO: when player selects no, program always outputs "no new _ to learn at the moment", even if player could learn something
		    switch (input) {
		    case 1:
		    	boolean abilityUpgradeAvailable = false;
		    	
		        // Print abilities and their descriptions
		        for (Abilities ability : Abilities.values()) 
		            System.out.println(ability.getAbilityName() + ": " + ability.getAbilityDescription() + " - Level " + ability.requiredAbilityLevel + " required.");

		        // logic for learning ability
		        for (int i = lastLearnedAbilityIndex + 1; i < Abilities.values().length; i++) {
		            Abilities ability = Abilities.values()[i];
		            if (ability.requiredAbilityLevel <= level && !learnedAbilities.getOrDefault(ability, false)) {
		            	abilityUpgradeAvailable = true;
		                System.out.println();
		                System.out.println("Would you like to learn " + ability.getAbilityName() + "? (Y/N)");
		                String response = in.nextLine().trim().toUpperCase();
		                if (response.equals("Y")) {
		                    System.out.println("Learning " + ability.getAbilityName() + "...");
		                    learnedAbilities.put(ability, true);
		                    System.out.println(ability.getAbilityName() + " learned!");
		                    lastLearnedAbilityIndex = i;
		                    playerMadeSelection = true;
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
		        if (!abilityUpgradeAvailable) {
		            System.out.println("No new abilities to learn at the moment.");
		            GameLogic.typeToContinue();
		        }
		        break;

		    case 2:
		    	boolean offMagUpgradeAvailable = false;
		        // Print offensive spells and their descriptions
		        for (OffMagSpells offSpell : OffMagSpells.values()) 
		            System.out.println(offSpell.getOffMagName() + ": " + offSpell.getOffMagDescription() + " - Level " + offSpell.requiredOffMagLevel + " required.");

		        // logic for learning offensive spell
		        for (int i = lastLearnedOffMagIndex + 1; i < OffMagSpells.values().length; i++) {
		            OffMagSpells offSpell = OffMagSpells.values()[i];
		            if (offSpell.requiredOffMagLevel <= level && !learnedOffensiveSpells.getOrDefault(offSpell, false)) {
		            	offMagUpgradeAvailable = true;
		                System.out.println();
		                System.out.println("Would you like to learn " + offSpell.getOffMagName() + "? (Y/N)");
		                String response = in.nextLine().trim().toUpperCase();
		                if (response.equals("Y")) {
		                    System.out.println("Learning " + offSpell.getOffMagName() + "...");
		                    learnedOffensiveSpells.put(offSpell, true);
		                    System.out.println(offSpell.getOffMagName() + " learned!");
		                    lastLearnedOffMagIndex = i;
		                    playerMadeSelection = true;
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
		        if (!offMagUpgradeAvailable) {
		            System.out.println("No new offensive magic spells to learn at the moment.");
		            GameLogic.typeToContinue();
		        }
		        break;

		    case 3:
		    	boolean suppMagUpgradeAvailable = false;
		        // Print support spells and their descriptions
		        for (SuppMagSpells suppSpell : SuppMagSpells.values()) 
		            System.out.println(suppSpell.getSuppMagName() + ": " + suppSpell.getSuppMagDescription() + " - Level " + suppSpell.requiredSuppMagLevel + " required.");

		        // logic for learning support spell
		        for (int i = lastLearnedSuppMagIndex + 1; i < SuppMagSpells.values().length; i++) {
		            SuppMagSpells suppSpell = SuppMagSpells.values()[i];
		            if (suppSpell.requiredSuppMagLevel <= level && !learnedSupportSpells.getOrDefault(suppSpell, false)) {
		            	suppMagUpgradeAvailable = true;
		                System.out.println();
		                System.out.println("Would you like to learn " + suppSpell.getSuppMagName() + "? (Y/N)");
		                String response = in.nextLine().trim().toUpperCase();
		                if (response.equals("Y")) {
		                    System.out.println("Learning " + suppSpell.getSuppMagName() + "...");
		                    learnedSupportSpells.put(suppSpell, true);
		                    System.out.println(suppSpell.getSuppMagName() + " learned!");
		                    lastLearnedSuppMagIndex = i;
		                    playerMadeSelection = true;
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
		        if (!suppMagUpgradeAvailable) {
		            System.out.println("No new support magic spells to learn at the moment.");
		            GameLogic.typeToContinue();
		        }
		        break;

		    default:
		        System.out.println("Invalid choice, please select again.");
		        break;
		    }
		}
	}
	
	public void printUpgrades() {
		// print out abilities
		boolean foundAbility = false;
		System.out.println("Abilities: ");
		for (Player.Abilities ability : Player.Abilities.values()) {
			if (AdventureGame.getPlayer().isAbilityLearned(ability)) {
				System.out.println(ability.getAbilityName() + " - " + ability.getAbilityDescription());
				foundAbility = true;
			}
		}
		if (!foundAbility)
			System.out.println("N/A");
		
		GameLogic.printHyphenSeparator(20);

		// print out offensive magic
		boolean foundOffMag = false;
		System.out.println("Offensive Spells: ");
		for (Player.OffMagSpells offMag : Player.OffMagSpells.values()) {
			if (AdventureGame.getPlayer().isOffensiveSpellLearned(offMag)) {
			System.out.println(offMag.getOffMagName() + " - " + offMag.getOffMagDescription());
			foundOffMag = true;
			}
		}
		if (!foundOffMag)
			System.out.println("N/A");
		
		GameLogic.printHyphenSeparator(20);

		// print out offensive magic
		boolean foundSuppMag = false;
		System.out.println("Support Spells: ");
		for (Player.SuppMagSpells suppMag : Player.SuppMagSpells.values()) {
			if (AdventureGame.getPlayer().isSupportSpellLearned(suppMag)) {
			System.out.println(suppMag.getSuppMagName() + " - " + suppMag.getSuppMagDescription());
			foundSuppMag = true;
			}
		}
		if (foundSuppMag == false)
			System.out.println("N/A");
		
		GameLogic.printHyphenSeparator(20);
	}
	
	// TODO: need a separate data structure to store CURRENT cooldowns on abilities so players can not spam them on load or start of every fight
	public static enum Abilities {
		STRENGTH("Strength", "Placeholder", 3, 1),
		AGILITY("Agility", "Placeholder", 5, 3),
		ENDURANCE("Endurance", "Placeholder", 4, 5),
		REFLEXES("Reflexes", "Placeholder", 6, 8);
	
		// instance variables for each ability
		private final String abilityName;
		private final String abilityDescription;
		private final int abilityCooldown;
		private final int requiredAbilityLevel;
		
		// Constructor
		Abilities(String abilityName, String abilityDescription, int abilityCooldown, int requiredLevel) {
			this.abilityName = abilityName;
			this.abilityDescription = abilityDescription;
			this.abilityCooldown = abilityCooldown;
			this.requiredAbilityLevel = requiredLevel;
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
	}
	
	public static enum OffMagSpells {
		ICE_SPIKE("Ice Spike", "Placeholder", 3, 1),
		FIREBALL("Fireball", "Placeholder", 5, 3),
		TELEPORT("Teleport", "Placeholder", 12, 5),
		LIGHTNING_BOLT("Lightning Bolt", "Placeholder", 20, 8),
		METEOR_SHOWER("Meteor Shower", "Placeholder", 35, 11);
			
		// instance variables for each offensive spell
		private final String offMagName;
		private final String offMagDescription;
		private final int offMagManaCost;
		private final int requiredOffMagLevel;
		
		// Constructor
		OffMagSpells(String offMagName, String offMagDescription, int offMagManaCost, int requiredLevel) {
			this.offMagName = offMagName;
			this.offMagDescription = offMagDescription;
			this.offMagManaCost = offMagManaCost;
			this.requiredOffMagLevel = requiredLevel;
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
	}

	public static enum SuppMagSpells {
		CURE_POISON("Cure Poison", "Placeholder", 4, 1),
		BURSTING_LIGHT("Burst of Light", "Placeholder", 6, 3),
		INVISIBILITY("Invisibility", "Placeholder", 10, 5),
		INCREASE_SPELL_DAMAGE("Increase Spell Damage", "Placeholder", 12, 8);
			
		// instance variables for each defensive spell
		private final String suppMagName;
		private final String suppMagDescription;
		private final int suppMagManaCost;
		private final int requiredSuppMagLevel;
		
		// Constructor
		SuppMagSpells(String suppMagName, String suppMagDescription, int suppMagManaCost, int requiredLevel) {
			this.suppMagName = suppMagName;
			this.suppMagDescription = suppMagDescription;
			this.suppMagManaCost = suppMagManaCost;
			this.requiredSuppMagLevel = requiredLevel;
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
	}
}
