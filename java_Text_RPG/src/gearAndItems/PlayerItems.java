package gearAndItems;

import com.dchimitt.main.AdventureGame;
import com.dchimitt.main.GameLogic;

import gearAndItems.PlayerGear.Gear;

import java.util.Map;
import java.util.EnumMap;
import java.util.Scanner;
import java.io.Serializable;

public class PlayerItems implements Serializable {	
	static Scanner in = new Scanner(System.in);
	
	// map to store item quantities
	private static final Map<Items, Integer> itemQuantities = new EnumMap<>(Items.class);
	
	// initializing quantity of 0 for each item in the map
	static {
		for (Items item : Items.values()) {
			itemQuantities.put(item,  0);
		}
	}
	
	public static enum Items {
		WEAK_HEALING_POTION("Weak Healing Potion", "Restores 10 hitpoints", 15, 15/4),
		HEALING_POTION("Healing Potion", "Restores 25 hitpoints", 50, 50/4),
		INFUSED_HEALING_POTION("Infused Healing Potion", "Restores 50 hitpoints", 150, 150/4),
		WEAK_MANA_POTION("Weak Mana Potion", "Restores 5 mana", 15, 15/4),
		MANA_POTION("Mana Potion", "Restores 15 mana", 50, 50/4),
		INFUSED_MANA_POTION("Infused Mana Potion", "Restores 30 mana", 150, 150/4),
		ANTIDOTE("Antidote", "Cures the poison status", 50, 50/4),
		REAPERS_BANE("Reaper's Bane", "Prevent death for __ turns", 99999, 99999/4);
	
		// instance variables for each ability
		private final String itemName;
		private final String itemDescription;
		private int itemCostInGold;
		private int itemSellingPrice; // 25% of purchase price
		
		// Constructor
		Items(String itemName, String itemDescription, int itemCostInGold, int itemSellingPrice) {
			this.itemName = itemName;
			this.itemDescription = itemDescription;
			this.itemCostInGold = itemCostInGold;
			this.itemSellingPrice = itemSellingPrice;
		}
		
		// getter methods for item enum
		public String getItemName() {
			return itemName;
		}		
		public String getItemDescription() {
			return itemDescription;
		}
		public int getItemCostInGold() {
			return itemCostInGold;
		}
		public int getItemSellingPrice() {
			return itemSellingPrice;
		}
	}
	
	// method to return copy of item quantity map for purposes of saving/loading
	public static Map<Items, Integer> getItemQuantities() {
	    return new EnumMap<>(itemQuantities);
	}
	
	// method to set the item quantities from a provided map
	public static void setItemQuantities(Map<Items, Integer> newQuantities) {
		itemQuantities.clear();
		itemQuantities.putAll(newQuantities);
	}
	
	// method to increase a particular item quantity based on item map
	public static void increaseItemQuantity(Items item) {
		itemQuantities.put(item, itemQuantities.getOrDefault(item, 0) + 1);
	}
			
	// method to use a particular item using the item map
	public static void useItem() {
	    boolean keepUsingItems = true;
	    
	    // Ask the player if they'd like to use a consumable item
        System.out.println("Would you like to use a consumable item? (Y/N)");
        String itemDecision = in.nextLine().trim().toUpperCase();

	    while (keepUsingItems) {
	        if (itemDecision.equals("Y")) {
	            // show player their consumable items
	            GameLogic.clearConsole();
	            printPlayerItems();
	            System.out.println();
	            System.out.println("Please type the name of the item you'd like to use.\nNOTE: must type names exactly as written with spaces included");

	            String itemToUse = in.nextLine().trim().toUpperCase();
	            GameLogic.clearConsole();

	            // Convert item name to Items enum
	            PlayerItems.Items item = null;
	            for (PlayerItems.Items i : PlayerItems.Items.values()) {
	                if (i.getItemName().toUpperCase().equals(itemToUse)) {
	                    item = i;
	                    break;
	                }
	            }

	            if (item != null) {
	                // check if player has the item they want to use
	                int currentQuantity = itemQuantities.getOrDefault(item, 0);
	                if (currentQuantity > 0) {
	                    // use item
	                	// TODO: prevent player from overcapping hp/mp, change outputs to reflect actual amounts gained
	                    switch (item) {
	                        case WEAK_HEALING_POTION:
	                            AdventureGame.getPlayer().currentHp += 10;
	                            System.out.println("Hitpoints increased by 10!");  
	                            break;
	                        case HEALING_POTION:
	                            AdventureGame.getPlayer().currentHp += 25;
	                            System.out.println("Hitpoints increased by 25!");  
	                            break;
	                        case INFUSED_HEALING_POTION:
	                            AdventureGame.getPlayer().currentHp += 50;
	                            System.out.println("Hitpoints increased by 50!");  
	                            break;
	                        case WEAK_MANA_POTION:
	                            AdventureGame.getPlayer().currentMana += 5;
	                            System.out.println("Mana increased by 10!");  
	                            break;
	                        case MANA_POTION:
	                            AdventureGame.getPlayer().currentMana += 15;
	                            System.out.println("Mana increased by 15!"); 
	                            break;
	                        case INFUSED_MANA_POTION:
	                            AdventureGame.getPlayer().currentMana += 30;
	                            System.out.println("Mana increased by 30!"); 
	                            break;
	                        case ANTIDOTE:
	                            // Add use logic for ANTIDOTE here
	                            System.out.println("Used Antidote.");
	                            break;
	                        case REAPERS_BANE:
	                            // Add use logic for REAPERS_BANE here
	                            System.out.println("Used Reaper's Bane.");
	                            break;
	                    }
	                    System.out.println();
	                    
	                    // print player hp/mana
	                    // TODO: when adding items in combat will have to move this
	                    System.out.println("Current HP: " + AdventureGame.getPlayer().currentHp + "/" + AdventureGame.getPlayer().maximumHp);
                        System.out.println("Current MP: " + AdventureGame.getPlayer().currentMana + "/" + AdventureGame.getPlayer().maximumMana);

	                    // update item quantity
	                    itemQuantities.put(item, currentQuantity - 1);
	                    System.out.println();

	                    // ask player if they'd like to use another item
	                    // TODO when in combat, player may not use more than one item in a row
	                    System.out.println("Would you like to use another item? (Y/N)");
	                    String decision = in.nextLine().trim().toUpperCase();
	                    if (!decision.equals("Y")) {
	                        keepUsingItems = false;
	                        GameLogic.clearConsole();
	                    }
	                }
	                else 
	                    System.out.println("You do not have any of that item left.");
	        } 
	        else 
	        	System.out.println("Item was not found in your inventory. Moving back to the map.");
	            GameLogic.typeToContinue();
	            break;
	        }
	        else {
	        	keepUsingItems = false;
	        	GameLogic.clearConsole();
	        }
	
	    }
	}
	
	// method to print a list of the players items
	public static void printPlayerItems() {
		System.out.println("\\\\Consumable items\\\\\n");
		for (Map.Entry<Items, Integer> entry : itemQuantities.entrySet()) {
			Items item = entry.getKey();
			int quantity = entry.getValue();
			if (quantity > 0) 
				System.out.println(item.getItemName() + ": " + item.getItemDescription() + " (" + quantity + ")");
		}
	}
}
