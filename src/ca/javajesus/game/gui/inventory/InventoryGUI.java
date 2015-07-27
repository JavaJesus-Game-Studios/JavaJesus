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

import ca.javajesus.game.Display;
import ca.javajesus.game.InputHandler;
import ca.javajesus.game.SoundHandler;
import ca.javajesus.game.entities.Player;
import ca.javajesus.game.graphics.Screen;
import ca.javajesus.game.gui.ScreenGUI;
import ca.javajesus.quests.Quest;

public class InventoryGUI extends ScreenGUI {

	private static final long serialVersionUID = 1L;

	public ItemScreenGUI inventory;
	JPanel mainScreen;
	private Player player;
	private int[] pixels;
	private Screen screen;
	protected int[] colors = new int[6 * 6 * 6];
	private int id = 0;
	CardLayout cl;

	public InventoryGUI(Player player) {

		image = new BufferedImage(250, 300, BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
		this.player = player;

		this.setFocusable(true);
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(Display.WIDTH * Display.SCALE,
				Display.HEIGHT * Display.SCALE));

		this.input = new InputHandler(this);
		inventory = new ItemScreenGUI(player, input);

		mainScreen = new JPanel(new CardLayout());
		mainScreen.add(new MainScreenGUI(), "Main");
		mainScreen.add(new QuestScreenGUI(), "Quests");
		mainScreen.add(new FactionScreenGUI(), "Factions");
		mainScreen.add(inventory, "Inventory");

		this.add(mainScreen, BorderLayout.LINE_START);
		cl = (CardLayout) mainScreen.getLayout();
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
			Display.displayGame();
		}
		
		if (id == 3) {
			inventory.tick();
		}

		switch (id) {
		case 0: {
			// Quests
			if (InputHandler.MouseX > 4 && InputHandler.MouseX < 445
					&& InputHandler.MouseY > 504 && InputHandler.MouseY < 740) {
				if (InputHandler.MouseButton == 1) {
					InputHandler.MouseButton = 0;
					SoundHandler.sound.play(SoundHandler.sound.click);
					cl.show(mainScreen, "Quests");
					this.id = 1;
					repaint();
				}

			}
			// Factions
			if (InputHandler.MouseX > 459 && InputHandler.MouseX < 904
					&& InputHandler.MouseY > 505 && InputHandler.MouseY < 740) {
				if (InputHandler.MouseButton == 1) {
					InputHandler.MouseButton = 0;
					SoundHandler.sound.play(SoundHandler.sound.click);
					cl.show(mainScreen, "Factions");
					this.id = 2;
					repaint();
				}
			}

			// Inventory
			if (InputHandler.MouseX > 4 && InputHandler.MouseX < 360
					&& InputHandler.MouseY > 40 && InputHandler.MouseY < 458) {
				if (InputHandler.MouseButton == 1) {
					InputHandler.MouseButton = 0;
					SoundHandler.sound.play(SoundHandler.sound.click);
					cl.show(mainScreen, "Inventory");
					this.id = 3;
					repaint();
				}

			}
		}
		case 1: {
			// Back Arrow
			if (InputHandler.MouseX > 16 && InputHandler.MouseX < 214
					&& InputHandler.MouseY > 17 && InputHandler.MouseY < 87) {
				if (InputHandler.MouseButton == 1) {
					InputHandler.MouseButton = 0;
					SoundHandler.sound.play(SoundHandler.sound.click);
					cl.show(mainScreen, "Main");
					this.id = 0;
					repaint();
				}

			}
		}
		case 2: {
			// Back Arrow
			if (InputHandler.MouseX > 16 && InputHandler.MouseX < 214
					&& InputHandler.MouseY > 17 && InputHandler.MouseY < 87) {
				if (InputHandler.MouseButton == 1) {
					InputHandler.MouseButton = 0;
					SoundHandler.sound.play(SoundHandler.sound.click);
					cl.show(mainScreen, "Main");
					this.id = 0;
					repaint();
				}

			}
		}
		case 3: {

			// Back Arrow
			if (InputHandler.MouseX > 16 && InputHandler.MouseX < 214
					&& InputHandler.MouseY > 17 && InputHandler.MouseY < 87) {
				if (InputHandler.MouseButton == 1) {
					InputHandler.MouseButton = 0;
					SoundHandler.sound.play(SoundHandler.sound.click);
					cl.show(mainScreen, "Main");
					this.id = 0;
					repaint();
				}

			}

		}
		}
	}

	public void paint(Graphics g) {
		super.paint(g);
		if (id == 0) {
			int xOffset = image.getWidth() / 2 - 75;
			int yOffset = image.getHeight() / 2 - 50;

			player.renderDisplay(screen, 16);

			for (int y = 0; y < screen.height; y++) {
				for (int x = 0; x < screen.width; x++) {
						pixels[x + y * image.getWidth()] = screen.pixels[x + y * screen.width];
				}

			}

			g.drawImage(image, xOffset, yOffset, image.getWidth(),
					image.getHeight(), this);

			g.setColor(Color.WHITE);
			g.setFont(new Font("Verdana", 0, 30));
			g.drawString(player.toString(), 20, 30);
			g.drawString("Quests", 159, 497);

			int offset = 0;
			for (Quest q : player.activeQuests) {
				g.drawString(q.toString(), 10, 550 + offset);
				offset += 50;
			}

			g.drawString("Factions", 606, 497);
		}
	}
}
