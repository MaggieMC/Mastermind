MASTERMIND

To run Mastermind either open in Eclipse and run the Mastermind.java file or navigate into directory in your terminal ensuring you have the Java compiler, enter javac *.java then java Mastermind to run the game from terminal. 

At the start of the game, the player must select the settings of the game before the game can start.
In this version of Mastermind, numbers are used instead of colours to make the text interface easier to use.
Each number can be considered to represent a different colour.

1.LOAD OR NOT
First, the player is asked to select whether to load a previously saved game or not.
If they enter 1. to signify Yes, the game will automatically start at the point they had saved.
If they enter 2. to signify No, the game will continue to ask the player to select the settings.

2.SELECT MODE
The player must then select what mode they wish to play. 
1.Human vs Human
	Here a Human Player 1 makes up a code and a Human Player 2 attempts to break it.
2.CPU vs Human
	Here the CPU randomly generates a code and a Human Player attempts to break it.
3.CPU vs CPU
	Here the CPU randomly generates a code and the CPU attempts to break it.
	
3.SELECT DIFFICULTY
1.Easy
	The codebreaker must break the code in 10 attempts or less.
2.Medium
	The codebreaker must break the code in 8 attempts or less.
3.Hard
	The codebreaker must break the code in 6 attempts or less.
	
4.SELECT COLOURS
In this case, represented by numbers. This will be the range of numbers (between 3 and 8) that can be used
to generate the code.

5.SELECT NUMBER OF PEGS
This will be the length of the code (between 3 and 8 pegs)

6.MAIN GAME
MODE 1:
Player 1 enters a code while Player 2 looks away. Once the code has been entered, 30 blank lines are printed
so the code is out of sight of Player 2. Player 2 is then prompted to either enter a guess, or enter 's' 
to save the game. This continues until the Player either guesses the correct code, enters 's' which saves the
game and then exits, or fails to guess the code and the number of turns has expired. The correct code is printed
before the game ends.

MODE 2:
This mode is the same as MODE 1, except the CPU generates the code. 

MODE 3:
The CPU randomly generates a code, and the Human must enter 'n' to watch the CPU attempt to break the code,
turn by turn. The Human can also enter 's' any time they are prompted to enter 'n' in order to save and exit
the game. Again, the correct code is printed before the game ends.
