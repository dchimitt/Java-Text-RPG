package gearAndItems;

import java.util.Scanner;
import java.util.EnumMap;
import java.util.Map;
import java.io.Serializable;

import com.dchimitt.main.AdventureGame;
import com.dchimitt.main.GameLogic;
import com.dchimitt.main.Player;

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
	
	public static void equipGear(Gear gear) {
        boolean keepEquipping = true;

        while (keepEquipping) {
            // Get the current quantity of the gear or default to 0
            int quantity = gearQuantities.getOrDefault(gear, 0);

            // Check if the player owns the gear
            if (quantity == 0) {
                System.out.println("Error: You cannot equip something you do not own!");
                return;
            }

            // Check the required stat type and player's stat value
            String statType = gear.getGearStatType();
            int playerStat = AdventureGame.player.getPlayerStat(statType);

            // Check if the player meets the stat requirement
            if (playerStat < gear.getGearStatRequirement()) {
                System.out.println("You do not have enough " + statType + " to wear that!");
                return;
            }

            // Handle equipping/unequipping of gear based on type
            if (gear.gearType.equals("weapon")) {
                handleGearSwap(gear, statType, "weapon", isWeaponEquipped, equippedWeapon);
                isWeaponEquipped = true;
                equippedWeapon = gear;
            } 
            else if (gear.gearType.equals("chest armor")) {
                handleGearSwap(gear, statType, "chest armor", isChestArmorEquipped, equippedChestArmor);
                isChestArmorEquipped = true;
                equippedChestArmor = gear;
            }

            // Show equipped gear and ask if the player wants to change another piece of gear
            GameLogic.clearConsole();
            printEquippedGear();
            GameLogic.printHyphenSeparator(20);
            System.out.println();
            printPlayerGearInInventory();
            System.out.println("Would you like to change another piece of gear? (Y/N)");
            String decision = in.nextLine().trim().toUpperCase();

            if (!decision.equals("Y")) 
                keepEquipping = false;
            else {
            	GameLogic.clearConsole();
                printEquippedGear();
                GameLogic.printHyphenSeparator(20);
                printPlayerGearInInventory();
                System.out.println("Please type the name of the piece of gear you want to equip. \nNOTE: must type names exactly as written");
                String gearName = in.nextLine().trim().toUpperCase();

                // Attempt to equip the next piece of gear
                Gear nextGear = getGearByName(gearName);
                if (nextGear != null) {
                	GameLogic.clearConsole();
                    gear = nextGear;
                } 
                else {
                    System.out.println("Invalid gear name. Exiting gear menu.");
                    keepEquipping = false;
                }
            }
        }
    }
	
	// Helper method to handle gear swap
    private static void handleGearSwap(Gear newGear, String statType, String gearType, boolean isEquipped, Gear equippedGear) {
        if (isEquipped && equippedGear != null) {
            unequipGear(equippedGear);
        }
        equipNewGear(newGear);
        GameLogic.typeToContinue();
    }

    private static void unequipGear(Gear gear) {
        System.out.println("Unequipping " + gear.getGearName() + "...");
        
        // add gear back to inventory
        increaseGearQuantity(gear); 
        
        // remove gear stats from player
        adjustPlayerStat(gear.getGearStatType(), -gear.getGearStatIncrease());
    }

    private static void equipNewGear(Gear gear) {
        System.out.println("Equipping " + gear.getGearName() + "...");
        int currentQuantity = gearQuantities.getOrDefault(gear,  0);
        if (currentQuantity <= 0) {
        	System.out.println("Error: You cannot equip something you do not own!");
        	GameLogic.typeToContinue();
        	return;
        }
        
        // remove the gear from player inventory
        gearQuantities.put(gear, currentQuantity - 1);
        
        // increase player's stats by gear increase
        adjustPlayerStat(gear.getGearStatType(), gear.getGearStatIncrease());
    }
    
    // helper method to get a specific gear by its name
    private static Gear getGearByName(String gearName) {
    	for (Gear gear : Gear.values()) {
    		if (gear.getGearName().toUpperCase().equals(gearName))
    			return gear;
    	}
    	return null;
    }

    private static void adjustPlayerStat(String statType, int statIncrease) {
        switch (statType) {
            case "strength":
                AdventureGame.getPlayer().strength += statIncrease;
                break;
            case "dexterity":
                AdventureGame.getPlayer().dexterity += statIncrease;
                break;
            case "intelligence":
                AdventureGame.getPlayer().intelligence += statIncrease;
                break;
        }
    }
	
	// Method to print the gear a player currently owns in their inventory
    public static void printPlayerGearInInventory() {
    	System.out.println("\\\\Gear in your inventory\\\\\n");
        for (Map.Entry<Gear, Integer> entry : gearQuantities.entrySet()) {
            Gear gear = entry.getKey();
            int quantity = entry.getValue();
            if (quantity > 0) 
                System.out.println(gear.getGearName() + ": " + gear.getGearDescription() + " (" + quantity + ")");
        }
        System.out.println();
    }
    
    public static void printEquippedGear() {
		System.out.println("\\\\You are currently wearing\\\\\n");
		if (equippedWeapon != null) 
			System.out.println("WEAPON: " + equippedWeapon.getGearName() + " (" + equippedWeapon.getGearDescription() + ")");
		else 
			System.out.println("WEAPON: None");
		
		if (equippedChestArmor != null)
			System.out.println("CHEST : " + equippedChestArmor.getGearName() + " (" + equippedChestArmor.getGearDescription() + ")");
		else
			System.out.println("CHEST : None");
		System.out.println();
	}
    
    // getter and setter methods for saving/loading of gear and boolean statuses
    public static Gear getEquippedWeapon() {
    	return equippedWeapon;
    }
    //
    public static Gear getEquippedChestArmor() {
    	return equippedChestArmor;
    }
    //
    public void setEquippedWeapon(Gear weapon) {
    	equippedWeapon = weapon;
    }
    public void setEquippedChestArmor(Gear armor) {
    	equippedChestArmor = armor;
    }
    public void setWeaponEquippedStatus(boolean status) {
    	isWeaponEquipped = status;
    }
    public void setChestArmorEquippedStatus(boolean status) {
    	isChestArmorEquipped = status;
    }

}
