import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class LoadSave {
	File codetxt = new File("code.txt");
	File guessestxt = new File("guesses.txt");
	File mtxt = new File("m.txt");
	File ntxt = new File("n.txt");
	File cntxt = new File("colourNo.txt");
	File pntxt = new File("pegsNo.txt");
	File modetxt = new File("mode.txt");
	File ttxt = new File("t.txt");
	File cpuguesstxt = new File("cpuguess.txt");
	
	/*When called, this method will save all the given variables into their separate files
	 * The program then exits, and must be restarted when the player wishes to continue*/
	public void saveGame(ArrayList<Integer> code, String allGuesses, int m, int n, int colourNo, int pegsNo, int mode, int t, ArrayList<Integer> cpuguess) throws IOException{
		Scanner scanner = new Scanner(System.in);
			saveCode(code, codetxt);
			saveCode(cpuguess, cpuguesstxt);
			saveGuesses(allGuesses);
			saveInteger(m, mtxt);
			saveInteger(n, ntxt);
			saveInteger(colourNo, cntxt);
			saveInteger(pegsNo, pntxt);
			saveInteger(mode, modetxt);
			saveInteger(t, ttxt);
			System.exit(0);
			scanner.close();
	}
	
	/*Saves an ArrayList<Integer> to a file
	 * Converts its elements into a String and then writes them to the File
	 * @param code
	 * 			ArrayList to be saved to the file
	 * @param f
	 * 			File which the ArrayList is to be saved to*/
	public void saveCode(ArrayList<Integer> code, File f) throws IOException{
		FileWriter fwcode = new FileWriter(f, false);
		BufferedWriter bwcode = new BufferedWriter(fwcode);
		for(int i=0; i<code.size(); i++){
			bwcode.write(code.get(i).toString() + "\n");
		}
		bwcode.close();
	}
	
	/*Saves the String to a File
	 * @param allGuesses
	 * 			the String which contains all guesses and feedback so far*/
	public void saveGuesses(String allGuesses) throws IOException{
		FileWriter fwguesses = new FileWriter(guessestxt, false);
		BufferedWriter bwguesses = new BufferedWriter(fwguesses);
		bwguesses.write(allGuesses);
		bwguesses.close();
	}
	
	/*Saves an Integer to a file after converting it to a String
	 * @param m
	 * 			Integer to be saved to file
	 * @param f
	 * 			File which the integer is to be saved to*/
	public void saveInteger(int m, File f) throws IOException{
		FileWriter fw = new FileWriter(f, false);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(Integer.toString(m));
		bw.close();
	}
	
	/*Loads ArrayList<Integer> from a file
	 * @param code
	 * 			ArrayList into which the loaded integers will be added
	 * @param f
	 * 			File from which the integers will be loaded
	 * @returns ArrayList which was stored in the file*/
	public ArrayList<Integer> loadCode(ArrayList<Integer> code, File f) throws FileNotFoundException{
		Scanner scannerCode = new Scanner(f);
		if(code.isEmpty()==false){
			code.clear(); 
		}
		while(scannerCode.hasNext()){
			code.add(scannerCode.nextInt());
		}
		scannerCode.close();
		code.trimToSize();
		return code;
	}
	
	/*Loads String from a file
	 * @returns String allGuesses that was stored in the file*/
	public String loadGuesses() throws FileNotFoundException{
		Scanner scannerGuesses = new Scanner(guessestxt);
		String guesses = "";
		while(scannerGuesses.hasNextLine()){
			guesses = guesses + scannerGuesses.nextLine() + "\n";
		}
		scannerGuesses.close();
		return guesses;
	}
	
	/*Loads Integer that was stored in file
	 * @param f
	 * 			File from which the Integer will be loaded
	 * @returns Integer that was stored in the file*/
	public Integer loadInteger(File f) throws FileNotFoundException{
		Scanner scannerM = new Scanner(f);
		int m;
		m = scannerM.nextInt();
		scannerM.close();
		return m;
	} 
}
