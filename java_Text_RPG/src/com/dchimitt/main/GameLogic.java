package com.dchimitt.main;
import java.util.Scanner;

// will never create object of this class, so everything here is static
public class GameLogic {
	static Scanner in = new Scanner(System.in);
	static Player player;
	
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
		
		// start main game loop
		// gameLoop();
	}
}
