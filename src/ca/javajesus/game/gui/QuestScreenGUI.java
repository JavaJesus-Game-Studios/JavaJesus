package ca.javajesus.game.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.io.IOException;

import javax.imageio.ImageIO;

import ca.javajesus.game.InputHandler;

public class QuestScreenGUI extends ScreenGUI{
	
	private static final long serialVersionUID = 1L;

	public QuestScreenGUI() {
		this.input = new InputHandler(this);
		try {
			this.image = ImageIO.read(MapGUI.class
					.getResource("/GUI/GUI_Inventory/GUI_QUEST_PAGE.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
	}
	
	public void tick() {
		
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), this);
	}

}
