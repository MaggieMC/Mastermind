/*Implements GameSetup interface and defines the initial methods which are used at the start of the game
 * to define parameters. Also provides some methods used in Display for calculations*/
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public abstract class Setup implements GameSetup{
	/*Prints intro to game*/
	public void intro(){
		System.out.println("In this version of Mastermind the colours will be represented by numbers");
		System.out.println();
		System.out.println();
	}
	
	/*Prints menu asking player whether they want to load a previously saved game or not*/
	public void loadOrNot(){
		System.out.println("Enter the corresponding number to select the following options.");
		System.out.println("Would you like to load a previously saved game?");
		System.out.println("1: Yes");
		System.out.println("2: No");
	}
	
	/*Prints menu asking player to choose mode*/
	public void chooseMode(){
		System.out.println("Choose which mode you would like to play by entering the corresponding number:");
		System.out.println("1: Human VS Human");
		System.out.println("2: CPU VS Human");
		System.out.println("3: CPU VS CPU");
	}
	
	/*Prints menu asking player to choose difficulty*/
	public void chooseDifficulty(){
		System.out.println("Choose the level of difficulty:");
		System.out.println("1: Easy");
		System.out.println("2: Medium");
		System.out.println("3: Hard");
	}
	
	public abstract void startGame() throws IOException;
	public abstract void loadDetails() throws FileNotFoundException;
	public abstract void mainGame() throws IOException;
	public abstract void setParameters();
	public abstract ArrayList<Integer> getCode(ArrayList<Integer> code1, String allGuesses1, int m, int n, int colourNo, int pegsNo, int mode, int t, ArrayList<Integer> cpuguess) throws IOException;
	public abstract ArrayList<Integer> getGuess(ArrayList<Integer> code1, String allGuesses1, int m, int n, int colourNo, int pegsNo, int mode, int t, ArrayList<Integer> cpuguess) throws IOException;
	public abstract void compareCodes();
}
