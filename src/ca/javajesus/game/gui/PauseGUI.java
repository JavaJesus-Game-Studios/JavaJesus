package ca.javajesus.game.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;

import javax.imageio.ImageIO;

import ca.javajesus.game.Game;
import ca.javajesus.game.InputHandler;

public class PauseGUI extends ScreenGUI {

	private static final long serialVersionUID = 1L;

	public PauseGUI() {
		this.setFocusable(true);
		try {
			this.image = ImageIO.read(PauseGUI.class
					.getResource("/GUI/Main_Menu_Background.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.input = new InputHandler(this);
		this.setBackground(Color.red);
	}
	
	public void tick() {
		if (input.esc.isPressed()) {
			input.esc.toggle(false);
			Game.displayGame();
		}
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null);
	}
}
