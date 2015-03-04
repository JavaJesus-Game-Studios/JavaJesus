package ca.javajesus.level;

import java.awt.Point;
import java.util.Random;

import ca.javajesus.game.entities.structures.PoorHouse;
import ca.javajesus.game.entities.vehicles.CenturyLeSabre;
import ca.javajesus.level.generation.HeightMap;
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
	}

	protected void generateLevel() {
		heightmap = new HeightMap(width, height).generateHeightMap(20);
		boolean spawnFound = true;
		for (int row = 0; row < heightmap.length; row++) {
			for (int col = 0; col < heightmap[row].length; col++) {
				int tile = col + row * width;
				if(heightmap[row][col] < 500) {
					tiles[tile] = heightmap[row][col];
				} else if(heightmap[row][col] == 500) {
					tiles[tile] = 0;
					this.addEntity(new PoorHouse(this, col * 8, row * 8));
				} else if(heightmap[row][col] == 501) {
					tiles[tile] = 0;
					this.addEntity(new CenturyLeSabre(this, "car", col * 8, row * 8));
				}
				if (heightmap[row][col] == 0) {
					if (row > 300 && col > 300) {
						while (spawnFound) {
							spawnPoint = new Point(col * 8, row * 8);
							spawnFound = false;
						}
					}
				}
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
		case 9:
			return Tile.GRASS2;
		case 10:
			return Tile.GRASS3;
		case 11:
			return Tile.GRASS_FLOWER;
		default:
			return Tile.VOID;
		}

	}
}
