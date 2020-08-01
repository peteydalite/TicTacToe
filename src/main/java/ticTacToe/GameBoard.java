package ticTacToe;

public class GameBoard implements Board {
	
	//Storing board as an array which i can hopefully figure out if there's a winner by the position of x's and o's using the index... hopefully"
	private String[] board;
	
	//Default gameboard
	public GameBoard() {
		this.board = new String[] {
				"1   |","2   |","3   ",
				"    |","    |","    ",
				"----","----","------",
				"4   |","5   |","6   ",
				"    |","    |","    ",
				"----","----","------",
				"7   |","8   |","9   ",
				"    |","    |","    "
		};
	}
	
	//Takes in a new string array, hopefully with user's choice included
	public void updateGameBoard(String[] updateBoard) {
		this.board = updateBoard;
	}
	
	//Displays the current board.
	//if the index is divisible by 3 I'm doing a print line to accurately display the board
	//Tabing for presentation
	public void getBoard() {
		int newLineRef = 0;
		for(int index = 0; index < this.board.length; index++) {
			if(newLineRef % 3 == 0) {
				System.out.println();
				newLineRef = 0;
				System.out.print("\t" + board[index]);
			}else {
				System.out.print(board[index]);
			}
			newLineRef+=1;
		}
		System.out.println();
	}
	
	public String[] getBoardArr() {
		return this.board;
	}
		
}
