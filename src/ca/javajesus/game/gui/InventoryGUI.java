package ca.javajesus.game.gui;

import java.awt.Color;
import java.io.IOException;

import javax.imageio.ImageIO;

import ca.javajesus.game.Game;
import ca.javajesus.game.InputHandler;

public class InventoryGUI extends ScreenGUI {

	private static final long serialVersionUID = 1L;

	public InventoryGUI() {
		this.setFocusable(true);
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
		if (input.i.isPressed()) {
			input.i.toggle(false);
			Game.displayGame();
		}
	}
}
