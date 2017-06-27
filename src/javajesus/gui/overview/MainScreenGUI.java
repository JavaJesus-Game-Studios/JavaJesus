package javajesus.gui.overview;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import javajesus.InputHandler;
import javajesus.JavaJesus;
import javajesus.gui.ScreenGUI;
import javajesus.gui.slots.PlayerSlotGUI;
import javajesus.gui.slots.Slot;

/*
 * The Overview Panel that displays the character, map, quests, and factions
 */
public class MainScreenGUI extends ScreenGUI implements MouseListener {

	private static final long serialVersionUID = 1L;

	private static final int WIDTH = JavaJesus.WINDOW_WIDTH + 10;
	private static final int HEIGHT = JavaJesus.WINDOW_HEIGHT + 85;

	private static final double BOTTOM_ROW_RATIO = .4; // Horizontal Spacing of Bottom Row
	private static final double TOP_ROW_RATIO = .4; // Vertical Spacing of Player Slot

	private static String WIDE_FILE_NAME = "/GUI/GUI_Inventory/GUI_wide_panel.png";
	private static String LARGE_FILE_NAME = "/GUI/GUI_Inventory/GUI_large_panel.png";
	
	private PlayerSlotGUI pScreen;
	
	private JPanel factionPanel, questPanel;

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
		questPanel = new Slot(WIDE_FILE_NAME);
		questPanel.setPreferredSize(new Dimension(WIDTH / 2, bottomRow.getHeight()));
		factionPanel = new Slot(WIDE_FILE_NAME);
		factionPanel.setPreferredSize(new Dimension(WIDTH / 2, bottomRow.getHeight()));
		bottomRow.add(questPanel, BorderLayout.WEST);
		bottomRow.add(factionPanel, BorderLayout.CENTER);
		this.add(bottomRow, BorderLayout.SOUTH);

		JPanel topRow = new JPanel(new BorderLayout());
		topRow.setPreferredSize(new Dimension(WIDTH,
				(int) (HEIGHT * (1 - BOTTOM_ROW_RATIO))));
		pScreen = new PlayerSlotGUI((int) (WIDTH * TOP_ROW_RATIO), topRow.getHeight(), LARGE_FILE_NAME, .75);
		topRow.add(pScreen, BorderLayout.WEST);
		topRow.add(new MapScreenGUI((int) (WIDTH * (1 - TOP_ROW_RATIO)), topRow.getHeight()), BorderLayout.CENTER);
		this.add(topRow, BorderLayout.CENTER);

		this.validate();
		
		pScreen.addMouseListener(this);
		questPanel.addMouseListener(this);
		factionPanel.addMouseListener(this);

	}

	public void tick() {
		pScreen.tick();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getSource() == pScreen) {
			OverviewGUI.displayInventory();
		} else if (e.getSource() == questPanel) {
			OverviewGUI.displayQuest();
		} else if (e.getSource() == factionPanel) {
			OverviewGUI.displayFaction();
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
