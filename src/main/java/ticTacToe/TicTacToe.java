package ticTacToe;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
	private int players;
	private boolean compRequired;
	private String[][] boardArr;
	private boolean isGameOver;
	private Player p1;
	private Player p2;
	private Map<Player, Boolean> playerKey = new HashMap<Player, Boolean>();
	private Scanner scanner = new Scanner(System.in);
	public TicTacToe(int players) {
		this.players = players;
		if (this.players == 1) {
			this.compRequired = true;
		}
		this.boardArr = new String[3][3];
	}

	public void setup() {
		//Initiate user for icon option. Choosing either X or O
		System.out.print("Player 1 enter if you want to be X or O: ");
		
		boolean isValidIcon = false;

		//Determine if user entered in correct icon
		do {
			String userIn = this.scanner.nextLine().trim();
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
				System.out.print("Please choose either X or O: ");
			}
			
		}while(!isValidIcon);
	}
	
	public void goesFirst() {
		
		//Determine who goes first by heads or tails choice. Using rando number generator for 0 or 1
		//0 -> heads   1 -> tails which will then be compared to user input
		
		Random rando = new Random();
		int decider = rando.nextInt(2);
		boolean isValidChoice = false;
		String userChoice = "";
		
		
		System.out.print("Player 1 choose (H)eads or (T)ails to determine who goes first: ");

		//Determine if user entered correct options. Prompt until so.
		do {
			userChoice = this.scanner.next().trim();

			if (userChoice.toLowerCase().equals("h") && decider == 0) {
				playerKey.put(p1, true);
				playerKey.put(p2, false);
				isValidChoice = true;
			} else if (userChoice.toLowerCase().equals("t") && decider == 0) {
				playerKey.put(p1, false);
				playerKey.put(p2, true);
				isValidChoice = true;
			} else if (userChoice.toLowerCase().equals("h") && decider == 1) {
				playerKey.put(p1, false);
				playerKey.put(p2, true);
				isValidChoice = true;
			} else if (userChoice.toLowerCase().equals("t") && decider == 1) {
				playerKey.put(p1, true);
				playerKey.put(p2, false);
				isValidChoice = true;
			} else {
				System.out.print("Invalid choice. Please choose either H or T: ");
			
			}

		} while (!isValidChoice);
		
		
		System.out.println();
		System.out.println();
		
		
		if(playerKey.get(p1) && decider == 0) {
			System.out.println("The coin landed on Heads. Player 1 goes first");
		}else if(playerKey.get(p1) && decider == 1) {
			System.out.println("The coin landed on Tails. Player 1 goes first");
		}else if(userChoice.toLowerCase().equals("h") && !this.compRequired) {
			System.out.println("The coin landed on Tails. Player 2 goes first.");
		}else if(userChoice.toLowerCase().equals("t") && !this.compRequired) {
			System.out.println("The coin landed on Heads. Player 2 goes first.");
		}else if(userChoice.toLowerCase().equals("t")) {
			System.out.println("The coin landed on Heads. Computer goes first.");
		}else {
			System.out.println("The coin landed on Tails. Computer goes first.");
		}
		System.out.println("******************************************************************");
		System.out.println();

	}
	
	public void takeTurn() {
		
	}
	
	public void updateBoard(Board currentBoard, Player playerTurn, int choice) {
		
	}

	public boolean getIsGameOver() {
		return this.isGameOver;

	}
	public final void playGame() {
		this.setup();
		this.goesFirst();
		Board gameBoard = new GameBoard();
		gameBoard.getBoard();
		
		if(this.compRequired && playerKey.get(p2)) {
			this.takeTurn();
		}
		
		
		//Prompt user(s) if their turn is up
		do {
			
		}while(!this.getIsGameOver());
		
	}
}
