import java.util.Scanner;

public class TicTacToe {

	private char[][] board = new char[3][3];
	private boolean[] moves = new boolean[9];
	private char player1;
	private char player2;
	private boolean player1turn = true;
	private boolean[] playerMoves = new boolean[9];
	Scanner scanner = new Scanner(System.in);

	public TicTacToe(char p1, char p2) {
		player1 = p1;
		player2 = p2;
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {
				board[row][col] = '?';
			}
		}
	}

	public void move() {
		if (player1turn) {
			System.out.println("Player1 enter the index of your move: ");
			boolean done = false;
			while (!done) {
				try {
					int move = scanner.nextInt();
					if (move >= 0 && move < 9 && !moves[move]) {
						done = true;
						moves[move] = true;
						updateBoard(move, player1);
					} else {
						System.out
								.println("Move is either taken or not an appropriate space.");
					}
				} catch (Exception e) {
					System.out
							.println("You must enter a real number. Try again.");
					scanner.nextLine();
				}
			}
			player1turn = false;
			return;
		}
		else {
			System.out.println("Player2 enter the index of your move: ");
			boolean done = false;
			while (!done) {
				try {
					int move = scanner.nextInt();
					if (move >= 0 && move < 9 && !moves[move]) {
						done = true;
						moves[move] = true;
						updateBoard(move, player2);
					} else {
						System.out
								.println("Move is either taken or not an appropriate space.");
					}
				} catch (Exception e) {
					System.out
							.println("You must enter a real number. Try again.");
					scanner.nextLine();
				}
			}
			player1turn = true;
			return;
		}
	}

	public void printBoard() {
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {
				if (col < board[col].length - 1)
					System.out.print(board[row][col] + "|");
				else
					System.out.println(board[row][col]);
			}
			if (row < board.length - 1)
				System.out.println("------");
		}
	}

	private void updateBoard(int num, char player) {
		switch (num) {
		case 0:
			board[0][0] = player; break;
		case 1:
			board[0][1] = player; break;
		case 2:
			board[0][2] = player; break;
		case 3:
			board[1][0] = player; break;
		case 4:
			board[1][1] = player; break;
		case 5:
			board[1][2] = player; break;
		case 6:
			board[2][0] = player; break;
		case 7:
			board[2][1] = player; break;
		case 8:
			board[2][2] = player; break;
		}
	}

	public void printRules() {
		System.out
				.println("Welcome to TicTacToe! When it is your turn, enter the number of the square you wish to draw.");
		System.out.println("Use the following outline to follow the indices: ");
		int i = 0;
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {
				if (col < board[col].length - 1)
					System.out.print(i + "|");
				else
					System.out.println(i);
				i++;
			}
			if (row < board.length - 1)
				System.out.println("------");
		}
	}
	
	public boolean checkIfWinner() {
		char player = board[0][0];
		for (int row = 0; row < board.length; row++) {
			player = board[row][0];
			if (player == '?') {
				continue;
			}
			for (int col = 0; col < board[row].length; col++) {
				if (board[row][col] != player) {
					break;
				}
				if (col == 2) {
					System.out.println("The winner is " + player);
					return true;
				}
			}
		}
		
		for (int col = 0; col < board[0].length; col++) {
			player = board[0][col];
			if (player == '?') {
				continue;
			}
			for (int row = 0; row < board.length; row++) {
				if (board[row][col] != player) {
					break;
				}
				if (row == 2) {
					System.out.println("The winner is " + player);
					return true;
				}
			}
			
		}
		
		player = board[0][0];
		for (int i = 0; i < board.length; i++) {
			if (player == '?') {
				continue;
			}
			if (board[i][i] != player) {
				break;
			}
			if (i == 2) {
				System.out.println("The winner is " + player);
				return true;
			}
		}
		player = board[2][0];
		for (int i = 0; i < board.length; i++) {
			if (player == '?') {
				continue;
			}
			if (board[board.length - 1 - i][i] != player) {
				break;
			}
			if (i == 2) {
				System.out.println("The winner is " + player);
				return true;
			}
		}
		
		for (int i = 0; i < moves.length; i++) {
			if (moves[i] == false) {
				return false;
			}
		}
		System.out.println("Cats Game");
		return true;
	}
	
	public boolean[] getPlayerMoves() {
		return playerMoves;
	}

}
