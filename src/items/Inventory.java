package items;

import game.entities.Player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Inventory implements Serializable {

	private static final long serialVersionUID = 2802272780270201723L;
	
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

	public void equip(Armor armor, Player player) {
		player.equip(armor);
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
		addItem(Item.shortSword);
		addItem(Item.longSword);
		addItem(Item.claymore);
		addItem(Item.sabre);
		addItem(Item.heavenlySword);
		addItem(Item.heavenlyShortSword);
		addItem(Item.kingSword);
		
		addItem(Item.revolver);
		addItem(Item.laserRevolver);
		addItem(Item.assaultRifle);
		addItem(Item.shotgun);
		addItem(Item.crossBow);
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
		} else if (item instanceof Armor) {
			misc.add(item);
		} else {
			usables.add(item);
		}
		items.add(item);
	}

	public void removeItem(Item item) {
		String ident = item.toString();
		for (Item e : items) {
		    if (e.toString().equals(ident)) {
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
					new int[] { 0, 0, 0 }, "Filler object"));
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
					new int[] { 0, 0, 0 }, "Filler object"));
			tempNum = Integer.MAX_VALUE;
		}
		for (int i = 0; i < tempList.length; i++)
			items.remove(0);
	}

}
