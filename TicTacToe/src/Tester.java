
public class Tester {
	
	public static void main(String[] arg) {
		
		TicTacToe board = new TicTacToe('X', 'O');
		board.printRules();
		
		boolean done = false;
		while (!done) {
			board.move();
			board.printBoard();
			if (board.checkIfWinner()) {
				done = true;
			}
		}
		System.out.println("Thanks for Playing!");
	}

}
