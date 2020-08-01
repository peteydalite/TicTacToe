package ticTacToe;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
	private int players;
	private boolean compRequired;
	private boolean isWinner;
	private boolean isGameOver;
	private String displayWinner;
	private String[] boardArr;
	private Player p1;
	private Player p2;
	private Random rando = new Random();
	private Board gameBoard;
	private Map<Player, Boolean> playerKey = new HashMap<Player, Boolean>();
	private Map<Integer, String> availableSpacesOnBoard = new HashMap<Integer, String>();
	private Map<Integer, Integer> boardPosToArrPos = new HashMap<Integer, Integer>();

	private Scanner scanner = new Scanner(System.in);

	public TicTacToe(int players) {
		this.players = players;
		if (this.players == 1) {
			this.compRequired = true;
		}
//		this.boardArr = new String[3][3];
		for (int i = 1; i <= 9; i++) {
			this.availableSpacesOnBoard.put(i, "");
		}

		this.boardPosToArrPos.put(1, 3);
		this.boardPosToArrPos.put(2, 4);
		this.boardPosToArrPos.put(3, 5);
		this.boardPosToArrPos.put(4, 12);
		this.boardPosToArrPos.put(5, 13);
		this.boardPosToArrPos.put(6, 14);
		this.boardPosToArrPos.put(7, 21);
		this.boardPosToArrPos.put(8, 22);
		this.boardPosToArrPos.put(9, 23);
	}

	public void chooseIcon() {
		// Initiate user for icon option. Choosing either X or O
		System.out.print("Player 1 enter if you want to be X or O: ");

		boolean isValidIcon = false;

		// Determine if user entered in correct icon
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
			} else {
				System.out.print("Please choose either X or O: ");
			}

		} while (!isValidIcon);
	}

	public void goesFirst() {

		// Determine who goes first by heads or tails choice. Using rando number
		// generator for 0 or 1
		// 0 -> heads 1 -> tails which will then be compared to user input

		int decider = rando.nextInt(2);
		boolean isValidChoice = false;
		String userChoice = "";

		System.out.print("Player 1 choose (H)eads or (T)ails to determine who goes first: ");

		// Determine if user entered correct options. Prompt until so.
		do {
			userChoice = this.scanner.nextLine().trim();

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

		if (playerKey.get(p1) && decider == 0) {
			System.out.println("The coin landed on Heads. Player 1 goes first");
		} else if (playerKey.get(p1) && decider == 1) {
			System.out.println("The coin landed on Tails. Player 1 goes first");
		} else if (userChoice.toLowerCase().equals("h") && !this.compRequired) {
			System.out.println("The coin landed on Tails. Player 2 goes first.");
		} else if (userChoice.toLowerCase().equals("t") && !this.compRequired) {
			System.out.println("The coin landed on Heads. Player 2 goes first.");
		} else if (userChoice.toLowerCase().equals("t")) {
			System.out.println("The coin landed on Heads. Computer goes first.");
		} else {
			System.out.println("The coin landed on Tails. Computer goes first.");
		}
		System.out.println("******************************************************************");
		System.out.println();

	}

	// Check to see if player's choice is available on the board
	public boolean checkChoice(int choice) {
		return this.availableSpacesOnBoard.get(choice).equals("");
	}

	public void takeTurn() {

		// If playing against comp, need to generate a choice
		// else take in user choice and evaluate their response
		// If user enters a non-number, should prompt for another input that is a number
		// 1-9
		// If user's choice is already taken then prompt for user for another choice
		// Once appropriate choice has been made and is not taken then update the board
		boolean isValid = false;
		int compChoice = 0;
		int numChoice = 0;

		if (compRequired && playerKey.get(p2)) {
			do {
				compChoice = rando.nextInt(10);
				if (compChoice == 0) {
					compChoice = 1;
				}
				if (!this.checkChoice(compChoice)) {
					// computer choice has been taken, need to choose another
					continue;
				} else {
					isValid = true;
				}
			} while (!isValid);
		} else {

			String userChoice = "";

			System.out.println();
			System.out.println();
			if (playerKey.get(p1)) {
				System.out.print("Player 1 enter your choice (1-9): ");
			} else if (!this.compRequired) {
				System.out.print("Player 2 enter your choice (1-9): ");
			}

			// Make sure the user's choice is a available and is within the position range
			do {
				userChoice = this.scanner.nextLine().trim();
				if (!userChoice.matches("\\d+")) {
					System.out.print("Invalid input. Please enter number 1-9: ");
					continue;
				} else {
					numChoice = Integer.parseInt(userChoice);
					if (numChoice < 1 || numChoice > 9) {
						System.out.print("Position unavailable. Please choose another: ");
						continue;
					} else if (!this.checkChoice(numChoice)) {
						System.out.print("Position unavailable. Please choose another: ");
						continue;
					} else {
						isValid = true;
					}

				}
			} while (!isValid);

		}

		// Update the available space array
		if (compRequired && playerKey.get(p2)) {
			this.availableSpacesOnBoard.replace(compChoice, p2.getIcon());
			this.updateBoard(p2, compChoice);
		} else if (compRequired && playerKey.get(p1)) {
			this.availableSpacesOnBoard.replace(numChoice, p1.getIcon());
			this.updateBoard(p1, numChoice);
		} else if (!compRequired && playerKey.get(p1)) {
			this.availableSpacesOnBoard.replace(numChoice, p1.getIcon());
			this.updateBoard(p1, numChoice);
		} else {
			this.availableSpacesOnBoard.replace(numChoice, p2.getIcon());
			this.updateBoard(p2, numChoice);
		}

	}

	public void updateBoard(Player playerTurn, int choice) {
		this.boardArr = this.gameBoard.getBoardArr();
		if (choice == 3 || choice == 6 || choice == 9) {
			this.boardArr[this.boardPosToArrPos.get(choice)] = "  " + playerTurn.getIcon();
		} else {
			this.boardArr[this.boardPosToArrPos.get(choice)] = "  " + playerTurn.getIcon() + " |";
		}
		this.gameBoard.updateGameBoard(this.boardArr);

	}

	public void isGameOver() {
		this.isGameOver = true;
		if (!this.isWinner) {
			for (Map.Entry<Integer, String> position : this.availableSpacesOnBoard.entrySet()) {
				if (position.getValue().equals("")) {
					this.isGameOver = false;
				}

			}
		}
	}

	public Boolean checkStatus(Player player) {
		String playerIcon = player.getIcon();
		this.isGameOver = true;

		Collection<String> boardValues = this.availableSpacesOnBoard.values();
		String[] posArr = boardValues.toArray(new String[boardValues.size()]);

		// Check for horizontal matches
		if (posArr[0].contains(playerIcon) && posArr[1].contains(playerIcon) && posArr[2].contains(playerIcon)) {
			return true;
		} else if (posArr[3].contains(playerIcon) && posArr[4].contains(playerIcon) && posArr[5].contains(playerIcon)) {
			return true;
		} else if (posArr[6].contains(playerIcon) && posArr[7].contains(playerIcon) && posArr[8].contains(playerIcon)) {
			return true;
		}

		// Check for vertical matches
		if (posArr[0].contains(playerIcon) && posArr[3].contains(playerIcon) && posArr[6].contains(playerIcon)) {
			return true;
		} else if (posArr[1].contains(playerIcon) && posArr[4].contains(playerIcon) && posArr[7].contains(playerIcon)) {
			return true;
		} else if (posArr[2].contains(playerIcon) && posArr[5].contains(playerIcon) && posArr[8].contains(playerIcon)) {
			return true;
		}

		// Check for horizontal matches
		if (posArr[0].contains(playerIcon) && posArr[4].contains(playerIcon) && posArr[8].contains(playerIcon)) {
			return true;
		} else if (posArr[2].contains(playerIcon) && posArr[4].contains(playerIcon) && posArr[6].contains(playerIcon)) {
			return true;
		}

		this.isGameOver = false;
		return false;

	}

	public final void playGame() {
		this.chooseIcon();
		this.goesFirst();
		gameBoard = new GameBoard();

		// If computer is first then they will call takeTurn first and then reset who's
		// up
		do {
			if (this.compRequired && playerKey.get(p2)) {
				this.takeTurn();
				this.isWinner = this.checkStatus(p2);
				if (this.isWinner) {
					this.displayWinner = "Computer Wins!";
				}
				playerKey.replace(p1, true);
				playerKey.replace(p2, false);
				// gameBoard.getBoard();
			} else {

				// Prompt user(s) if their turn is up

				gameBoard.getBoard();
				this.takeTurn();

				System.out.println();
				System.out.println();

				if (playerKey.get(p1)) {
					this.isWinner = this.checkStatus(p1);
				} else {
					this.isWinner = this.checkStatus(p2);
				}

				if (this.isWinner && playerKey.get(p1)) {
					this.displayWinner = "Player 1 Wins!";
				} else if (this.isWinner && this.compRequired) {
					this.displayWinner = "Computer Wins!";
				} else if (this.isWinner) {
					this.displayWinner = "Player 2 Wins!";
				}

				// Switch who's turn it is
				if (playerKey.get(p1)) {
					playerKey.replace(p1, false);
					playerKey.replace(p2, true);
				} else {
					playerKey.replace(p1, true);
					playerKey.replace(p2, false);
				}

				// gameBoard.getBoard();

			}
			this.isGameOver();
		} while (!this.isGameOver);

		System.out.println();

		if (this.isWinner) {
			System.out.println(this.displayWinner);
		} else {
			System.out.println("It's a Tie!");
		}

		this.gameBoard.getBoard();
	}
}
