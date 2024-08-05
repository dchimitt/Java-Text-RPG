package gearAndItems;

import gearAndItems.PlayerItems.Items;

public class PlayerGear {
	public static enum Gear {
		KIDS_FLUTE("Kid's Flute", "This is the last heirloom you have to remind you of your son", 0, 0, 0, 0, 25),
		STARTING_SWORD("Starting Sword", "Placeholder", 0, 0, 0, 50, 50/4),
		STARTING_DAGGER("Starting Dagger", "Placeholder", 0, 0, 0, 50, 50/4),
		STARTING_WAND("Starting Wand", "Placeholder", 0, 0, 0, 50, 50/4),
		STARTING_CHAINMAIL("Starting Chainmail", "Placeholder", 0, 0, 0, 40, 40/4),
		STARTING_VEST("Starting Vest", "Placeholder", 0, 0, 0, 40, 40/4),
		STARTING_ROBE("Starting Robe", "Placeholder", 0, 0, 0, 40, 40/4),
		MIDDLE_SWORD("Middle Sword", "Placeholder", 0, 0, 0, 175, 175/4),
		MIDDLE_DAGGER("Middle Dagger", "Placeholder", 0, 0, 0, 175, 175/4),
		MIDDLE_WAND("Middle Wand", "Placeholder", 0, 0, 0, 175, 175/4),
		MIDDLE_CHAINMAIL("Middle Chainmail", "Placeholder", 0, 0, 0, 150, 150/4),
		MIDDLE_VEST("Middle Vest", "Placeholder", 0, 0, 0, 150, 150/4),
		MIDDLE_ROBE("Middle Robe", "Placeholder", 0, 0, 0, 150, 150/4),
		FINAL_SWORD("Final Sword", "Placeholder", 0, 0, 0, 525, 525/4),
		FINAL_DAGGER("Final Dagger", "Placeholder", 0, 0, 0, 525, 525/4),
		FINAL_WAND("Final Wand", "Placeholder", 0, 0, 0, 525, 525/4),
		FINAL_CHAINMAIL("Final Chainmail", "Placeholder", 0, 0, 0, 415, 415/4),
		FINAL_VEST("Final Vest", "Placeholder", 0, 0, 0, 415, 415/4),
		FINAL_ROBE("Final Robe", "Placeholder", 0, 0, 0, 415, 415/4);
	
		// instance variables for each ability
		private final String gearName;
		private final String gearDescription;
		private final int gearStatRequirement;
		private final int gearStatIncrease;
		private int gearQuantity;
		private int gearCostInGold;
		private int gearSellingPrice; // 25% of purchase price
		
		// Constructor
		Gear(String gearName, String gearDescription, int gearStatRequirement, int gearStatIncrease, int gearQuantity, int gearCostInGold, int gearSellingPrice) {
			this.gearName = gearName;
			this.gearDescription = gearDescription;
			this.gearStatRequirement = gearStatRequirement;
			this.gearStatIncrease = gearStatIncrease;
			this.gearQuantity = gearQuantity;
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
		public int getGearQuantity() {
			return gearQuantity;
		}
		public int getGearCostInGold() {
			return gearCostInGold;
		}
	}
	
	// methods to increase item quantity when player makes a purchase or obtains drop from monsters/bosses
	public static void increaseStartingSwordQuantity() {
		Gear.STARTING_SWORD.gearQuantity++;
	}
	public static void increaseStartingDaggerQuantity() {
		Gear.STARTING_DAGGER.gearQuantity++;	
	}
	public static void increaseStartingWandQuantity() {
		Gear.STARTING_WAND.gearQuantity++;	
	}
	public static void increaseStartingChainmailQuantity() {
		Gear.STARTING_CHAINMAIL.gearQuantity++;	
	}
	public static void increaseStartingVestQuantity() {
		Gear.STARTING_VEST.gearQuantity++;
	}
	public static void increaseStartingRobeQuantity() {
		Gear.STARTING_ROBE.gearQuantity++;	
	}
	public static void increaseMiddleSwordQuantity() {
		Gear.MIDDLE_SWORD.gearQuantity++;	
	}
	public static void increaseMiddleDaggerQuantity() {
		Gear.MIDDLE_DAGGER.gearQuantity++;	
	}
	public static void increaseMiddleWandQuantity() {
		Gear.MIDDLE_WAND.gearQuantity++;
	}
	public static void increaseMiddleChainmailQuantity() {
		Gear.MIDDLE_CHAINMAIL.gearQuantity++;
	}
	public static void increaseMiddleVestQuantity() {
		Gear.MIDDLE_VEST.gearQuantity++;	
	}
	public static void increaseMiddleRobeQuantity() {
		Gear.MIDDLE_ROBE.gearQuantity++;
	}
	public static void increaseFinalSwordQuantity() {
		Gear.FINAL_SWORD.gearQuantity++;
	}
	public static void increaseFinalDaggerQuantity() {
		Gear.FINAL_DAGGER.gearQuantity++;	
	}
	public static void increaseFinalWandQuantity() {
		Gear.FINAL_WAND.gearQuantity++;
	}
	public static void increaseFinalChainmailQuantity() {
		Gear.FINAL_CHAINMAIL.gearQuantity++;
	}
	public static void increaseFinalVestQuantity() {
		Gear.FINAL_VEST.gearQuantity++;
	}
	public static void increaseFinalRobeQuantity() {
		Gear.FINAL_ROBE.gearQuantity++;
	}
			
	// methods to equip gear and apply stats to the player
	public static void equipKidsFlute() {
				
	}
	public static void equipStartingSword() {
						
	}
	public static void equipStartingDagger() {
						
	}
	public static void equipStartingWand() {
						
	}
	public static void equipStartingChainmail() {
						
	}
	public static void equipStartingVest() {
						
	}
	public static void equipStartingRobe() {
						
	}
	public static void equipMiddleSword() {
						
	}
	public static void equipMiddleDagger() {
						
	}
	public static void equipMiddleWand() {
						
	}
	public static void equipMiddleChainmail() {
						
	}
	public static void equipMiddleVest() {
						
	}
	public static void equipMiddleRobe() {
						
	}
	public static void equipFinalSword() {
						
	}
	public static void equipFinalDagger() {
						
	}
	public static void equipFinalWand() {
						
	}
	public static void equipFinalChainmail() {
						
	}
	public static void equipFinalVest() {
						
	}
	public static void equipFinalRobe() {
						
	}	
			
	// methods to unequip gear and apply stats to the player
	public static void unequipKidsFlute() {
				
	}
	public static void unequipStartingSword() {
								
	}
	public static void unequipStartingDagger() {
								
	}
	public static void unequipStartingWand() {
								
	}
	public static void unequipStartingChainmail() {
								
	}
	public static void unequipStartingVest() {
								
	}
	public static void unequipStartingRobe() {
								
	}
	public static void unequipMiddleSword() {
								
	}
	public static void unequipMiddleDagger() {
								
	}
	public static void unequipMiddleWand() {
								
	}
	public static void unequipMiddleChainmail() {
								
	}
	public static void unequipMiddleVest() {
								
	}
	public static void unequipMiddleRobe() {
								
	}
	public static void unequipFinalSword() {
								
	}
	public static void unequipFinalDagger() {
								
	}
	public static void unequipFinalWand() {
								
	}
	public static void unequipFinalChainmail() {
								
	}
	public static void unequipFinalVest() {
								
	}
	public static void unequipFinalRobe() {
								
	}
	
	// method to print the gear a player currently owns
	public static void printPlayerGear() {
		for (Gear gear: Gear.values()) {
			if (gear.getGearQuantity() > 0) 
				System.out.println(gear.getGearName() + ": " + gear.getGearDescription() + " (" + gear.getGearQuantity() + ")");
		}
	}
}
