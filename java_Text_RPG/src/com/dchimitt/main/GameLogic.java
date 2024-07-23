package com.dchimitt.main;
import java.util.Scanner;

// will never create object of this class, so everything here is static
public class GameLogic {
	static Scanner in = new Scanner(System.in);
	
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
		}
		while (input < 1 || input > userChoices);
		return input;
	}
}
