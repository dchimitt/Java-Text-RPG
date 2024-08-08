package com.dchimitt.main;
import java.util.Scanner;
import java.io.Serializable;

public class BattleLogic implements Serializable {
	Scanner in = new Scanner(System.in);
	public static void startBattle(Character enemy) {
		while (true) {
			GameLogic.clearConsole();
			GameLogic.printHyphenHeader(enemy.name + " (level " + enemy.level + ")" + "\nHP: " + enemy.currentHp + "/" + enemy.maximumHp);
			GameLogic.printTildeHeader(AdventureGame.getPlayer().name + " (level " + AdventureGame.getPlayer().level + ")" + "\nHP: " + AdventureGame.getPlayer().currentHp + "/" + AdventureGame.getPlayer().maximumHp);
			System.out.println("(1) Attack\n(2) Abilities\n(3) Offensive Magic\n(4) Support Magic\n(5) Defend\n(6) Use item\n(7) Run");
			int input = GameLogic.intUserInput("--> ", 7);
			
			if (input == 1) {
				int damagePlayerDoes = AdventureGame.getPlayer().attack() - enemy.defend();
				int damagePlayerTakes = enemy.attack() - AdventureGame.getPlayer().defend();
				if (damagePlayerTakes < 0) {
					// adding damage if player defends well
					damagePlayerDoes -= damagePlayerTakes / 2;
					damagePlayerTakes = 0;
				}
				if (damagePlayerDoes < 0)
					damagePlayerDoes = 0;
				AdventureGame.getPlayer().currentHp -= damagePlayerTakes;
				enemy.currentHp -= damagePlayerDoes;

				// print information to show what occurred this round
				GameLogic.clearConsole();
				GameLogic.printHyphenHeader("BATTLE ROUND INFORMATION");
				System.out.println("You dealt " + damagePlayerDoes + " damage to the " + enemy.name + ".");
				System.out.println("The " + enemy.name + " dealt " + damagePlayerTakes + " damage to you.");
				if (AdventureGame.getPlayer().currentHp <= 0) {
					GameLogic.playerIsDead();
					break;
				} 
				else if (enemy.currentHp <= 0) {
					// show round information before player wins
					GameLogic.clearConsole();
					GameLogic.printHyphenHeader("BATTLE ROUND INFORMATION");
					System.out.println("You dealt " + damagePlayerDoes + " damage to the " + enemy.name + ".");
					System.out.println("The " + enemy.name + " dealt " + damagePlayerTakes + " damage to you.");
					
					// player wins the battle
					GameLogic.printTildeSeparator(30);
					System.out.println("You defeated the " + enemy.name + "!");
					// TODO: edit experience values later
					int experienceGained = enemy.level + 2;
					AdventureGame.getPlayer().currentExp += experienceGained;
					System.out.println("You earned " + experienceGained + " experience points!");
					GameLogic.didPlayerLevelUp();
					GameLogic.typeToContinue();
					break;
				}
			} 
			else if (input == 2) {
				AdventureGame.getPlayer().useAbility();
			} 
			else if (input == 3) {
				AdventureGame.getPlayer().useMagic();
			} 
			else if (input == 4) {
				AdventureGame.getPlayer().useMagic();
			} 
			else if (input == 5) {
				AdventureGame.getPlayer().defend();
			} 
			else if (input == 6) {
				// useItem();
			} 
			else {
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
					if (AdventureGame.getPlayer().currentHp <= 0) 
						GameLogic.playerIsDead();
				}
			}
			GameLogic.typeToContinue();
		}
	}
}