package javajesus.gui.overview;

import java.awt.Dimension;
import java.awt.Graphics;
import java.io.IOException;

import javax.imageio.ImageIO;

import javajesus.InputHandler;
import javajesus.JavaJesus;
import javajesus.gui.ScreenGUI;

public class QuestScreenGUI extends ScreenGUI {

	private static final long serialVersionUID = 1L;
	private final int WIDTH = (int) (JavaJesus.WINDOW_WIDTH + 10);
	private final int HEIGHT = (int) (JavaJesus.WINDOW_HEIGHT + 85);

	public QuestScreenGUI() {
		this.input = new InputHandler(this);
		try {
			this.image = ImageIO.read(QuestScreenGUI.class
					.getResource("/GUI/GUI_Inventory/GUI_QUEST_PAGE.png"));
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
