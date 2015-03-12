package ca.javajesus.game.gui.inventory;

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
import ca.javajesus.game.Game;
import ca.javajesus.game.InputHandler;
import ca.javajesus.game.entities.Player;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.game.gui.ScreenGUI;

public class InventoryGUI extends ScreenGUI {

	private static final long serialVersionUID = 1L;

	public ItemScreenGUI inventory;
	JPanel mainScreen;
	private Player player;
	private int[] pixels;
	private Screen screen;
	protected int[] colors = new int[6 * 6 * 6];

	public InventoryGUI(Player player) {

		image = new BufferedImage(250, 300, BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
		this.player = player;
		
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
		init();

	}

	private void init() {
		int index = 0;
		for (int r = 0; r < 6; r++) {
			for (int g = 0; g < 6; g++) {
				for (int b = 0; b < 6; b++) {
					int rr = (r * 255 / 5);
					int gg = (g * 255 / 5);
					int bb = (b * 255 / 5);

					colors[index++] = rr << 16 | gg << 8 | bb;
				}
			}
		}
		screen = new Screen(image.getWidth(), image.getHeight());
	}

	public void tick() {

		if (input.i.isPressed() || input.esc.isPressed()) {
			input.i.toggle(false);
			input.esc.toggle(false);
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

	public void paint(Graphics g) {
		super.paint(g);
		int xOffset = image.getWidth() / 2 - 75;
		int yOffset = image.getHeight() / 2 - 50;

		player.renderDisplay(screen, 16);

		for (int y = 0; y < screen.height; y++) {
			for (int x = 0; x < screen.width; x++) {
				int colorCode = screen.pixels[x + y * screen.width];
				if (colorCode < 255)
					pixels[x + y * image.getWidth()] = colors[colorCode];
			}

		}
		
		g.drawImage(image, xOffset, yOffset, image.getWidth(), image.getHeight(), this);

		g.setColor(Color.WHITE);
		g.setFont(new Font("Verdana", 0, 30));
		g.drawString(player.toString(), 20, 30);
		g.drawString("Quests", 159, 497);
		
		int offset = 0;
		for (Quest q: player.activeQuests) {
			g.drawString(q.toString(), 10, 550 + offset);
			offset += 50;
		}
		
		g.drawString("Factions", 606, 497);
	}
}
