package gearAndItems;

import java.io.Serializable;

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
		System.out.println("Current gold: " + GameLogic.player.gold);
	}
	
	public static void purchaseItem(PlayerItems.Items item) {
		// item.increaseItemQuantity();
		if (GameLogic.player.gold >= item.getItemCostInGold()) {
			System.out.println("You have purchased " + item.getItemName() + ".");
			GameLogic.player.gold = GameLogic.player.gold - item.getItemCostInGold();
			System.out.println("Current gold: " + GameLogic.player.gold);
			if (item.getItemName().equals("Weak Healing Potion"))
				PlayerItems.increaseWeakHealingPotionQuantity();
			else if (item.getItemName().equals("Healing Potion"))
				PlayerItems.increaseHealingPotionQuantity();
			else if (item.getItemName().equals("Infused Healing Potion"))
				PlayerItems.increaseInfusedHealingPotionQuantity();
			else if (item.getItemName().equals("Weak Mana Potion"))
				PlayerItems.increaseWeakManaPotionQuantity();
			else if (item.getItemName().equals("Mana Potion"))
				PlayerItems.increaseManaPotionQuantity();
			else if (item.getItemName().equals("Infused Mana Potion"))
				PlayerItems.increaseInfusedManaPotionQuantity();
			else if (item.getItemName().equals("Antidote"))
				PlayerItems.increaseAntidoteQuantity();
			else if (item.getItemName().equals("Reaper's Bane"))
				PlayerItems.increaseReapersBaneQuantity();	
		}
		else {
			System.out.println("I'm sorry, you don't have enough gold to purchase that!");
		}
	}
}
