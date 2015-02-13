import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;

public class BoardButton extends JButton implements ActionListener {

	private static final long serialVersionUID = 1L;

	public int value;
	private BufferedImage x;
	private BufferedImage o;
	private TicTacToe board;
	private boolean taken = false;

	private int id;

	public BoardButton(int num, TicTacToe board) {

		this.value = 0;
		id = num;
		this.board = board;
		this.addActionListener(this);

		try {
			this.x = ImageIO.read(BoardGUI.class.getResource("/res/X.png"));
			this.o = ImageIO.read(BoardGUI.class.getResource("/res/O.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void update() {
		if (!taken) {
			SoundHandler.sound.play(SoundHandler.click);
			board.moves[id] = true;
			if (board.player1turn) {
				board.updateBoard(id, board.player1);
				value = 1;
				board.player1turn = false;
			} else {
				board.updateBoard(id, board.player2);
				board.player1turn = true;
				value = 2;
			}
			taken = true;
			this.repaint();
		}
	}

	public void actionPerformed(ActionEvent e) {
		update();
	}

	public void paint(Graphics g) {
		switch (value) {
		case 0: {
			setOpaque(false);
			setContentAreaFilled(false);
			setBorderPainted(false);
			return;
		}
		case 1: {
			g.drawImage(x, 0, 0, null);
			return;
		}
		case 2: {
			g.drawImage(o, 0, 0, null);
			return;
		}
		default: {
			setOpaque(false);
			setContentAreaFilled(false);
			setBorderPainted(false);
			return;
		}
		}
	}

}
