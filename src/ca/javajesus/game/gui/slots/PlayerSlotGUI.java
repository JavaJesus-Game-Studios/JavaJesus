package ca.javajesus.game.gui.slots;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import ca.javajesus.game.entities.Player;
import ca.javajesus.game.gfx.Screen;

public class PlayerSlotGUI extends Slot {

	private static final long serialVersionUID = 1L;

	private Player player;
	private int width;
	private int height;
	private int id = 0;

	private Screen screen;
	protected BufferedImage image2;
	private int[] pixels;
	protected int[] colors = new int[6 * 6 * 6];

	public PlayerSlotGUI(Player player, int num, double scale, double yScale) {
		super("/GUI/GUI_Inventory/GUI_Panel.png");
		this.id = num;
		width = (int) (image.getWidth()) - 60;
		height = (int) (image.getHeight());
		if (id == 0) {
			height -= 200;
		}
		if (id == 2) {
			height -= 80;
		}
		image2 = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
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
		screen = new Screen(width, height);
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int xOffset = 0;
		int yOffset = 0;
		switch (id) {
		case 0: {
			xOffset = (int) (image.getWidth() / 4.0) - 100;
			yOffset = (int) (image.getHeight() / 4.0);
			break;
		}
		case 1: {
			xOffset = (int) (image.getWidth() / 4.0) - 85;
			yOffset = (int) (image.getHeight() / 4.0) - 110;
			break;
		}
		case 2: {
			xOffset = (int) (image.getWidth() / 4.0) - 100;
			yOffset = (int) (image.getHeight() / 4.0) - 50;
			break;
		}
		}
		player.renderDisplay(screen, 5);
		for (int y = 0; y < screen.height; y++) {
			for (int x = 0; x < screen.width; x++) {
				int colorCode = screen.pixels[x + y * screen.width];
				if (colorCode < 255)
					pixels[x + y * width] = colors[colorCode];
			}

		}
		g.drawImage(image, 0, 0, (int) (image.getWidth()),
				(int) (image.getHeight()), this);
		g.drawImage(image2, xOffset, yOffset, width, height, null);
	}

}
