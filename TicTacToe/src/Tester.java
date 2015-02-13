import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;


public class Tester extends JFrame{

	private static final long serialVersionUID = 1L;
	private TicTacToe board;
	private AI robot;
	
	public Tester() {
		board = new TicTacToe(true);
		BoardGUI gui = new BoardGUI(board);
	    this.robot = new AI("easy", board, gui);
		
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
		
		System.out.println(board.getRules());
		
		boolean done = false;
		while (!done) {
			if (board.checkIfWinner()) {
				done = true;
				BoardGUI.gameDone = true;
				SoundHandler.sound.play(SoundHandler.end);
				break;
			}
			if (board.playingAI && !board.player1turn) {
				robot.move();
			}
		}
		System.out.println("Thanks for Playing!");
		
	}

	public static void main(String[] arg) {
		
		new Tester();
		
	}
	
}
