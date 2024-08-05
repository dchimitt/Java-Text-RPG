package gearAndItems;

import java.io.Serializable;

public class ItemVendor implements Serializable {
	private String vendorName;
	private PlayerItems.Items[] itemsForSale;
	
	public ItemVendor(String vendorName, PlayerItems.Items[] itemsForSale) {
		this.vendorName = vendorName;
		this.itemsForSale = itemsForSale;
	}
	
	public void displayItems() {
		System.out.println("Welcome to " + vendorName + "! Here are the items currently for sale:");
		int x = 1; // to add options in front of item
		for (PlayerItems.Items item : itemsForSale) {
			// TODO: add item cost
			System.out.println("(" + x + ") " + item.getItemName() + ": " + item.getItemDescription());
			x++;
		}
	}
	
	public void purchaseItem(PlayerItems.Items item) {
		// item.increaseItemQuantity();
		System.out.println("You have purchased " + item.getItemName() + ".");
	}
}
