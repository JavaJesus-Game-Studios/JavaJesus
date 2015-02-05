import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class BoardGUI extends JPanel{
	
	private BufferedImage image;
	public int width;
	public int height;
	
	public BoardGUI() {
		try {
			this.image = ImageIO.read(BoardGUI.class
					.getResource("/res/TicTacToe_Board.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.setLayout(new GridLayout());
		this.width = image.getWidth();
		this.height = image.getHeight();
		this.setPreferredSize(new Dimension(width, height));
	}
	
	public void tick() {
		
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null);
	}

}
