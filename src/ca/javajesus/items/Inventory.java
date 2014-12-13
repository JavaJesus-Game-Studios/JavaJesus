package ca.javajesus.items;

import java.util.ArrayList;
import java.util.List;

import ca.javajesus.game.entities.Player;

public class Inventory {

	public List<Item> items = new ArrayList<Item>();
	private Player player;

	public Inventory(Player player) {
		this.player = player;
	}

	public void addItem(Item item) {
		items.add(item);
	}

	public void removeItem(Item item) {
		items.remove(item);
	}

	public void sortItemsAlphabetically() {

	}

	public void sortItemsByID() {

	}

}
