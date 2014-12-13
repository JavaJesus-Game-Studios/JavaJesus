package ca.javajesus.items;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ca.javajesus.game.entities.Player;

public class Inventory {

	public List<Item> items = new ArrayList<Item>();
	private Player player;

	public Inventory(Player player) {
		this.player = player;
		this.fillTerms();
	}

	public void addItem(Item item) {
		items.add(item);
	}

	public void removeItem(Item item) {
		items.remove(item);
	}

	public void sortItemsAlphabetically() {
		Item[] tempList = new Item[items.size()];
		for(int i = 0; i < tempList.length; i++)
			tempList[i] = items.get(i);
		Arrays.sort(tempList, Item.ItemNameComparator);
		for(int i = 0; i < tempList.length; i++)
			items.add(i + tempList.length, items.get(items.indexOf(tempList[i])));
		for(int i = 0; i < tempList.length; i++)
			items.remove(0);
	}

	public void sortItemsByID(){
		Item[] tempList = new Item[items.size()];
		for(int i = 0; i < tempList.length; i++)
			tempList[i] = items.get(i);
		Arrays.sort(tempList);
		for(int i = 0; i < tempList.length; i++)
			items.add(i + tempList.length, items.get(items.indexOf(tempList[i])));
		for(int i = 0; i < tempList.length; i++)
			items.remove(0);
	}

	private void fillTerms() {
		items.add(Item.test1);
		items.add(Item.test2);
		items.add(Item.test3);
		items.add(Item.test4);
	}

	public void printlist() {
		for (int i = 0; i < items.size(); i++) {
			System.out.print(items.get(i) + " - ");
		}
	}
}
