package gearAndItems;

import java.io.Serializable;

import com.dchimitt.main.AdventureGame;
import com.dchimitt.main.GameLogic;

public class ItemVendor implements Serializable {
	private String vendorName;
	private PlayerItems.Items[] itemsForSale;
	
	public ItemVendor(String vendorName, PlayerItems.Items[] itemsForSale) {
		this.vendorName = vendorName;
		this.itemsForSale = itemsForSale;
	}
	
	public void displayItems() {
		System.out.println("Welcome to " + vendorName + "! Here are the items currently for sale");
		int x = 1; // to add options in front of item
		for (PlayerItems.Items item : itemsForSale) {
			// TODO: add item cost
			System.out.println("(" + x + ") " + item.getItemName() + ": " + item.getItemDescription() + " (cost = " + item.getItemCostInGold() + " gold)");
			x++;
		}
		System.out.println();
		System.out.println("(" + x + ") Go back to town menu without making a purchase");
		GameLogic.printTildeSeparator(60);
		System.out.println("Current gold: " + AdventureGame.getPlayer().gold);
	}
	
	public static void purchaseItem(PlayerItems.Items item) {
	    // Check if the player has enough gold
	    if (AdventureGame.getPlayer().gold >= item.getItemCostInGold()) {
	        // Deduct the item cost from the player's gold
	        AdventureGame.getPlayer().gold -= item.getItemCostInGold();
	        
	        // Increase the item quantity in the player's inventory
	        PlayerItems.increaseItemQuantity(item);
	        
	        // Print purchase confirmation and current gold
	        System.out.println("You have purchased " + item.getItemName() + ".");
	        System.out.println("Current gold: " + AdventureGame.getPlayer().gold);
	    } 
	    else 
	        System.out.println("I'm sorry, you don't have enough gold to purchase that!");	    
	}
}
