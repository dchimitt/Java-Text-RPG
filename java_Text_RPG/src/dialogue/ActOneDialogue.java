package dialogue;

import com.dchimitt.main.GameLogic;

public class ActOneDialogue implements java.io.Serializable {
	public static void momIntroDialogue() {
		System.out.println("PLACEHOLDER HI SON");
		GameLogic.typeToContinue();
	}
	
	public static void fightThraxxDialogue() {
		System.out.println("You reach the exit of the cave, a sigh of relief on your face.");
		System.out.println("Walking toward the exit, a shadow appears out of the corner of your eye.");
		System.out.println("A hideous troll, equipped with a stone hammer and at least three times your size, snorts and scowls.");
		System.out.println("You plant your feet, ready to challenge the massive beast before you...");
		System.out.println("The troll yells, \"ME SMORC ME SMASH TINY HOOMAN!!!\" and charges you");
	}
}
