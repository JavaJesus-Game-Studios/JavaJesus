package game.gui.overview;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

import game.Display;
import game.entities.Player;
import game.gui.ScreenGUI;
import items.Inventory;
import items.Item;

public class InventoryGUI extends ScreenGUI {

	private static final long serialVersionUID = 1L;

	private String hoverText = "Inventory";

	private Inventory inventory;

	private static final double TOP_PANEL_HEIGHT_RATIO = 0.15;
	private static final double SMALL_PANEL_WIDTH_RATIO = 0.3;
	private static final int NUM_ROWS = 4;

	private static InventoryRow r1, r2, r3, r4;

	private TopPanel topPanel;
	
	private static Player player;

	public InventoryGUI(Player player) {
		
		InventoryGUI.player = player;

		inventory = player.getInventory();

		int width = Display.FRAME_WIDTH;
		int height = Display.FRAME_HEIGHT;

		this.setLayout(new BorderLayout());
		BorderLayout l = (BorderLayout) this.getLayout();
		l.setVgap(-10);

		topPanel = new TopPanel(width, (int) (height * TOP_PANEL_HEIGHT_RATIO), hoverText);
		this.add(topPanel, BorderLayout.NORTH);

		JPanel body = new JPanel(new GridLayout(NUM_ROWS, 1));
		((GridLayout) body.getLayout()).setVgap(-10);

		r1 = new InventoryRow("Guns", (ArrayList<Item>) inventory.getGuns());
		r2 = new InventoryRow("Swords", (ArrayList<Item>) inventory.getSwords());
		r3 = new InventoryRow("Usables", (ArrayList<Item>) inventory.getConsumables());
		r4 = new InventoryRow("Misc", (ArrayList<Item>) inventory.getMisc());

		body.add(r1);
		body.add(r2);
		body.add(r3);
		body.add(r4);

		update();

		this.add(body, BorderLayout.WEST);
		
		this.setPreferredSize(new Dimension(width, height));
		this.validate();

	}

	public void updateText(String text) {
		topPanel.updateText(text);
	}

	public static void update() {
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
			((FlowLayout) this.getLayout()).setHgap(0);

			this.add(new InventorySmallPanel((int) (width * SMALL_PANEL_WIDTH_RATIO), height / NUM_ROWS, name));

			p = new InventoryLongPanel((int) (width * (1 - SMALL_PANEL_WIDTH_RATIO)), height / NUM_ROWS,
					InventoryGUI.this, items, player);

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
