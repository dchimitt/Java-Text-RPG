package gearAndItems;

import gearAndItems.PlayerItems.Items;
import java.util.EnumMap;
import java.util.Map;

import com.dchimitt.main.AdventureGame;

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
		KIDS_FLUTE("Kid's Flute", "This is the last heirloom you have to remind you of your son", "strength", 0, 0, 0, 50),
		STARTING_SWORD("Starting Sword", "Placeholder", "strength", 0, 0, 50, 50/4),
		STARTING_DAGGER("Starting Dagger", "Placeholder", "dexterity", 0, 0, 50, 50/4),
		STARTING_WAND("Starting Wand", "Placeholder", "intelligence", 0, 0, 50, 50/4),
		STARTING_CHAINMAIL("Starting Chainmail", "Placeholder", "strength", 0, 0, 40, 40/4),
		STARTING_VEST("Starting Vest", "Placeholder", "dexterity", 0, 0, 40, 40/4),
		STARTING_ROBE("Starting Robe", "Placeholder", "intelligence", 0, 0, 40, 40/4),
		MIDDLE_SWORD("Middle Sword", "Placeholder", "strength", 0, 0, 175, 175/4),
		MIDDLE_DAGGER("Middle Dagger", "Placeholder", "dexterity", 0, 0, 175, 175/4),
		MIDDLE_WAND("Middle Wand", "Placeholder", "intelligence", 0, 0, 175, 175/4),
		MIDDLE_CHAINMAIL("Middle Chainmail", "Placeholder", "strength", 0, 0, 150, 150/4),
		MIDDLE_VEST("Middle Vest", "Placeholder", "dexterity", 0, 0, 150, 150/4),
		MIDDLE_ROBE("Middle Robe", "Placeholder", "intelligence", 0, 0, 150, 150/4),
		FINAL_SWORD("Final Sword", "Placeholder", "strength", 0, 0, 525, 525/4),
		FINAL_DAGGER("Final Dagger", "Placeholder", "dexterity", 0, 0, 525, 525/4),
		FINAL_WAND("Final Wand", "Placeholder", "intelligence", 0, 0, 525, 525/4),
		FINAL_CHAINMAIL("Final Chainmail", "Placeholder", "strength", 0, 0, 415, 415/4),
		FINAL_VEST("Final Vest", "Placeholder", "dexterity", 0, 0, 415, 415/4),
		FINAL_ROBE("Final Robe", "Placeholder", "intelligence", 0, 0, 415, 415/4);
	
		// instance variables for each ability
		private final String gearName;
		private final String gearDescription;
		private final String gearStatType;
		private final int gearStatRequirement;
		private final int gearStatIncrease;
		private int gearCostInGold;
		private int gearSellingPrice; // 25% of purchase price
		
		// Constructor
		Gear(String gearName, String gearDescription, String gearStatType, int gearStatRequirement, int gearStatIncrease, int gearCostInGold, int gearSellingPrice) {
			this.gearName = gearName;
			this.gearDescription = gearDescription;
			this.gearStatType = gearStatType;
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
		// set starting quantity to default 0 or amount player has
		int quantity = gearQuantities.getOrDefault(gear, 0);
		
		// check if player has the gear
		if (quantity == 0)
			System.out.println("You cannot equip something you do not own!");
		
		// check if player has gear AND if their specific stat meets the stat requirement to wear the selected gear
		// FOR STRENGTH GEAR
		if ((quantity > 0) && (gear.getGearStatType().equals("strength")) && (AdventureGame.getPlayer().strength >= gear.getGearStatRequirement())) {
			System.out.println("Equipping " + gear.getGearName() + "...");
			gearQuantities.put(gear, quantity - 1);
			if (gear.getGearName().trim().toUpperCase().equals("STARTING SWORD")) 
				AdventureGame.getPlayer().strength += gear.getGearStatIncrease();
			else if (gear.getGearName().trim().toUpperCase().equals("STARTING CHAINMAIL")) 
				AdventureGame.getPlayer().strength += gear.getGearStatIncrease();
			// TODO: add as needed (maybe think of an easier way to equip vs. having to do this for every piece of gear...
			//       OR just have a base stat requirement and player only needs one stat at that level to wear it?
			else if ((quantity > 0) && (gear.getGearStatType().equals("strength")) && (AdventureGame.getPlayer().strength < gear.getGearStatRequirement()))
				System.out.println("You do not have enough strength to wear that!");
		}
		
		// FOR DEXTERITY GEAR
		if ((quantity > 0) && (gear.getGearStatType().equals("dexterity")) && (AdventureGame.getPlayer().dexterity >= gear.getGearStatRequirement())) {
			System.out.println("Equipping " + gear.getGearName() + "...");
			gearQuantities.put(gear, quantity - 1);
			if (gear.getGearName().trim().toUpperCase().equals("STARTING DAGGER")) 
				AdventureGame.getPlayer().dexterity += gear.getGearStatIncrease();
			else if (gear.getGearName().trim().toUpperCase().equals("STARTING VEST")) 
				AdventureGame.getPlayer().dexterity += gear.getGearStatIncrease();
			// TODO: add as needed (maybe think of an easier way to equip vs. having to do this for every piece of gear...
			//       OR just have a base stat requirement and player only needs one stat at that level to wear it?
			else if ((quantity > 0) && (gear.getGearStatType().equals("dexterity")) && (AdventureGame.getPlayer().dexterity < gear.getGearStatRequirement()))
				System.out.println("You do not have enough dexterity to wear that!");
		}
		
		// FOR INTELLIGENCE GEAR
		if ((quantity > 0) && (gear.getGearStatType().equals("intelligence")) && (AdventureGame.getPlayer().intelligence >= gear.getGearStatRequirement())) {
			System.out.println("Equipping " + gear.getGearName() + "...");
			gearQuantities.put(gear, quantity - 1);
			if (gear.getGearName().trim().toUpperCase().equals("STARTING WAND")) 
				AdventureGame.getPlayer().intelligence += gear.getGearStatIncrease();
			else if (gear.getGearName().trim().toUpperCase().equals("STARTING ROBE")) 
				AdventureGame.getPlayer().intelligence += gear.getGearStatIncrease();
			// TODO: add as needed (maybe think of an easier way to equip vs. having to do this for every piece of gear...
			//       OR just have a base stat requirement and player only needs one stat at that level to wear it?
			else if ((quantity > 0) && (gear.getGearStatType().equals("intelligence")) && (AdventureGame.getPlayer().intelligence < gear.getGearStatRequirement()))
				System.out.println("You do not have enough intelligence to wear that!");
		}
	}
			
	public static void unequipGear(Gear gear) {
		int quantity = gearQuantities.getOrDefault(gear, 0);
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
