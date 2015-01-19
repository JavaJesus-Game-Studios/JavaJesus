package ca.javajesus.game.gui;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MapGUI extends ScreenGUI {

	private static final long serialVersionUID = 1L;

	public MapGUI() {
		this.setFocusable(true);
		try {
			this.image = ImageIO.read(MapGUI.class
					.getResource("/GUI/GUI-Map.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void tick() {
		
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null);
	}

}
