package game.gui.inventory;

import game.Display;
import game.InputHandler;
import game.gui.ScreenGUI;

import java.awt.Dimension;
import java.awt.Graphics;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MainScreenGUI extends ScreenGUI {

	private static final long serialVersionUID = 1L;

	private final int WIDTH = (int) (Display.FRAME_WIDTH + 10);
	private final int HEIGHT = (int) (Display.FRAME_HEIGHT  + 85);

	public MainScreenGUI() {
		this.input = new InputHandler(this);
		try {
			this.image = ImageIO.read(MainScreenGUI.class
					.getResource("/GUI/GUI_Inventory/Inventory_Main.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
	}

	public void tick() {

	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, WIDTH, HEIGHT, this);
	}

}
