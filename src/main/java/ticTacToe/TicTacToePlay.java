package ticTacToe;

import java.util.Scanner;

public class TicTacToePlay {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		
		//Initial prompts.. could probably store this in a method
		System.out.print("Please enter 1 to play against computer or 2 for two players: ");
		String userInput = "";
		int numPlayer = 0;
		boolean validPlayers = false;
		do {
		userInput = scanner.nextLine().trim();
		if(userInput.equals("1") || userInput.equals("2")) {
			numPlayer = Integer.parseInt(userInput);
			validPlayers = true;
		}else {
			System.out.print("Please enter 1 to play against computer or 2 for two players: ");
		}
		}while(!validPlayers);	
		
		
		TicTacToe newGame = new TicTacToe(numPlayer);
		newGame.playGame();
	}

}
