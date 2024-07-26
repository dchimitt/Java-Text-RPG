package com.dchimitt.main;
import java.util.Scanner;

// will never create object of this class, so everything here is static
public class GameLogic {
	static Scanner in = new Scanner(System.in);
	static Player player;
	
	public static boolean isRunning;
	
	// method to get user input 
	public static int userInput(String prompt, int userChoices) {
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
			int input = userInput("--> ", 2);
			if (input == 1)
				pickedName = true;
		} while (!pickedName);
					
		// create new player object
		player = new Player(name);
		
		// print introduction for main story
		MainStory.printIntroduction();
		
		// set isRunning to true so game loop can continue
		isRunning = true;
		
		// start main game loop
		gameLoop();
	}
	
	// method to continue game
	public static void continueGame() {
		
	}
	
	// print character sheet
	public static void characterSheet() {
		clearConsole();
		printHeader("CHARACTER SHEET");
		System.out.println(player.name);
		System.out.println("Level: " + player.level);
		System.out.println("HP: " + player.currentHp + "/" + player.maximumHp);
		System.out.println("EXP: " + player.currentExp + "/" + player.expToLevel);
		printSeperator(20);
		
		// print out abilities
		// NOTE: currently cannot reference learnedAbility value in Player class. Fix.
		
		System.out.println("Abilities: " );
		/*
		for (Player.Abilities abilities : Player.Abilities.values()) {
			if (Player.Abilities.learnedAbility) {
				
			}
		}
		*/
		
		/*
		// print out offensive magic
		// TO-DO: IMPLEMENT
		 
		// print out offensive magic
		// TO-DO: IMPLEMENT
		*/
		
		typeToContinue();
	}
	
	// print main menu
	public static void printMenu() {
		clearConsole();
		printHeader("MENU");
		System.out.println("Choose an action:");
		printSeperator(20);
		System.out.println("(1) Continue the game");
		System.out.println("(2) Open your character sheet");
		System.out.println("(3) Exit game");		
	}
	
	// main game loop
	public static void gameLoop() {
		while(isRunning) {
			printMenu();
			int input = userInput("--> ", 3);
			if (input == 1) 
				continueGame();
			else if (input == 2) 
				characterSheet();
			else
				isRunning = false;
		}
	}
}
