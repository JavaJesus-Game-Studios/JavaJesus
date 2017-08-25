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
	 * @param src - source mob
	 * @param dest - destination mob
	 */
	public Path(Mob src, Mob dest) {
		
		// destination mob
		this.destination = dest;
		origin = new Point(dest.getX(), dest.getY());
		
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
		for (Entity e: src.getLevel().getEntities()) {
			if (e instanceof SolidEntity) {
				
				// fill the inside of the bounds as solid tiles
				for (int i = 0; i < e.getBounds().height; i++) {
					for (int j = 0; j < e.getBounds().width; j++) {
						int xTile = (e.getBounds().x + j) >> 3;
						int yTile = (e.getBounds().y + i) >> 3;
						if (xTile >= 0 && xTile < Level.LEVEL_WIDTH && yTile >=0 && yTile < Level.LEVEL_WIDTH) {
							filtered[xTile + yTile * Level.LEVEL_WIDTH] = '0';
						}
					}
				}
				
			}
		}
		
		// the final path
		List<Node> path = Pathfind.generatePath(filtered, src.getX() >> 3, src.getY() >> 3,
		        dest.getX() >> 3, dest.getY() >> 3);
		
		
		// make sure path exists
		if (path != null) {
			for (int i = path.size() - 2; i >= 0; i--) {
				Node node = path.get(i);
				scripts.add(new Script(src, new Point(node.x, node.y)));
				//src.getLevel().alterTile(node.x, node.y, Tile.CONCRETE());
			}
		}

	}
	
	/**
	 * @return whether or not the destination mob has moved
	 */
	public boolean isValid() {
		return destination.getX() == origin.x && destination.getY() == origin.y;
	}
	
	/**
	 * @param script - single node to visit
	 */
	public Path(Script script) {
		scripts.add(script);
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
