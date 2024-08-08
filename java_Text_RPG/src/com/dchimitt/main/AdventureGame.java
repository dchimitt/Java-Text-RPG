package com.dchimitt.main;

import java.io.Serializable;

import dialogue.ActOneDialogue;
import mainStory.MainStory;
import maps.ActOneMap;
import gearAndItems.PlayerItems;
import gearAndItems.PlayerGear;
import com.dchimitt.main.Player;

import java.util.Scanner;
import java.util.Map;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class AdventureGame implements java.io.Serializable {
	
	public static Player player;
	static Scanner in = new Scanner(System.in);
	public static AdventureGame game;
	
	public static Player getPlayer() {
		return player;
	}
	
	public static void setPlayer(Player player) {
		AdventureGame.player = player;
	}
	
	// NOTE: saving and loading of objects MUST be done in exact same order for serialization to work!
	public static void saveGame() {
	    try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("savegame.ser"))) {
	        // Save Player
	        out.writeObject(player);
	        
	        // Save player's currently equipped gear
	        out.writeObject(player.getPlayerGear());

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
	        out.writeInt(player.getLastLearnedAbilityIndex());
	        out.writeInt(player.getLastLearnedOffMagIndex());
	        out.writeInt(player.getLastLearnedSuppMagIndex());

	        // Save learned status of abilities
	        for (Player.Abilities ability : Player.Abilities.values()) {
	            out.writeBoolean(player.isAbilityLearned(ability)); 
	        }

	        // Save learned status of offensive spells
	        for (Player.OffMagSpells offSpell : Player.OffMagSpells.values()) {
	            out.writeBoolean(player.isOffensiveSpellLearned(offSpell)); 
	        }

	        // Save learned status of support spells
	        for (Player.SuppMagSpells suppSpell : Player.SuppMagSpells.values()) {
	            out.writeBoolean(player.isSupportSpellLearned(suppSpell)); 
	        }

	        System.out.println("Game saved successfully!");
	    } catch (IOException e) {
	        e.printStackTrace();
	        System.out.println("Failed to save game.");
	    }
	}

	
	public static void loadGame() {
	    try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("savegame.ser"))) {
	        // Load Player
	        player = (Player) in.readObject();

	        // Load player's gear
	        PlayerGear loadedGear = (PlayerGear) in.readObject();
	        player.setPlayerGear(loadedGear);
	        
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
	        player.setLastLearnedAbilityIndex(in.readInt());
	        player.setLastLearnedOffMagIndex(in.readInt());
	        player.setLastLearnedSuppMagIndex(in.readInt());
	        
	        // Load learned abilities
	        for (Player.Abilities ability : Player.Abilities.values()) {
	            boolean learned = in.readBoolean();
	            player.setAbilityLearned(ability, learned);
	        }

	        // Load learned offensive spells
	        for (Player.OffMagSpells offSpell : Player.OffMagSpells.values()) {
	            boolean learned = in.readBoolean();
	            player.setOffensiveSpellLearned(offSpell, learned);
	        }

	        // Load learned support spells
	        for (Player.SuppMagSpells suppSpell : Player.SuppMagSpells.values()) {
	            boolean learned = in.readBoolean();
	            player.setSupportSpellLearned(suppSpell, learned);
	        }

	        System.out.println("Game loaded successfully!");
	    } catch (IOException | ClassNotFoundException e) {
	        e.printStackTrace();
	        System.out.println("Failed to load game.");
	    }
	}
	
	public static void main(String[] args) {
		// print title
		GameLogic.printHyphenSeparator(40);
		GameLogic.printHyphenSeparator(30);
		System.out.println("TALES OF SORROW");
		System.out.println("TEXT RPG BY DANIEL CHIMITT");
		GameLogic.printHyphenSeparator(30);
		GameLogic.printHyphenSeparator(40);
		
		// allow user to start new game or load existing game
		System.out.println("(1) New game");
		System.out.println("(2) Load game");
		int selection = GameLogic.intUserInput("-->", 2); 
		if (selection == 1) {
			boolean pickedName = false;
			String name;

			// allow player to choose name
			do {
				GameLogic.clearConsole();
				GameLogic.printHyphenHeader("What is your name, brave adventurer?");
				name = in.nextLine();

				GameLogic.clearConsole();
				GameLogic.printHyphenHeader("Your name is " + name + ".\nIs that correct?");
				System.out.println("(1) Yes!");
				System.out.println("(2) No, let me change it.");
				int input = GameLogic.intUserInput("--> ", 2);
				if (input == 1)
					pickedName = true;
			} while (!pickedName);

			// create new player object
			// params: name, current exp, exp to level, starting gold, starting act, movement counter since last fight
			// TODO: change starting gold back to some small amount (increased for testing purposes)
			Player player = new Player(name, 0, 11, 9999999, 1, 0);
			
			// set the player in the game instance
			setPlayer(player);

			// print introduction for main story
			MainStory.printIntroduction();

			// allow player to choose three stats and one ability, offensive spell, or
			// support spell at the start of game
			player.pickThreeStats();
			player.chooseUpgrade();

			ActOneDialogue.momIntroDialogue();

			// set isRunning to true so game loop can continue
			GameLogic.isRunning = true;

			// set lastTownVisited to starting location
			GameLogic.lastTownVisited = ActOneMap.getCurrentPlayerPosition();

			// town options appear when first starting the game
			TownOptions.townOptions();

			// start main game loop
			GameLogic.continueGame();
		}
		else {
			loadGame();
			GameLogic.continueGame();
		}
	}
}
