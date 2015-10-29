package game.gui.overview;

import game.Display;
import game.InputHandler;
import game.gui.ScreenGUI;
import game.gui.slots.PlayerSlotGUI;
import game.gui.slots.Slot;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

/*
 * The Overview Panel that displays the character, map, quests, and factions
 */
public class MainScreenGUI extends ScreenGUI {

	private static final long serialVersionUID = 1L;

	private static final int WIDTH = Display.FRAME_WIDTH + 10;
	private static final int HEIGHT = Display.FRAME_HEIGHT + 85;

	private static final double BOTTOM_ROW_RATIO = .4; // Horizontal Spacing of Bottom Row
	private static final double TOP_ROW_RATIO = .4; // Vertical Spacing of Player Slot

	private static String WIDE_FILE_NAME = "/GUI/GUI_Inventory/GUI_wide_panel.png";
	private static String LARGE_FILE_NAME = "/GUI/GUI_Inventory/GUI_large_panel.png";
	
	private PlayerSlotGUI pScreen;

	/**
	 * Initializes the inputhandler and background panels
	 */
	public MainScreenGUI() {
		this.input = new InputHandler(this);
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setLayout(new BorderLayout());

		JPanel bottomRow = new JPanel(new BorderLayout());
		bottomRow.setPreferredSize(new Dimension(WIDTH,
				(int) (HEIGHT * BOTTOM_ROW_RATIO)));
		Slot p1 = new Slot(WIDE_FILE_NAME);
		p1.setPreferredSize(new Dimension(WIDTH / 2, bottomRow.getHeight()));
		Slot p2 = new Slot(WIDE_FILE_NAME);
		p2.setPreferredSize(new Dimension(WIDTH / 2, bottomRow.getHeight()));
		bottomRow.add(p1, BorderLayout.WEST);
		bottomRow.add(p2, BorderLayout.CENTER);
		this.add(bottomRow, BorderLayout.SOUTH);

		JPanel topRow = new JPanel(new BorderLayout());
		topRow.setPreferredSize(new Dimension(WIDTH,
				(int) (HEIGHT * (1 - BOTTOM_ROW_RATIO))));
		pScreen = new PlayerSlotGUI((int) (WIDTH * TOP_ROW_RATIO), topRow.getHeight(), LARGE_FILE_NAME, .75);
		topRow.add(pScreen, BorderLayout.WEST);
		topRow.add(new MapScreenGUI((int) (WIDTH * (1 - TOP_ROW_RATIO)), topRow.getHeight()), BorderLayout.CENTER);
		this.add(topRow, BorderLayout.CENTER);

		this.validate();

	}

	public void tick() {
		pScreen.tick();
	}

}
