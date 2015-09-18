package ca.javajesus.game.gui.inventory;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.IOException;

import javax.imageio.ImageIO;

import ca.javajesus.game.Display;
import ca.javajesus.game.InputHandler;
import ca.javajesus.game.SoundHandler;
import ca.javajesus.game.entities.Player;
import ca.javajesus.game.graphics.Screen;
import ca.javajesus.game.gui.ScreenGUI;
import ca.javajesus.items.Armor;
import ca.javajesus.items.Item;

public class ItemScreenGUI extends ScreenGUI {

	private static final long serialVersionUID = 1L;
	private Player player;
	private final int width = (int) (Display.FRAME_WIDTH + 10);
	private final int height = (int) (Display.FRAME_HEIGHT + 85);
	private Screen screen;
	private Screen screen3;
	private Screen screen4;
	private Screen screen5;
	private final int rowWidth = (int) (630.0 / 900 * Display.FRAME_WIDTH);
	private final int rowHeight = (int) (140.0 / (300.0 / 12 * 9) * Display.FRAME_HEIGHT);
	protected BufferedImage image2 = new BufferedImage(rowWidth, rowHeight,
			BufferedImage.TYPE_INT_RGB);
	protected BufferedImage image3 = new BufferedImage(rowWidth, rowHeight,
			BufferedImage.TYPE_INT_RGB);
	protected BufferedImage image4 = new BufferedImage(rowWidth, rowHeight,
			BufferedImage.TYPE_INT_RGB);
	protected BufferedImage image5 = new BufferedImage(rowWidth, rowHeight,
			BufferedImage.TYPE_INT_RGB);
	protected BufferedImage image;
	private int[] pixels = ((DataBufferInt) image2.getRaster().getDataBuffer())
			.getData();
	private int[] pixels3 = ((DataBufferInt) image3.getRaster().getDataBuffer())
			.getData();
	private int[] pixels4 = ((DataBufferInt) image4.getRaster().getDataBuffer())
			.getData();
	private int[] pixels5 = ((DataBufferInt) image5.getRaster().getDataBuffer())
			.getData();
	InputHandler input;
	private String hoverText = "Inventory";
	private boolean isHovering = false;

	public ItemScreenGUI(Player player, InputHandler input) {
		this.player = player;
		this.input = input;
		try {
			this.image = ImageIO.read(MapScreenGUI.class
					.getResource("/GUI/GUI_Inventory/GUI_Inventory_Page.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.setPreferredSize(new Dimension(width, height));
		screen = new Screen(rowWidth, rowHeight);
		screen3 = new Screen(rowWidth, rowHeight);
		screen4 = new Screen(rowWidth, rowHeight);
		screen5 = new Screen(rowWidth, rowHeight);
	}

	public void paint(Graphics g) {
		int offset = 0;
		for (Item e : player.inventory.guns) {
			e.render(screen, 20 + offset, 20);
			offset += 50;
		}
		offset = 0;
		for (Item e : player.inventory.swords) {
			e.render(screen3, 20 + offset, 20);
			offset += 50;
		}
		offset = 0;
		for (Item e : player.inventory.usables) {
			e.render(screen4, 20 + offset, 20);
			offset += 50;
		}
		offset = 0;
		for (Item e : player.inventory.misc) {
			e.render(screen5, 20 + offset, 20);
			offset += 50;
		}
		for (int y = 0; y < screen.height; y++) {
			for (int x = 0; x < screen.width; x++) {
				pixels[x + y * rowWidth] = screen.pixels[x + y * screen.width];
			}

		}
		for (int y = 0; y < screen3.height; y++) {
			for (int x = 0; x < screen3.width; x++) {
				pixels3[x + y * rowWidth] = screen3.pixels[x + y
						* screen3.width];
			}

		}
		for (int y = 0; y < screen4.height; y++) {
			for (int x = 0; x < screen4.width; x++) {
				pixels4[x + y * rowWidth] = screen4.pixels[x + y
						* screen4.width];
			}

		}
		for (int y = 0; y < screen5.height; y++) {
			for (int x = 0; x < screen5.width; x++) {
				pixels5[x + y * rowWidth] = screen5.pixels[x + y
						* screen5.width];
			}

		}

		g.drawImage(image, 0, 0, width, height, this);
		g.drawImage(image2, 217, 107, rowWidth, rowHeight, null);
		g.drawImage(image3, 217, 265, rowWidth, rowHeight, null);
		g.drawImage(image4, 217, 423, rowWidth, rowHeight, null);
		g.drawImage(image5, 217, 581, rowWidth, rowHeight, null);

		g.setColor(Color.white);
		g.setFont(new Font("Verdana", 0, 50));
		if (isHovering) {
			g.drawString(hoverText, 350, 70);
		} else
			g.drawString("Inventory", 350, 70);
		g.drawString("Guns", 40, 200);
		g.drawString("Swords", 18, 350);
		g.drawString("Items", 38, 500);
		g.drawString("Misc", 40, 660);
	}

	public void tick() {

		isHovering = false;
		repaint();
		for (int i = 0; i < player.inventory.guns.size(); i++) {
			if (InputHandler.MouseX > (218 + i * 48)
					&& InputHandler.MouseX < (266 + i * 48)
					&& InputHandler.MouseY > 107 && InputHandler.MouseY < 155) {
				isHovering = true;
				hoverText = player.inventory.guns.get(i).name + " Qty: " + player.inventory.guns.get(i).amount;
				repaint();
				if (InputHandler.MouseButton == 1) {
					InputHandler.MouseButton = 0;
					SoundHandler.sound.play(SoundHandler.sound.click);
					player.inventory
							.equip(player.inventory.guns.get(i), player);
					screen.clear();
					repaint();
				}

			}
		}
		for (int i = 0; i < player.inventory.swords.size(); i++) {
			if (InputHandler.MouseX > (218 + i * 48)
					&& InputHandler.MouseX < (266 + i * 48)
					&& InputHandler.MouseY > 265 && InputHandler.MouseY < 313) {
				isHovering = true;
				hoverText = player.inventory.swords.get(i).name + " Qty: " + player.inventory.swords.get(i).amount;
				repaint();
				if (InputHandler.MouseButton == 1) {
					InputHandler.MouseButton = 0;
					SoundHandler.sound.play(SoundHandler.sound.click);
					player.inventory.equip(player.inventory.swords.get(i),
							player);
					screen3.clear();
					repaint();
				}

			}
		}
		for (int i = 0; i < player.inventory.usables.size(); i++) {
			if (InputHandler.MouseX > (218 + i * 48)
					&& InputHandler.MouseX < (266 + i * 48)
					&& InputHandler.MouseY > 423 && InputHandler.MouseY < 471) {
				isHovering = true;
				hoverText = player.inventory.usables.get(i).name + " Qty: " + player.inventory.usables.get(i).amount;
				repaint();
				if (InputHandler.MouseButton == 1) {
					InputHandler.MouseButton = 0;
					SoundHandler.sound.play(SoundHandler.sound.click);
					player.inventory.equip(player.inventory.usables.get(i),
							player);
					screen4.clear();
					repaint();
				}

			}
		}
		for (int i = 0; i < player.inventory.misc.size(); i++) {
			if (InputHandler.MouseX > (218 + i * 48)
					&& InputHandler.MouseX < (266 + i * 48)
					&& InputHandler.MouseY > 581 && InputHandler.MouseY < 629) {
				isHovering = true;
				hoverText = player.inventory.misc.get(i).name + " Qty: " + player.inventory.swords.get(i).amount;
				repaint();
				if (InputHandler.MouseButton == 1) {
					InputHandler.MouseButton = 0;
					SoundHandler.sound.play(SoundHandler.sound.click);
					if (player.inventory.misc.get(i) instanceof Armor) {
						player.inventory.equip(
								(Armor) player.inventory.misc.get(i), player);
					} else
						player.inventory.equip(player.inventory.misc.get(i),
								player);
					screen5.clear();
					repaint();
				}

			}
		}

	}

}
