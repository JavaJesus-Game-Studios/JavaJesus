package ca.javajesus.game.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

import ca.javajesus.game.Game;
import ca.javajesus.game.InputHandler;
import ca.javajesus.game.entities.Player;
import ca.javajesus.game.gui.slots.PlayerSlotGUI;
import ca.javajesus.game.gui.slots.QuestSlotGUI;
import ca.javajesus.game.gui.slots.FactionsSlotGUI;

public class InventoryGUI extends ScreenGUI {

	private static final long serialVersionUID = 1L;

	public ItemScreenGUI inventory;
	public int id = 0;
	private JPanel activeScreen;
	private Player player;
	JPanel panel = new JPanel(new BorderLayout(0, 0));
	JPanel slots = new JPanel(new GridLayout(2, 0));

	public InventoryGUI(Player player) {

		inventory = new ItemScreenGUI(player);
		this.input = new InputHandler(this);

		JPanel p1 = new FactionsSlotGUI();
		JPanel p2 = new QuestSlotGUI();
		JPanel p3 = new MapGUI();
		this.player = player;

		this.activeScreen = new PlayerSlotGUI(player, 0, 0.75, 1.3);
		((PlayerSlotGUI) activeScreen).setScale(0.75);
		((PlayerSlotGUI) activeScreen).setYScale(1.3);

		this.setFocusable(true);
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(Game.WIDTH * Game.SCALE,
				Game.HEIGHT * Game.SCALE));

		slots.add(p3);
		JPanel bottom = new JPanel(new BorderLayout());
		bottom.add(p2, BorderLayout.LINE_START);
		bottom.add(p1, BorderLayout.CENTER);
		slots.add(bottom);

		panel.add(activeScreen, BorderLayout.LINE_START);
		panel.add(slots, BorderLayout.CENTER);

		this.add(panel, BorderLayout.LINE_START);

	}

	public void tick() {
		if (input.i.isPressed()) {
			input.i.toggle(false);
			Game.displayGame();
		}
		// Quests
		if (InputHandler.MouseX > 487 && InputHandler.MouseX < 688
				&& InputHandler.MouseY > 424 && InputHandler.MouseY < 723) {
			if (InputHandler.MouseButton == 1) {
				InputHandler.MouseButton = 0;
				panel.removeAll();
				panel.add(inventory, BorderLayout.LINE_START);
				panel.add(slots, BorderLayout.CENTER);
				revalidate();
				repaint();
			}

		}
		// Factions
		if (InputHandler.MouseX > 704 && InputHandler.MouseX < 905
				&& InputHandler.MouseY > 424 && InputHandler.MouseY < 723) {
			if (InputHandler.MouseButton == 1) {
				InputHandler.MouseButton = 0;
				panel.removeAll();
				panel.add(inventory, BorderLayout.LINE_START);
				panel.add(slots, BorderLayout.CENTER);
				revalidate();
				repaint();
			}

		}

		// Player
		if (InputHandler.MouseX > 14 && InputHandler.MouseX < 464
				&& InputHandler.MouseY > 14 && InputHandler.MouseY < 73) {
			if (InputHandler.MouseButton == 1) {
				InputHandler.MouseButton = 0;
				panel.removeAll();
				panel.add(activeScreen, BorderLayout.LINE_START);
				panel.add(slots, BorderLayout.CENTER);
				revalidate();
				repaint();
			}

		}
	}
}
