package javajesus.items;

import javajesus.graphics.SpriteSheet;

/*
 * Armor is an equippable item that has a defense value
 */
public class Armor extends Item {

	// serialization
	private static final long serialVersionUID = 5210600935647831893L;
	
	// defense to add
	private int defense;
	
	// spritesheet row
	private int yRow;
	
	// kind of armor to be rendered
	private ArmorSet armor;
	
	// armor spritesheet
	private SpriteSheet sheet;

	/**
	 * Armor()
	 * Creates an armor item that can be equipped to
	 * modify player defense and rendering
	 * 
	 * @param name - name of the item
	 * @param id - id of the item
	 * @param xTile - x tile on spritesheet (GUI DISPLAY)
	 * @param yTile - y tile on spritesheet (GUI DISPLAY)
	 * @param color - color set (GUI DISPLAY)
	 * @param description - item description
	 * @param armor - kind of armor
	 */
	public Armor(String name, int id, int xTile, int yTile, int[] color,
			String description, ArmorSet armor) {
		super(name, id, xTile, yTile, color, description, true);
		
		// instance data
		this.armor = armor;
		calcStats();
	}

	/**
	 * @return defense value of this armor when equipped
	 */
	public int getDefense() {
		return defense;
	}

	/**
	 * @return rendering offset
	 */
	public int getRow() {
		return yRow;
	}
	
	/**
	 * @return type of armor equipped
	 */
	public ArmorSet getType() {
		return armor;
	}

	/**
	 * Helper method for calculating data
	 */
	private void calcStats() {
		switch (armor) {
		case VEST:
			defense = 3;
			yRow = 12;
			sheet = SpriteSheet.playerVestedGuns;
			return;
		case KNIGHT:
			defense = 5;
			yRow = 18;
			sheet = SpriteSheet.playerKnightedGuns;
			return;
		case HORNED:
			defense = 7;
			yRow = 25;
			sheet = SpriteSheet.playerHornedGuns;
			return;
		case OWL:
			defense = 9;
			yRow = 27;
			sheet = SpriteSheet.playerIstrahiimGuns;
			return;
		}
	}
	
	/**
	 * @return Player-equipped Armor spritesheet
	 */
	public SpriteSheet getGunSpritesheet() {
		return sheet;
	}

	/**
	 * Types if armor to equip
	 */
	public enum ArmorSet {
		VEST, KNIGHT, HORNED, OWL
	}

}
