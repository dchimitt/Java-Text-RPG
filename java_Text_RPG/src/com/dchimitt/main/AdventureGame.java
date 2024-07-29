package com.dchimitt.main;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class AdventureGame implements java.io.Serializable {
	
	static AdventureGame game;
	
	public static void saveGame() {
		try {
			FileOutputStream fos = new FileOutputStream("Adv.sav");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(game);
			oos.flush();
			oos.close();
			System.out.println("--Game saved--");
		} catch (Exception e) {
			System.out.println("Serialization error! Can't save data." + e.getClass() + ": " + e.getMessage());			
		}
	}
	
	public static void loadGame() {
		try {
			FileInputStream fis = new FileInputStream("Adv.sav");
			ObjectInputStream ois = new ObjectInputStream(fis);
			game = (AdventureGame) ois.readObject();
			ois.close();
			System.out.println("--Game loaded--");
			GameLogic.gameLoop();
		} catch (Exception e) {
			System.out.println("Serialization error! Can't load data.");
			System.out.println(e.getClass() + ": " + e.getMessage());
		}
	}
	
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
		else {
			loadGame();
			GameLogic.gameLoop();
		}
	}
}
