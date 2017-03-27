/*Extends CPU, contains method that is used in CodeBreaker algorithm*/
import java.util.Random;

public class CPUBreaker extends CPU{
	/*Generates a random number from the range ArrayList
	 * @param colours
	 * 			the number of colours selected by the player
	 * @return random Integer x from chosen range*/
	public Integer createGuess(Integer colours){
		range(colours);
		Random random = new Random();
		int j = random.nextInt(colours);
		int x = range.get(j);
		return x;
	}
}