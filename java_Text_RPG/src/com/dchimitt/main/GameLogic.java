package com.dchimitt.main;

import actOneEnemies.ActOneEnemyCreation;

import java.util.Scanner;
import java.io.Serializable;
import dialogue.ActOneDialogue;
import mainStory.MainStory;
import maps.ActOneMap;
import maps.ActOneMap.Direction;
import gearAndItems.PlayerItems;
import gearAndItems.PlayerGear;

// will never create object of this class, so everything here is static
public class GameLogic implements java.io.Serializable {
	static Scanner in = new Scanner(System.in);
	public static Player player;
	static int goldCost;
	static ActOneMap mapOne = new ActOneMap(); // initializing act one map
	static Room lastTownVisited; // variable used for teleportPlayerTo method in class ActOneMap
	static boolean diedInBossFight = false;

	// set act one bosses to undefeated
	static boolean actOneThraxxDefeated = false;
	static boolean actOneSecondBossDefeated = false;
	static boolean actOneFinalBossDefeated = false;

	// set act two bosses to undefeated
	static boolean actTwoFirstBossDefeated = false;
	static boolean actTwoSecondBossDefeated = false;
	static boolean actTwoFinalBossDefeated = false;

	// set act three bosses to undefeated
	static boolean actThreeFirstBossDefeated = false;
	static boolean actThreeSecondBossDefeated = false;
	static boolean finalBossOfGameDefeated = false;

	public static boolean isRunning;

	// method to get input when integers are required
	public static int intUserInput(String prompt, int userChoices) {
		int input;
		do {
			System.out.println(prompt);
			try {
				input = in.nextInt();
				in.nextLine();
			} catch (Exception e) {
				in.nextLine();
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

	public static void printHyphenSeparator(int n) {
		for (int i = 0; i < n; i++)
			System.out.print("-");
		System.out.println();
	}
	
	public static void printTildeSeparator(int n) {
		for (int i = 0; i < n; i++)
			System.out.print("~");
		System.out.println();
	}

	public static void printHyphenHeader(String title) {
		printHyphenSeparator(30);
		System.out.println(title);
		printHyphenSeparator(30);
	}
	public static void printTildeHeader(String title) {
		printTildeSeparator(30);
		System.out.println(title);
		printTildeSeparator(30);
	}

	public static void typeToContinue() {
		System.out.println("\nPress any key and hit enter to continue.");
		in.nextLine(); // this will take the next token entered
	}

	public static void startGame() {
		boolean pickedName = false;
		String name;

		// allow player to choose name
		do {
			clearConsole();
			printHyphenHeader("What is your name, brave adventurer?");
			name = in.nextLine();

			clearConsole();
			printHyphenHeader("Your name is " + name + ".\nIs that correct?");
			System.out.println("(1) Yes!");
			System.out.println("(2) No, let me change it.");
			int input = intUserInput("--> ", 2);
			if (input == 1)
				pickedName = true;
		} while (!pickedName);

		// create new player object
		// params: name, current exp, exp to level, starting gold, starting act, movement counter since last fight
		// TODO: change starting gold back to some small amount (increased for testing purposes)
		player = new Player(name, 0, 11, 9999999, 1, 0);

		// print introduction for main story
		MainStory.printIntroduction();

		// allow player to choose three stats and one ability, offensive spell, or
		// support spell at the start of game
		player.pickThreeStats();
		player.chooseUpgrade();

		ActOneDialogue.momIntroDialogue();

		// set isRunning to true so game loop can continue
		isRunning = true;

		// set lastTownVisited to starting location
		lastTownVisited = ActOneMap.getCurrentPlayerPosition();

		// town options appear when first starting the game
		TownOptions.townOptions();

		// start main game loop
		continueGame();
	}

	public static void checkAct() {
		if (actOneFinalBossDefeated == false)

		if (actOneFinalBossDefeated == true && actTwoFinalBossDefeated == false)
				player.currentAct = 2;
		if (actTwoFinalBossDefeated == true && finalBossOfGameDefeated == false)
			player.currentAct = 3;
	}

	// currently, only returns true if the town name contains "town" somewhere in string
	// TODO: add other options later if needed
	public static boolean isInTown() {
		Room currentRoom = ActOneMap.getCurrentPlayerPosition();
		if (currentRoom.isTown())
			return true;
		else
			return false;
	}

	// TODO: implement townLastVisited method
	public static Room lastTownVisited() {
		return lastTownVisited;
	}

	// TODO: implement playerIsDead method
	public static void playerIsDead() {
		GameLogic.clearConsole();
		System.out.println("You have died...\nNow teleporting to your last visited town.");
		typeToContinue();
		diedInBossFight = true; // this will reset if boss is encountered
		player.currentHp = player.maximumHp / 3;
		if (lastTownVisited.getName().equals("Town of Reizart:")) {
			ActOneMap.teleportPlayerTo(lastTownVisited);
			TownOptions.townOptions();
		}
		else if (lastTownVisited.getName().equals("SOME TOWN:")) {
			ActOneMap.teleportPlayerTo(lastTownVisited);
			TownOptions.townOptions();
		}
		
		// player loses gold and exp when dying (currently set to 50% and 50%, respectively)
		player.gold = player.gold / 2;
		player.currentExp = player.currentExp / 2;
		
		// TODO: add future towns here
	}
	
	
	public static void notEnoughGold() {
		System.out.println("You don't have enough gold to do that!");
	}
	
	public static void wantsToRestAtInn() {
		Room currentRoom = ActOneMap.getCurrentPlayerPosition();
		int reizartCostToRest = 10;
		int someTownCostToRest = 30;
		System.out.println("Welcome! Would you like to rest here for the night? (Fully recovers HP and MP)");
		if (currentRoom.getName().equals("Town of Reizart:"))
			System.out.println("Cost: " + reizartCostToRest + " gold\nCurrent gold: " + player.gold + " gold");
		else if (currentRoom.getName().equals("Some Town:"))
			System.out.println("Cost: " + someTownCostToRest + " gold\nCurrent gold: " + player.gold + " gold");
		System.out.println("(1) Yes\n(2) No");
		int userInput = intUserInput("-->", 2);
		if (userInput == 1 && player.currentAct == 1) {
			if (currentRoom.getName().equals("Town of Reizart:")) {
				if (player.gold >= reizartCostToRest) {
					restAtInn();
				}
				else {
					notEnoughGold();
					typeToContinue();
					clearConsole();
				}
			}
			else if (currentRoom.getName().equals("SOME TOWN:")) {
				if (player.gold >= someTownCostToRest) {
					restAtInn();
				}
				else {
					notEnoughGold();
					typeToContinue();
					clearConsole();
				}
			}
		}
	}
	
	public static void restAtInn() {
		System.out.println("Thank you for your patronage!");
		System.out.println("You sleep soundly for the night, waking up completely refreshed.");
		player.currentHp = player.maximumHp;
		player.currentMana = player.maximumMana;
		typeToContinue();
		clearConsole();
	}
	
	public static void talkToNPC() {
		Room currentRoom = ActOneMap.getCurrentPlayerPosition();
		if (currentRoom.getName().equals("Town of Reizart:")) 
			ActOneDialogue.momTalkText();
		// TODO: add more NPC dialogue options
		// else if (currentRoom.getName().equals("SOME TOWN:"))
	}

	// TODO: fix random encounters starting on various rooms such as Cave Entrance
	// or towns
	public static void randomEncounter() {
		if (player.currentAct == 1) {
			Room currentRoom = ActOneMap.getCurrentPlayerPosition();
			if (isInTown())
				return;
			if (currentRoom.getName() == "Plains:") {
				// formula to generate a pseudorandom number from 1 to 2
				int randomNumber = (int) (Math.random() * (2 - 1 + 1)) + 1;
				if (randomNumber == 1) {
					ActOneEnemyCreation enemy = new ActOneEnemyCreation();
					Character plainsGoblin = enemy.createPlainsGoblin();
					BattleLogic.startBattle(plainsGoblin);
				} 
				else {
					ActOneEnemyCreation enemy = new ActOneEnemyCreation();
					Character plainsSnake = enemy.createPlainsSnake();
					BattleLogic.startBattle(plainsSnake);
				}
			}
			if (currentRoom.getName() == "Reizart Cave:") {
				// formula to generate a pseudorandom number from 1 to 3
				int randomNumber = (int) (Math.random() * (3 - 1 + 1) + 1);
				if (randomNumber == 1) {
					ActOneEnemyCreation enemy = new ActOneEnemyCreation();
					Character caveBat = enemy.createCaveBat();
					BattleLogic.startBattle(caveBat);
				} 
				else if (randomNumber == 2) {
					ActOneEnemyCreation enemy = new ActOneEnemyCreation();
					Character caveSpider = enemy.createCaveSpider();
					BattleLogic.startBattle(caveSpider);
				} 
				else {
					ActOneEnemyCreation enemy = new ActOneEnemyCreation();
					Character caveSlug = enemy.createCaveSlug();
					BattleLogic.startBattle(caveSlug);
				}
			}
			if (currentRoom.getName() == "IDK YET:") {
				// formula to generate a pseudorandom number from 1 to 2
				int randomNumber = (int) (Math.random() * (2 - 1 + 1) + 1);
				if (randomNumber == 1) {

				} 
				else {

				}
			}
		} 
		else if (player.currentAct == 2) {

		} 
		else {

		}
	}

	public static void checkForBossEncounter() {
		if (player.currentAct == 1) {
			Room currentRoom = ActOneMap.getCurrentPlayerPosition();
			if (currentRoom.getName().equals("Reizart Cave Exit:") && !actOneThraxxDefeated) {
				ActOneEnemyCreation enemy = new ActOneEnemyCreation();
				Character caveBossThraxx = enemy.createCaveBossThraxx();
				ActOneDialogue.fightThraxxDialogue();
				BattleLogic.startBattle(caveBossThraxx);
				// if (player won the fight)
				if (!diedInBossFight)
					actOneThraxxDefeated = true;
			}
		} 
		else if (player.currentAct == 2) {

		} 
		else {

		}
	}
	
	// method to calculate experience required to level up using polynomial formula
	/*
	TODO: adjust formula as needed for game's progression
	a * level^b -- ensures exponential growth, adjust a and b to control how steep the curve is
	c * level   -- adds linear component to growth, ensuring early levels are easier to achieve
	-----------------------------------------------------------------------------------------------
	To increase difficulty at higher levels, increase value of a or b
	To make early levels easier, increase the value of c
	-----------------------------------------------------------------------------------------------
	With current coefficients:
	Level 1 = 11 xp || level 5 = 87 xp  || level 9 = 211 xp  || level 13 = 383 xp || level 17 = 603 xp
	Level 2 = 26 xp || level 6 = 114 xp || level 10 = 250 xp || level 14 = 434 xp || level 18 = 666 xp
	Level 3 = 43 xp || level 7 = 143 xp || level 11 = 291 xp || level 15 = 487 xp || level 19 = 731 xp
	Level 4 = 64 xp || level 8 = 176 xp || level 12 = 336 xp || level 16 = 544 xp || level 20 = 800 xp
	
	*/
	public static int calculateExperienceToLevel() {
		final double a = 1.5; // exponential growth factor
		final double b = 2.0; // exponent for exponential growth
		final double c = 10; // linear growth factor
		
		return (int) (a * Math.pow(player.level, b) + c * player.level);
	}
	
	// method called when the player levels up
	// TODO: better balancing for hp/mp gains, experience for next levels (currently no leftover exp etc.), when player obtains upgrade points, etc.
	public static boolean didPlayerLevelUp() {
		if (player.currentExp >= player.expToLevel) {
			System.out.println("Congratulations, you have leveled up!");
			typeToContinue();
			player.level++;
			
			// player gets random maximum HP boost of 4 (60% chance) or 5 (40% chance)
			if (Math.random() >= 0.6)
				player.maximumHp = player.maximumHp + 5;
			else 
				player.maximumHp = player.maximumHp + 4;
			
			// player gets random maximum MP boost of 3 (60% chance) or 4 (40% chance)
			if (Math.random() >= 0.6)
				player.maximumMana = player.maximumMana + 4;
			else
				player.maximumMana = player.maximumMana + 3;
			
			// give player a percentage of missing hp and mp back 
			player.currentHp = player.currentHp + (player.maximumHp / 5);
			player.currentMana = player.currentMana + (player.maximumMana / 5);
			
			// reset player experience and increase experience required for next level
			// TODO: might add leftover experience so that player retains the rest of exp earned on level-up
			// int leftoverExperience; 
			player.currentExp = 0;
			player.expToLevel = calculateExperienceToLevel();
			
			// player gains three stat points per level obtained
			player.pickThreeStats();
			
			// player obtains upgrade point every 2 levels (odd levels)
			if (player.level % 2 != 0)
				player.chooseUpgrade();
			return true;
		}
		else 
			return false;
	}

	// method to continue game
	public static void continueGame() {
		while (isRunning) {
			if (isInTown()) 
				TownOptions.townOptions();
		
			GameLogic.clearConsole();
			checkAct();
			boolean isInFight = false;
			ActOneMap.printPlayerPosition();
			System.out.println("Type N, S, E, or W to move in a direction.\nType M to access the menu.");
			do {
				String directionInput = in.nextLine().trim().toUpperCase();
				if (directionInput.equals("N"))
					ActOneMap.movePlayerTo(Direction.NORTH);
				else if (directionInput.equals("S"))
					ActOneMap.movePlayerTo(Direction.SOUTH);
				else if (directionInput.equals("E"))
					ActOneMap.movePlayerTo(Direction.EAST);
				else if (directionInput.equals("W"))
					ActOneMap.movePlayerTo(Direction.WEST);
				else if (directionInput.equals("M")) {
					printMenu();
					// TODO: fix infinite loop when player presses 3 or 4 to save or save and exit
					int input = intUserInput("--> ", 4);
					if (input == 1)
						continueGame();
					else if (input == 2) {
						characterSheet();
						continueGame();
					}
					else if (input == 3) {
						AdventureGame.saveGame();
						typeToContinue();
					} 
					else {
						AdventureGame.saveGame();
						isRunning = false;
					}
				}

				// toggle boss fight if player moves into correct room
				checkForBossEncounter();
			
				// random encounter logic
				int movementsSinceLastFight = player.getMovementCounter();
				double encounterRate;
				double encounterRateConstant = 0.1;				
				// formula: R(n) = 1 - e^(-kn), where n = steps since last encounter and k is encounterRateConstant
				// rates with k = 0.1:
				// 1 step: 9.52% || 2 steps: 18.13% || 3 steps: 25.92% || 4 steps: 32.97% || 5 steps (if fight not forced): 39.35%
				if (movementsSinceLastFight < 5) 
					encounterRate = (1 - Math.exp(-1 * encounterRateConstant * movementsSinceLastFight));
				else
					encounterRate = 1.0;
				if (Math.random() <= encounterRate && !isInTown() && !ActOneMap.playersPathIsBlocked() && !ActOneMap.playerMovingToSafeRoom()) {
					isInFight = true;
					player.resetMovementCounter();
				}				
			} while (!isInFight);
			System.out.println("You've encountered a monster!");
			GameLogic.typeToContinue();
			randomEncounter();
		}
	}

	// print character sheet
	public static void characterSheet() {
		clearConsole();
		printHyphenHeader("CHARACTER SHEET");
		System.out.println(player.name);
		System.out.println("Level: " + player.level);
		System.out.println("HP: " + player.currentHp + "/" + player.maximumHp);
		System.out.println("MP: " + player.currentMana + "/" + player.maximumMana);
		System.out.println("EXP: " + player.currentExp + "/" + player.expToLevel);
		System.out.println("Gold: " + player.gold);
		printHyphenSeparator(20);

		// print out abilities
		boolean foundAbility = false;
		System.out.println("Abilities: ");
		for (Player.Abilities abilities : Player.Abilities.values()) {
			if (abilities.getLearnedAbility()) {
				System.out.println(abilities.getAbilityName() + " - " + abilities.getAbilityDescription());
				foundAbility = true;
			}
		}
		if (foundAbility == false)
			System.out.println("N/A");
		GameLogic.printHyphenSeparator(20);

		// print out offensive magic
		boolean foundOffMag = false;
		System.out.println("Offensive Spells: ");
		for (Player.OffMagSpells offMag : Player.OffMagSpells.values()) {
			if (offMag.getLearnedOffMag()) {
				System.out.println(offMag.getOffMagName() + " - " + offMag.getOffMagDescription());
				foundOffMag = true;
			}
		}
		if (foundOffMag == false)
			System.out.println("N/A");
		GameLogic.printHyphenSeparator(20);

		// print out offensive magic
		boolean foundSuppMag = false;
		System.out.println("Support Spells: ");
		for (Player.SuppMagSpells suppMag : Player.SuppMagSpells.values()) {
			if (suppMag.getLearnedSuppMag()) {
				System.out.println(suppMag.getSuppMagName() + " - " + suppMag.getSuppMagDescription());
				foundSuppMag = true;
			}
		}
		if (foundSuppMag == false)
			System.out.println("N/A");
		GameLogic.printHyphenSeparator(20);
		System.out.println("(1) View inventory\n(2) View equipped gear\n(3) Continue game");
		int input = intUserInput("-->", 3);
		// TODO: add functionality for using items while in inventory
		if (input == 1) {
			System.out.println("Consumable items:\n");
			PlayerItems.printPlayerItems();
			typeToContinue();
			System.out.println("Gear:\n");
			PlayerGear.printPlayerGear();
		}
		else if (input == 2) {
			
		}
		else {
			
		}
		typeToContinue();
	}

	// print main menu
	public static void printMenu() {
		clearConsole();
		printHyphenHeader("MENU");
		System.out.println("(1) Continue the game");
		System.out.println("(2) Open your character sheet");
		System.out.println("(3) Save game");
		System.out.println("(4) Save and exit game");
	}
}
