package ca.javajesus.game.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

import ca.javajesus.game.Game;
import ca.javajesus.game.InputHandler;
import ca.javajesus.game.entities.Player;
import ca.javajesus.game.gui.slots.InventorySlotGUI;
import ca.javajesus.game.gui.slots.MapSlotGUI;
import ca.javajesus.game.gui.slots.PlayerSlotGUI;
import ca.javajesus.game.gui.slots.QuestSlotGUI;
import ca.javajesus.game.gui.slots.StatsSlotGUI;
import ca.javajesus.game.gui.slots.StorySlotGUI;

public class InventoryGUI extends ScreenGUI {

	private static final long serialVersionUID = 1L;

	public ItemScreenGUI inventory;
	public int id = 0;
	private JPanel activeScreen;

	public InventoryGUI(Player player) {

		inventory = new ItemScreenGUI(player);
		this.input = new InputHandler(this);

		JPanel p1 = new StatsSlotGUI();
		JPanel p2 = new QuestSlotGUI();
		JPanel p3 = new MapGUI();

		this.activeScreen = new PlayerSlotGUI(player, 0, 0.75, 1.3);
		((PlayerSlotGUI) activeScreen).setScale(0.75);
		((PlayerSlotGUI) activeScreen).setYScale(1.3);

		this.setFocusable(true);
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(Game.WIDTH * Game.SCALE,
				Game.HEIGHT * Game.SCALE));

		JPanel panel = new JPanel(new BorderLayout(0, 0));

		JPanel slots = new JPanel(new GridLayout(2, 2));
		slots.add(p3);
		slots.add(p1);
		slots.add(p2);

		panel.add(activeScreen, BorderLayout.LINE_START);
		panel.add(slots, BorderLayout.CENTER);

		this.add(panel, BorderLayout.LINE_START);

	}

	public void tick() {
		if (input.i.isPressed()) {
			input.i.toggle(false);
			Game.displayGame();
		}
		switch (id) {
		default:
			id = 0;
		}
	}
}
