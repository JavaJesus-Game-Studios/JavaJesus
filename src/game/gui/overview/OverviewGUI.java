package game.gui.overview;

import game.Display;
import game.Game;
import game.InputHandler;
import game.SoundHandler;
import game.entities.Player;
import game.graphics.Screen;
import game.gui.ScreenGUI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JPanel;

import quests.Quest;

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

	public OverviewGUI() {

		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(Display.FRAME_WIDTH,
				Display.FRAME_HEIGHT));
		this.setBackground(Color.BLACK);

		this.input = new InputHandler(this);
		inventory = new InventoryGUI(Game.player, input);
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
			Display.displayGame();
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
