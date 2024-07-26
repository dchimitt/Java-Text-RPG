package com.dchimitt.main;

// stores methods to print out sections of main storyline
public class MainStory {
	public static void printIntroduction() {
		GameLogic.clearConsole();
		GameLogic.printSeperator(30);
		System.out.println("Welcome, fresh mea- I mean, " + GameLogic.player.name);
		System.out.println("PLACEHOLDER");
		System.out.println("PLACEHOLDER");
		System.out.println("PLACEHOLDER");
		GameLogic.typeToContinue();
	}
}
