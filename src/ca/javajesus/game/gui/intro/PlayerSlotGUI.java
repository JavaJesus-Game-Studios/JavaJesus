package ca.javajesus.game.gui.intro;

import java.awt.Dimension;
import java.awt.Graphics;
import java.io.IOException;

import javax.imageio.ImageIO;

import ca.javajesus.game.gui.MapGUI;
import ca.javajesus.game.gui.ScreenGUI;

public class PlayerSlotGUI extends ScreenGUI {

	private static final long serialVersionUID = 1L;
	
	public PlayerSlotGUI() {
		
		try {
			this.image = ImageIO.read(MapGUI.class
					.getResource("/GUI/PlayerGUI.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.setPreferredSize(new Dimension(image.getWidth() * 4, image.getHeight() * 4));
	}
	
	public void tick() {
		
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, image.getWidth() * 4, image.getHeight() * 4, this);
	}

}