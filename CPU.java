/*Class defines the methods to be used by the CPU CodeMaker and CPU CodeBreaker*/

import java.util.ArrayList;
import java.util.Random;

public class CPU {
	ArrayList<Integer> range = new ArrayList<Integer>();
	
	/*Creates ArrayList with the number of colours selected by player
	 * to use in creating random numbers
	 * @param colours
	 * 			the number of colours selected by the player
	 * @return ArrayList of colours*/
	public ArrayList<Integer> range(Integer colours){
		
		for(int i=1; i<=colours; i++){
			range.add(i);
		}
		range.trimToSize();
		return range;
	}
	
	/*Creates code by selecting the next random number between 0-(colours-1), and using
	 * this as an index from which to select the next number from the range ArrayList.
	 * Also used to generate the first guess by the CPU Breaker
	 * @param pegs 
	 * 			the number of pegs selected by the player
	 * @param colours
	 * 			the number of colours selected by the player
	 * @return ArrayList of randomly generated numbers from the chosen range*/
	
	public ArrayList<Integer> setCode(Integer pegs, Integer colours){
		range(colours);
		ArrayList<Integer> code = new ArrayList<Integer>(pegs);
		Random random = new Random();
		for(int i=0; i<pegs; i++){
			int j = random.nextInt(colours);
			int x = range.get(j);
			code.add(x);
		}
		return code;
	}
}