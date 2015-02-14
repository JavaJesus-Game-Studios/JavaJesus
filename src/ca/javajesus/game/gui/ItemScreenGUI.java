package ca.javajesus.game.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JPanel;

import ca.javajesus.game.entities.Player;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.items.Item;

public class ItemScreenGUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private Player player;
	private int width = 900;
	private int height = 250;

	private Screen screen;
	protected BufferedImage image = new BufferedImage(width, height,
			BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer())
			.getData();
	protected int[] colors = new int[6 * 6 * 6];
	
	public ItemScreenGUI(Player player) {
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

	public void paint(Graphics g) {
		for (Item e: player.inventory.items) {
			e.render(screen);
		}
		for (int y = 0; y < screen.height; y++) {
			for (int x = 0; x < screen.width; x++) {
				int colorCode = screen.pixels[x + y * screen.width];
				if (colorCode < 255)
					pixels[x + y * width] = colors[colorCode];
			}

		} 
		g.drawImage(image, 0, 0, width, height, null);
	}

}
