import java.util.Random;

public class AI {

	private String difficulty;
	private TicTacToe board;
	Random random = new Random();
	private boolean[] playerMoves;

	public AI(String difficulty, TicTacToe board) {
		this.difficulty = difficulty;
		this.board = board;
	}

	public int move() {
		playerMoves = board.getPlayerMoves();
		switch (difficulty) {
		case "medium": {
			return 0;
		}
		case "hard": {
			return 0;
		}
		default: {
			return random.nextInt(9);
		}
		}
	}
	
	private boolean isWinningMove() {
		return true;
	}
	
	private boolean isBlockingMove() {
		return true;
	}
	
	private boolean canFork() {
		return true;
	}
	
	private boolean canBlockFork() {
		return true;
	}

}
