package ticTacToe;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
	private int players;
	private boolean compRequired;
	private String[][] boardArr;
	private Map<Player, Boolean> playerKey = new HashMap<Player, Boolean>();

	public TicTacToe(int players) {
		this.players = players;
		if (this.players == 1) {
			this.compRequired = true;
		}
		this.boardArr = new String[3][3];
	}

	public void setup() {
		//Initiate user for icon option. Choosing either X or O
		Scanner scanner = new Scanner(System.in);
		System.out.print("Player 1 enter if you want to be 'X' or 'O': ");
		
		boolean isValidIcon = false;
		Player p1;
		Player p2;

		//Determine if user entered in correct icon
		do {
			String userIn = scanner.nextLine().trim();
			if (!compRequired && userIn.toUpperCase().equals("X")) {
				p1 = new Player("X");
				p2 = new Player("O");
				isValidIcon = true;
			} else if (!compRequired && userIn.toUpperCase().equals("O")) {
				p1 = new Player("O");
				p2 = new Player("X");
				isValidIcon = true;
			} else if (userIn.toUpperCase().equals("X")) {
				p1 = new Player("X");
				p2 = new Player("O");
				isValidIcon = true;
			} else if (userIn.toUpperCase().equals("O")) {
				p1 = new Player("O");
				p2 = new Player("X");
				isValidIcon = true;
			}else {
				System.out.print("Please choose either 'X' or 'O': ");
			}
			
		}while(!isValidIcon);
		
		
		//Determine who goes first by heads or tails choice. Using rando number generator for 0 or 1
		//0 -> heads   1 -> tails which will then be compared to user input
		Random rando = new Random();
		int decider = rando.nextInt(2);
		boolean isValidChoice = false;

		System.out.println("Player 1 choose 'Heads' or 'Tails' to determine who goes first: ");

		//Determine if user entered correct options. Prompt until so.
		do {
			String userChoice = scanner.next().trim();

			if (userChoice.toLowerCase().equals("heads") && decider == 0) {
				playerKey.put(p1, true);
				playerKey.put(p2, false);
				isValidChoice = true;
			} else if (userChoice.toLowerCase().equals("tails") && decider == 0) {
				playerKey.put(p1, false);
				playerKey.put(p2, true);
			} else if (userChoice.toLowerCase().equals("heads") && decider == 1) {
				playerKey.put(p1, false);
				playerKey.put(p2, true);
				isValidChoice = true;
			} else if (userChoice.toLowerCase().equals("tails") && decider == 1) {
				playerKey.put(p1, true);
				playerKey.put(p2, false);
				isValidChoice = true;
			} else {
				System.out.print("Invalid choice. Please choose either 'Heads' or 'Tails': ");
			}

		} while (!isValidChoice);

	}
	
	public void takeTurn() {
		
	}
	
	public void updateBoard(Board currentBoard, Player playerTurn, int choice) {
		
	}

	public boolean isGameOver() {
		return false;

	}
}
