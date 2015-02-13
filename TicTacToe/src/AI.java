import java.util.ArrayList;
import java.util.Random;

public class AI {

	private String difficulty;
	private TicTacToe board;
	Random random = new Random();
	private boolean[] playerMoves;
	private BoardGUI gui;
	private ArrayList<Integer> moves = new ArrayList<Integer>();

	public AI(String difficulty, TicTacToe board, BoardGUI gui) {
		this.difficulty = difficulty;
		this.board = board;
		this.gui = gui;
	}

	public void move() {
		moves.removeAll(moves);
		playerMoves = board.getPlayerMoves();
		for (int i = 0; i < board.moves.length; i++) {
			if (!board.moves[i]) {
				moves.add(i);
			}
		}
		switch (difficulty) {
		case "medium": {
			for (int move: moves) {
				if (isWinningMove(move)) {
					((BoardButton) gui.getComponent(move)).update();
					return;
				} 
			}
			for (int move: moves) {
				if (isBlockingMove(move)) {
					((BoardButton) gui.getComponent(move)).update();
					return;
				} 
			}
			int num = moves.get(random.nextInt(moves.size()));
			((BoardButton) gui.getComponent(num)).update();
			return;
		}
		case "hard": {
			for (int move: moves) {
				if (isWinningMove(move)) {
					((BoardButton) gui.getComponent(move)).update();
					return;
				} 
			}
			for (int move: moves) {
				if (isBlockingMove(move)) {
					((BoardButton) gui.getComponent(move)).update();
					return;
				} 
			}
			for (int move: moves) {
				if (canFork(move)) {
					((BoardButton) gui.getComponent(move)).update();
					return;
				} 
			}
			for (int move: moves) {
				if (canBlockFork(move)) {
					((BoardButton) gui.getComponent(move)).update();
					return;
				} 
			}
			int num = moves.get(random.nextInt(moves.size()));
			((BoardButton) gui.getComponent(num)).update();
			return;
		}
		default: {
			// Easy difficulty is default
			int num = moves.get(random.nextInt(moves.size()));
			((BoardButton) gui.getComponent(num)).update();
			return;
		}
		}
	}

	private boolean isWinningMove(int num) {
		return true;
	}

	private boolean isBlockingMove(int num) {
		return true;
	}

	private boolean canFork(int num) {
		return true;
	}

	private boolean canBlockFork(int num) {
		return true;
	}

}
