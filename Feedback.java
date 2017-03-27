/*Class contains methods that help create and edit the feedback string used in compareCodes() in Display*/
import java.util.ArrayList;

public class Feedback {
	/*Creates ArrayList of blank feedback that will be edited in compareCode() in Display
	 * @param pegs
	 * 			the number of pegs chosen by the player
	 * @returns ArrayList full of as many '*' as the number of pegs*/
	public ArrayList<Character> initialFeedback(Integer pegs){
		ArrayList<Character> initialFeedback = new ArrayList<Character>(pegs);
		for(int i=0; i<pegs; i++){
			initialFeedback.add('*');
		}
		initialFeedback.trimToSize();
		return initialFeedback;
	}
	
	/*Creates the winning String, so the resulted feedback can be compared to this to check
	 * if the player has won or not
	 * @param pegs
	 * 			the number of pegs selected by the player
	 * @returns the winning String*/
	public String winningString(int pegs){
		String s = "";
		for(int i=0; i<pegs; i++){
			s = s+ "B";
		}
		return s;
	}
	
	/*Converts ArrayList<Character> to String
	 * @param feedback
	 * 			ArrayList<Character> to be converted to a String
	 * @returns feedback as a String*/
	public String viewAsString(ArrayList<Character> feedback){
		StringBuilder s = new StringBuilder(feedback.size());
		for(Character c:feedback){
			s.append(c);
		}
		return s.toString();
	}
	
	/*Converts ArrayList<Integer> to String
	 * @param
	 * 			ArrayList<Integer> to be converted to String
	 * @returns guess as a String*/
	public String viewAsStringInteger(ArrayList<Integer> guess){
		StringBuilder s = new StringBuilder(guess.size());
		for(Integer i:guess){
			s.append(i);
			s.append(" ");
		}
		return s.toString();
	}
	
	/*Sorts the feedback ArrayList so that all 'B' are displayed first, followed by
	 * 'W' and finally '*' 
	 * @param feedback
	 * 			ArrayList<Character> to be sorted
	 * @returns sorted feedback*/
	public ArrayList<Character> sortFeedback(ArrayList<Character> feedback){
		for(int i=0; i<feedback.size(); i++){
			if(feedback.get(i)=='B'){
				feedback.remove(i);
				feedback.add(0,'B');
			}
		}
		return feedback;
	}
}