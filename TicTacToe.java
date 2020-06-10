/*
 * JungBok Cho
 * CPSC 5002, Seattle University
 * This is free and unencumbered software released into the public domain.
 */
package choj8_p1;
/**
 * This is a program to create and display the game Tic-Tac-Toe.
 * 
 * @author  JungBok Cho
 * @version 1.0
 */
public class TicTacToe {
	private int size;					// To hold the size of the board
	private char[][] board;			// Create a board with two-dimensional array
	private char player = 'X';		// To hold a value of player
	
	/**
	 * This constructor initializes the object of TicTacToe class.
	 * 
	 * @param boardSize	The board size
	 */
	public TicTacToe(int boardSize) {
		size = boardSize;
	}
	
	/**
	 * Reset the board
	 */
	public void setGame() {
		board = new char[size][size];
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				board[i][j] = ' ';
			}
		}
	}
	
	/**
	 * Display the turn
	 */
	public void turn() {
		if(player == 'X') {
			System.out.println(player + ", it is your turn.");
		} else if(player == 'O') {
			System.out.println(player + ", it is your turn.");
		}
	}
	
	/**
	 * Change the turn
	 */
	public void switchTurn() {
		if(player == 'X') {
			player = 'O';
		} else if(player == 'O') {
			player = 'X';
		}
	}
	
	/**
	 * Check whether the spot is open
	 * 
	 * @param putRow	A value of row user inputs
	 * @param putCol	A value of column user inputs
	 * @return	Return true if the space is open
	 * 			Return false if it is already taken
	 */
	public boolean openSpot(int putRow, int putCol) {
		if(board[putRow][putCol] == ' ') {
			return true;
		}
		return false;
	}
	
	/**
	 * Check who is the winner
	 * 
	 * @return	Return the winner
	 * 			Return just space if there is no winner.
	 */
	public char checkWin() {
		char checkingRow = ' ',		// To hold a winner from row section
			  checkingCol = ' ',		// To hold a winner from column section
			  checkingDia1 = ' ',	// To hold a winner from the 1st diagonal
			  checkingDia2 = ' ';	// To hold a winner from the 2nd diagonal

		// Check each row and each column if there is a winner
		for(int i = 0; i < size; i++) {
			if(checkRow(i) != ' ') {
				checkingRow = checkRow(i);
			} 
			if(checkCol(i) != ' ') {
				checkingCol = checkCol(i);
			}
		}
		// Check each diagonal if there is a winner
		checkingDia1 = checkDia1();
		checkingDia2 = checkDia2();
		
		// Return the winner if there is a winner
		if(checkingRow != ' ') {
			return checkingRow;
		} else if(checkingCol != ' ') {
			return checkingCol;
		} else if(checkingDia1 != ' '){
			return checkingDia1;
		} else if(checkingDia2 != ' ') {
			return checkingDia2;
		}
		
		// If not, return a space
		return ' ';
	}
	/**
	 * Check each row
	 * 
	 * @param index	Index of a row
	 * @return			Return player if there is winner
	 * 					Return just space if not
	 */
	private char checkRow(int index) {
		for(int col = 0; col < size - 1; col++) {
			if(board[index][col] != player) {
				return ' ';
			}
			if(board[index][0] == board[index][col + 1]
					&&	board[index][0] == player ) {
				if(board[index][col] == board[index][col + 1] 
						&& col == size - 2) {
					return player;
				}
			}
		}
		return ' ';		 
	}
	
	/**
	 * Check each column
	 * 
	 * @param index	Index of a column
	 * @return			Return player if there is winner
	 * 					Return just space if not
	 */
	private char checkCol(int index) {
		for(int row = 0; row < size - 1; row++) {
			if(board[row][index] != player) {
				return ' ';
			}
			if(board[0][index] == board[row + 1][index]
					&& board[0][index] == player) {
				if(board[row][index] == board[row + 1][index] 
						&& row == size - 2) {
					return player;
				}
			}
		}
		return ' ';		 
	}
	
	/**
	 * Check the first diagonal
	 * 
	 * @return	Return player if there is winner
	 * 			Return just space if not
	 */
	private char checkDia1() {
		for(int i = 0; i < size - 1; i++) {
			if(board[i][i] != player) {
				return  ' ';
			}
			if(board[0][0] == player && board[0][0] == board[i + 1][i + 1]) {
				if(board[i][i] == board[i + 1][i + 1] && i == size - 2) {
					return player;
				}
			} 
		}
		return ' ';
	}
	
	/**
	 * Check the second diagonal
	 * 
	 * @return	Return player if there is winner
	 * 			Return just space if not
	 */
	private char checkDia2() {
		for(int i = 0, j = size - 1; i < size - 1 && j >= 1; i++, j--) {
			if(board[j][i] != player) {
				return ' ';
			}
			if(board[size - 1][0] == player && 
				board[size - 1][0] == board[j - 1][i + 1]) {
				if(board[j][i] == board[i][j] && i == size - 2) {
					return player;
				}
			} 
		}
		return ' ';
	}
	
	/**
	 * Update the board
	 * 
	 * @param putRow	The row user inputs
	 * @param putCol	The column user inputs
	 */
	public void updateBoard(int putRow, int putCol) {
		board[putRow][putCol] = player;
	}
	
	/**
	 * Print the board
	 */
	public void printBoard() {
		// Print a header
		System.out.println();
		for(int i = 0; i < size; i++) {
			if(i < 10) {
				System.out.print("    " + i);
			} else {
				System.out.print("   " + i);
			}
		}
		System.out.println();
		
		// Print the content of the game
		for(int i = 0; i < size; i++) {
			if(i < 10) {
				System.out.print(" " + i);
			} else {
				System.out.print(i);
			}
			for(int j = 0; j < size; j++) {
				if(board[i][j] == 0) {
					System.out.printf("  %c %c", ' ', '|');
				} else {
					System.out.printf("  %c %c", board[i][j], '|');
				}
			}
			
			// Print lines to separate sections
			System.out.print("\n ");
			for(int k = 0; k < size; k++) {
				System.out.print("-----");
			}
			System.out.println("\n");
		}
		System.out.println();
	}
	
}
