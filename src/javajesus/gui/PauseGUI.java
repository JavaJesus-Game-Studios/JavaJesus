package javajesus.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javajesus.InputHandler;
import javajesus.JavaJesus;

public class PauseGUI extends ScreenGUI {

	// Used for serialization
	private static final long serialVersionUID = 1L;
	
	// Pause screen that is added to the screen
	private PausePanelGUI panel;

	/**
	 * Creates a Pause screen
	 */
	public PauseGUI() {
		panel = new PausePanelGUI();
		this.setFocusable(true);
		this.setLayout(new BorderLayout());
		this.input = new InputHandler(this);
		this.setPreferredSize(new Dimension(JavaJesus.WINDOW_WIDTH + 10, JavaJesus.WINDOW_HEIGHT + 10));
		this.add(panel, BorderLayout.CENTER);
	}
	
	/**
	 * Checks if any buttons are clicked every tick
	 */
	public void tick() {
		if (input.esc.isPressed() || panel.resumeIsPressed) {
			input.esc.toggle(false);
			panel.resumeIsPressed = false;
			JavaJesus.displayGame();
		}
		panel.repaint();
	}
}
