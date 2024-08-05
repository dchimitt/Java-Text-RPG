package gearAndItems;

import com.dchimitt.main.GameLogic;

public class PlayerItems {
	public static enum Items {
		WEAK_HEALING_POTION("Weak Healing Potion", "Restores 10 hitpoints", 0, 15),
		HEALING_POTION("Healing Potion", "Restores 25 hitpoints", 0, 50),
		INFUSED_HEALING_POTION("Infused Healing Potion", "Restores 50 hitpoints", 0, 150),
		WEAK_MANA_POTION("Weak Mana Potion", "Restores 5 mana", 0, 15),
		MANA_POTION("Mana Potion", "Restores 15 mana", 0, 50),
		INFUSED_MANA_POTION("Infused Mana Potion", "Restores 30 mana", 0, 150),
		ANTIDOTE("Antidote", "Cures the poison status", 0, 50),
		REAPERS_BANE("Reaper's Bane", "Prevent death for __ turns", 0, 99999);
	
		// instance variables for each ability
		private final String itemName;
		private final String itemDescription;
		private int itemQuantity;
		private int itemCostInGold;
		
		// Constructor
		Items(String itemName, String itemDescription, int itemQuantity, int itemCostInGold) {
			this.itemName = itemName;
			this.itemDescription = itemDescription;
			this.itemQuantity = itemQuantity;
			this.itemCostInGold = itemCostInGold;
		}
		
		// getter methods
		public String getItemName() {
			return itemName;
		}		
		public String getItemDescription() {
			return itemDescription;
		}
		public int getItemQuantity() {
			return itemQuantity;
		}
		public int getItemCostInGold() {
			return itemCostInGold;
		}
	}
	
	// methods to increase item quantity when player makes a purchase or obtains as drop from monster/boss
	public static void increaseWeakHealingPotionQuantity() {
		Items.WEAK_HEALING_POTION.itemQuantity++;
	}
	public static void increaseHealingPotionQuantity() {
		Items.HEALING_POTION.itemQuantity++;
	}
	public static void increaseInfusedHealingPotionQuantity() {
		Items.INFUSED_HEALING_POTION.itemQuantity++;
	}
	public static void increaseWeakManaPotionQuantity() {
		Items.WEAK_MANA_POTION.itemQuantity++;
	}
	public static void increaseManaPotionQuantity() {
		Items.MANA_POTION.itemQuantity++;
	}
	public static void increaseInfusedManaPotionQuantity() {
		Items.INFUSED_HEALING_POTION.itemQuantity++;
	}
	public static void increaseAntidoteQuantity() {
		Items.ANTIDOTE.itemQuantity++;
	}
	public static void increaseReapersBaneQuantity() {
		Items.REAPERS_BANE.itemQuantity++;
	}
			
	// methods to use healing potions
	public static void useWeakHealingPotion() {
		GameLogic.player.currentHp = GameLogic.player.currentHp + 10;
		Items.WEAK_HEALING_POTION.itemQuantity--;
	}
	public static void useHealingPotion() {
		GameLogic.player.currentHp = GameLogic.player.currentHp + 25;
		Items.HEALING_POTION.itemQuantity--;
	}
	public static void useInfusedHealingPotion() {
		GameLogic.player.currentHp = GameLogic.player.currentHp + 50;
		Items.INFUSED_HEALING_POTION.itemQuantity--;
	}
			
	// methods to use mana potions
	public static void useWeakManaPotion() {
		GameLogic.player.currentMana = GameLogic.player.currentMana + 5;
		Items.WEAK_MANA_POTION.itemQuantity--;
	}
	public static void useManaPotion() {
		GameLogic.player.currentMana = GameLogic.player.currentMana + 15;
		Items.MANA_POTION.itemQuantity--;
	}
	public static void useInfusedManaPotion() {
		GameLogic.player.currentMana = GameLogic.player.currentMana + 30;
		Items.INFUSED_MANA_POTION.itemQuantity--;
	}		
			
	// methods to use other items
	public static void useAntidote() {
		Items.ANTIDOTE.itemQuantity--;
	}
	public static void useReapersBane() {
		Items.REAPERS_BANE.itemQuantity--;
	}
	
	// method to print a list of the players items (must be placed outside of enum scope to work...)
	public static void printPlayerItems() {
		for (Items item: Items.values()) {
			if (item.getItemQuantity() > 0) 
				System.out.println(item.getItemName() + ": " + item.getItemDescription() +  " (" + item.getItemQuantity() + ")");
		}
	}
}
