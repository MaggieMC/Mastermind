/*Sets up the basic structure of the playing board*/

public class Board implements BoardStructure{
	/*Prints the amount of colours to be used.
	 * @param colourNo 
	 * 			the number of colours selected by the player*/
	public void printColours(int colours){
		int x = 1;
		for(int i=0; i<colours; i++){
			System.out.print(x + " ");
			x++;
		}
	}
	
	/*Prints stars to represent the hidden code
	 * @param pegsNo
	 * 			the number of pegs selected by the player*/
	public void hiddenCode(int pegs){
		for(int i=0; i<pegs; i++){
			System.out.print("* ");
		}
	}
	
	/*Prints the top of the board - number of colours and hidden code
	 * @param colourNo
	 * 			the number of colours selected by the player
	 * @param pegsNo
	 * 			the number of pegs selected by the player*/
	public void topOfBoard(int colours, int pegs){
		System.out.println();
		printColours(colours);
		System.out.println();
		System.out.println();
		hiddenCode(pegs);
		System.out.println();
	}
	
	/*Prints the unplayed board - changes after each turn
	 * @param pegsNo
	 * 			the number of pegs selected by the player
	 * @param n
	 * 			n is lessened by one each time the codes are compared, meaning that it tracks
	 * 			the number of turns*/
	public void unplayedBoard(int pegs, int n){
		for(int i = 0; i<n; i++){
			for(int y=0; y<pegs; y++){
				System.out.print("_ ");
			}
			System.out.print("   ");
			for(int y=0; y<pegs; y++){
				System.out.print("*");
			}
			System.out.println();
		}
		System.out.println();
	}
}
