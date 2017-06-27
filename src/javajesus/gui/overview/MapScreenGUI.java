package javajesus.gui.overview;

import java.awt.Dimension;
import java.awt.Graphics;
import java.io.IOException;

import javax.imageio.ImageIO;

import javajesus.gui.ScreenGUI;

public class MapScreenGUI extends ScreenGUI {

	private static final long serialVersionUID = 1L;
	
	public MapScreenGUI(int width, int height) {
		try {
			this.image = ImageIO.read(MapScreenGUI.class
					.getResource("/GUI/GUI_Inventory/GUI_MAP_2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.setPreferredSize(new Dimension(width, height));
	}
	
	public void tick() {
		
	}
	
	protected void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
	}

}
