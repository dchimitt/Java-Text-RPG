package com.dchimitt.main;
import java.util.Scanner;

public class BattleLogic {
	static Scanner in = new Scanner(System.in);
	public static void startBattle(Character enemy) {
		while (true) {
			GameLogic.clearConsole();
			GameLogic.printHyphenHeader(enemy.name + " (level " + enemy.level + ")" + "\nHP: " + enemy.currentHp + "/" + enemy.maximumHp);
			GameLogic.printTildeHeader(GameLogic.player.name + " (level " + GameLogic.player.level + ")" + "\nHP: " + GameLogic.player.currentHp + "/" + GameLogic.player.maximumHp);
			System.out.println("(1) Attack\n(2) Abilities\n(3) Offensive Magic\n(4) Support Magic\n(5) Defend\n(6) Use item\n(7) Run");
			int input = GameLogic.intUserInput("--> ", 7);
			
			if (input == 1) {
				int damagePlayerDoes = GameLogic.player.attack() - enemy.defend();
				int damagePlayerTakes = enemy.attack() - GameLogic.player.defend();
				if (damagePlayerTakes < 0) {
					// adding damage if player defends well
					damagePlayerDoes -= damagePlayerTakes / 2;
					damagePlayerTakes = 0;
				}
				if (damagePlayerDoes < 0)
					damagePlayerDoes = 0;
				GameLogic.player.currentHp -= damagePlayerTakes;
				enemy.currentHp -= damagePlayerDoes;

				// print information to show what occurred this round
				GameLogic.clearConsole();
				GameLogic.printHyphenHeader("BATTLE");
				System.out.println("You dealt " + damagePlayerDoes + " damage to the " + enemy.name + ".");
				System.out.println("The " + enemy.name + " dealt " + damagePlayerTakes + " damage to you.");
				if (GameLogic.player.currentHp <= 0) {
					GameLogic.playerIsDead();
					break;
				} else if (enemy.currentHp <= 0) {
					// show round information before player wins
					GameLogic.clearConsole();
					GameLogic.printHyphenHeader("BATTLE");
					System.out.println("You dealt " + damagePlayerDoes + " damage to the " + enemy.name + ".");
					System.out.println("The " + enemy.name + " dealt " + damagePlayerTakes + " damage to you.");
					
					// player wins the battle
					GameLogic.printTildeSeparator(20);
					System.out.println("You defeated the " + enemy.name + "!");
					GameLogic.player.currentExp += enemy.strength; // TODO Fix exp gained later
					System.out.println("You earned " + enemy.currentExp + " experience points!");
					GameLogic.typeToContinue();
					break;
				}
			} else if (input == 2) {
				GameLogic.player.useAbility();
			} else if (input == 3) {
				GameLogic.player.useMagic();
			} else if (input == 4) {
				GameLogic.player.useMagic();
			} else if (input == 5) {
				GameLogic.player.defend();
			} else if (input == 6) {
				// useItem();
			} else {
				GameLogic.clearConsole();

				// TODO: potentially add scaling % chance as the number of successful fights in a row
				// 50% chance to run away from fight
				if (Math.random() <= 0.5) {
					GameLogic.printHyphenHeader("You ran away from the " + enemy.name + "!");
					GameLogic.typeToContinue();
					break;
				} else {
					GameLogic.printHyphenHeader("You didn't manage to escape.");
					// TODO: add random variation to damage taken
					int failedEscapeDamage = enemy.attack() / 2;
					System.out.println("The enemy strikes your back for " + failedEscapeDamage + " damage!");
					if (GameLogic.player.currentHp <= 0) 
						GameLogic.playerIsDead();
				}
			}
			GameLogic.typeToContinue();
		}
	}
}