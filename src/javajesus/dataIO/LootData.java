package javajesus.dataIO;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javajesus.items.Item;

/*
 * Loads loot data into chest inventories
 */
public class LootData {

	// directory for loot tables
	private static final String DIR = "/WORLD_DATA/LOOT_DATA/";

	// for dealing with item chances
	private static final Random random = new Random();

	/**
	 * Loads the items into the array list
	 * 
	 * @param type - which table to load
	 * @param contents - where the items are loaded into
	 */
	public static final void load(byte type, ArrayList<Item> contents) {

		// create the input stream from class path loot table
		try (BufferedInputStream in = new BufferedInputStream(LootData.class.getResourceAsStream(DIR + type))) {

			// now load in the Items
			while (in.available() >= 3) {

				// load the item and its data
				Item e = Item.getItem(in.read());
				int amount = in.read();
				int chance = in.read();
				
				// add it amount times
				for (int i = 0; i < amount; i++) {

					// add it only if it passes a probability check
					if (random.nextInt(100) < chance) {
						contents.add(e);
					}

				}
			}

		} catch (IOException e) {
			System.err.println("Loot Not Loaded");
			e.printStackTrace();
		}
	}

}
