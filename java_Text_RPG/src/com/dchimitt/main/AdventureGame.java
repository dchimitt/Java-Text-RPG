package com.dchimitt.main;

import java.io.Serializable;
import java.util.Scanner;

import dialogue.ActOneDialogue;
import mainStory.MainStory;
import maps.ActOneMap;
import gearAndItems.PlayerItems;
import gearAndItems.PlayerGear;
import com.dchimitt.main.Player;



public class AdventureGame implements Serializable {
	
	public static Player player;
	static Scanner in = new Scanner(System.in);
	public static AdventureGame game;
	
	public static Player getPlayer() {
		return player;
	}
	
	public static void setPlayer(Player player) {
		AdventureGame.player = player;
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
			// params: name, current exp, exp to level, starting gold, starting act, movement counter since last fight, ability 1-4 cooldowns (initialize w/ 0)
			// TODO: change starting gold back to some small amount (increased for testing purposes)
			Player player = new Player(name, 0, 11, 9999999, 1, 0, 0, 0, 0, 0);
			
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
			SaveLoadLogic.loadGame();
			GameLogic.continueGame();
		}
	}
}
