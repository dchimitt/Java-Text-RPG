package com.dchimitt.main;
import java.util.Scanner;

import com.dchimitt.main.ActOneMap.Direction;

// will never create object of this class, so everything here is static
public class GameLogic {
	static Scanner in = new Scanner(System.in);
	static Player player;
	static ActOneMap mapOne = new ActOneMap();
	
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
		
		// print title
		printSeperator(40);
		printSeperator(30);
		System.out.println("TALES OF SORROW");
		System.out.println("TEXT RPG BY DANIEL CHIMITT");
		printSeperator(30);
		printSeperator(40);
		typeToContinue();
		
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
		player = new Player(name, 0, 12);
		
		// print introduction for main story
		MainStory.printIntroduction();
		
		// set isRunning to true so game loop can continue
		isRunning = true;
		
		// start main game loop
		gameLoop();
	}
	
	/*
	public static void checkAct() {
		if (defeatedActOneBoss == false)
			player.currentAct = 1;
		if (defeatedActOneBoss == true && defeatedActTwoBoss == false)
			player.currentAct = 2;
		if (deafeatedActTwoBoss == true && defeatedActThreeBoss == false)
			player.currentAct = 3;
	}
	*/
	
	// method to continue game
	public static void continueGame() {
		GameLogic.clearConsole();
		//checkAct();
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
		System.out.println("(3) Save and exit game");		
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
			else
				// add save functionality here
				isRunning = false;
		}
	}
}
