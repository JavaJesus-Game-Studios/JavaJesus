package ca.javajesus.level.tile;

import java.util.Random;

import ca.javajesus.game.gfx.Colors;

public class TreeTile extends MultiTile {

	private static Random random = new Random();

	/* Stephen, fix all the colors */
	public static Tile LEAF1 = new BaseTile(10, 0, 7, getColor(), 1);
	public static Tile LEAF2 = new BaseTile(11, 1, 7, getColor(), 2);
	public static Tile LOG1 = new BaseTile(12, 0, 8, Colors.get(131, 000,
			Colors.toHex("#BFAD47"), 1), 3);
	public static Tile LOG2 = new BaseTile(13, 1, 8, Colors.get(131, 000,
			Colors.toHex("#BFAD47"), 1), 4);
	public static Tile LOG3 = new BaseTile(14, 0, 9, Colors.get(131, 000,
			Colors.toHex("#BFAD47"), 1), 5);
	public static Tile LOG4 = new BaseTile(15, 1, 9, Colors.get(131, 000,
			Colors.toHex("#BFAD47"), 1), 6);
	
	protected static Tile[] blockList = { LEAF1, LEAF2, LOG1, LOG2, LOG3, LOG4 };

	public TreeTile(int id, int levelColour, int width, int height) {
		super(id, levelColour, width, height, blockList);
	}

	private static int getColor() {
		switch (random.nextInt(5)) {
		case 0:
			return Colors.get(-1, 444, 222, 111);
		case 1:
			return 1;
		case 2:
			return 1;

		case 3:
			return 1;
		default:
			return Colors.get(-1, 444, 222, 111);
		}
	}

}
