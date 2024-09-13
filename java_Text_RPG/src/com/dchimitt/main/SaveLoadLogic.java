package com.dchimitt.main;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;

import gearAndItems.PlayerGear;
import gearAndItems.PlayerGear.Gear;
import gearAndItems.PlayerItems;
import maps.ActOneMap;

public class SaveLoadLogic {
	// NOTE: saving and loading of objects MUST be done in exact same order for serialization to work!
	public static void saveGame() {
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("savegame.ser"))) {
			// Save Player
			out.writeObject(AdventureGame.player);
		        
			// Save player's currently equipped gear
			out.writeObject(PlayerGear.getEquippedWeapon());
			out.writeObject(PlayerGear.getEquippedChestArmor());
			out.writeObject(PlayerGear.isWeaponEquipped);
			out.writeObject(PlayerGear.isChestArmorEquipped);

			// Save item quantities
			out.writeObject(PlayerItems.getItemQuantities()); 
		        
			// Save gear quantities
			out.writeObject(PlayerGear.getGearQuantities());

			// Save ActOneMap
			out.writeObject(GameLogic.mapOne); // Save ActOneMap

			// Save other game states
			out.writeObject(GameLogic.lastTownVisited);
			out.writeObject(GameLogic.diedInBossFight);
			out.writeObject(GameLogic.actOneThraxxDefeated);
			out.writeObject(GameLogic.actOneSecondBossDefeated);
			out.writeObject(GameLogic.actOneFinalBossDefeated);
			out.writeObject(GameLogic.actTwoFirstBossDefeated);
			out.writeObject(GameLogic.actTwoSecondBossDefeated);
			out.writeObject(GameLogic.actTwoFinalBossDefeated);
			out.writeObject(GameLogic.actThreeFirstBossDefeated);
			out.writeObject(GameLogic.actThreeSecondBossDefeated);
			out.writeObject(GameLogic.finalBossOfGameDefeated);
			out.writeObject(GameLogic.isRunning);

			// Save player position
			out.writeInt(ActOneMap.getCurrentPlayerPositionIndex());

			// Save learned abilities and spells
			out.writeInt(AdventureGame.player.getLastLearnedAbilityIndex());
			out.writeInt(AdventureGame.player.getLastLearnedOffMagIndex());
			out.writeInt(AdventureGame.player.getLastLearnedSuppMagIndex());

			// Save learned status of abilities
			for (Player.Abilities ability : Player.Abilities.values()) {
				out.writeBoolean(AdventureGame.player.isAbilityLearned(ability)); 
			}

			// Save learned status of offensive spells
			for (Player.OffMagSpells offSpell : Player.OffMagSpells.values()) {
				out.writeBoolean(AdventureGame.player.isOffensiveSpellLearned(offSpell)); 
			}

			// Save learned status of support spells
			for (Player.SuppMagSpells suppSpell : Player.SuppMagSpells.values()) {
				out.writeBoolean(AdventureGame.player.isSupportSpellLearned(suppSpell)); 
			}

		    } catch (IOException e) {
		    	e.printStackTrace();
		    	System.out.println("Failed to save game.");
		    }
			GameLogic.clearConsole();
			System.out.println("Game saved successfully!");
			GameLogic.typeToContinue();
		}

		
		public static void loadGame() {
		    try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("savegame.ser"))) {
		        // Load Player
		    	AdventureGame.player = (Player) in.readObject();

		        // Load player's gear
		        Gear equippedWeapon = (Gear) in.readObject();
		        Gear equippedChestArmor = (Gear) in.readObject();
		        Player.playerGear.setEquippedWeapon(equippedWeapon);
		        Player.playerGear.setEquippedChestArmor(equippedChestArmor);
		        
		        // Load equipment boolean statuses
		        boolean isWeaponEquipped = (boolean) in.readObject();
		        boolean isChestArmorEquipped = (boolean) in.readObject();
		        Player.playerGear.setWeaponEquippedStatus(isWeaponEquipped);
		        Player.playerGear.setChestArmorEquippedStatus(isChestArmorEquipped);
		        
		        // Load item quantities
		        Object itemQuantitiesObject = in.readObject();
		        Map<PlayerItems.Items, Integer> itemQuantities = (Map<PlayerItems.Items, Integer>) itemQuantitiesObject;
		        PlayerItems.setItemQuantities(itemQuantities);
		        
		        // Load gear quantities
		        Map<PlayerGear.Gear, Integer> gearQuantities = (Map<PlayerGear.Gear, Integer>) in.readObject();
		        PlayerGear.setGearQuantities(gearQuantities);

		        // Load ActOneMap
		        Object mapObject = in.readObject();
		        GameLogic.mapOne = (ActOneMap) mapObject;

		        // Load other game states
		        GameLogic.lastTownVisited = (Room) in.readObject();
		        GameLogic.diedInBossFight = (boolean) in.readObject();
		        GameLogic.actOneThraxxDefeated = (boolean) in.readObject();
		        GameLogic.actOneSecondBossDefeated = (boolean) in.readObject();
		        GameLogic.actOneFinalBossDefeated = (boolean) in.readObject();
		        GameLogic.actTwoFirstBossDefeated = (boolean) in.readObject();
		        GameLogic.actTwoSecondBossDefeated = (boolean) in.readObject();
		        GameLogic.actTwoFinalBossDefeated = (boolean) in.readObject();
		        GameLogic.actThreeFirstBossDefeated = (boolean) in.readObject();
		        GameLogic.actThreeSecondBossDefeated = (boolean) in.readObject();
		        GameLogic.finalBossOfGameDefeated = (boolean) in.readObject();
		        GameLogic.isRunning = (boolean) in.readObject();

		        // Load player position
		        int loadedPositionIndex = in.readInt();
		        ActOneMap.setCurrentPlayerPositionIndex(loadedPositionIndex);

		        // Load learned abilities and spells
		        AdventureGame.player.setLastLearnedAbilityIndex(in.readInt());
		        AdventureGame.player.setLastLearnedOffMagIndex(in.readInt());
		        AdventureGame.player.setLastLearnedSuppMagIndex(in.readInt());
		        
		        // Load learned abilities
		        for (Player.Abilities ability : Player.Abilities.values()) {
		            boolean learned = in.readBoolean();
		            AdventureGame.player.setAbilityLearned(ability, learned);
		        }

		        // Load learned offensive spells
		        for (Player.OffMagSpells offSpell : Player.OffMagSpells.values()) {
		            boolean learned = in.readBoolean();
		            AdventureGame.player.setOffensiveSpellLearned(offSpell, learned);
		        }

		        // Load learned support spells
		        for (Player.SuppMagSpells suppSpell : Player.SuppMagSpells.values()) {
		            boolean learned = in.readBoolean();
		            AdventureGame.player.setSupportSpellLearned(suppSpell, learned);
		        }
		    } catch (IOException | ClassNotFoundException e) {
		        e.printStackTrace();
		        System.out.println("Failed to load game.");
		    }
		    GameLogic.clearConsole();
		    System.out.println("Game loaded successfully!");
		    GameLogic.typeToContinue();
		}	
}
