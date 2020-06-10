/*
 * JungBok Cho
 * Tic Tac Toe game in Java
 */
import java.util.Scanner;

/**
 * This is a program to play the game Tic-Tac-Toe.
 * 
 * @author  JungBok Cho
 * @version 1.0
 */
public class P1 {
	
	 private static int winX = 0,		// To count how many times X wins
			    winO = 0,		// To count how many times O wins
			    tie = 0,		// TO count how many times nobody wins
			    putRow,		// To hold a value of row
			    putCol;		// To hold a value of column
	
	
	/**
	 * Create an object of TicTacToe class and 
	 * use methods in the class to play the game.
	 * 
	 * @param args A string array containing the command line arguments.
	 */
	public static void main(String[] args) {
		final String YES = "y";  // Constant to repeat the program
		String again;		 // Variable to repeat the game
		int boardSize;		 // To hold the board size
		int gameAfterGame = 0;	 // To change the player
		char winner;  		 // To hold a value of winner
		
		System.out.println("\nWelcome to TicTacToe!\n");
		
		// Create Scanner object
		Scanner keyboard = new Scanner(System.in);
		
		// Check whether user wants to repeat the program or not
		do {
			// Call chooseSize method to get board size from user
			boardSize = chooseSize(keyboard);
			System.out.println();
			
			// Create TicTacToe object
			TicTacToe tic = new TicTacToe(boardSize);
			
			// Change the player when new game starts
			if((gameAfterGame++ % 2) != 0) {
				tic.switchTurn();
			}
			
			// Call playGame method and assign it
			winner = playGame(keyboard, tic, boardSize);
			
			// Call winnerList method
			winnerList(winner);
			
			// Ask if user wants to play again
			System.out.print("Do you want to play again? (y/n) ");
			again = keyboard.nextLine();
			System.out.println();
		} while(again.equalsIgnoreCase(YES));
		
		System.out.println("Thank you for playing TicTacToe!");
		
		// Close Scanner
		keyboard.close();
	}
	
	
	/**
	 * Get the board size from user
	 * 
	 * @param keyboard  To prompt the user to enter the board size
	 * @return  Return the board size
	 */
	private static int chooseSize(Scanner keyboard) {
		int size;		// To hold a value of the board size
		final int MIN = 3;	// To hold a value of minimum range
		final int MAX = 25;	// To hold a value of maximum range
		
		System.out.println("You can choose the size of the board."
				+ "\nThe samllest is 3 x 3 and the largest is 25 x 25.\n"
				+ "The board must be an odd-sized board.\n");
		
		// Check whether what user inputs is an odd number
		// in the range between 3 and 25
		do {
			System.out.print("Enter the size you want: ");
			size = keyboard.nextInt();
			
			if(size < MIN || size > MAX || size % 2 == 0) {
				System.out.println("You cannot create " + size + " x " + size + 
						" board. Try again...\n");
			}
		} while(size < MIN || size > MAX || size % 2 == 0);
		
		return size;
	}
	
	
	/**
	 * Play the game
	 * 
	 * @param keyboard 	To prompt the user to enter row and column
	 * @param tic		TicTacToe object
	 * @param boardSize	The board size
	 * @return  Return the winner. If there is no winner, return just space 
	 */
	private static char playGame(Scanner keyboard, TicTacToe tic, int boardSize) {
		char winner;		// To hold a value of winner
		int count;		// To count how many times users played
		
		count = 0;		// To reset the count
		winner = ' ';		// To reset the winner
		tic.setGame();		// To reset the board
		
		// Check if there is a winner
		do {
			// Call printBoard method
			tic.printBoard();
			
			// Call checkOpen method
			checkOpen(keyboard, tic, boardSize);
			
			// Call updateBoard
			tic.updateBoard(putRow, putCol);
			
			// Do not check if no winner possible
			if(count++ >= boardSize * 2 - 2) {
				// Call checkWin method and assign it
				winner = tic.checkWin();
			}
			// Call switchTurn method to change the turn
			tic.switchTurn();
		} while(winner == ' ' && count <= boardSize * boardSize - 1);
		
		// Remove input buffer
		keyboard.nextLine();
		
		return winner;
	}
	
	
	/**
	 * Check if the position user wants is open or not
	 * 
	 * @param keyboard	To prompt the user to enter row and column
	 * @param tic		TicTacToe object
	 * @param boardSize	The board size
	 */
	private static void checkOpen(Scanner keyboard, TicTacToe tic, int boardSize) {
		boolean isOpen;	// To check if the position is open
		
		do {
			checkRowColLocation(keyboard, tic, boardSize);
					
			// call openSpot method and assign it
			isOpen = tic.openSpot(putRow, putCol);
					
			// Print this if the position is not open
			if(!isOpen) {
				System.out.println("Bad location, try again...\n");
			}
		} while(!isOpen);
	}
	
	
	/**
	 * Check whether what user inputs is in the range 
	 * between 0 and the board size - 1
	 * 
	 * @param keyboard	To prompt the user to enter row and column
	 * @param tic		TicTacToe object
	 * @param boardSize	The board size
	 */
	private static void checkRowColLocation(Scanner keyboard, TicTacToe tic, int boardSize) {
		do {
			// Call turn method
			tic.turn();
			
			// Ask user to input row and column
			System.out.print("Which row? ");
			putRow = keyboard.nextInt();
			System.out.print("Which column? ");
			putCol = keyboard.nextInt();
			
			// Print this if what user inputs is not in the range
			if(putRow < 0 || putRow >= boardSize || putCol < 0 
					|| putCol >= boardSize) {
				System.out.println("You cannot put there, try again...\n");
			}	
		} while(putRow < 0 || putRow >= boardSize
			|| putCol < 0 || putCol >= boardSize);
	}
	
	
	/**
	 * Display the winner list
	 * 
	 * @param winner  The winner
	 */
	private static void winnerList(char winner) {
		
		// Check who is the winner and count the numbers
		if(winner == ' ') {
			tie++;
		} else if(winner == 'X') {
			winX++;
		} else {
			winO++;
		}
		
		System.out.println("\nX has won " + winX + " games.");
		System.out.println("O has won " + winO + " games.");	
		System.out.println("There have been " + tie + " tie games.\n");
	}
	
}
