package ca.javajesus.game.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ca.javajesus.game.Game;
import ca.javajesus.game.InputHandler;

public class InventoryGUI extends ScreenGUI {

	private static final long serialVersionUID = 1L;
	JLabel weapons, armor, potions, misc;

	public InventoryGUI() {
		this.setFocusable(true);
		this.setLayout(new BorderLayout(0, 0));
		this.input = new InputHandler(this);
		
		weapons = new JLabel("Weapons");
		armor = new JLabel("Armor");
		potions = new JLabel("Potions");
		misc = new JLabel("Misc");
		
		JPanel buttons = new JPanel(new FlowLayout(0));
		buttons.add(weapons);
		buttons.add(armor);
		buttons.add(potions);
		buttons.add(misc);
		
		JPanel panel = new JPanel(new BorderLayout(0, 0));
		panel.add(new Slot(), BorderLayout.SOUTH);
		panel.add(new TopInventoryGUI(), BorderLayout.NORTH);
		panel.add(buttons, BorderLayout.CENTER);

		this.add(panel, BorderLayout.CENTER);
		
	}
	
	public void tick() {
		if (input.i.isPressed()) {
			input.i.toggle(false);
			Game.displayGame();
		}
	}
}
