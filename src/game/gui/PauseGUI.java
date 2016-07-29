package game.gui;

import game.Display;
import game.InputHandler;

import java.awt.BorderLayout;
import java.awt.Dimension;

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
		this.setPreferredSize(new Dimension(Display.FRAME_WIDTH + 10, Display.FRAME_HEIGHT + 10));
		this.add(panel, BorderLayout.CENTER);
	}
	
	/**
	 * Checks if any buttons are clicked every tick
	 */
	public void tick() {
		if (input.esc.isPressed() || panel.resumeIsPressed) {
			input.esc.toggle(false);
			panel.resumeIsPressed = false;
			Display.displayGame();
		}
		panel.repaint();
	}
}
