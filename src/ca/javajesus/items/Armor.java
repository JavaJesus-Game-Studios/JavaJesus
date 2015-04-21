package ca.javajesus.items;

public class Armor extends Item {

	private static final long serialVersionUID = 5210600935647831893L;
	
	private int defense;
	private int yRow;
	private ArmorSet armor;
	private int shield;

	public Armor(String name, int id, int xTile, int yTile, int[] color,
			String description, ArmorSet armor) {
		super(name, id, xTile, yTile, color, description);
		this.armor = armor;
		calcStats();
	}

	public int getShield() {
		return shield;
	}

	public int getDefense() {
		return defense;
	}

	public int getRow() {
		return yRow;
	}
	
	public ArmorSet getType() {
		return armor;
	}

	private void calcStats() {
		switch (armor) {
		case VEST:
			defense = 3;
			yRow = 12;
			shield = 10;
			return;
		case KNIGHT:
			defense = 5;
			yRow = 18;
			shield = 40;
			return;
		case HORNED:
			defense = 7;
			yRow = 25;
			shield = 80;
			return;
		case OWL:
			defense = 9;
			yRow = 27;
			shield = 200;
			return;
		}
	}

	public enum ArmorSet {
		VEST, KNIGHT, HORNED, OWL
	}

}
