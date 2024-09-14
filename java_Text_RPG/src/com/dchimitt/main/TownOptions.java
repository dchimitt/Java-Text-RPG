package com.dchimitt.main;

import java.util.Scanner;
import gearAndItems.GearVendor;
import gearAndItems.ItemVendor;
import gearAndItems.PlayerGear;
import gearAndItems.PlayerItems;
import maps.ActOneMap;
import maps.ActOneMap.Direction;

public class TownOptions {
	static Scanner in = new Scanner(System.in);
	
	public static void townOptions() {
		GameLogic.clearConsole();
		boolean inTown = true;
		ItemVendor itemVendor;
		GearVendor gearVendor;
		GameLogic.lastTownVisited = ActOneMap.getCurrentPlayerPosition();
		while (inTown) {
			System.out.println("You are in " + GameLogic.lastTownVisited.getName() + "\nOPTIONS:");
			System.out.println("(1) Rest at inn\n(2) Item vendor\n(3) Gear vendor\n(4) Talk to NPC\n(5) Character sheet\n(6) Save game\n(7) Save and quit\n(8) Leave town");
			int input = GameLogic.intUserInput("-->", 8);
			
			// Player wants to rest at inn
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
			
			// Player visits item vendor
			// TODO: change to do-while loop to prevent exit if player does not type B/S
			// TODO: add formatting so text aligns for readability
			// TODO: add selling functionality
			// TODO: minimize redundant code
			else if (input == 2) {
				GameLogic.clearConsole();
				System.out.println("Would you like to buy or sell items? (B/S)");
				String buyOrSellItemsDecision = in.nextLine().trim().toUpperCase();
				
				// Player chooses to buy items 
				if (buyOrSellItemsDecision.equals("B")) {
					boolean keepPurchasingItems = true;
					while (keepPurchasingItems) {
						GameLogic.clearConsole();
						if (GameLogic.lastTownVisited.getName().equals("Town of Reizart:")) {
							itemVendor = ActOneMap.getReizartItemVendor();
							itemVendor.displayItems();
							int itemChoice = GameLogic.intUserInput("-->", 4);
							switch (itemChoice) {
								case 1:
									GameLogic.clearConsole();
									ItemVendor.purchaseItem(PlayerItems.Items.WEAK_HEALING_POTION);
									break;
								case 2:
									GameLogic.clearConsole();
									ItemVendor.purchaseItem(PlayerItems.Items.WEAK_MANA_POTION);
									break;
								case 3:
									GameLogic.clearConsole();
									ItemVendor.purchaseItem(PlayerItems.Items.ANTIDOTE);
									break;
								case 4:
									break;
							}
							// Second break to prevent asking player to purchase another item if selecting return to town option
							if (itemChoice == 4)
								break;
						}
						
						// TODO: add more item vendors in each town here as necessary
						
						System.out.println("Would you like to purchase another item? (Y/N)");
						String purchaseDecision = in.nextLine().trim().toUpperCase();
						if (!purchaseDecision.equals("Y"))
							keepPurchasingItems = false;
						GameLogic.clearConsole();
					}
					GameLogic.clearConsole();
				}
				else if (buyOrSellItemsDecision.equals("S")) {					
					GameLogic.clearConsole();
					boolean keepSellingItems = true;
					while (keepSellingItems) {
					    System.out.println("Please type the name of the item you'd like to sell exactly as written!");
						System.out.println();
						
						// Show player their inventory of items and get input for the item to sell
						PlayerItems.printPlayerItems();						
						String itemToSellDecision = in.nextLine().trim().toUpperCase();
			            GameLogic.clearConsole();

			            // Find the typed item in the Item enum and check if the player has at least one of that item
			            PlayerItems.Items itemToSell = null;
			            for (PlayerItems.Items item : PlayerItems.Items.values()) {
			            	if (item.getItemName().trim().equalsIgnoreCase(itemToSellDecision)) {
			            		itemToSell = item;
			            		break;
			            	}
			            }
			            
			            // Player has at least one of the selected item to sell
			            if (itemToSell != null) {
			            	int currentItemQuantity = PlayerItems.getItemQuantities().getOrDefault(itemToSell, 0);
			            	
			            	if (currentItemQuantity > 0) {
			            		int sellingPrice = itemToSell.getItemSellingPrice();
			            		
			            		// Add the selling price of item to player's gold
			            		AdventureGame.getPlayer().gold += sellingPrice;
			            		// TODO: print player's gold after each item sold
			            		
			            		// Decrease quantity of the item by 1 and tell player how many remain
			            		PlayerItems.decreaseItemQuantity(itemToSell);
			            		System.out.println("You now have " + (currentItemQuantity - 1) + " " + itemToSell.getItemName() + "(s) left.");
			            		
			            		// Ask player if they would like to sell another item
			            		System.out.println("Would you like to sell another item? (Y/N)");
			            		String sellMoreItemsDecision = in.nextLine().trim().toUpperCase();
			            		if (!sellMoreItemsDecision.equals("Y")) {
			            			keepSellingItems = false;
			            			GameLogic.clearConsole();
			            		}
			            		else 
			            			GameLogic.clearConsole();
			            		}
			            	
			            	// Player tries to sell an item they no longer have at least one of
			            	else {
			            		System.out.println("Item was not found in your inventory.");
			            		GameLogic.typeToContinue();
			            		keepSellingItems = false;
			            	}
			            	
			            	GameLogic.clearConsole();
			            }
			            
			            // Player misspells item
			            else {
			            	System.out.println("No such item exists (please check spelling and try again.");
			            	GameLogic.typeToContinue();
			            	break;
			            }
					}
				}
				else {
					GameLogic.clearConsole();
					System.out.println("You don't want to buy or sell items? Quit wasting my time kid!\nNow returning to town menu...");
					GameLogic.typeToContinue();
				}
				GameLogic.clearConsole();
			}
			
			// Player visits gear vendor
			// TODO: change to do-while loop to prevent exit if player does not type B/S
			// TODO: add formatting so text aligns for readability
			// TODO: add selling functionality
			// TODO: minimize redundant code
			else if (input == 3) {
				GameLogic.clearConsole();
				System.out.println("Would you like to buy or sell gear? (B/S)");
				String buyOrSellGearDecision = in.nextLine().trim().toUpperCase();
				if (buyOrSellGearDecision.equals("B")) {
					boolean keepPurchasingGear = true;
					while (keepPurchasingGear) {
						GameLogic.clearConsole();
						if (GameLogic.lastTownVisited.getName().equals("Town of Reizart:")) {
							gearVendor = ActOneMap.getReizartGearVendor();
							gearVendor.displayGear();
							int gearChoice = GameLogic.intUserInput("-->", 7);
							switch (gearChoice) {
								case 1:
									GameLogic.clearConsole();
									GearVendor.purchaseGear(PlayerGear.Gear.STARTING_SWORD);
									break;
								case 2:
									GameLogic.clearConsole();
									GearVendor.purchaseGear(PlayerGear.Gear.STARTING_DAGGER);
									break;
								case 3:
									GameLogic.clearConsole();
									GearVendor.purchaseGear(PlayerGear.Gear.STARTING_WAND);
									break;
								case 4:
									GameLogic.clearConsole();
									GearVendor.purchaseGear(PlayerGear.Gear.STARTING_CHAINMAIL);
									break;
								case 5:
									GameLogic.clearConsole();
									GearVendor.purchaseGear(PlayerGear.Gear.STARTING_VEST);
									break;
								case 6:
									GameLogic.clearConsole();
									GearVendor.purchaseGear(PlayerGear.Gear.STARTING_ROBE);
									break;
								case 7:
									break;
							}
							// Second break to prevent asking player to purchase another piece of gear if selecting return to town option
							if (gearChoice == 7)
								break;
						}
						System.out.println("Would you like to purchase another piece of gear? (Y/N)");
						String decision = in.nextLine().trim().toUpperCase();
						if (!decision.equals("Y"))
							keepPurchasingGear = false;
						GameLogic.clearConsole();
					}
					GameLogic.clearConsole();
				}
				else if (buyOrSellGearDecision.equals("S")) {
					// implement gear selling logic here
					boolean keepSellingGear = true;
					GameLogic.clearConsole();
				}
				else {
					GameLogic.clearConsole();
					System.out.println("You don't want to buy or sell gear? Quit wasting my time kid!\nNow returning to town menu...");
					GameLogic.typeToContinue();
				}
				GameLogic.clearConsole();
			}
			
			// Player talks to NPC
			else if (input == 4) {
				GameLogic.clearConsole();
				GameLogic.talkToNPC();
			}
			
			// Player prints character sheet
			else if (input == 5) {
				GameLogic.characterSheet();
			}
			
			// Player saves game
			else if (input == 6) {
				SaveLoadLogic.saveGame();
				GameLogic.clearConsole();
			}
			
			// Player saves and exits game
			else if (input == 7) {
				SaveLoadLogic.saveGame();
				GameLogic.isRunning = false;
				break;
			}
			
			// Player leaves town in chosen direction
			else {
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
