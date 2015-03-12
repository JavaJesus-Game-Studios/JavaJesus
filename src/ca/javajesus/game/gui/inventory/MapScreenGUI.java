package ca.javajesus.game.gui.inventory;

import java.awt.Dimension;
import java.awt.Graphics;
import java.io.IOException;

import javax.imageio.ImageIO;

import ca.javajesus.game.gui.ScreenGUI;

public class MapScreenGUI extends ScreenGUI {

	private static final long serialVersionUID = 1L;

	public MapScreenGUI() {
		try {
			this.image = ImageIO.read(MapScreenGUI.class
					.getResource("/GUI/GUI_Inventory/GUI_MAP_2.png"));
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
