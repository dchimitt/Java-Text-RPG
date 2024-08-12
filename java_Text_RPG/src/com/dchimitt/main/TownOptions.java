package com.dchimitt.main;

import java.util.Scanner;
import gearAndItems.GearVendor;
import gearAndItems.ItemVendor;
import gearAndItems.PlayerGear;
import gearAndItems.PlayerItems;
import maps.ActOneMap;
import maps.ActOneMap.Direction;

public class TownOptions {
	public static void townOptions() {
		Scanner in = new Scanner(System.in);
		GameLogic.clearConsole();
		boolean inTown = true;
		ItemVendor itemVendor;
		GearVendor gearVendor;
		GameLogic.lastTownVisited = ActOneMap.getCurrentPlayerPosition();
		while (inTown) {
			System.out.println("You are in " + GameLogic.lastTownVisited.getName() + "\nOPTIONS:");
			System.out.println("(1) Rest at an inn\n(2) Item vendor\n(3) Gear vendor\n(4) Character sheet\n(5) Talk to NPC\n(6) Leave town");
			int input = GameLogic.intUserInput("-->", 6);
			// TODO implement methods
			if (input == 1) {
				if (ActOneMap.getCurrentPlayerPosition().getName().equals("Town of Reizart:")) {
					GameLogic.clearConsole();
					System.out.println("Innkeeper:");
					System.out.println("Hey, " + AdventureGame.getPlayer().name + "! You can rest for free at your house! I couldn't live with myself if I charged you for a night here!");
					GameLogic.typeToContinue();
					GameLogic.clearConsole();
				}
				else {
					GameLogic.clearConsole();
					GameLogic.wantsToRestAtInn();
					GameLogic.clearConsole();
				}
			} 
			// player visits item vendor
			// TODO: add functionality to make multiple purchases at a time before leaving menu
			// TODO: add selling functionality
			else if (input == 2) {
				GameLogic.clearConsole();
				if (GameLogic.lastTownVisited.getName().equals("Town of Reizart:")) {
					itemVendor = ActOneMap.getReizartItemVendor();
					itemVendor.displayItems();
					int itemChoice = GameLogic.intUserInput("-->", 4);
					switch (itemChoice) {
						case 1:
							itemVendor.purchaseItem(PlayerItems.Items.WEAK_HEALING_POTION);
							GameLogic.typeToContinue();
							break;
						case 2:
							itemVendor.purchaseItem(PlayerItems.Items.WEAK_MANA_POTION);
							GameLogic.typeToContinue();
							break;
						case 3:
							itemVendor.purchaseItem(PlayerItems.Items.ANTIDOTE);
							GameLogic.typeToContinue();
							break;
						case 4:
							break;
					}
				}
				GameLogic.clearConsole();
			} 
			// player visits gear vendor
			// TODO: add functionality to make multiple purchases at a time before leaving menu
			// TODO: add selling functionality
			else if (input == 3) {
				GameLogic.clearConsole();
				if (GameLogic.lastTownVisited.getName().equals("Town of Reizart:")) {
					gearVendor = ActOneMap.getReizartGearVendor();
					gearVendor.displayGear();
					int gearChoice = GameLogic.intUserInput("-->", 7);
					switch (gearChoice) {
						case 1:
							gearVendor.purchaseGear(PlayerGear.Gear.STARTING_SWORD);
							GameLogic.typeToContinue();
							break;
						case 2:
							gearVendor.purchaseGear(PlayerGear.Gear.STARTING_DAGGER);
							GameLogic.typeToContinue();
							break;
						case 3:
							gearVendor.purchaseGear(PlayerGear.Gear.STARTING_WAND);
							GameLogic.typeToContinue();
							break;
						case 4:
							gearVendor.purchaseGear(PlayerGear.Gear.STARTING_CHAINMAIL);
							GameLogic.typeToContinue();
							break;
						case 5:
							gearVendor.purchaseGear(PlayerGear.Gear.STARTING_VEST);
							GameLogic.typeToContinue();
							break;
						case 6:
							gearVendor.purchaseGear(PlayerGear.Gear.STARTING_ROBE);
							GameLogic.typeToContinue();
							break;
						case 7:
							break;
					}
				}
				GameLogic.clearConsole();
			} 
			else if (input == 4) {
				GameLogic.characterSheet();
			}
			else if (input == 5) {
				GameLogic.clearConsole();
				GameLogic.talkToNPC();
			} 
			else {
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
				GameLogic.clearConsole();
				boolean validDirection = false;
				do {
					System.out.println("Which direction would you like to leave town in?");
					System.out.println("N, S, E, or W?");
					String townExitInput = in.nextLine().trim().toUpperCase();
					if (townExitInput.equals("N")) {
						ActOneMap.movePlayerTo(Direction.NORTH);
						validDirection = true;
					}
					else if (townExitInput.equals("S")) {
						ActOneMap.movePlayerTo(Direction.SOUTH);
						validDirection = true;
					}
					else if (townExitInput.equals("E")) {
						ActOneMap.movePlayerTo(Direction.EAST);
						validDirection = true;
					}
					else if (townExitInput.equals("W")) {
						ActOneMap.movePlayerTo(Direction.WEST);
						validDirection = true;
					}
				} while (!validDirection);
				inTown = false;
				GameLogic.clearConsole();
				GameLogic.checkAct();
				ActOneMap.printPlayerPosition();
				System.out.println("Type N, S, E, or W to move in a direction.");
			}
		}
	}
}
