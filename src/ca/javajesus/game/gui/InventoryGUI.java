package ca.javajesus.game.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ca.javajesus.game.Game;
import ca.javajesus.game.InputHandler;

public class InventoryGUI extends ScreenGUI {

	private static final long serialVersionUID = 1L;
	JLabel weapons, armor, potions, misc;

	public InventoryGUI() {
		this.setFocusable(true);
		this.input = new InputHandler(this);
		weapons = new JLabel("Weapons");
		armor = new JLabel("Armor");
		potions = new JLabel("Potions");
		misc = new JLabel("Misc");
		
		this.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel(new BorderLayout(0, 0));
		panel.add(new Slot(), BorderLayout.EAST);
		panel.add(new MapGUI(), BorderLayout.WEST);

		this.add(panel, BorderLayout.CENTER);
		
	}
	
	public void tick() {
		if (input.i.isPressed()) {
			input.i.toggle(false);
			Game.displayGame();
		}
	}
}
