/*Defines the methods to be used by Human players - both CodeMakers and CodeBreakers*/
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;

import java.util.Scanner;

public class Human {
	Scanner scanner = new Scanner(System.in);
	LoadSave loadsave = new LoadSave();
	
	/*Deals with command line input from Human players - both CodeMakers and CodeBreakers. Also allows the
	 * input of 's' in order to save the game
	 * The following parameters are here so that they can be entered into the save method if 's' is entered
	 * @param code
	 * @param allGuesses
	 * @param m
	 * @param n
	 * @param colourNo
	 * 			the number of colours selected by player, in order to ensure the number entered is within range
	 * @param pegsNo
	 * @param mode
	 * @param t
	 * @param cpuguess
	 * @return Integer input from the player*/
	public Integer enterCode(ArrayList<Integer> code, String allGuesses, int m, int n, int colourNo, int pegsNo, int mode, int t, ArrayList<Integer> cpuguess) throws IOException {
		int x = 0;
		if(scanner.hasNext("s")){
			loadsave.saveGame(code, allGuesses, m, n, colourNo, pegsNo, mode, t, cpuguess);
		}
		while(x==0){
			try{
				x = scanner.nextInt();
			}
			catch(InputMismatchException ime){
				System.err.println("Invalid input. Please start again");
				System.exit(0);
			}
		}
		if(x<1 || x>colourNo){
			System.err.println("Invalid input. Please start again");
			System.exit(0);
		}
		return x; 
	}
	
	/*Allows Human player to select the options at the start of the game
	 * @param a
	 * 			max value that x can be
	 * @param b
	 * 			min value that x can be
	 * @return Integer input from player*/
	public Integer startChoices(int a, int b){
		int x = 0;
		while(x==0){
			try{
				x = scanner.nextInt();
			}
			catch(InputMismatchException ime){
				System.err.println("Invalid input. Please start again");
				System.exit(0);
			}
		}
		if(x>a || x<b){
			System.err.println("Invalid input. Please start again");
			System.exit(0);
		}
		return x;
	}
	
	
	/*Constructs ArrayList<Integer> from player input - either the code or the guess
	 * The following parameters are here so that they can be entered into the save method if 's' is entered
	 * @param code
	 * @param allGuesses
	 * @param m
	 * @param n
	 * @param colourNo
	 * @param pegsNo
	 * 			the number of pegs chosen by the player, to ensure the array is the right size
	 * @param mode
	 * @param t
	 * @param cpuguess
	 * @return Integer input from the player*/
	public ArrayList<Integer> setCode(ArrayList<Integer> code, String allGuesses, int m, int n, int colourNo, int pegsNo, int mode, int t, ArrayList<Integer> cpuguess) throws IOException{
		ArrayList<Integer> guess = new ArrayList<Integer>(pegsNo);
		while(scanner.hasNext()){
			guess.add(enterCode(code, allGuesses, m, n, colourNo, pegsNo, mode, t, cpuguess));
			if(guess.size()==pegsNo){
				break;
			}
		}
		return guess;
	}
}
