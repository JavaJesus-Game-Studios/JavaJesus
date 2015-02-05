import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;


public class Tester extends JFrame{

	private static final long serialVersionUID = 1L;
	
	public Tester() {
		BoardGUI gui = new BoardGUI();
		setPreferredSize(new Dimension(gui.width + 5, gui.height + 28));
		setTitle("Tic Tac Toe");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		getContentPane().add(gui);
		pack();
		requestFocus();
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		toFront();
		repaint();
		
		run();
		
	}
	
	public void run() {
		
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

	public static void main(String[] arg) {
		
		new Tester();
		
	}
	
}
