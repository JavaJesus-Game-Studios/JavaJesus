package ca.javajesus.game.gui.inventory;

import java.awt.Dimension;
import java.awt.Graphics;
import java.io.IOException;

import javax.imageio.ImageIO;

import ca.javajesus.game.Display;
import ca.javajesus.game.InputHandler;
import ca.javajesus.game.gui.ScreenGUI;

public class MainScreenGUI extends ScreenGUI {

	private static final long serialVersionUID = 1L;

	private final int WIDTH = (int) (Display.WIDTH * Display.SCALE + 10);
	private final int HEIGHT = (int) (Display.HEIGHT * Display.SCALE + 85);

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
