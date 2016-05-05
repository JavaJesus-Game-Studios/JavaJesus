package items;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/*
 * A container that holds various items 
 */
public class Inventory implements Serializable {

	private static final long serialVersionUID = 2802272780270201723L;

	// list of all items
	private final List<Item> items = new ArrayList<Item>();

	// all guns
	private final List<Item> guns = new ArrayList<Item>();

	// all swords
	private final List<Item> swords = new ArrayList<Item>();

	// all usables
	private final List<Item> usables = new ArrayList<Item>();

	// everything else
	private final List<Item> misc = new ArrayList<Item>();

	// the most recently used sword
	private Sword selectedSword;

	// the most recently used gun
	private Gun selectedGun;

	// the current armor in use
	private Armor selectedArmor;

	/**
	 * Creates an inventory
	 */
	public Inventory() {
		giveDefaultItems();
	}

	/**
	 * @return the most recently used sword
	 */
	public final Sword getSword() {
		return selectedSword;
	}

	/**
	 * @return the most recently used gun
	 */
	public final Gun getGun() {
		return selectedGun;
	}

	/**
	 * @return the armor in use
	 */
	public final Armor getArmor() {
		return selectedArmor;
	}

	/**
	 * @param gun
	 *            the selected gun
	 */
	public final void select(Gun gun) {
		selectedGun = gun;
	}

	/**
	 * @param armor
	 *            the selected armor
	 */
	public final void select(Armor armor) {
		selectedArmor = armor;
	}

	/**
	 * @param sword
	 *            the selected sword
	 */
	public final void select(Sword sword) {
		selectedSword = sword;
	}

	/**
	 * Handles what happens when an item is selected
	 * 
	 * @param item
	 *            the item clicked
	 */
	public final void select(Item item) {

		item.use();
		if (item.getQuantity() == 0) {
			remove(item);
		}

	}

	/**
	 * Initial items in the list
	 */
	private void giveDefaultItems() {

		add(Item.apple);
		add(Item.shortSword);
		add(Item.longSword);
		add(Item.claymore);
		add(Item.sabre);
		add(Item.heavenlySword);
		add(Item.heavenlyShortSword);
		add(Item.kingSword);

		add(Item.revolver);
		add(Item.laserRevolver);
		add(Item.assaultRifle);
		add(Item.shotgun);
		add(Item.crossBow);
	}

	/**
	 * Adds an item to the inventory
	 * 
	 * @param item
	 *            the new item
	 */
	public void add(Item item) {

		// checks if the item is already added
		for (Item e : items) {
			if (e == item) {
				e.take();
				return;
			}
		}

		// adds the item to the lists
		if (item instanceof Gun) {
			guns.add((Gun) item);
		} else if (item instanceof Sword) {
			swords.add((Sword) item);
		} else if (item instanceof Armor) {
			misc.add(item);
		} else {
			usables.add(item);
		}

		items.add(item);
	}

	/**
	 * Removes an item from the inventory
	 * 
	 * @param item
	 */
	public void remove(Item item) {

		if (item instanceof Gun) {
			guns.remove(item);
		} else if (item instanceof Sword) {
			swords.remove(item);
		} else {
			usables.remove(item);
		}
		items.remove(item);
	}

	/**
	 * Sorts items alphabetically
	 */
	public void sortItemsAlphabetically() {

		// like a bubble sort
		for (int i = 1; i < items.size(); i++) {

			// index of the item with the smallest name
			int low = i;

			for (int j = i + 1; j < items.size(); j++) {

				// moves the smallest name to the bottom
				if (items.get(j).getName().compareToIgnoreCase(items.get(low).getName()) < 0) {
					low = j;
				}
			}

			// swap the elements
			Item temp = items.get(i);
			items.set(i, items.get(low));
			items.set(low, temp);

		}
	}

	/**
	 * Sorts items by ID
	 */
	public void sortItemsByID() {

		// like a bubble sort
		for (int i = 1; i < items.size(); i++) {

			// index of the item with the smallest ID
			int low = i;

			for (int j = i + 1; j < items.size(); j++) {

				// moves the smallest ID to the bottom
				if (items.get(j).getId() < items.get(low).getId()) {
					low = j;
				}
			}

			// swap the elements
			Item temp = items.get(i);
			items.set(i, items.get(low));
			items.set(low, temp);

		}
	}

	/**
	 * @return a list of the guns in this inventory
	 */
	public final List<Item> getGuns() {
		return guns;
	}

	/**
	 * @return a list of the guns in this inventory
	 */
	public final List<Item> getSwords() {
		return swords;
	}

	/**
	 * @return a list of the guns in this inventory
	 */
	public final List<Item> getConsumables() {
		return usables;
	}

	/**
	 * @return a list of the guns in this inventory
	 */
	public final List<Item> getMisc() {
		return misc;
	}

}
