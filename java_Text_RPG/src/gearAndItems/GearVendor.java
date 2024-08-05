package gearAndItems;

import java.io.Serializable;

public class GearVendor implements Serializable {
	private String vendorName;
	private PlayerGear.Gear[] gearForSale;
	
	public GearVendor(String vendorName, PlayerGear.Gear[] gearForSale) {
		this.vendorName = vendorName;
		this.gearForSale = gearForSale;
	}
	
	public void displayGear() {
		System.out.println("Welcome to " + vendorName + "! Here is the gear currently for sale:");
		int x = 1; // to add options in front of gear
		for (PlayerGear.Gear gear : gearForSale) {
			// TODO: add item cost
			System.out.println("(" + x + ") " + gear.getGearName() + ": " + gear.getGearDescription());
			x++;
		}
	}
	
	public void purchaseGear(PlayerGear.Gear gear) {
		// gear.increaseGeartQuantity();
		System.out.println("You have purchased " + gear.getGearName() + ".");
	}
}
