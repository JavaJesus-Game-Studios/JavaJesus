package ca.javajesus.game.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MapGUI extends ScreenGUI {

	private static final long serialVersionUID = 1L;

	public MapGUI() {
		try {
			this.image = ImageIO.read(MapGUI.class
					.getResource("/GUI/GUI_Inventory/GUI_Map_2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
	}
	
	public void tick() {
		
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, image.getWidth() / 13, image.getHeight() / 13, this);
	}

}
