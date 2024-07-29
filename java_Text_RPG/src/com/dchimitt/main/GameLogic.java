package com.dchimitt.main;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import dialogue.ActOneDialogue;
import mainStory.MainStory;
import maps.ActOneMap;
import maps.ActOneMap.Direction;

// will never create object of this class, so everything here is static
public class GameLogic {
	static Scanner in = new Scanner(System.in);
	static GameLogic game;
	static Player player;
	static ActOneMap mapOne = new ActOneMap();
	
	public static boolean defeatedActOneBoss = false;
	public static boolean defeatedActTwoBoss = false;
	public static boolean defeatedActThreeBoss = false;
	
	public static boolean isRunning;
	
	// method to get input when integers are required
	public static int intUserInput(String prompt, int userChoices) {
		int input;
		do {
			System.out.println(prompt);
			try {
				input = in.nextInt();				
			}
			catch(Exception e) {
				input = -1;
				System.out.println("That is not an integer. Please enter an integer: ");
			}
		} while (input < 1 || input > userChoices);
		return input;
	}
	
	public static void clearConsole() {
		for (int i = 0; i < 100; i++)
			System.out.println();
	}
	
	public static void printSeperator(int n) {
		for (int i = 0; i < n; i++)
			System.out.print("-");
		System.out.println();
	}
	
	public static void printHeader(String title) {
		printSeperator(30);
		System.out.println(title);
		printSeperator(30);
	}
	
	public static void typeToContinue() {
		System.out.println("\nPress any key and hit enter to continue.");
		in.next(); // this will take the next token entered
	}
	
	public static void startGame() {
		boolean pickedName = false;
		String name; 
		
		// allow player to choose name
		do {
			clearConsole();
			printHeader("What is your name, brave adventurer?");
			name = in.next();
			
			clearConsole();
			printHeader("Your name is " + name + ".\nIs that correct?");
			System.out.println("(1) Yes!");
			System.out.println("(2) No, let me change it.");
			int input = intUserInput("--> ", 2);
			if (input == 1)
				pickedName = true;
		} while (!pickedName);
		
		// create new player object and set location to first room of act one
		player = new Player(name, 0, 12, 1);
		
		// print introduction for main story
		MainStory.printIntroduction();
		
		// allow player to choose three stats and one ability, offensive spell, or support spell at the start of game
		player.pickThreeStats();
		player.chooseUpgrade();
		
		ActOneDialogue.momIntroDialogue();
		
		// set isRunning to true so game loop can continue
		isRunning = true;
		
		// start main game loop
		gameLoop();
	}
	
	
	public static void checkAct() {
		if (defeatedActOneBoss == false)
			
		if (defeatedActOneBoss == true && defeatedActTwoBoss == false)
			player.currentAct = 2;
		if (defeatedActTwoBoss == true && defeatedActThreeBoss == false)
			player.currentAct = 3;
	}
	
	public static void randomEncounter() {
		if (player.currentAct == 1) {
			Room currentRoom = ActOneMap.getCurrentPlayerPosition();	
			if (currentRoom.getName() == "Town of Reizart:" || currentRoom.getName() == "SOME TOWN:") 
				return;
			if (currentRoom.getName() == "Plains:") {
				
			}
			if (currentRoom.getName() == "Reizart Cave:") {
				
			}
			if (currentRoom.getName() == "IDK YET:") {
				
			}
		}
	}
	
	// method to continue game
	public static void continueGame() {
		GameLogic.clearConsole();
		checkAct();
		System.out.println("Type N, S, E, or W to move in a direction.");
		do {
			String directionInput = in.next().trim().toUpperCase();
			if (directionInput.equals("N"))
				mapOne.movePlayerTo(Direction.NORTH);
			else if (directionInput.equals("S"))
				mapOne.movePlayerTo(Direction.SOUTH);
			else if (directionInput.equals("E"))
				mapOne.movePlayerTo(Direction.EAST);
			else if (directionInput.equals("W"))
				mapOne.movePlayerTo(Direction.WEST);
		} while (!isInFight());
		System.out.println("You've encountered a monster!");
		GameLogic.typeToContinue();
	}
	
	public static boolean isInFight()
	{
		return false;
	}
	
	// print character sheet
	public static void characterSheet() {
		clearConsole();
		printHeader("CHARACTER SHEET");
		System.out.println(player.name);
		System.out.println("Level: " + player.level);
		System.out.println("HP: " + player.currentHp + "/" + player.maximumHp);
		System.out.println("MP: " + player.currentMana + "/" + player.maximumMana);
		System.out.println("EXP: " + player.currentExp + "/" + player.expToLevel);
		printSeperator(20);
		
		// print out abilities
		boolean foundAbility = false;
		System.out.println("Abilities: " );	
		for (Player.Abilities abilities : Player.Abilities.values()) {
			if (abilities.getLearnedAbility()) {
				System.out.println(abilities.getAbilityName() + " - " + abilities.getAbilityDescription());	
				foundAbility = true;
			}
		}
		if (foundAbility == false)
			System.out.println("N/A");
		GameLogic.printSeperator(20);
		
		// print out offensive magic
		boolean foundOffMag = false;
		System.out.println("Offensive Spells: " );	
		for (Player.OffMagSpells offMag : Player.OffMagSpells.values()) {
			if (offMag.getLearnedOffMag()) {
				System.out.println(offMag.getOffMagName() + " - " + offMag.getOffMagDescription());
				foundOffMag = true;
			}
		}
		if (foundOffMag == false)
			System.out.println("N/A");
		GameLogic.printSeperator(20);
		 
		// print out offensive magic
		boolean foundSuppMag = false;
		System.out.println("Support Spells: " );	
		for (Player.SuppMagSpells suppMag : Player.SuppMagSpells.values()) {
			if (suppMag.getLearnedSuppMag()) {
				System.out.println(suppMag.getSuppMagName() + " - " + suppMag.getSuppMagDescription());
				foundSuppMag = true;
			}
		}
		if (foundSuppMag == false)
			System.out.println("N/A");
		GameLogic.printSeperator(20);
		typeToContinue();
	}
	
	// print main menu
	public static void printMenu() {
		clearConsole();
		printHeader("MENU");
		System.out.println("(1) Continue the game");
		System.out.println("(2) Open your character sheet");
		System.out.println("(3) Save game.");	
		System.out.println("(4) Save and exit game.");
	}
	
	public static void saveGame() {
		try {
			FileOutputStream fos = new FileOutputStream("Adv.sav");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(game);
			oos.flush();
			oos.close();
			System.out.println("Game saved.");
		} catch (Exception e) {
			System.out.println("Serialization error! Can't save data." + e.getClass() + ": " + e.getMessage());			
		}
	}
	
	public static void loadGame() {
		try {
			FileInputStream fis = new FileInputStream("Adv.sav");
			ObjectInputStream ois = new ObjectInputStream(fis);
			game = (GameLogic) ois.readObject();
			ois.close();
			System.out.println("--Game loaded--");
		} catch (Exception e) {
			System.out.println("Serialization error! Can't load data.");
			System.out.println(e.getClass() + ": " + e.getMessage());
		}
	}
	
	// main game loop
	public static void gameLoop() {
		while(isRunning) {
			printMenu();
			int input = intUserInput("--> ", 3);
			if (input == 1) 
				continueGame();
			else if (input == 2) 
				characterSheet();
			else if (input == 3)
				saveGame();
			else {
				saveGame();
				isRunning = false;
			}
		}
	}
}
