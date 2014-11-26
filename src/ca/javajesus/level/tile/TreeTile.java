package ca.javajesus.level.tile;

//import java.util.Random;

import ca.javajesus.game.gfx.Colors;

public class TreeTile extends MultiTile {

	//private static Random random = new Random();

	public static final Tile LEAF1 = new BaseTile(10, 0, 7, Colors.get(131, 111,
			-1, Colors.toHex("#008015")), 1);
	public static final Tile LEAF2 = new BaseTile(11, 1, 7, Colors.get(131, 111,
			-1 , Colors.toHex("#008015")), 2);
	public static final Tile LOG1 = new BaseTile(12, 0, 8, Colors.get(131, 111,
			Colors.toHex("#624300"), 141), 3);
	public static final Tile LOG2 = new BaseTile(13, 1, 8, Colors.get(131, 111,
			Colors.toHex("#624300"), 141), 4);
	public static final Tile LOG3 = new BaseTile(14, 0, 9, Colors.get(131, 111,
			Colors.toHex("#624300"), 141), 5);
	public static final Tile LOG4 = new BaseTile(15, 1, 9, Colors.get(131, 111,
			Colors.toHex("#624300"), 141), 6);
	
	protected static Tile[] blockList = { LEAF1, LEAF2, LOG1, LOG2, LOG3, LOG4 };

	public TreeTile(int id, int levelColour, int width, int height) {
		super(id, levelColour, width, height, blockList);
	}

	// Maybe use this later
	/*private static int getColor() {
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
	}*/

}
