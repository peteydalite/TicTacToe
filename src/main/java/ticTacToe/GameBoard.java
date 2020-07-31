package ticTacToe;

public class GameBoard implements Board {
	private String[] board;
	
	
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
	
	public void updateGameBoard(String[] updateBoard) {
		this.board = updateBoard;
	}
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
	}
		
}
