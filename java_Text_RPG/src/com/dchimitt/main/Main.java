package com.dchimitt.main;

public class Main {
	public static void main(String[] args) {
		// print title
		GameLogic.printSeperator(40);
		GameLogic.printSeperator(30);
		System.out.println("TALES OF SORROW");
		System.out.println("TEXT RPG BY DANIEL CHIMITT");
		GameLogic.printSeperator(30);
		GameLogic.printSeperator(40);
		
		// allow user to start new game or load existing game
		System.out.println("New game - type 1");
		System.out.println("Load game - type 2");
		int selection = GameLogic.intUserInput("-->", 2); 
		if (selection == 1) 
			GameLogic.startGame();
		else
			GameLogic.loadGame();
	}
}
