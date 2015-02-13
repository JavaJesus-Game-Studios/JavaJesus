import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

public class BoardGUI extends JPanel {

	private BufferedImage image;
	private BufferedImage x;
	private BufferedImage o;
	private BufferedImage tie;
	private TicTacToe board;
	public int width;
	public int height;
	public static boolean gameDone = false;

	public BoardGUI(TicTacToe board) {
		try {
			this.image = ImageIO.read(BoardGUI.class
					.getResource("/res/TicTacToe_Board.png"));
			this.x = ImageIO.read(BoardGUI.class.getResource("/res/X.png"));
			this.o = ImageIO.read(BoardGUI.class.getResource("/res/O.png"));
			this.tie = ImageIO.read(BoardGUI.class.getResource("/res/tie.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.board = board;
		GridLayout grid = new GridLayout(3, 3);
		grid.setHgap(9);
		grid.setVgap(9);
		this.setLayout(grid);
		this.width = image.getWidth();
		this.height = image.getHeight();
		this.setPreferredSize(new Dimension(width, height));

		JButton b1 = new BoardButton(0, board);

		JButton b2 = new BoardButton(1, board);

		JButton b3 = new BoardButton(2, board);

		JButton b4 = new BoardButton(3, board);

		JButton b5 = new BoardButton(4, board);

		JButton b6 = new BoardButton(5, board);

		JButton b7 = new BoardButton(6, board);

		JButton b8 = new BoardButton(7, board);

		JButton b9 = new BoardButton(8, board);

		this.add(b1);
		this.add(b2);
		this.add(b3);
		this.add(b4);
		this.add(b5);
		this.add(b6);
		this.add(b7);
		this.add(b8);
		this.add(b9);
	}

	public void tick() {

	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (gameDone && !board.tie) {
			for (Component b : this.getComponents()) {
				b.setVisible(false);
			}
			if (board.player1turn) {
				g.drawImage(o, 0, 0, image.getWidth(), image.getHeight(), this);
			} else {
				g.drawImage(x, 0, 0, image.getWidth(), image.getHeight(), this);
			}
		} else if (gameDone && board.tie) {
			for (Component b : this.getComponents()) {
				b.setVisible(false);
			}
			g.drawImage(tie, 0, 0, image.getWidth(), image.getHeight(), this);
		} else
			g.drawImage(image, 0, 0, null);
	}

}
