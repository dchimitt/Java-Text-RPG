package gearAndItems;

import java.io.Serializable;

public class GearVendor implements Serializable {
	private String vendorName;
	private PlayerGear.Gear[] gearForSale;
	
	public GearVendor(String vendorName, PlayerGear.Gear[] GearForSale) {
		this.vendorName = vendorName;
		this.gearForSale = gearForSale;
	}
	
	public void displayGear() {
		System.out.println("Welcome to " + vendorName + "! Here is the gear currently for sale:");
		for (PlayerGear.Gear gear : gearForSale) {
			// TODO: add item cost
			System.out.println(gear.getGearName() + ": " + gear.getGearDescription());
		}
	}
	
	public void purchaseItem(PlayerGear.Gear gear) {
		// gear.increaseGeartQuantity();
		System.out.println("You have purchased " + gear.getGearName() + ".");
	}
}
