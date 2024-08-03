package dialogue;

import com.dchimitt.main.GameLogic;

public class ActOneDialogue implements java.io.Serializable {
	public static void momIntroDialogue() {
		GameLogic.clearConsole();
		System.out.println("Mother:");
		System.out.println("PLACEHOLDER HI SON");
		GameLogic.typeToContinue();
		GameLogic.clearConsole();
	}
	
	public static void momTalkText() {
		GameLogic.clearConsole();
		System.out.println("Mother:\nYou're always welcome at your home, dearie!\nFeel free to take a nap!");
		GameLogic.player.currentHp = GameLogic.player.maximumHp;
		GameLogic.player.currentMana = GameLogic.player.maximumMana;
		System.out.println("\nYour health and mana have been fully restored! Thanks mom!");
		GameLogic.typeToContinue();
		GameLogic.clearConsole();
	}
	
	public static void fightThraxxDialogue() {
		GameLogic.clearConsole();
		System.out.println("You reach the exit of the cave, a sigh of relief on your face.");
		System.out.println("Walking toward the exit, a shadow appears out of the corner of your eye.");
		System.out.println("A hideous troll, equipped with a stone hammer and at least three times your size, snorts and scowls.");
		System.out.println("You plant your feet, ready to challenge the massive beast before you...");
		System.out.println("The troll yells, \"ME SMORC ME SMASH TINY HOOMAN!!!\" and charges you");
		GameLogic.typeToContinue();
	}
}
