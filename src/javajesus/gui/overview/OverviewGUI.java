package javajesus.gui.overview;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import javajesus.InputHandler;
import javajesus.JavaJesus;
import javajesus.entities.Player;
import javajesus.gui.ScreenGUI;

/*
 * The Overview Menu of the Inventory Screen
 */
public class OverviewGUI extends ScreenGUI {

	private static final long serialVersionUID = 1L;

	// layout used to switch containers
	private static CardLayout cl;

	private static final int OVERVIEW = 0, INVENTORY = 1, QUEST = 2,
			FACTION = 3;
	private static int id = OVERVIEW;

	private static JPanel mainScreen;

	private InventoryGUI inventory;
	private MainScreenGUI main;

	public OverviewGUI(Player player) {

		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(JavaJesus.WINDOW_WIDTH,
				JavaJesus.WINDOW_HEIGHT));
		this.setBackground(Color.BLACK);

		this.input = new InputHandler(this);
		inventory = new InventoryGUI(player);
		main = new MainScreenGUI();

		mainScreen = new JPanel(new CardLayout());
		mainScreen.add(main, "Main");
		mainScreen.add(new QuestScreenGUI(), "Quests");
		mainScreen.add(new FactionScreenGUI(), "Factions");
		mainScreen.add(inventory, "Inventory");

		this.add(mainScreen, BorderLayout.CENTER);
		cl = (CardLayout) mainScreen.getLayout();

	}

	public void tick() {

		if (input.i.isPressed() || input.esc.isPressed()) {
			input.i.toggle(false);
			input.esc.toggle(false);
			JavaJesus.displayGame();
		}

		if (id == INVENTORY) {
			inventory.tick();
		}
		if (id == OVERVIEW) {
			main.tick();
		}

	}

	public static void displayMain() {
		id = OVERVIEW;
		cl.show(mainScreen, "Main");
	}

	public static void displayInventory() {
		id = INVENTORY;
		cl.show(mainScreen, "Inventory");
	}

	public static void displayQuest() {
		id = QUEST;
		cl.show(mainScreen, "Quests");
	}

	public static void displayFaction() {
		id = FACTION;
		cl.show(mainScreen, "Factions");
	}

	public InventoryGUI getInventory() {
		return this.inventory;
	}
}
