package game.gui.overview;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import items.Item;

public class InventoryLongPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private BufferedImage image;
	private String file = "/GUI/GUI_Inventory/GUI_long_panel.png";

	private int size;

	private static final int SIZE_OFFSET = 5;

	private InventoryGUI inven;

	private ArrayList<Item> items = new ArrayList<>();

	private ArrayList<Item> inventory;

	public InventoryLongPanel(int width, int height, InventoryGUI i, ArrayList<Item> inventory) {

		this.inven = i;
		this.inventory = inventory;

		try {
			this.image = ImageIO.read(InventoryLongPanel.class.getResource(file));
		} catch (Exception e) {
			e.printStackTrace();
		}

		size = width / (height - SIZE_OFFSET);

		this.setPreferredSize(new Dimension(width, height));

		this.validate();

	}

	public void update() {
		for (Item e : inventory) {
			if (!items.contains(e)) {
				items.add(e);
				this.add(new InventoryItemPanel(size, size, e, inven));
			}
		}
		this.repaint();
	}

	public void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
	}

}
