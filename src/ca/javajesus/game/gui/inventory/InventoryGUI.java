package ca.javajesus.game.gui.inventory;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import ca.javajesus.game.Game;
import ca.javajesus.game.InputHandler;
import ca.javajesus.game.entities.Player;
import ca.javajesus.game.gui.ScreenGUI;

public class InventoryGUI extends ScreenGUI {

	private static final long serialVersionUID = 1L;

	public ItemScreenGUI inventory;
	public int id = 0;
	JPanel mainScreen;
	
	public InventoryGUI(Player player) {

		this.setFocusable(true);
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(Game.WIDTH * Game.SCALE,
				Game.HEIGHT * Game.SCALE));
		
		inventory = new ItemScreenGUI(player);
		this.input = new InputHandler(this);

		mainScreen = new JPanel(new CardLayout());
		mainScreen.add(new MainScreenGUI(), "Main");
		mainScreen.add(new QuestScreenGUI(), "Quests");
		mainScreen.add(new FactionScreenGUI(), "Factions");
		mainScreen.add(inventory, "Inventory");

		this.add(mainScreen, BorderLayout.LINE_START);

	}

	public void tick() {
		
		System.out.println("X :" + InputHandler.MouseX +", Y: " + InputHandler.MouseY);
		
		if (input.i.isPressed()) {
			input.i.toggle(false);
			Game.displayGame();
		}
		// Quests
		if (InputHandler.MouseX > 4 && InputHandler.MouseX < 445
				&& InputHandler.MouseY > 504 && InputHandler.MouseY < 740) {
			if (InputHandler.MouseButton == 1) {
				InputHandler.MouseButton = 0;
				CardLayout cl = (CardLayout) mainScreen.getLayout();
				cl.show(mainScreen, "Quests");
				repaint();
			}

		}
		// Factions
		if (InputHandler.MouseX > 459 && InputHandler.MouseX < 904
				&& InputHandler.MouseY > 505 && InputHandler.MouseY < 740) {
			if (InputHandler.MouseButton == 1) {
				InputHandler.MouseButton = 0;
				CardLayout cl = (CardLayout) mainScreen.getLayout();
				cl.show(mainScreen, "Factions");
				repaint();

			}
		}

		// Inventory
		if (InputHandler.MouseX > 4 && InputHandler.MouseX < 360
				&& InputHandler.MouseY > 40 && InputHandler.MouseY < 458) {
			if (InputHandler.MouseButton == 1) {
				InputHandler.MouseButton = 0;
				CardLayout cl = (CardLayout) mainScreen.getLayout();
				cl.show(mainScreen, "Main");
				repaint();
			}

		}
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE, this);
	}
}
