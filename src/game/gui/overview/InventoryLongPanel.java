package game.gui.overview;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import items.Item;

public class InventoryLongPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	//private BufferedImage image;
	//private String file = "/GUI/GUI_Inventory/GUI_long_panel.png";

	private InventoryGUI inven;

	private ArrayList<Item> items = new ArrayList<>();

	private ArrayList<Item> inventory;

	private static final int NUM_ROW = 2, NUM_COL = 8;

	private static final int MAX_ITEMS = NUM_ROW * NUM_COL;

	public InventoryLongPanel(int width, int height, InventoryGUI i, ArrayList<Item> inventory) {

		this.inven = i;
		this.inventory = inventory;

		this.setLayout(new GridLayout(NUM_ROW, NUM_COL));

		this.setBackground(Color.BLACK);

		/*try {
			this.image = ImageIO.read(InventoryLongPanel.class.getResource(file));
		} catch (Exception e) {
			e.printStackTrace();
		}*/

		this.setPreferredSize(new Dimension(width, height));

		this.validate();

	}

	public void update() {
		// removes used items
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i) != null && !inventory.contains(items.get(i))) {
				items.remove(i);
				this.remove(i);
				i--;
			}
		}
		// adds blank items
		for (int i = items.size(); i < MAX_ITEMS; i++) {
			items.add(null);
			this.add(new JLabel(""));
		}
		// adds items
		for (int j = 0; j < inventory.size(); j++) {
			Item e = inventory.get(j);
			if (!items.contains(e)) {
				for (int i = 0; i < items.size(); i++) {
					if (items.get(i) == null) {
						items.set(i, e);
						this.remove(i);
						this.add(new InventoryItemPanel(e, inven), i);
						break;
					}
				}

			}
		}
		this.validate();
		this.repaint();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
	}

}
