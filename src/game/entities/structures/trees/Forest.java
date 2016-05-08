package game.entities.structures.trees;

import java.awt.Rectangle;
import java.util.Random;
import game.entities.Entity;
import game.entities.SolidEntity;
import level.Level;
import level.tile.AnimatedTile;
import level.tile.BasicSolidTile;
import level.tile.Tile;

/*
 * This class generates trees across a given area
 */
public class Forest {

	// helper variable used to space out tree generation
	private static int treeHeight;

	// used to randomly generated spacing
	private static final Random random = new Random();

	// spacing used between tree widths
	private static final int HORIZONTAL_SPACING = 23;

	/**
	 * Determines if a tree can be placed at this location
	 * 
	 * @param level
	 *            level to check
	 * @param x
	 *            the x coord
	 * @param y
	 *            the y coord
	 * @return true if the tree can be placed there
	 */
	private static boolean checkTile(Level level, int x, int y) {

		// get the tile at the location
		Tile t = level.getTile(x >> 3, y >> 3);

		// trees can't grow on solids or water
		if (t instanceof BasicSolidTile || t instanceof AnimatedTile) {
			return false;
		}

		// don't place a tree in a building
		for (Entity e : level.getEntities()) {
			if (e instanceof SolidEntity) {
				if (e.getBounds().intersects(new Rectangle(x, y, HORIZONTAL_SPACING, treeHeight))) {
					return false;
				}
			}
		}

		return true;
	}

	/**
	 * Generates a forest of trees at the specified location
	 * 
	 * @param level
	 *            the level to place it on
	 * @param x
	 *            the left corner of the forest
	 * @param y
	 *            the upper bound of the forest
	 * @param width
	 *            the width of the forest
	 * @param height
	 *            the height of the forest
	 */
	public static final void generateForest(Level level, int x, int y, int width, int height) {

		for (int i = 0; i < width; i += (HORIZONTAL_SPACING + random.nextInt(6))) {

			for (int j = 0; j < height; j += treeHeight) {

				switch (random.nextInt(10)) {

				case 0: {
					treeHeight = 58 + random.nextInt(10);
					if (checkTile(level, i, j)) {
						level.add(new DeadSequoia(level, x + i, y + j));
					}
					break;
				}
				case 1:
				case 2:
				case 3: {
					treeHeight = 58 + random.nextInt(10);
					if (checkTile(level, i, j)) {
						level.add(new LargeSequoia(level, x + i, y + j));
					}
					break;
				}
				case 4:
				case 5:
				case 6: {
					treeHeight = 44 + random.nextInt(10);
					if (checkTile(level, i, j)) {
						level.add(new MediumSequoia(level, x + i, y + j));
					}
					break;
				}
				default: {
					treeHeight = 32 + random.nextInt(10);
					if (checkTile(level, i, j)) {
						level.add(new SmallSequoia(level, x + i, y + j));
					}

					break;
				}
				}
			}
		}

	}

}
