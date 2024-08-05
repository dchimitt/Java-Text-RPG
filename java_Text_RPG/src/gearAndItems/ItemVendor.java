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
		for (PlayerItems.Items item : itemsForSale) {
			// TODO: add item cost
			System.out.println(item.getItemName() + ": " + item.getItemDescription());
		}
	}
	
	public void purchaseItem(PlayerItems.Items item) {
		// item.increaseItemQuantity();
		System.out.println("You have purchased " + item.getItemName() + ".");
	}
}
