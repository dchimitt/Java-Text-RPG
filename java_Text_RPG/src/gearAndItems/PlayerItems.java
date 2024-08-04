package gearAndItems;

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
			
		}
		public void increaseHealingPotionQuantity() {
			
		}
		public void increaseInfusedHealingPotionQuantity() {
			
		}
		public void increaseWeakManaPotionQuantity() {
			
		}
		public void increaseManaPotionQuantity() {
			
		}
		public void increaseInfusedManaPotionQuantity() {
			
		}
		public void increaseAntidoteQuantity() {
			
		}
		public void increaseReviveQuantity() {
			
		}
		
		// methods to use healing potions
		public void useWeakHealingPotion() {
			
		}
		public void useHealingPotion() {
			
		}
		public void useInfusedHealingPotion() {
			
		}
		
		// methods to use mana potions
		public void useWeakManaPotion() {
			
		}
		public void useManaPotion() {
			
		}
		public void useInfusedManaPotion() {
			
		}		
		
		// methods to use other items
		public void useAntidote() {
			
		}
		public void useReapersBane() {
			
		}
	}
}
