package gearAndItems;

import com.dchimitt.main.AdventureGame;
import java.util.Map;
import java.util.EnumMap;
import java.io.Serializable;

public class PlayerItems implements Serializable {	
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
			
	// methods to use a particular item using the item map
	public static void useItem(Items item) {
        int currentQuantity = itemQuantities.getOrDefault(item, 0);
        if (currentQuantity > 0) {
            // item use logic for each item here
            if (item == Items.WEAK_HEALING_POTION) {
                AdventureGame.getPlayer().currentHp += 10;
                System.out.println("Current hitpoints increased by 10!\n" + AdventureGame.player.currentHp + "/" + AdventureGame.player.maximumHp);
            }
            else if (item == Items.HEALING_POTION) {
                AdventureGame.getPlayer().currentHp += 25;
                System.out.println("Current hitpoints increased by 25!\n" + AdventureGame.player.currentHp + "/" + AdventureGame.player.maximumHp);
            }
            else if (item == Items.INFUSED_HEALING_POTION) {
                AdventureGame.getPlayer().currentHp += 50;
                System.out.println("Current hitpoints increased by 50!\n" + AdventureGame.player.currentHp + "/" + AdventureGame.player.maximumHp);
            }
            else if (item == Items.WEAK_MANA_POTION) {
                AdventureGame.getPlayer().currentMana += 5;
                System.out.println("Current mana increased by 5!\n" + AdventureGame.player.currentMana + "/" + AdventureGame.player.maximumMana);
            }
            else if (item == Items.MANA_POTION) {
                AdventureGame.getPlayer().currentMana += 15;
                System.out.println("Current mana increased by 15!\n" + AdventureGame.player.currentMana + "/" + AdventureGame.player.maximumMana);
            }
            else if (item == Items.INFUSED_MANA_POTION) {
                AdventureGame.getPlayer().currentMana += 30;
                System.out.println("Current mana increased by 30!\n" + AdventureGame.player.currentMana + "/" + AdventureGame.player.maximumMana);
            }
            else if (item == Items.ANTIDOTE) { 
                // add use logic later
            }
            else if (item == Items.REAPERS_BANE) {
                // add use logic later
            }
            itemQuantities.put(item, currentQuantity - 1);
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
