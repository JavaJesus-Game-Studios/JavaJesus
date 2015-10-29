package game.gui.slots;

import game.Game;
import game.entities.Player;
import game.graphics.Screen;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class PlayerSlotGUI extends Slot {

	private static final long serialVersionUID = 1L;

	private Player player;
	private int width;
	private int height;

	private Screen screen;
	public BufferedImage image2;
	private int[] pixels;
	protected int[] colors = new int[6 * 6 * 6];

	public PlayerSlotGUI(Player player) {
		super("/GUI/GUI_Inventory/GUI_PLAYER.png");
		width = image.getWidth();
		height = image.getHeight();
		image2 = new BufferedImage(width - 50, height - 300,
				BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt) image2.getRaster().getDataBuffer()).getData();
		this.player = player;
		this.setPreferredSize(new Dimension(width, height));
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
		screen = new Screen(width - 50, height - 300);
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int xOffset = image.getWidth() / 2 - image2.getWidth() / 2 + 10;
		int yOffset = image.getHeight() / 2 - image2.getHeight() / 2;

		player.renderDisplay(screen, 25);

		for (int y = 0; y < screen.height; y++) {
			for (int x = 0; x < screen.width; x++) {
				pixels[x + y * (width - 50)] = screen.pixels[x + y
						* screen.width];
			}

		}

		g.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), this);
		g.drawImage(image2, xOffset, yOffset, width - 50, height - 300, null);
		g.setColor(Color.YELLOW);
		g.setFont(new Font(Game.FONT_NAME, 0, 50));
		g.drawString(player.toString(), 50, 65);
	}

}
