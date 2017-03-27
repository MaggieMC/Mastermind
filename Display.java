/*Contains main game logic, where all variables needed are updated and other classes are instantiated*/
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Display extends Setup{
	LoadSave loadsave = new LoadSave();
	Human human = new Human();
	Human setup = new Human();
	CPUBreaker cpub = new CPUBreaker();
	Board board = new Board();
	CPU cpumaker = new CPU();
	Feedback fb = new Feedback();
	
	File codetxt = new File("code.txt");
	File mtxt = new File("m.txt");
	File ntxt = new File("n.txt");
	File cntxt = new File("colourNo.txt");
	File pntxt = new File("pegsNo.txt");
	File modetxt = new File("mode.txt");
	File ttxt = new File("t.txt");
	File cpuguesstxt = new File("cpuguess.txt");
	
	int colourNo, pegsNo, mode;
	int n, m; 
	int t = 0; //number of turns
	
	ArrayList<Character> initialFeedback = new ArrayList<Character>();
	ArrayList<Integer> guess = new ArrayList<Integer>();
	ArrayList<Integer> code = new ArrayList<Integer>();
	ArrayList<Integer> editCode = new ArrayList<Integer>();
	ArrayList<Integer> editGuess = new ArrayList<Integer>();
	ArrayList<Integer> cpuguess = new ArrayList<Integer>();
	ArrayList<Integer> cpualter = new ArrayList<Integer>();
	ArrayList<Character> feedback = new ArrayList<Character>(getPegs());
	String allGuesses = "";
	
	Scanner scanner = new Scanner(System.in);
	
	/*Displays the start menu and takes and stores input from the player into the variables*/
	public void startGame() throws IOException{
		intro();
		loadOrNot();
		int load = setup.startChoices(2, 1);
		/*If player wants to load previous game, go to loadDetails() and display board with 
		 * the game that has been played up until now, then continue game*/
		if(load==1){
			loadDetails();
			board.topOfBoard(getColours(), getPegs());
			System.out.print(allGuesses);
			board.unplayedBoard(pegsNo, n);
			mainGame();
		}
		else{
			chooseMode();
			int x = setup.startChoices(3, 1);
			
			chooseDifficulty();
			int y = setup.startChoices(3, 1);
			//If 'Easy' chosen, the player gets 10 turns
			if(y==1){
				m = 10;
				n = 10;
			}
			//If 'Medium' chosen, the player gets 8 turns
			else if(y==2){
				m = 8;
				n = 8;
			}
			//If 'Hard' chosen, the player gets 6 turns
			else if(y==3){
				m = 6;
				n = 6;
			}
			if(x==1){
				mode = 1;
				board();
				mainGame();
			}
			else if(x==2){
				mode = 2;
				board();
				mainGame();
			}
			else if(x==3){
				mode = 3;
				board();
				mainGame();
			}
		}
	}
	
	/*Loads all variables from the files and stores them in variables to be used in the game*/
	public void loadDetails() throws FileNotFoundException{
		code = loadsave.loadCode(code, codetxt);
		code.trimToSize();
		allGuesses = loadsave.loadGuesses();
		m = loadsave.loadInteger(mtxt);
		n = loadsave.loadInteger(ntxt);
		colourNo = loadsave.loadInteger(cntxt);
		pegsNo = loadsave.loadInteger(pntxt);
		mode = loadsave.loadInteger(modetxt);
		t = loadsave.loadInteger(ttxt);
		/*If CPU VS CPU was the last saved game, the last played guess is stored so the 
		 * compareCode() method can continue the CodeBreaker algorithm from when it was stopped*/
		if(mode==3){
			guess = loadsave.loadCode(guess, cpuguesstxt);
		}
	}
	
	/*Asks for and stores the number of colours and pegs*/
	public void setParameters(){
		System.out.println("Choose number of colours (between 3 and 8)");
		colourNo = setup.startChoices(8, 3);
		System.out.println("Choose number of pegs (between 3 and 8)");
		pegsNo = setup.startChoices(8, 3);
	}
	
	/*@returns number of colours chosen by the player*/
	public Integer getColours(){
		return colourNo;
	}
	
	/*@returns number of pegs chosen by the player*/
	public Integer getPegs(){
		return pegsNo;
	}
	
	/*Gets code from either Human player or randomly generated CPU depending on what number mode was set to
	 * @returns an ArrayList<Integer> containing the code to be used in the game*/
	public ArrayList<Integer> getCode(ArrayList<Integer> code1, String allGuesses1, int m, int n, int colourNo, int pegsNo, int mode, int t, ArrayList<Integer> cpuguess) throws IOException{
		if(mode==1){
			code = setup.setCode(code1, allGuesses1, m, n, colourNo, pegsNo, mode, t, cpuguess);
			code.trimToSize();
		}
		else if(mode==2 || mode==3){
			code = cpumaker.setCode(getPegs(), getColours());
			code.trimToSize();
		}
		
		return code;
	}
	
	/*Gets the guess from either the Human player, or the initial randomly generated guess from the CPU CodeBreaker
	 * @returns an ArrayList<Integer> containing the guess*/
	public ArrayList<Integer> getGuess(ArrayList<Integer> code1, String allGuesses1, int m, int n, int colourNo, int pegsNo, int mode, int t, ArrayList<Integer> cpuguess) throws IOException{
		if(mode==1 || mode==2){
			guess = human.setCode(code1, allGuesses1, m, n, colourNo, pegsNo, mode, t, cpuguess);
			guess.trimToSize();
		}
		else if(mode==3){
			guess = cpub.setCode(getPegs(), getColours());
			guess.trimToSize();
		}
		
		return guess;
	}
	
	/*Prints initial blank board and gets code*/
	public void board() throws IOException{
		setParameters();
		System.out.println();
		board.topOfBoard(getColours(), getPegs());
		board.unplayedBoard(pegsNo, n);
		if(mode==1){
			System.out.println("Player 1 please enter code. Player 2 look away!");
		}
		getCode(code, allGuesses, m, n, getColours(), getPegs(), mode, t, guess);
		/*If Human VS Human is chosen, spaces are printed so Player 2 can't see the code*/
		if(mode==1){
			for(int x=0; x<31; x++){
				System.out.println();
			}
		}
	}
	
	/*Copies code into ArrayList<Integer> editCode which is edited during the compareCodes() method*/
	public void editCode(){
		for(int i=0; i<code.size(); i++){
			int x = code.get(i);
			editCode.add(x);
		}
		editCode.trimToSize();
	}
	
	/*Copies guess into ArrayList<Integer> editGuess which is edited uring the compareCodes() method*/
	public void editGuess(){
		for(int i=0; i<guess.size(); i++){
			int x = guess.get(i);
			editGuess.add(x);
		}
		editGuess.trimToSize();
	}
	
	/*ArrayList<Integer> cpuGuess is filled with 0s to start with, and is edited during the compareCodes() method
	 * to create the next guess that the CPU CodeBreaker will try*/
	public void cpuGuess(){
		for(int i=0; i<getPegs(); i++){
			cpuguess.add(0);
		}
		cpuguess.trimToSize();
	}
	
	/*The indices of all the remaining 0s in cpuguess are added to cpualter so that any further alterations
	 * to cpuguess do not affect the numbers that have already been copied into it*/
	public ArrayList<Integer> cpuAlter(ArrayList<Integer> cpuguess){
		if(cpualter.isEmpty()==false){
			cpualter.clear();
		}
		for(int i=0; i<cpuguess.size(); i++){
			if(cpuguess.get(i)==0){
				cpualter.add(i);
			}
		}
		cpualter.trimToSize();
		return cpualter;
	}
	
	/*Here the guess is compared to the code, no matter what mode has been chosen. As this is done, the feedback
	 * is updated, and the CPU CodeBreaker algorithm is at work if the value of mode is 3. 
	 * By the end the feedback is sorted, and the allGuesses string is updated*/
	public void compareCodes(){
		/*All the ArrayLists that will be needed are instantiated*/
		int lengthGuess = getPegs();
		editCode();
		editGuess();
		if(mode==3){
			cpuGuess();
		}
		feedback = fb.initialFeedback(lengthGuess);
		/*In this loop, if the number at guess.get(i) is both contained in the code, and is at the same index,
		 * a 'B' is added to the beginning of the feedback ArrayList and one of the '*' characters is removed.
		 * The number in question is set to 0 in the editCode ArrayList and to 9 in editGuess to avoid repetition.
		 * If the mode is 3, the CPU CodeBreaker algorithm copies this number to cpuguess as we know it is 
		 * part of the code.*/
		for(int i = 0; i< lengthGuess; i++){
			if(code.contains(guess.get(i))==true){
				if(guess.get(i)==editCode.get(i)){
					editCode.set(i, 0);
					if(mode==3){
						cpuguess.set(i, guess.get(i));
					}
					editGuess.set(i, 9);
					feedback.remove((lengthGuess-1));
					feedback.add(0,'B');
				}
			}
		}
		/*cpualter is created*/
		cpuAlter(cpuguess);
		/*This time the editCode and editGuess ArrayLists are compared to each other to avoid repetition
		 * if editGuess.get(i) is contained in editCode, a 'W' is added to feedback and a '*' is removed.
		 * The first instance of the number in editCode is set to 0 to account for multiple instances of the
		 * number in editGuess.
		 * If the mode is 3, the last index stored in cpualter is stored in int s (if i is equal to this index
		 * then the one before is used). The number in question is added at this index to cpuguess, and the 
		 * index is removed from cpualter.*/
		for(int i = 0; i< lengthGuess; i++){
			if(editCode.contains(editGuess.get(i))==true){
				if(mode==3){
					int s = (cpualter.size()-1);
					if(i==cpualter.get(s)){
						s = (cpualter.size()-2);
					}
					cpuguess.set(cpualter.get(s), guess.get(i));
					cpualter.remove(s);
				}
				int index = editCode.indexOf(editGuess.get(i));
				editCode.set(index, 0);
				feedback.remove((lengthGuess-1));
				feedback.add(0,'W');
			}
		}
		/*If mode is 3, all the spaces that have not yet been filled in cpuguess (all the remaining 0s)
		 * are filled by a randomly generated number using the same method that generates the initial CPU guess*/
		if(mode==3){
			for(int i =0; i<cpuguess.size(); i++){
				if(cpuguess.get(i)==0){
					int x = cpub.createGuess(getColours());
					cpuguess.set(i, x);
				}
			}
		}
		/*Feedback is sorted, the feedback and guess ArrayLists are converted to strings and added to allGuesses,
		 * 1 is taken away from n so one less line of the blank board is printed, and all the ArrayLists used
		 * only during this method are cleared again.*/
		fb.sortFeedback(feedback);
		String s = fb.viewAsString(feedback);
		String g = fb.viewAsStringInteger(guess);
		allGuesses = allGuesses+g+"   "+s+"\r\n";
		n = n-1;
		editCode.clear();
		editGuess.clear();
		/*If the code has been guessed, the board is printed with the winning guess and the game terminates*/
		if(s.equals(fb.winningString(getPegs()))){
			board.topOfBoard(getColours(), getPegs());
			System.out.print(allGuesses);
			board.unplayedBoard(getPegs(), n);
			System.out.println("Congratulations, you have guessed the code!");
			System.exit(0);
		}
		if(mode==3){
			for(int i=0; i<cpuguess.size(); i++){
				guess.set(i, cpuguess.get(i));
			}
			cpuguess.clear();
		}
	}
	
	/*The loop that carries out the main game is detailed here
	 * t is the number of turns, which is updated each time the loop is completed and m is the number of rows.*/
	public void mainGame() throws IOException{
		for(int i=t; i<m; i++){
			/*If the mode is CPU vs CPU, 'n' must be entered to continue the loop. If 's' is entered, the game
			 * is saved*/
			if(mode==3){
				System.out.println("Enter 'n' to continue or enter 's' to save");
				while(scanner.hasNext()){
					String s = scanner.next();
					if(s.equals("n")){
						break;
					}
					else if(s.equals("s")){
						loadsave.saveGame(code, allGuesses, m, n, getColours(), getPegs(), mode, t, guess);
					}
					else{
						continue;
					}
				}
			}
			/*If the mode is CPU vs CPU and this is the first turn, the first guess is generated randomly
			 * Otherwise the player is prompted to enter a guess*/
			if(mode==3){
				if(i==0){
					getGuess(code, allGuesses, m, n, getColours(), getPegs(), mode, t, guess);
				}
			}
			else if(mode==1 || mode==2){
				System.out.println("Please enter guess or enter 's' to save");
				getGuess(code, allGuesses, m, n, getColours(), getPegs(), mode, t, guess);
				
			}
			/*The guess is compared to the code, the board containing all guesses so far and remaining
			 * lines is printed, and the number of turns is increased by one*/
			compareCodes();
			board.topOfBoard(getColours(), getPegs());
			System.out.print(allGuesses);
			board.unplayedBoard(getPegs(), n);
			t++;
		}
		scanner.close();
		/*The game reaches this point if the user has run out of turns and has not guessed the code.
		 * The code is printed before the program terminates.*/
		System.out.println("The code was:");
		for(int x=0; x<code.size(); x++){
			System.out.print(code.get(x));
		}
		System.out.println();
		System.out.println();
		
		System.out.println("Game over.");
	}
}