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
import gearAndItems.PlayerGear.Gear;

// will never create object of this class
public class GameLogic implements Serializable {
	static Scanner in = new Scanner(System.in);
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

	public static void checkAct() {
		if (actOneFinalBossDefeated == false)

		if (actOneFinalBossDefeated == true && actTwoFinalBossDefeated == false)
				AdventureGame.player.currentAct = 2;
		if (actTwoFinalBossDefeated == true && finalBossOfGameDefeated == false)
			AdventureGame.player.currentAct = 3;
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

	public static Room lastTownVisited() {
		return lastTownVisited;
	}

	public static void playerIsDead() {
		GameLogic.clearConsole();
		System.out.println("You have died...\nNow teleporting to your last visited town.");
		typeToContinue();
		diedInBossFight = true; // this will reset if boss is encountered
		AdventureGame.getPlayer().currentHp = AdventureGame.getPlayer().maximumHp / 3;
		if (lastTownVisited.getName().equals("Town of Reizart:")) {
			ActOneMap.teleportPlayerTo(lastTownVisited);
			TownOptions.townOptions();
		}
		else if (lastTownVisited.getName().equals("SOME TOWN:")) {
			ActOneMap.teleportPlayerTo(lastTownVisited);
			TownOptions.townOptions();
		}
		
		// player loses gold and exp when dying (currently set to 50% and 50%, respectively)
		AdventureGame.getPlayer().gold = AdventureGame.getPlayer().gold / 2;
		AdventureGame.getPlayer().currentExp = AdventureGame.getPlayer().currentExp / 2;
		
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
			System.out.println("Cost: " + reizartCostToRest + " gold\nCurrent gold: " + AdventureGame.getPlayer().gold + " gold");
		else if (currentRoom.getName().equals("Some Town:"))
			System.out.println("Cost: " + someTownCostToRest + " gold\nCurrent gold: " + AdventureGame.getPlayer().gold + " gold");
		System.out.println("(1) Yes\n(2) No");
		int userInput = intUserInput("-->", 2);
		if (userInput == 1 && AdventureGame.getPlayer().currentAct == 1) {
			if (currentRoom.getName().equals("Town of Reizart:")) {
				if (AdventureGame.getPlayer().gold >= reizartCostToRest) {
					restAtInn();
				}
				else {
					notEnoughGold();
					typeToContinue();
					clearConsole();
				}
			}
			else if (currentRoom.getName().equals("SOME TOWN:")) {
				if (AdventureGame.getPlayer().gold >= someTownCostToRest) {
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
		AdventureGame.getPlayer().currentHp = AdventureGame.getPlayer().maximumHp;
		AdventureGame.getPlayer().currentMana = AdventureGame.getPlayer().maximumMana;
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
		if (AdventureGame.getPlayer().currentAct == 1) {
			Room currentRoom = ActOneMap.getCurrentPlayerPosition();
			if (isInTown())
				return;
			if (currentRoom.getName().equals("Plains:")) {
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
			if (currentRoom.getName().equals("Reizart Cave:")) {
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
		else if (AdventureGame.getPlayer().currentAct == 2) {

		} 
		else {

		}
	}

	public static void checkForBossEncounter() {
		if (AdventureGame.getPlayer().currentAct == 1) {
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
		else if (AdventureGame.getPlayer().currentAct == 2) {

		} 
		else {

		}
	}
	
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
		
		return (int) (a * Math.pow(AdventureGame.getPlayer().level, b) + c * AdventureGame.getPlayer().level);
	}
	
	// method called when the player levels up
	// TODO: better balancing for hp/mp gains, experience for next levels (currently no leftover exp etc.), when player obtains upgrade points, etc.
	public static boolean didPlayerLevelUp() {
		if (AdventureGame.getPlayer().currentExp >= AdventureGame.getPlayer().expToLevel) {
			System.out.println("Congratulations, you have leveled up!");
			typeToContinue();
			AdventureGame.getPlayer().level++;
			
			// player gets random maximum HP boost of 4 (60% chance) or 5 (40% chance)
			if (Math.random() >= 0.6)
				AdventureGame.getPlayer().maximumHp = AdventureGame.getPlayer().maximumHp + 5;
			else 
				AdventureGame.getPlayer().maximumHp = AdventureGame.getPlayer().maximumHp + 4;
			
			// player gets random maximum MP boost of 3 (60% chance) or 4 (40% chance)
			if (Math.random() >= 0.6)
				AdventureGame.getPlayer().maximumMana = AdventureGame.getPlayer().maximumMana + 4;
			else
				AdventureGame.getPlayer().maximumMana = AdventureGame.getPlayer().maximumMana + 3;
			
			// give player a percentage of missing hp and mp back 
			AdventureGame.getPlayer().currentHp = AdventureGame.getPlayer().currentHp + (AdventureGame.getPlayer().maximumHp / 5);
			AdventureGame.getPlayer().currentMana = AdventureGame.getPlayer().currentMana + (AdventureGame.getPlayer().maximumMana / 5);
			
			// reset player experience and increase experience required for next level
			// TODO: might add leftover experience so that player retains the rest of exp earned on level-up
			// int leftoverExperience; 
			AdventureGame.getPlayer().currentExp = 0;
			AdventureGame.getPlayer().expToLevel = calculateExperienceToLevel();
			
			// player gains three stat points per level obtained
			AdventureGame.getPlayer().pickThreeStats();
			
			// player obtains upgrade point every 2 levels (odd levels)
			if (AdventureGame.getPlayer().level % 2 != 0)
				AdventureGame.getPlayer().chooseUpgrade();
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
	        boolean hasMovedThisTurn = false; // variable used to prevent fights unless a player has actively moved
	        ActOneMap.printPlayerPosition();
	        System.out.println("Type N, S, E, or W to move in a direction.\nType M to access the menu.");

	        String directionInput = in.nextLine().trim().toUpperCase();

	        if (directionInput.equals("N")) {
	            ActOneMap.movePlayerTo(Direction.NORTH);
	            hasMovedThisTurn = true;
	        }
	        else if (directionInput.equals("S")) {
	            ActOneMap.movePlayerTo(Direction.SOUTH);
	            hasMovedThisTurn = true;
	        }
	        else if (directionInput.equals("E")) {
	            ActOneMap.movePlayerTo(Direction.EAST);
	            hasMovedThisTurn = true;
	        }
	        else if (directionInput.equals("W")) {
	            ActOneMap.movePlayerTo(Direction.WEST);
	            hasMovedThisTurn = true;
	        }
	        else if (directionInput.equals("M")) {
	            boolean menuActive = true;
	            
	            while (menuActive) {
	                printMenu();
	                int input = intUserInput("--> ", 4);
					if (input == 1) {
						menuActive = false;
						break;
					}			
					else if (input == 2) {
						characterSheet();
						break;
					}
					else if (input == 3) {
						SaveLoadLogic.saveGame();
						GameLogic.typeToContinue();
						menuActive = false;
						break;
					} 
					else {
						SaveLoadLogic.saveGame();
						isRunning = false;
						menuActive = false;
						break;
					}
				}
	        }

			// check for boss fight toggle if player moves into a boss-specific room; does not trigger if player has already beaten that specific boss once
			if (hasMovedThisTurn)
				checkForBossEncounter();
			
			// random encounter logic that may occur if player has moved, if they are not in town, they are not blocked when moving, and are not moving to a safe room
			if (hasMovedThisTurn && !isInTown() && !ActOneMap.playersPathIsBlocked() && !ActOneMap.playerMovingToSafeRoom()) {
				int movementsSinceLastFight = AdventureGame.getPlayer().getMovementCounter();
				double encounterRate;
				double encounterRateConstant = 0.1;				
				// formula: R(n) = 1 - e^(-kn), where n = steps since last encounter and k is encounterRateConstant
				// rates with k = 0.1:
				// 1 step: 9.52% || 2 steps: 18.13% || 3 steps: 25.92% || 4 steps: 32.97% || 5 steps (if fight not forced): 39.35%
				if (movementsSinceLastFight < 5) 
					encounterRate = (1 - Math.exp(-1 * encounterRateConstant * movementsSinceLastFight));
				else
					encounterRate = 1.0;
				if (Math.random() <= encounterRate) {
					GameLogic.clearConsole();
					System.out.println("You've encountered a monster!");
					GameLogic.typeToContinue();
					randomEncounter();
					AdventureGame.getPlayer().resetMovementCounter();
				}
			}
	    }
	}

	// print character sheet
	public static void characterSheet() {
		clearConsole();
		printHyphenHeader("CHARACTER SHEET");
		System.out.println(AdventureGame.getPlayer().name);
		System.out.println("Level: " + AdventureGame.getPlayer().level);
		System.out.println("HP: " + AdventureGame.getPlayer().currentHp + "/" + AdventureGame.getPlayer().maximumHp);
		System.out.println("MP: " + AdventureGame.getPlayer().currentMana + "/" + AdventureGame.getPlayer().maximumMana);
		System.out.println("Strength: " + AdventureGame.getPlayer().strength);
		System.out.println("Dexterity: " + AdventureGame.getPlayer().dexterity);
		System.out.println("Intelligence " + AdventureGame.getPlayer().intelligence);
		System.out.println("EXP: " + AdventureGame.getPlayer().currentExp + "/" + AdventureGame.getPlayer().expToLevel);
		System.out.println("Gold: " + AdventureGame.getPlayer().gold);
		printTildeSeparator(20);

		// print player's upgrades
		AdventureGame.getPlayer().printUpgrades();
		
		System.out.println("(1) View/use consumable items\n(2) View/change equipment\n(3) Go back to map");
		int input = intUserInput("-->", 3);
		
		// player wants to view or use consumable items
		if (input == 1) {
			GameLogic.clearConsole();
			PlayerItems.printPlayerItems();
			System.out.println();
			GameLogic.printHyphenSeparator(20);
			System.out.println();
			PlayerGear.printPlayerGearInInventory();	
			
			// ask player if they'd like to use an item
			PlayerItems.useItem();
		}
		
		// player wants to view/change equipped gear
		// TODO: make most of this a method in PlayerGear.java to reduce redundancy
		else if (input == 2) {
			clearConsole();
	        PlayerGear.printEquippedGear();
	        GameLogic.printHyphenSeparator(20);
	        System.out.println();
	        PlayerGear.printPlayerGearInInventory();
	        System.out.println("Would you like to change a piece of gear you are currently equipping? (Y/N)");
	        String equipDecision = in.nextLine().trim().toUpperCase();
	        
	        // player chooses yes (equip gear)
	        if (equipDecision.trim().toUpperCase().equals("Y")) {
	        	System.out.println("Please type the name of the piece of gear you want to equip. \nNOTE: must type names exactly as written with spaces included");
	        	String equipToPutOn = in.nextLine().trim().toUpperCase();
	        	
	        	// Convert the input into Gear enum
	        	Gear gearToEquip = null;
	        	for (Gear gear : Gear.values()) {
	        		if (gear.getGearName().trim().equalsIgnoreCase(equipToPutOn)) {
	        			GameLogic.clearConsole();
	        			gearToEquip = gear;
	        			GameLogic.clearConsole();
	        			break;
	        		}
	        	}
	        	
	        	// equip the gear is selection is valid, or print error message if it was incorrectly entered
	        	if (gearToEquip == null) {
	        		System.out.println("Invalid gear name. Please check the name and try again.");
	        		GameLogic.typeToContinue();
	        	}
	        	else {
	        		PlayerGear.equipGear(gearToEquip);
	        	}
	        }
	        // player chooses no (do not equip gear)
	        else if (equipDecision.trim().toUpperCase().equals("N")) {
	        	// do nothing
	        }
	        else {
	        	System.out.println("Invalid input. Returning to map.");
	        }	        
		}
		// player wants to continue game
		else {
			GameLogic.clearConsole();
		}
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
