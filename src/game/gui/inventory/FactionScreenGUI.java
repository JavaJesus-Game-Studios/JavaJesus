package game.gui.inventory;

import game.Display;
import game.InputHandler;
import game.gui.ScreenGUI;

import java.awt.Dimension;
import java.awt.Graphics;
import java.io.IOException;

import javax.imageio.ImageIO;

public class FactionScreenGUI extends ScreenGUI {

	private static final long serialVersionUID = 1L;
	private final int WIDTH = (int) (Display.FRAME_WIDTH + 10);
	private final int HEIGHT = (int) (Display.FRAME_HEIGHT + 85);

	public FactionScreenGUI() {
		this.input = new InputHandler(this);
		try {
			this.image = ImageIO.read(FactionScreenGUI.class
					.getResource("/GUI/GUI_Inventory/GUI_FACTIONS_PAGE.png"));
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
