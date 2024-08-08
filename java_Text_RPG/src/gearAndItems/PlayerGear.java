package gearAndItems;

import gearAndItems.PlayerItems.Items;
import java.util.EnumMap;
import java.util.Map;

public class PlayerGear {
	// enum map used to store gear quantities
	private static final Map<Gear, Integer> gearQuantities = new EnumMap<>(Gear.class);
	
	// Initialize quantity of 0 for each gear in the map
    static {
        for (Gear gear : Gear.values()) {
            gearQuantities.put(gear, 0);
        }
    }
	
	public static enum Gear {
		KIDS_FLUTE("Kid's Flute", "This is the last heirloom you have to remind you of your son", 0, 0, 0, 25),
		STARTING_SWORD("Starting Sword", "Placeholder", 0, 0, 50, 50/4),
		STARTING_DAGGER("Starting Dagger", "Placeholder", 0, 0, 50, 50/4),
		STARTING_WAND("Starting Wand", "Placeholder", 0, 0, 50, 50/4),
		STARTING_CHAINMAIL("Starting Chainmail", "Placeholder", 0, 0, 40, 40/4),
		STARTING_VEST("Starting Vest", "Placeholder", 0, 0, 40, 40/4),
		STARTING_ROBE("Starting Robe", "Placeholder", 0, 0, 40, 40/4),
		MIDDLE_SWORD("Middle Sword", "Placeholder", 0, 0, 175, 175/4),
		MIDDLE_DAGGER("Middle Dagger", "Placeholder", 0, 0, 175, 175/4),
		MIDDLE_WAND("Middle Wand", "Placeholder", 0, 0, 175, 175/4),
		MIDDLE_CHAINMAIL("Middle Chainmail", "Placeholder", 0, 0, 150, 150/4),
		MIDDLE_VEST("Middle Vest", "Placeholder", 0, 0, 150, 150/4),
		MIDDLE_ROBE("Middle Robe", "Placeholder", 0, 0, 150, 150/4),
		FINAL_SWORD("Final Sword", "Placeholder", 0, 0, 525, 525/4),
		FINAL_DAGGER("Final Dagger", "Placeholder", 0, 0, 525, 525/4),
		FINAL_WAND("Final Wand", "Placeholder", 0, 0, 525, 525/4),
		FINAL_CHAINMAIL("Final Chainmail", "Placeholder", 0, 0, 415, 415/4),
		FINAL_VEST("Final Vest", "Placeholder", 0, 0, 415, 415/4),
		FINAL_ROBE("Final Robe", "Placeholder", 0, 0, 415, 415/4);
	
		// instance variables for each ability
		private final String gearName;
		private final String gearDescription;
		private final int gearStatRequirement;
		private final int gearStatIncrease;
		private int gearCostInGold;
		private int gearSellingPrice; // 25% of purchase price
		
		// Constructor
		Gear(String gearName, String gearDescription, int gearStatRequirement, int gearStatIncrease, int gearCostInGold, int gearSellingPrice) {
			this.gearName = gearName;
			this.gearDescription = gearDescription;
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
		gearQuantities.put(gear,  currentQuantity + 1);
	}
			
	public static void equipGear(Gear gear) {
		// implement logic later
	}
			
	public static void unequipGear(Gear gear) {
		// implement later
	}
	
	// Method to print the gear a player currently owns
    public static void printPlayerGear() {
        for (Map.Entry<Gear, Integer> entry : gearQuantities.entrySet()) {
            Gear gear = entry.getKey();
            int quantity = entry.getValue();
            if (quantity > 0) 
                System.out.println(gear.getGearName() + ": " + gear.getGearDescription() + " (" + quantity + ")");
        }
    }
}
