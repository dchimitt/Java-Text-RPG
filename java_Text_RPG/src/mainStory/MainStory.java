package mainStory;
import com.dchimitt.main.GameLogic;
import java.io.Serializable;

// stores methods to print out sections of main storyline
public class MainStory implements java.io.Serializable {
	public static void printIntroduction() {
		GameLogic.clearConsole();
		GameLogic.printHyphenSeparator(30);
		System.out.println("Welcome, brave soul.");
		System.out.println("PLACEHOLDER");
		System.out.println("PLACEHOLDER");
		System.out.println("PLACEHOLDER");
		GameLogic.typeToContinue();
	}
}
