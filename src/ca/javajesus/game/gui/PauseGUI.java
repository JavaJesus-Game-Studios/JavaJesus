package ca.javajesus.game.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import ca.javajesus.game.Display;
import ca.javajesus.game.InputHandler;

public class PauseGUI extends ScreenGUI {

	private static final long serialVersionUID = 1L;
	PausePanelGUI panel;

	public PauseGUI() {
		panel = new PausePanelGUI();
		this.setFocusable(true);
		this.setLayout(new BorderLayout());
		this.input = new InputHandler(this);
		this.setBackground(Color.red);
		this.setPreferredSize(new Dimension(panel.width, panel.height));
		this.add(panel, BorderLayout.CENTER);
		
	}
	
	public void tick() {
		if (input.esc.isPressed() || panel.resumeIsPressed) {
			input.esc.toggle(false);
			panel.resumeIsPressed = false;
			Display.displayGame();
		}
		panel.repaint();
	}
}
