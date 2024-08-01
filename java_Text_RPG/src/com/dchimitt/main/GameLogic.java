package com.dchimitt.main;

import actOneEnemies.ActOneEnemyCreation;

import java.util.Scanner;
import dialogue.ActOneDialogue;
import mainStory.MainStory;
import maps.ActOneMap;
import maps.ActOneMap.Direction;

// will never create object of this class, so everything here is static
public class GameLogic implements java.io.Serializable {
	static Scanner in = new Scanner(System.in);
	static Player player;
	static int goldCost;
	static ActOneMap mapOne = new ActOneMap(); // initializating act one map
	static Room lastTownVisited; // variable used for player teleportion on defeat
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
			} catch (Exception e) {
				in.next();
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

		// create new player object
		// params: name, current exp, exp to level, starting gold, starting act
		player = new Player(name, 0, 12, 5, 1);

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
		System.out.println("You are in town!\n OPTIONS:");
		System.out.println("(1) Rest at an inn\n(2) Item vendor\n(3) Gear vendor\n(4) Talk to NPC\n(5) Leave town");
		int input = intUserInput("-->", 5);
		if (input == 1) {
			// restAtInn();
		} else if (input == 2) {
			// itemVendor();
		} else if (input == 3) {
			// gearVendor();
		} else if (input == 4) {
			// talkToNPC();
		} else {
			// leaveTown();
		}

		// start main game loop
		gameLoop();
	}

	public static void checkAct() {
		if (actOneFinalBossDefeated == false)

			if (actOneFinalBossDefeated == true && actTwoFinalBossDefeated == false)
				player.currentAct = 2;
		if (actTwoFinalBossDefeated == true && finalBossOfGameDefeated == false)
			player.currentAct = 3;
	}

	public static boolean isInTown() {
		Room currentRoom = ActOneMap.getCurrentPlayerPosition();
		if (currentRoom.getName().equals("Town of Reizart:") || currentRoom.getName().equals("SOME TOWN:"))
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
		diedInBossFight = true;
		if (lastTownVisited.getName().equals("Town of Reizart:")) {
			ActOneMap.teleportPlayerTo(lastTownVisited);
		}
		else if (lastTownVisited.getName().equals("SOME TOWN:"))
			ActOneMap.teleportPlayerTo(lastTownVisited);
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
					System.out.println("Thank you for your patronage!");
					System.out.println("You sleep soundly for the night, waking up completely refreshed.");
					player.currentHp = player.maximumHp;
					player.currentMana = player.maximumMana;
					typeToContinue();
					clearConsole();
				}
				else {
					notEnoughGold();
					typeToContinue();
					clearConsole();
				}
			}
			else if (currentRoom.getName().equals("SOME TOWN:")) {
				if (player.gold >= someTownCostToRest) {
					System.out.println("Thank you for your patronage!");
					System.out.println("You sleep soundly for the night, waking up completely refreshed.");
					player.currentHp = player.maximumHp;
					player.currentMana = player.maximumMana;
					typeToContinue();
					clearConsole();
				}
				else {
					notEnoughGold();
					typeToContinue();
					clearConsole();
				}
			}
		}
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
				} else {
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
				} else if (randomNumber == 2) {
					ActOneEnemyCreation enemy = new ActOneEnemyCreation();
					Character caveSpider = enemy.createCaveSpider();
					BattleLogic.startBattle(caveSpider);
				} else {
					ActOneEnemyCreation enemy = new ActOneEnemyCreation();
					Character caveSlug = enemy.createCaveSlug();
					BattleLogic.startBattle(caveSlug);
				}
			}
			if (currentRoom.getName() == "IDK YET:") {
				// formula to generate a pseudorandom number from 1 to 2
				int randomNumber = (int) (Math.random() * (2 - 1 + 1) + 1);
				if (randomNumber == 1) {

				} else {

				}
			}
		} else if (player.currentAct == 2) {

		} else {

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
		} else if (player.currentAct == 2) {

		} else {

		}
	}

	// TODO (possibly make this its own class due to length and complexity of this
	// method

	// method to continue game
	public static void continueGame() {
		GameLogic.clearConsole();
		checkAct();
		boolean isInFight = false;
		ActOneMap.printPlayerPosition();
		System.out.println("Type N, S, E, or W to move in a direction.");
		do {
			String directionInput = in.next().trim().toUpperCase();
			if (directionInput.equals("N"))
				ActOneMap.movePlayerTo(Direction.NORTH);
			else if (directionInput.equals("S"))
				ActOneMap.movePlayerTo(Direction.SOUTH);
			else if (directionInput.equals("E"))
				ActOneMap.movePlayerTo(Direction.EAST);
			else if (directionInput.equals("W"))
				ActOneMap.movePlayerTo(Direction.WEST);

			// toggle boss fight if player moves into correct room
			checkForBossEncounter();

			// if the player is in a town, options to rest, purchase/sell items and gear,
			// and talk to npc
			// TODO town options infinite loop if player reenters town
			if (isInTown()) {
				boolean inTown = true;
				lastTownVisited = ActOneMap.getCurrentPlayerPosition();
				while (inTown) {
					System.out.println("You are in town!\n OPTIONS:");
					System.out.println(
							"(1) Rest at an inn\n(2) Item vendor\n(3) Gear Vendor\n(4) Talk to NPC\n(5) Leave town");
					int input = intUserInput("-->", 5);
					// TODO implement methods
					if (input == 1) {
						wantsToRestAtInn();
					} else if (input == 2) {
						// itemVendor();
					} else if (input == 3) {
						// gearVendor();
					} else if (input == 4) {
						// talkToNPC();
					} else {
						/*
						 * TODO: 
						 *
						 * 1) If leaving town by selecting a direction that results in player movement
						 * being blocked, player will end up still on the town room but options no
						 * longer show. Also, formatting prints movement blocked but instantly clears
						 * console making it confusing for player.
						 * 
						 * 2) Random encounters possible when #1 occurs
						 */
						System.out.println("Which direction would you like to leave town in?");
						System.out.println("N, S, E, or W?");
						String townExitInput = in.next().trim().toUpperCase();
						if (townExitInput.equals("N"))
							ActOneMap.movePlayerTo(Direction.NORTH);
						else if (townExitInput.equals("S"))
							ActOneMap.movePlayerTo(Direction.SOUTH);
						else if (townExitInput.equals("E"))
							ActOneMap.movePlayerTo(Direction.EAST);
						else if (townExitInput.equals("W"))
							ActOneMap.movePlayerTo(Direction.WEST);
						inTown = false;
						GameLogic.clearConsole();
						checkAct();
						ActOneMap.printPlayerPosition();
						System.out.println("Type N, S, E, or W to move in a direction.");
					}
				}
			}

			// 20% chance to encounter random battle
			// TODO: implement scaling encounter chance that increases the further a player
			// travels without encountering a battle, increasing to 100% after x amount of
			// movements
			// TODO: remove chance for encounter if player movement is blocked
			if (Math.random() <= 0.2 && !isInTown())
				isInFight = true;

		} while (!isInFight);
		System.out.println("You've encountered a monster!");
		GameLogic.typeToContinue();
		randomEncounter();
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
		System.out.println("Gold: " + player.gold);
		printSeperator(20);

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
		GameLogic.printSeperator(20);

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
		GameLogic.printSeperator(20);

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
		GameLogic.printSeperator(20);
		typeToContinue();
	}

	// print main menu
	public static void printMenu() {
		clearConsole();
		printHeader("MENU");
		System.out.println("(1) Continue the game");
		System.out.println("(2) Open your character sheet");
		System.out.println("(3) Save game");
		System.out.println("(4) Save and exit game");
	}

	// main game loop
	public static void gameLoop() {
		while (isRunning) {
			printMenu();
			int input = intUserInput("--> ", 4);
			if (input == 1)
				continueGame();
			else if (input == 2)
				characterSheet();
			else if (input == 3) {
				AdventureGame.saveGame();
				typeToContinue();
			} else {
				AdventureGame.saveGame();
				isRunning = false;
			}
		}
	}
}
