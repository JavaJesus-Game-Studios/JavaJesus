package ca.javajesus.level;

import java.awt.Point;
import java.util.Random;

import ca.javajesus.game.Game;
import ca.javajesus.game.entities.structures.Transporter;
import ca.javajesus.level.tile.Tile;

public class RandomLevel2 extends Level {

	private int[][] heightmap;

	Random rand = new Random();

	/**
	 * Generates a random level with smooth terrain based on a simple array
	 * noise map
	 * 
	 * @param width
	 *            : The width of the level
	 * @param height
	 *            : The height of the level
	 */
	public RandomLevel2(int width, int height) {
		super(width, height);
		boolean spawnFound = false;
		int x = 0, y = 0;
		while (!spawnFound) {
			if (heightmap[y][x] != 0 || heightmap[y][x] != 2) {
				spawnFound = true;
				spawnPoint = new Point(x, y);
			}
			x++;
			y++;
		}
	}

	protected void generateLevel() {
		heightmap = new HeightMap(width, height).generateHeightMap(20);
		for (int y = 0; y < heightmap.length; y++) {
			for (int x = 0; x < heightmap[y].length; x++) {
				int tile = x + y * width;
				tiles[tile] = heightmap[y][x];
			}
		}
	}

	@Override
	protected void initNPCPlacement() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initSpawnerPlacement() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initChestPlacement() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void otherEntityPlacement() {
		// TODO Auto-generated method stub

	}

	public Tile getTile(int x, int y) {
		if (0 > x || x >= width || 0 > y || y >= height)
			return Tile.VOID;
		switch (tiles[x + y * width]) {
		case 0:
			return Tile.GRASS;
		case 1:
			return Tile.SAND;
		case 2:
			return Tile.STONE;
		case 3:
			return Tile.DIRTROAD;
		case 4:
			return Tile.WATER;
		case 5:
			return Tile.ROAD1;
		case 6:
			return Tile.FIRE;
		case 7:
			return Tile.WATERSAND;
		case 8:
			return Tile.ROAD2;
		default:
			return Tile.VOID;
		}

	}
}