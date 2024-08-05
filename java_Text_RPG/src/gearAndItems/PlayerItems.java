package gearAndItems;

import com.dchimitt.main.GameLogic;

public class PlayerItems {
	public static enum Items {
		WEAK_HEALING_POTION("Weak Healing Potion", "Placeholder", 0),
		HEALING_POTION("Healing Potion", "Placeholder", 0),
		INFUSED_HEALING_POTION("Infused Healing Potion", "Placeholder", 0),
		WEAK_MANA_POTION("Weak Mana Potion", "Placeholder", 0),
		MANA_POTION("Mana Potion", "Placeholder", 0),
		INFUSED_MANA_POTION("Infused Mana Potion", "Placeholder", 0),
		ANTIDOTE("Weak Healing Potion", "Placeholder", 0),
		REAPERS_BANE("Reaper's Bane", "Prevent death for __ turns.", 0);
	
		// instance variables for each ability
		private final String itemName;
		private final String itemDescription;
		private int itemQuantity;
		
		// Constructor
		Items(String itemName, String itemDescription, int itemQuantity) {
			this.itemName = itemName;
			this.itemDescription = itemDescription;
			this.itemQuantity = itemQuantity;
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
		
		// methods to increase item quantity when player makes a purchase or obtains as drop from monster/boss
		public void increaseWeakHealingPotionQuantity() {
			Items.WEAK_HEALING_POTION.itemQuantity++;
		}
		public void increaseHealingPotionQuantity() {
			Items.HEALING_POTION.itemQuantity++;
		}
		public void increaseInfusedHealingPotionQuantity() {
			Items.INFUSED_HEALING_POTION.itemQuantity++;
		}
		public void increaseWeakManaPotionQuantity() {
			Items.WEAK_MANA_POTION.itemQuantity++;
		}
		public void increaseManaPotionQuantity() {
			Items.MANA_POTION.itemQuantity++;
		}
		public void increaseInfusedManaPotionQuantity() {
			Items.INFUSED_HEALING_POTION.itemQuantity++;
		}
		public void increaseAntidoteQuantity() {
			Items.ANTIDOTE.itemQuantity++;
		}
		public void increaseReapersBaneQuantity() {
			Items.REAPERS_BANE.itemQuantity++;
		}
		
		// method to print the items a player currently owns
		public void printPlayerItems() {
			for (Items item: Items.values()) {
				if (item.getItemQuantity() > 0) 
					System.out.println(item.getItemName() + ": " + item.getItemQuantity());
			}
		}
		
		// methods to use healing potions
		public void useWeakHealingPotion() {
			GameLogic.player.currentHp = GameLogic.player.currentHp + 10;
			Items.WEAK_HEALING_POTION.itemQuantity--;
		}
		public void useHealingPotion() {
			GameLogic.player.currentHp = GameLogic.player.currentHp + 25;
			Items.HEALING_POTION.itemQuantity--;
		}
		public void useInfusedHealingPotion() {
			GameLogic.player.currentHp = GameLogic.player.currentHp + 50;
			Items.INFUSED_HEALING_POTION.itemQuantity--;
		}
		
		// methods to use mana potions
		public void useWeakManaPotion() {
			GameLogic.player.currentMana = GameLogic.player.currentMana + 5;
			Items.WEAK_MANA_POTION.itemQuantity--;
		}
		public void useManaPotion() {
			GameLogic.player.currentMana = GameLogic.player.currentMana + 15;
			Items.MANA_POTION.itemQuantity--;
		}
		public void useInfusedManaPotion() {
			GameLogic.player.currentMana = GameLogic.player.currentMana + 30;
			Items.INFUSED_MANA_POTION.itemQuantity--;
		}		
		
		// methods to use other items
		public void useAntidote() {
			Items.ANTIDOTE.itemQuantity--;
		}
		public void useReapersBane() {
			Items.REAPERS_BANE.itemQuantity--;
		}
	}
}
