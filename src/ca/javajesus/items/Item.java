package ca.javajesus.items;

import java.util.ArrayList;
import java.util.List;

public abstract class Item {
	
	public List<Item> items = new ArrayList<Item>();
	public String name;
	public int id;
	
	public Item(String name, int id) {
		this.name = name;
		this.id = id;
		items.add(this);
	}

}
