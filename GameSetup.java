/*Interface to define the structure of the game*/
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public interface GameSetup {
	void intro();
	void loadOrNot();
	void chooseMode();
	void chooseDifficulty();
	void startGame() throws IOException;
	void loadDetails() throws FileNotFoundException;
	void mainGame() throws IOException;
	void setParameters();
	ArrayList<Integer> getCode(ArrayList<Integer> code1, String allGuesses1, int m, int n, int colourNo, int pegsNo, int mode, int t, ArrayList<Integer> cpuguess) throws IOException;
	ArrayList<Integer> getGuess(ArrayList<Integer> code1, String allGuesses1, int m, int n, int colourNo, int pegsNo, int mode, int t, ArrayList<Integer> cpuguess) throws IOException;
	void compareCodes();
}
