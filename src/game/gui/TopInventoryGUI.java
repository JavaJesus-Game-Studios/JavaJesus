package game.gui;

import game.gui.inventory.MapScreenGUI;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class TopInventoryGUI extends JPanel {
	
	private static final long serialVersionUID = 1L;

	public TopInventoryGUI() {
		
		this.setLayout(new BorderLayout());
		JPanel panel = new JPanel(new BorderLayout(0, 0));
		panel.add(new MapScreenGUI(), BorderLayout.EAST);
		this.add(panel, BorderLayout.CENTER);
	}

}
