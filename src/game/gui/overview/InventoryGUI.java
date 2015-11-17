package game.gui.overview;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import game.Display;
import game.Game;
import game.gui.ScreenGUI;
import items.Inventory;
import items.Item;

public class InventoryGUI extends ScreenGUI {

	private static final long serialVersionUID = 1L;

	private String hoverText = "Inventory";

	private Inventory inventory;

	private static final double TOP_PANEL_HEIGHT_RATIO = 0.15;
	private static final double SMALL_PANEL_WIDTH_RATIO = 0.3;
	private static final int NUM_ROWS = 5;

	private InventoryRow r1, r2, r3, r4;

	public InventoryGUI() {

		inventory = Game.player.inventory;

		int width = Display.FRAME_WIDTH;
		int height = Display.FRAME_HEIGHT;

		this.setLayout(new BorderLayout());

		this.add(new TopPanel(width, (int) (height * TOP_PANEL_HEIGHT_RATIO), hoverText), BorderLayout.NORTH);

		JPanel body = new JPanel();

		body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));
		r1 = new InventoryRow("Guns", (ArrayList<Item>) inventory.guns);
		r2 = new InventoryRow("Swords", (ArrayList<Item>) inventory.swords);
		r3 = new InventoryRow("Usables", (ArrayList<Item>) inventory.usables);
		r4 = new InventoryRow("Misc", (ArrayList<Item>) inventory.misc);

		body.add(r1);
		body.add(r2);
		body.add(r3);
		body.add(r4);

		update();

		this.add(body, BorderLayout.CENTER);

		this.setPreferredSize(new Dimension(width, height));
		this.validate();

	}

	public void update() {
		r1.update();
		r2.update();
		r3.update();
		r4.update();
	}

	private class InventoryRow extends JPanel {

		private static final long serialVersionUID = 1L;

		private InventoryLongPanel p;

		public InventoryRow(String name, ArrayList<Item> items) {

			int width = Display.FRAME_WIDTH;
			int height = Display.FRAME_HEIGHT;

			this.add(new InventorySmallPanel((int) (width * SMALL_PANEL_WIDTH_RATIO), height / NUM_ROWS, name));

			p = new InventoryLongPanel((int) (width * (1 - SMALL_PANEL_WIDTH_RATIO)), height / NUM_ROWS,
					InventoryGUI.this, items);

			this.add(p);

			this.setPreferredSize(new Dimension(width, height / NUM_ROWS));
			this.validate();
		}

		public void update() {
			p.update();
		}

	}

	@Override
	public void tick() {

	}

}
