package gearAndItems;

import java.io.Serializable;

import com.dchimitt.main.GameLogic;

public class GearVendor implements Serializable {
	private String vendorName;
	private PlayerGear.Gear[] gearForSale;
	
	public GearVendor(String vendorName, PlayerGear.Gear[] gearForSale) {
		this.vendorName = vendorName;
		this.gearForSale = gearForSale;
	}
	
	public void displayGear() {
		System.out.println("Welcome to " + vendorName + "! Here is the gear currently for sale");
		int x = 1; // to add options in front of gear
		for (PlayerGear.Gear gear : gearForSale) {
			// TODO: add item cost
			System.out.println("(" + x + ") " + gear.getGearName() + ": " + gear.getGearDescription() + " (cost = " + gear.getGearCostInGold() + " gold)");
			x++;
		}
		System.out.println();
		System.out.println("(" + x + ") Go back to town menu without making a purchase");
		GameLogic.printTildeSeparator(60);
		System.out.println("Current gold: " + GameLogic.player.gold);
	}
	
	public void purchaseGear(PlayerGear.Gear gear) {
		// gear.increaseGeartQuantity();
		if (GameLogic.player.gold >= gear.getGearCostInGold()) {
			System.out.println("You have purchased " + gear.getGearName() + ".");
			GameLogic.player.gold = GameLogic.player.gold - gear.getGearCostInGold();
			System.out.println("Current gold: " + GameLogic.player.gold);
			if (gear.getGearName().equals("Starting Sword"))
				PlayerGear.increaseStartingSwordQuantity();
			else if (gear.getGearName().equals("Starting Dagger"))
				PlayerGear.increaseStartingDaggerQuantity();
			else if (gear.getGearName().equals("Starting Wand"))
				PlayerGear.increaseStartingWandQuantity();
			else if (gear.getGearName().equals("Starting Chainmail"))
				PlayerGear.increaseStartingChainmailQuantity();
			else if (gear.getGearName().equals("Starting Vest"))
				PlayerGear.increaseStartingVestQuantity();
			else if (gear.getGearName().equals("Starting Robe"))
				PlayerGear.increaseStartingRobeQuantity();
			//
			else if (gear.getGearName().equals("Middle Sword"))
				PlayerGear.increaseMiddleSwordQuantity();
			else if (gear.getGearName().equals("Middle Dagger"))
				PlayerGear.increaseMiddleDaggerQuantity();
			else if (gear.getGearName().equals("Middle Wand"))
				PlayerGear.increaseMiddleWandQuantity();
			else if (gear.getGearName().equals("Middle Chainmail"))
				PlayerGear.increaseMiddleChainmailQuantity();
			else if (gear.getGearName().equals("Middle Vest"))
				PlayerGear.increaseMiddleVestQuantity();
			else if (gear.getGearName().equals("Middle Robe"))
				PlayerGear.increaseMiddleRobeQuantity();
			//
			else if (gear.getGearName().equals("Final Sword"))
				PlayerGear.increaseFinalSwordQuantity();
			else if (gear.getGearName().equals("Final Dagger"))
				PlayerGear.increaseFinalDaggerQuantity();
			else if (gear.getGearName().equals("Final Wand"))
				PlayerGear.increaseFinalWandQuantity();
			else if (gear.getGearName().equals("Final Chainmail"))
				PlayerGear.increaseFinalChainmailQuantity();
			else if (gear.getGearName().equals("Final Vest"))
				PlayerGear.increaseFinalVestQuantity();
			else if (gear.getGearName().equals("Final Robe"))
				PlayerGear.increaseFinalRobeQuantity();		
		}
		else {
			System.out.println("I'm sorry, you don't have enough gold to purchase that!");
		}
	}
}
