package gearAndItems;

import java.util.Scanner;
import java.util.EnumMap;
import java.util.Map;
import java.io.Serializable;

import com.dchimitt.main.AdventureGame;

import gearAndItems.PlayerItems.Items;

public class PlayerGear implements Serializable {
	static Scanner in = new Scanner(System.in);
	// enum map used to store gear quantities
	private static final Map<Gear, Integer> gearQuantities = new EnumMap<>(Gear.class);
	
	// global boolean values of whether player has a particular piece of gear already equipped
	public static boolean isWeaponEquipped;
	public static boolean isChestArmorEquipped;
	
	// global String values of player's equipped gear
	public static Gear equippedWeapon;
	public static Gear equippedChestArmor;
	
	// Constructor to initialize quantity of 0 for each gear in the map
    static {
        for (Gear gear : Gear.values()) {
            gearQuantities.put(gear, 0);
        }
        equippedWeapon = null;
        equippedChestArmor = null;
        isWeaponEquipped = false;
        isChestArmorEquipped = false;
    }
	
	public static enum Gear {
		KIDS_FLUTE("Kid's Flute", "This is the last heirloom you have to remind you of your son", "strength", "weapon", 0, 0, 0, 50),
		STARTING_SWORD("Starting Sword", "Placeholder", "strength", "weapon", 0, 0, 50, 50/4),
		STARTING_DAGGER("Starting Dagger", "Placeholder", "dexterity", "weapon", 0, 0, 50, 50/4),
		STARTING_WAND("Starting Wand", "Placeholder", "intelligence", "weapon", 0, 0, 50, 50/4),
		STARTING_CHAINMAIL("Starting Chainmail", "Placeholder", "strength", "chest armor", 0, 0, 40, 40/4),
		STARTING_VEST("Starting Vest", "Placeholder", "dexterity", "chest armor", 0, 0, 40, 40/4),
		STARTING_ROBE("Starting Robe", "Placeholder", "intelligence", "chest armor", 0, 0, 40, 40/4),
		MIDDLE_SWORD("Middle Sword", "Placeholder", "strength", "weapon", 0, 0, 175, 175/4),
		MIDDLE_DAGGER("Middle Dagger", "Placeholder", "dexterity", "weapon", 0, 0, 175, 175/4),
		MIDDLE_WAND("Middle Wand", "Placeholder", "intelligence", "weapon", 0, 0, 175, 175/4),
		MIDDLE_CHAINMAIL("Middle Chainmail", "Placeholder", "strength", "chest armor", 0, 0, 150, 150/4),
		MIDDLE_VEST("Middle Vest", "Placeholder", "dexterity", "chest armor", 0, 0, 150, 150/4),
		MIDDLE_ROBE("Middle Robe", "Placeholder", "intelligence", "chest armor", 0, 0, 150, 150/4),
		FINAL_SWORD("Final Sword", "Placeholder", "strength", "weapon", 0, 0, 525, 525/4),
		FINAL_DAGGER("Final Dagger", "Placeholder", "dexterity", "weapon", 0, 0, 525, 525/4),
		FINAL_WAND("Final Wand", "Placeholder", "intelligence", "weapon", 0, 0, 525, 525/4),
		FINAL_CHAINMAIL("Final Chainmail", "Placeholder", "strength", "chest armor", 0, 0, 415, 415/4),
		FINAL_VEST("Final Vest", "Placeholder", "dexterity", "chest armor", 0, 0, 415, 415/4),
		FINAL_ROBE("Final Robe", "Placeholder", "intelligence", "chest armor", 0, 0, 415, 415/4);
	
		// instance variables for each ability
		private final String gearName;
		private final String gearDescription;
		private final String gearStatType;
		private final String gearType;
		private final int gearStatRequirement;
		private final int gearStatIncrease;
		private int gearCostInGold;
		private int gearSellingPrice; // 25% of purchase price
		
		// Constructor
		Gear(String gearName, String gearDescription, String gearStatType, String gearType, int gearStatRequirement, int gearStatIncrease, int gearCostInGold, int gearSellingPrice) {
			this.gearName = gearName;
			this.gearDescription = gearDescription;
			this.gearStatType = gearStatType;
			this.gearType = gearType;
			this.gearStatRequirement = gearStatRequirement;
			this.gearStatIncrease = gearStatIncrease;
			this.gearCostInGold = gearCostInGold;
			this.gearSellingPrice = gearSellingPrice;
		}
		
		// getter methods
		public String getGearName() {
			return gearName;
		}
		public String getGearDescription() {
			return gearDescription;
		}
		public String getGearStatType() {
			return gearStatType;
		}
		public int getGearStatRequirement() {
			return gearStatRequirement;
		}
		public int getGearStatIncrease() {
			return gearStatIncrease;
		}
		public int getGearCostInGold() {
			return gearCostInGold;
		}
	}
	
	// Methods for saving and loading gear quantities
    public static Map<Gear, Integer> getGearQuantities() {
        return new EnumMap<>(gearQuantities);
    }
    
    public static void setGearQuantities(Map<Gear, Integer> newQuantities) {
        gearQuantities.clear();
        gearQuantities.putAll(newQuantities);
    }
	
	// method to increase gear quantity from purchase or boss drops
	public static void increaseGearQuantity(Gear gear) {
		int currentQuantity = gearQuantities.getOrDefault(gear,  0);
		gearQuantities.put(gear, currentQuantity + 1);
	}
	
	// TODO: possible bug if player tries to equip the exact same piece of gear they are already wearing
	public static void equipGear(Gear gear) {		
		// set starting quantity to default 0 or amount player has
		int quantity = gearQuantities.getOrDefault(gear, 0);
		
		// check if player has the gear
		if (quantity == 0) {
			System.out.println("You cannot equip something you do not own!");
			return;
		}
		
		// check the stat type that is required for this piece of gear and check the corresponding stat the player has of that type
		String statType = gear.getGearStatType();
		int playerStat = AdventureGame.player.getPlayerStat(statType);
		
		// check if player meets the stat requirement for the piece of gear they want to equip
		if (playerStat < gear.getGearStatRequirement()) {
			System.out.println("You do not have enough " + statType + " to wear that!");
			return;
		}
		
		// handle logic for equipping/unequipping of strength gear
		if (statType.equals("strength") && gear.gearType.equals("weapon")) {
			if (!isWeaponEquipped) {
				System.out.println("Equipping " + gear.getGearName() + "...");
				gearQuantities.put(gear,  quantity - 1);
				AdventureGame.getPlayer().strength += gear.getGearStatIncrease();
				isWeaponEquipped = true;
				equippedWeapon = gear;
			}
			else {
				// weapon is already equipped, ask player if they want to remove it first
				System.out.println("You already have a weapon equipped! Unequip it now? Y for yes, N for no");
				String unequipDecision = in.nextLine().trim();
				if (unequipDecision.trim().toUpperCase().equals("Y")) {
					// unequip weapon and add back to inventory
					System.out.println("Unequipping " + equippedWeapon.getGearName() + "...");
					gearQuantities.put(equippedWeapon, gearQuantities.getOrDefault(equippedWeapon, 0) + 1);
	                AdventureGame.getPlayer().strength -= equippedWeapon.getGearStatIncrease();
	                isWeaponEquipped = false;
	                equippedWeapon = null;

	                // Equip the new weapon
	                System.out.println("Equipping " + gear.getGearName() + "...");
	                gearQuantities.put(gear, quantity - 1);
	                AdventureGame.getPlayer().strength += gear.getGearStatIncrease();
	                isWeaponEquipped = true;
	                equippedWeapon = gear;
	            } 
				else 
	                System.out.println("You chose not to equip " + gear.getGearName() + ".");
			}
		}
		if (statType.equals("strength") && gear.gearType.equals("chest armor")) {
			if (!isChestArmorEquipped) {
				System.out.println("Equipping " + gear.getGearName() + "...");
				gearQuantities.put(gear,  quantity - 1);
				AdventureGame.getPlayer().strength += gear.getGearStatIncrease();
				isChestArmorEquipped = true;
				equippedChestArmor = gear;
			}
			else {
				// chest armor is already equipped, ask player if they want to remove it first
				System.out.println("You already have a chest piece equipped! Unequip it now? Y for yes, N for no");
				String unequipDecision = in.nextLine().trim();
				if (unequipDecision.trim().toUpperCase().equals("Y")) {
					// unequip chest armor and add back to inventory
					System.out.println("Unequipping " + equippedChestArmor.getGearName() + "...");
					gearQuantities.put(equippedChestArmor, gearQuantities.getOrDefault(equippedChestArmor, 0) + 1);
	                AdventureGame.getPlayer().strength -= equippedChestArmor.getGearStatIncrease();
	                isChestArmorEquipped = false;
	                equippedChestArmor = null;

	                // Equip the new chest armor
	                System.out.println("Equipping " + gear.getGearName() + "...");
	                gearQuantities.put(gear, quantity - 1);
	                AdventureGame.getPlayer().strength += gear.getGearStatIncrease();
	                isChestArmorEquipped = true;
	                equippedChestArmor = gear;
	            } 
				else 
	                System.out.println("You chose not to equip " + gear.getGearName() + ".");
			}
		}
		
		// handle logic for equipping/unequipping of dexterity gear
		if (statType.equals("dexterity") && gear.gearType.equals("weapon")) {
			if (!isWeaponEquipped) {
				System.out.println("Equipping " + gear.getGearName() + "...");
				gearQuantities.put(gear,  quantity - 1);
				AdventureGame.getPlayer().dexterity += gear.getGearStatIncrease();
				isWeaponEquipped = true;
				equippedWeapon = gear;
			}
			else {
				// weapon is already equipped, ask player if they want to remove it first
				System.out.println("You already have a weapon equipped! Unequip it now? Y for yes, N for no");
				String unequipDecision = in.nextLine().trim();
				if (unequipDecision.trim().toUpperCase().equals("Y")) {
					// unequip weapon and add back to inventory
					System.out.println("Unequipping " + equippedWeapon.getGearName() + "...");
					gearQuantities.put(equippedWeapon, gearQuantities.getOrDefault(equippedWeapon, 0) + 1);
			        AdventureGame.getPlayer().dexterity -= equippedWeapon.getGearStatIncrease();
			        isWeaponEquipped = false;
			        equippedWeapon = null;

			        // Equip the new weapon
			        System.out.println("Equipping " + gear.getGearName() + "...");
			        gearQuantities.put(gear, quantity - 1);
			        AdventureGame.getPlayer().dexterity += gear.getGearStatIncrease();
			        isWeaponEquipped = true;
			        equippedWeapon = gear;
				} 
				else 
					System.out.println("You chose not to equip " + gear.getGearName() + ".");
			}
		}
		if (statType.equals("dexterity") && gear.gearType.equals("chest armor")) {
			if (!isChestArmorEquipped) {
				System.out.println("Equipping " + gear.getGearName() + "...");
				gearQuantities.put(gear,  quantity - 1);
				AdventureGame.getPlayer().dexterity += gear.getGearStatIncrease();
				isChestArmorEquipped = true;
				equippedChestArmor = gear;
			}
			else {
				// chest armor is already equipped, ask player if they want to remove it first
				System.out.println("You already have a chest piece equipped! Unequip it now? Y for yes, N for no");
				String unequipDecision = in.nextLine().trim();
				if (unequipDecision.trim().toUpperCase().equals("Y")) {
					// unequip chest armor and add back to inventory
					System.out.println("Unequipping " + equippedChestArmor.getGearName() + "...");
					gearQuantities.put(equippedChestArmor, gearQuantities.getOrDefault(equippedChestArmor, 0) + 1);
			        AdventureGame.getPlayer().dexterity -= equippedChestArmor.getGearStatIncrease();
			        isChestArmorEquipped = false;
			        equippedChestArmor = null;

			        // Equip the new chest armor
			        System.out.println("Equipping " + gear.getGearName() + "...");
			        gearQuantities.put(gear, quantity - 1);
			        AdventureGame.getPlayer().dexterity += gear.getGearStatIncrease();
			        isChestArmorEquipped = true;
			        equippedChestArmor = gear;
			    } 
				else 
					System.out.println("You chose not to equip " + gear.getGearName() + ".");
			}
		}
				
		// handle logic for equipping/unequipping of intelligence gear
		if (statType.equals("intelligence") && gear.gearType.equals("weapon")) {
			if (!isWeaponEquipped) {
				System.out.println("Equipping " + gear.getGearName() + "...");
				gearQuantities.put(gear,  quantity - 1);
				AdventureGame.getPlayer().intelligence += gear.getGearStatIncrease();
				isWeaponEquipped = true;
				equippedWeapon = gear;
			}
			else {
				// weapon is already equipped, ask player if they want to remove it first
				System.out.println("You already have a weapon equipped! Unequip it now? Y for yes, N for no");
				String unequipDecision = in.nextLine().trim();
				if (unequipDecision.trim().toUpperCase().equals("Y")) {
					// unequip weapon and add back to inventory
					System.out.println("Unequipping " + equippedWeapon.getGearName() + "...");
					gearQuantities.put(equippedWeapon, gearQuantities.getOrDefault(equippedWeapon, 0) + 1);
			        AdventureGame.getPlayer().intelligence -= equippedWeapon.getGearStatIncrease();
			        isWeaponEquipped = false;
			        equippedWeapon = null;

			        // Equip the new weapon
			        System.out.println("Equipping " + gear.getGearName() + "...");
			        gearQuantities.put(gear, quantity - 1);
			        AdventureGame.getPlayer().intelligence += gear.getGearStatIncrease();
			        isWeaponEquipped = true;
			        equippedWeapon = gear;
				} 
				else 
					System.out.println("You chose not to equip " + gear.getGearName() + ".");
			}
		}
		if (statType.equals("intelligence") && gear.gearType.equals("chest armor")) {
			if (!isChestArmorEquipped) {
				System.out.println("Equipping " + gear.getGearName() + "...");
				gearQuantities.put(gear,  quantity - 1);
				AdventureGame.getPlayer().intelligence += gear.getGearStatIncrease();
				isChestArmorEquipped = true;
				equippedChestArmor = gear;
			}
			else {
				// chest armor is already equipped, ask player if they want to remove it first
				System.out.println("You already have a chest piece equipped! Unequip it now? Y for yes, N for no");
				String unequipDecision = in.nextLine().trim();
				if (unequipDecision.trim().toUpperCase().equals("Y")) {
					// unequip chest armor and add back to inventory
					System.out.println("Unequipping " + equippedChestArmor.getGearName() + "...");
					gearQuantities.put(equippedChestArmor, gearQuantities.getOrDefault(equippedChestArmor, 0) + 1);
			        AdventureGame.getPlayer().intelligence -= equippedChestArmor.getGearStatIncrease();
			        isChestArmorEquipped = false;
			        equippedChestArmor = null;

			        // Equip the new chest armor
			        System.out.println("Equipping " + gear.getGearName() + "...");
			        gearQuantities.put(gear, quantity - 1);
			        AdventureGame.getPlayer().intelligence += gear.getGearStatIncrease();
			        isChestArmorEquipped = true;
			        equippedChestArmor = gear;
			    } 
				else 
					System.out.println("You chose not to equip " + gear.getGearName() + ".");
			}
		}
	}
	
	// Method to print the gear a player currently owns in their inventory
    public static void printPlayerGearInInventory() {
        for (Map.Entry<Gear, Integer> entry : gearQuantities.entrySet()) {
            Gear gear = entry.getKey();
            int quantity = entry.getValue();
            if (quantity > 0) 
                System.out.println(gear.getGearName() + ": " + gear.getGearDescription() + " (" + quantity + ")");
        }
    }
    
    public static void printEquippedGear() {
		System.out.println("--You are currently wearing--");
		if (equippedWeapon != null) 
			System.out.println("WEAPON: " + equippedWeapon.getGearName() + " (" + equippedWeapon.getGearDescription() + ")");
		else 
			System.out.println("WEAPON: None");
		
		if (equippedChestArmor != null)
			System.out.println("CHEST: " + equippedChestArmor.getGearName() + " (" + equippedChestArmor.getGearDescription() + ")");
		else
			System.out.println("CHEST: None");
	}
    
    // getter and setter methods for equiping gear
    public static Gear getEquippedWeapon() {
    	return equippedWeapon;
    }
    //
    public static Gear getEquippedChestArmor() {
    	return equippedChestArmor;
    }
    //
    public void setEquippedGear(Gear weapon, Gear armor) {
    	equippedWeapon = weapon;
    	equippedChestArmor = armor;
    }

}
