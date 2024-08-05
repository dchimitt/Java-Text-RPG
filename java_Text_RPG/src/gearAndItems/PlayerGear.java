package gearAndItems;

import gearAndItems.PlayerItems.Items;

public class PlayerGear {
	public static enum Gear {
		KIDS_FLUTE("Kid's Flute", "This is the last heirloom you have to remind you of your son.", 0, 0, 0),
		STARTING_SWORD("Starting Sword", "Placeholder", 0, 0, 0),
		STARTING_DAGGER("Starting Dagger", "Placeholder", 0, 0, 0),
		STARTING_WAND("Starting Wand", "Placeholder", 0, 0, 0),
		STARTING_CHAINMAIL("Starting Chainmail", "Placeholder", 0, 0, 0),
		STARTING_VEST("Starting Vest", "Placeholder", 0, 0, 0),
		STARTING_ROBE("Starting Robe", "Placeholder", 0, 0, 0),
		MIDDLE_SWORD("Middle Sword", "Placeholder", 0, 0, 0),
		MIDDLE_DAGGER("Middle Dagger", "Placeholder", 0, 0, 0),
		MIDDLE_WAND("Middle Wand", "Placeholder", 0, 0, 0),
		MIDDLE_CHAINMAIL("Middle Chainmail", "Placeholder", 0, 0, 0),
		MIDDLE_VEST("Middle Vest", "Placeholder", 0, 0, 0),
		MIDDLE_ROBE("Middle Robe", "Placeholder", 0, 0, 0),
		FINAL_SWORD("Final Sword", "Placeholder", 0, 0, 0),
		FINAL_DAGGER("Final Dagger", "Placeholder", 0, 0, 0),
		FINAL_WAND("Final Wand", "Placeholder", 0, 0, 0),
		FINAL_CHAINMAIL("Final Chainmail", "Placeholder", 0, 0, 0),
		FINAL_VEST("Final Vest", "Placeholder", 0, 0, 0),
		FINAL_ROBE("Final Robe", "Placeholder", 0, 0, 0);
	
		// instance variables for each ability
		private final String gearName;
		private final String gearDescription;
		private final int gearStatRequirement;
		private final int gearStatIncrease;
		private int gearQuantity;
		
		// Constructor
		Gear(String gearName, String gearDescription, int gearStatRequirement, int gearStatIncrease, int gearQuantity) {
			this.gearName = gearName;
			this.gearDescription = gearDescription;
			this.gearStatRequirement = gearStatRequirement;
			this.gearStatIncrease = gearStatIncrease;
			this.gearQuantity = gearQuantity;
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
		
		// methods to increase item quantity when player makes a purchase or obtains drop from monsters/bosses
		public void increaseStartingSwordQuantity() {
			
		}
		public void increaseStartingDaggerQuantity() {
			
		}
		public void increaseStartingWandQuantity() {
			
		}
		public void increaseStartingChainmailQuantity() {
			
		}
		public void increaseStartingVestQuantity() {
			
		}
		public void increaseStartingRobeQuantity() {
			
		}
		public void increaseMiddleSwordQuantity() {
			
		}
		public void increaseMiddleDaggerQuantity() {
			
		}
		public void increaseMiddleWandQuantity() {
			
		}
		public void increaseMiddleChainmailQuantity() {
			
		}
		public void increaseMiddleVestQuantity() {
			
		}
		public void increaseMiddleRobeQuantity() {
			
		}
		public void increaseFinalSwordQuantity() {
			
		}
		public void increaseFinalDaggerQuantity() {
			
		}
		public void increaseFinalWandQuantity() {
			
		}
		public void increaseFinalChainmailQuantity() {
			
		}
		public void increaseFinalVestQuantity() {
			
		}
		public void increaseFinalRobeQuantity() {
			
		}
		
		// method to print the items a player currently owns
		public void printPlayerGear() {
			for (Gear gear: Gear.values()) {
				if (gear.getGearQuantity() > 0) 
					System.out.println(gear.getGearName() + ": " + gear.getGearQuantity());
			}
		}
		
		// methods to equip gear and apply stats to the player
		public void equipKidsFlute() {
			
		}
		public void equipStartingSword() {
					
		}
		public void equipStartingDagger() {
					
		}
		public void equipStartingWand() {
					
		}
		public void equipStartingChainmail() {
					
		}
		public void equipStartingVest() {
					
		}
		public void equipStartingRobe() {
					
		}
		public void equipMiddleSword() {
					
		}
		public void equipMiddleDagger() {
					
		}
		public void equipMiddleWand() {
					
		}
		public void equipMiddleChainmail() {
					
		}
		public void equipMiddleVest() {
					
		}
		public void equipMiddleRobe() {
					
		}
		public void equipFinalSword() {
					
		}
		public void equipFinalDagger() {
					
		}
		public void equipFinalWand() {
					
		}
		public void equipFinalChainmail() {
					
		}
		public void equipFinalVest() {
					
		}
		public void equipFinalRobe() {
					
		}	
		
		// methods to unequip gear and apply stats to the player
		public void unequipKidsFlute() {
			
		}
		public void unequipStartingSword() {
							
		}
		public void unequipStartingDagger() {
							
		}
		public void unequipStartingWand() {
							
		}
		public void unequipStartingChainmail() {
							
		}
		public void unequipStartingVest() {
							
		}
		public void unequipStartingRobe() {
							
		}
		public void unequipMiddleSword() {
							
		}
		public void unequipMiddleDagger() {
							
		}
		public void unequipMiddleWand() {
							
		}
		public void unequipMiddleChainmail() {
							
		}
		public void unequipMiddleVest() {
							
		}
		public void unequipMiddleRobe() {
							
		}
		public void unequipFinalSword() {
							
		}
		public void unequipFinalDagger() {
							
		}
		public void unequipFinalWand() {
							
		}
		public void unequipFinalChainmail() {
							
		}
		public void unequipFinalVest() {
							
		}
		public void unequipFinalRobe() {
							
		}
	}
}
