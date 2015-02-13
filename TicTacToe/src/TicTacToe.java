import java.util.Scanner;

public class TicTacToe {

	private char[][] board = new char[3][3];
	public  boolean[] moves = new boolean[9];
	public char player1;
	public char player2;
	public boolean player1turn = true;
	private boolean[] playerMoves = new boolean[9];
	public boolean playingAI;
	public boolean tie = false;
	
	public TicTacToe(boolean ai) {
		this.playingAI = ai;
		player1 = 'X';
		player2 = 'O';
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {
				board[row][col] = '?';
			}
		}
	}
	
	public void updateBoard(int num, char player) {
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

	public String getRules() {
		return "Welcome to TicTacToe! Player1 goes first!";
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
		System.out.println("Tie!");
		tie = true;
		return true;
	}
	
	public boolean[] getPlayerMoves() {
		return playerMoves;
	}

}
