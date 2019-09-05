package javajesus.utility.movement;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javajesus.entities.Entity;
import javajesus.entities.Mob;
import javajesus.entities.SolidEntity;
import javajesus.level.Level;
import javajesus.level.tile.Tile;

/**
 * @author Derek
 *
 * A path is a list of destinations taken from a path in Pathfind and converts
 * it into a usable form for entities to utilize
 */
public class Path {

	// list of scripts for the mob to use
	private ArrayList<Script> scripts = new ArrayList<Script>();

	// the original destination mob
	private Mob destination;
	private Point origin;

	/**
	 * Creates a path from one mob to another
	 * 
	 * @param src - source mob
	 * @param dest - destination mob
	 */
	public Path(Mob src, Mob dest) {

		// destination mob
		this.destination = dest;
		origin = new Point(dest.getX(), dest.getY() + dest.getBounds().height - 1);

		// original level tiles
		int[] tiles = src.getLevel().getTiles();

		// filtered array to pass to path generator
		char[] filtered = new char[tiles.length];

		// filter the tiles by solid/ not solid
		for (int i = 0; i < tiles.length; i++) {
			if (Tile.tileList[tiles[i]].isSolid() || Tile.isWater((byte) tiles[i])) {
				filtered[i] = '0';
			} else {
				filtered[i] = '1';
			}
		}

		// now filter for solid entities
		for (Entity e : src.getLevel().getEntities()) {
			if (e instanceof SolidEntity || (e != src && e instanceof Mob && ((Mob) e).getTarget() == dest)) {

				// fill the inside of the bounds as solid tiles
				for (int i = 0; i < e.getBounds().height; i++) {
					for (int j = 0; j < e.getBounds().width; j++) {
						int xTile = (e.getBounds().x + j) >> 3;
						int yTile = (e.getBounds().y + i) >> 3;
						if (xTile >= 0 && xTile < Level.LEVEL_WIDTH && yTile >= 0 && yTile < Level.LEVEL_WIDTH) {
							filtered[xTile + yTile * Level.LEVEL_WIDTH] = '0';
						}
					}
				}
			}
		}

		// get the destination tile
		Point d = getValidPoint(filtered, src, destination);
		
		// source point
		Point s = getValidPoint(filtered, src, src);

		// no valid points found
		if (d == null || s == null) {
			return;
		}
		
		// the final path
		List<Node> path = Pathfind.generatePath(filtered, s.x,
		        s.y, d.x, d.y);

		// make sure path exists
		if (path != null) {
			for (int i = path.size() - 2; i >= 0; i--) {
				Node node = path.get(i);
				scripts.add(new Script(src, new Point(node.x, node.y)));
			}
		}

	}
	
	/**
	 */
	public Path() {
	}
	
	public void add(Script script) {
		scripts.add(script);
	}

	/**
	 * @param tiles - list of valid tiles in 0/1 format
	 * @param src - the source mob
	 * @param target - the target mob
	 * @return a valid point in tile coords around the target
	 */
	public Point getValidPoint(char[] tiles, Mob src, Mob target) {

		// the number of positions
		int positions = 4;

		// the first index to check
		int current = 0;

		// coming from the south
		if (target.getY() + 25 < src.getY()) {
			current = 0;

			// coming from the north
		} else if (target.getY() - 25 > src.getY()) {
			current = 1;

			// coming from the west
		} else if (target.getX() > src.getX()) {
			current = 2;

			// coming from the east
		} else {
			current = 3;
		}

		// coords
		int x = 0, y = 0;

		// loop through all possible spots
		for (int i = 0; i < positions; i++) {

			switch (current) {

			// south spot
			case 0:
				x = target.getX() >> 3;
				y = (target.getY() + target.getBounds().height + src.getBounds().height - 8) >> 3;
				if (x + y * Level.LEVEL_WIDTH < tiles.length && tiles[x + y * Level.LEVEL_WIDTH] == '1') {
					return new Point(x, y);
				}
				break;

			// north
			case 1:
				x = target.getX() >> 3;
				y = (target.getY() - 8) >> 3;
				if (x + y * Level.LEVEL_WIDTH >= 0 && tiles[x + y * Level.LEVEL_WIDTH] == '1') {
					return new Point(x, y);
				}
				break;

			// coming from west
			case 2:
				x = (target.getX() - 8) >> 3;
				y = (target.getY() + target.getBounds().height - 1) >> 3;
				if (tiles[x + y * Level.LEVEL_WIDTH] == '1') {
					return new Point(x, y);
				}
				break;

			// coming from east
			default:
				x = (target.getX() + target.getBounds().width + 1) >> 3;
				y = (target.getY() + target.getBounds().height - 1) >> 3;
				if (tiles[x + y * Level.LEVEL_WIDTH] == '1') {
					return new Point(x, y);
				}
				break;
			}

			// try the next position
			current = (current + 1) % positions;
		}

		// no valid spot found
		return null;

	}

	/**
	 * @return whether or not the destination mob has moved
	 */
	public boolean isValid() {
		return destination.getX() == origin.x && destination.getY() == origin.y;
	}


	/**
	 * @return whether or not the path exists
	 */
	public boolean exists() {
		return scripts.size() > 0;
	}

	/**
	 * Checks if the mob moves to the next node
	 */
	public void update() {
		if (scripts.get(0).isCompleted()) {
			scripts.remove(0);
		}
	}

	/**
	 * @return the next node, or script, in the path
	 */
	public Script next() {
		return scripts.get(0);
	}

}
