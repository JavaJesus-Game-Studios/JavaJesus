package ca.javajesus.game.gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class TopInventoryGUI extends JPanel {
	
	public TopInventoryGUI() {
		
		this.setLayout(new BorderLayout());
		JPanel panel = new JPanel(new BorderLayout(0, 0));
		panel.add(new MapGUI(), BorderLayout.EAST);
		this.add(panel, BorderLayout.CENTER);
	}

}
