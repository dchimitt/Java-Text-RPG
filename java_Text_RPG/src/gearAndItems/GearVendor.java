package gearAndItems;

import java.io.Serializable;

import com.dchimitt.main.AdventureGame;
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
		System.out.println("Current gold: " + AdventureGame.getPlayer().gold);
	}
	
	public static void purchaseGear(PlayerGear.Gear gear) {
	    // Check if the player has enough gold
	    if (AdventureGame.getPlayer().gold >= gear.getGearCostInGold()) {
	        // Deduct the item cost from the player's gold
	        AdventureGame.getPlayer().gold -= gear.getGearCostInGold();
	        
	        // Increase the item quantity in the player's inventory
	        PlayerGear.increaseGearQuantity(gear);
	        
	        // Print purchase confirmation and current gold
	        System.out.println("You have purchased " + gear.getGearName() + ".");
	        System.out.println("Current gold: " + AdventureGame.getPlayer().gold);
	    } 
	    else 
	        System.out.println("I'm sorry, you don't have enough gold to purchase that!");	    
	}
}
