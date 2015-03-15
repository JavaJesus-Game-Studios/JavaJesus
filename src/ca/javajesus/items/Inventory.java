package ca.javajesus.items;

import java.util.ArrayList;
import java.util.List;

import ca.javajesus.game.entities.Player;
import ca.javajesus.game.gfx.Colors;

public class Inventory {

	public List<Item> items = new ArrayList<Item>();
	public List<Item> guns = new ArrayList<Item>();
	public List<Item> swords = new ArrayList<Item>();
	public List<Item> usables = new ArrayList<Item>();
	public List<Item> misc = new ArrayList<Item>();

	public Inventory() {
		giveDefaultItems();
	}

	public Sword getSword(Player player) {
		for (Item e : items) {
			if (e instanceof Sword) {
				((Sword) e).addPlayer(player);
				return (Sword) e;
			}
		}
		return null;
	}

	public void equip(Item item, Player player) {
		items.remove(item);
		items.add(0, item);
		if (!(item instanceof Gun || item instanceof Sword)) {
			removeItem(item);
			player.changeHealth(10);
			player.stamina = player.startStamina;
			if (player.getHealth() > player.getStartHealth()) {
				player.heal();
			}
		}
		player.equip();
	}

	public Gun getGun(Player player) {
		for (Item e : items) {
			if (e instanceof Gun) {
				if (e instanceof Bazooka) {
					((Bazooka) e).addPlayer(player);
				}
				return (Gun) e;
			}
		}
		return null;
	}

	private void giveDefaultItems() {
		addItem(Item.apple);

	}

	public void addItem(Item item) {
		int num = item.id;
		for (Item e : items) {
			if (e.id == num) {
				e.amount++;
				return;
			}
		}
		if (item instanceof Gun) {
			guns.add(item);
		} else if (item instanceof Sword) {
			swords.add(item);
		} else {
			usables.add(item);
		}
		items.add(item);
	}

	public void removeItem(Item item) {
		int num = item.id;
		for (Item e : items) {
			if (e.id == num) {
				if (item.amount > 1) {
					item.amount--;
					return;
				}
			}
		}
		if (item instanceof Gun) {
			guns.remove(item);
		} else if (item instanceof Sword) {
			swords.remove(item);
		} else {
			usables.remove(item);
		}
		items.remove(item);
	}

	public void sortItemsAlphabetically() {
		int[] tempList = new int[items.size()];
		String tempString = items.get(0).name;
		for (int i = 0; i < tempList.length; i++) {
			for (int j = 1; j < tempList.length; j++) {
				if (tempString.compareToIgnoreCase(items.get(j).name) > 0) {
					tempString = items.get(j).name;
					tempList[i] = j;
				}
			}
			items.add(items.get(tempList[i]));
			items.set(tempList[i], new Item("ZZZZZZZ", Integer.MAX_VALUE, 0, 0,
					Colors.get(-1, 500, 500, Colors.fromHex("#FF0000")),
					"Filler object"));
			tempString = items.get(0).name;
		}
		for (int i = 0; i < tempList.length; i++)
			items.remove(0);
	}

	public void sortItemsByID() {
		int[] tempList = new int[items.size()];
		int tempNum = Integer.MAX_VALUE;
		for (int i = 0; i < tempList.length; i++) {
			for (int j = 0; j < tempList.length; j++) {
				if (items.get(j).id < tempNum) {
					tempNum = items.get(j).id;
					tempList[i] = j;
				}
			}
			items.add(items.get(tempList[i]));
			items.set(tempList[i], new Item("ZZZZZZZ", Integer.MAX_VALUE, 0, 0,
					Colors.get(-1, 500, 500, Colors.fromHex("#FF0000")),
					"Filler object"));
			tempNum = Integer.MAX_VALUE;
		}
		for (int i = 0; i < tempList.length; i++)
			items.remove(0);
	}

}
