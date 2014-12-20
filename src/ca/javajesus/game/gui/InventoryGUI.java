package ca.javajesus.game.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import ca.javajesus.game.Game;
import ca.javajesus.game.InputHandler;
import ca.javajesus.game.entities.Player;

public class InventoryGUI extends JPanel {

	private static final long serialVersionUID = 1L;

	private BufferedImage image;
	private InputHandler input;
	private int tickCount = 0;
	private boolean canChange = false;

	public InventoryGUI() {
		try {
			this.image = ImageIO.read(InventoryGUI.class
					.getResource("/GUI/Main_Menu_Background.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.input = new InputHandler(this);
		this.setBackground(Color.blue);

	}

	public void tick() {
		if (input.i.isPressed() && canChange) {
			canChange = false;
			tickCount = 0;
			Game.removeInventory();
		}
		
		tickCount++;
		if (tickCount > 50) {
			canChange = true;
		}
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null);
	}

}
