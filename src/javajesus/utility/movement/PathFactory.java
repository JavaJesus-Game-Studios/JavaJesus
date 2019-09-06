package javajesus.utility.movement;

import java.awt.Point;
import java.util.List;

import javajesus.ai.AIManager;
import javajesus.entities.Entity;
import javajesus.entities.Mob;
import javajesus.entities.SolidEntity;
import javajesus.level.Level;
import javajesus.level.tile.Tile;

public class PathFactory {

	public static final int MDP = 0, DIJKSTRA = 1, TILE_ALIGNMENT = 2;

	public static Path make(Mob src, Mob dest, int type) {
		Path path = new Path();
		switch (type) {
		case MDP:
			path.add(new MovementVector(src, AIManager.bestPointToMoveTo(src), 0));
			break;
		case TILE_ALIGNMENT:
			int dx = src.getX() % 8;
			int dy = src.getY() % 8;
			if (dx <= 4) {
				dx = -dx;
			} else {
				dx = 8 - dx;
			}
			if (dy <= 4) {
				dy = -dy;
			} else {
				dy = 8 - dy;
			}

			path.add(new MovementVector(src, new Point(src.getX() + dx, src.getY() + dy), 0));
			break;
		case DIJKSTRA:
			return createDijkstra(src, dest);
		}

		return path;
	}

	private static Path createDijkstra(Mob src, Mob dest) {

		Path path = new Path();

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
			if (e instanceof SolidEntity || e == dest) {

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
		Point d = getValidPoint(filtered, src, dest);

		// source point
		Point s = new Point(src.getX() >> 3, (src.getY() + src.getBounds().height) >> 3);

		// no valid points found
		if (d == null) {
			return path;
		}

		// the final path
		List<Node> nodes = Pathfind.generatePath(filtered, s.x, s.y, d.x, d.y);

		// make sure nodes exists
		if (nodes != null) {
			for (int i = nodes.size() - 2; i >= 0; i--) {
				Node node = nodes.get(i);
				path.add(new MovementVector(src, new Point(node.x, node.y)));
			}
		}

		return path;
	}

	/**
	 * @param tiles  - list of valid tiles in 0/1 format
	 * @param src    - the source mob
	 * @param target - the target mob
	 * @return a valid point in tile coords around the target
	 */
	public static Point getValidPoint(char[] tiles, Mob src, Mob target) {

		// the number of positions
		int positions = 4;

		// the first index to check
		int current = 0;

		// coming from the south
		if (target.getY() + target.getBounds().height < src.getY()) {
			current = 0;

			// coming from the north
		} else if (target.getY() > src.getY() + target.getBounds().height) {
			current = 1;

			// coming from the west
		} else if (target.getX() > src.getX() + target.getBounds().width) {
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
				y = (target.getY() + target.getBounds().height + src.getBounds().height) >> 3;
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
				x = (target.getX() - src.getBounds().width) >> 3;
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

}
