package ca.javajesus.items;

import java.util.ArrayList;
import java.util.List;

public class Item{
	
	public static Item MAX = new Item("ZZZZZZZZZZ", Integer.MAX_VALUE);
	public static Item test1 = new Item("Apple", 0);
	public static Item test2 = new Item("Charles", 5);
	public static Item test3 = new Item("John", 34);
	public static Item test4 = new Item("David", 6);
	
	public List<Item> items = new ArrayList<Item>();
	public String name;
	public int id;
	
	public Item(String name, int id) {
		this.name = name;
		this.id = id;
		items.add(this);
	}
	
	public String toString() {
		return name;
	}
}
