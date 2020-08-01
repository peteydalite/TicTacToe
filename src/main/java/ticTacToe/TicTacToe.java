package ticTacToe;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
	private int players;
	private boolean compRequired;
	private String[][] boardArr;
	private Player p1;
	private Player p2;
	private Random rando = new Random();
	private Map<Player, Boolean> playerKey = new HashMap<Player, Boolean>();
	private Map<Integer, String> availableSpacesOnBoard = new HashMap<Integer, String>();
	private Scanner scanner = new Scanner(System.in);

	public TicTacToe(int players) {
		this.players = players;
		if (this.players == 1) {
			this.compRequired = true;
		}
//		this.boardArr = new String[3][3];
		for(int i = 1; i <=9; i++) {
			this.availableSpacesOnBoard.put(i, "");
		}
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
		
		
		int decider = rando.nextInt(2);
		boolean isValidChoice = false;
		String userChoice = "";
		
		
		System.out.print("Player 1 choose (H)eads or (T)ails to determine who goes first: ");

		//Determine if user entered correct options. Prompt until so.
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
	
	//Check to see if player's choice is available on the board
	public boolean checkChoice(int choice) {
		return this.availableSpacesOnBoard.get(choice).equals("");
	}
	
	public void takeTurn() {
				
		//If playing against comp, need to generate a choice
		//else take in user choice and evaluate their response
		//If user enters a non-number, should prompt for another input that is a number 1-9
		//If user's choice is already taken then prompt for user for another choice
		//Once appropriate choice has been made and is not taken then update the board
		boolean isValid = false;
		int compChoice = 0;
		int numChoice = 0;
		
		if(compRequired && playerKey.get(p2)) {
			do {
				compChoice = rando.nextInt(10);
				if(compChoice == 0) {
					compChoice = 1;
				}
				if(!this.checkChoice(compChoice)) {
					//computer choice has been taken, need to choose another
					continue;
				}else{
					isValid = true;
				}
			}while(!isValid);
		}else {

			String userChoice = "";
			
			if(playerKey.get(p1)) {
				System.out.print("Player 1 enter your choice (1-9): ");
			}else if(!this.compRequired) {
				System.out.print("Player 2 enter your choice (1-9): ");
			}
							
			
			do {
				userChoice = this.scanner.nextLine().trim();
				if(!userChoice.matches("\\d+")) {
					System.out.print("Invalid input. Please enter number 1-9: ");
					continue;
				}else {
					numChoice = Integer.parseInt(userChoice);
					if(numChoice < 1 || numChoice > 9) {
						System.out.print("Position unavailable. Please choose another: ");
						continue;
					}else if(!this.checkChoice(numChoice)) {
						System.out.print("Position unavailable. Please choose another: ");
						continue;
					}else {
						isValid = true;
					}
					
				}
			}while(!isValid);
				
		}
		
		//Update the available space array
		if(compRequired && playerKey.get(p2)) {
			this.availableSpacesOnBoard.replace(compChoice, p2.getIcon());
		}else if(compRequired && playerKey.get(p1)) {
			this.availableSpacesOnBoard.replace(numChoice, p1.getIcon());
		}else if(!compRequired && playerKey.get(p1)) {
			this.availableSpacesOnBoard.replace(numChoice, p1.getIcon());
		}else {
			this.availableSpacesOnBoard.replace(numChoice, p2.getIcon());
		}
		
		
	}
	
	public void updateBoard(Board currentBoard, Player playerTurn, int choice) {
		
	}

	public boolean isGameOver() {
		for( Map.Entry<Integer, String> position : this.availableSpacesOnBoard.entrySet()) {
			if(position.getValue().equals("")) {
				return false;
			}
			
		}
		return true;

	}
	public final void playGame() {
		this.setup();
		this.goesFirst();
		Board gameBoard = new GameBoard();
		gameBoard.getBoard();
		
		
		//If computer is first then they will call takeTurn first and then reset who's up
		if(this.compRequired && playerKey.get(p2)) {
			this.takeTurn();
			playerKey.replace(p1, true);
			playerKey.replace(p2, false);
		}
		
		
		//Prompt user(s) if their turn is up
		do {
			
			this.takeTurn();
			
			//Switch who's turn it is
			if(playerKey.get(p1)) {
				playerKey.replace(p1, false);
				playerKey.replace(p2, true);
			}else {
				playerKey.replace(p1, true);
				playerKey.replace(p2, false);
			}
				
			
		}while(!this.isGameOver());
		
	}
}
