package ca.javajesus.game.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import ca.javajesus.game.Game;
import ca.javajesus.game.InputHandler;
import ca.javajesus.game.entities.Player;

public class InventoryGUI extends ScreenGUI {

	private static final long serialVersionUID = 1L;
	
	public ItemScreenGUI inventory;
	public int id = 0;

	public InventoryGUI(Player player) {
		
		inventory = new ItemScreenGUI(player);
		this.setFocusable(true);
		this.setLayout(new BorderLayout(0, 0));
		this.input = new InputHandler(this);
		
		JPanel panel = new JPanel(new BorderLayout(0, 0));
		//panel.add(new Slot(), BorderLayout.CENTER);
		//panel.add(new TopInventoryGUI(), BorderLayout.NORTH);
		panel.add(inventory, BorderLayout.CENTER);

		this.add(panel, BorderLayout.CENTER);
		
	}
	
	public void tick() {
		if (input.i.isPressed()) {
			input.i.toggle(false);
			Game.displayGame();
		}
	}

}
