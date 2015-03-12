package ca.javajesus.game.gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import ca.javajesus.game.gui.inventory.MapScreenGUI;

public class TopInventoryGUI extends JPanel {
	
	private static final long serialVersionUID = 1L;

	public TopInventoryGUI() {
		
		this.setLayout(new BorderLayout());
		JPanel panel = new JPanel(new BorderLayout(0, 0));
		panel.add(new MapScreenGUI(), BorderLayout.EAST);
		this.add(panel, BorderLayout.CENTER);
	}

}
