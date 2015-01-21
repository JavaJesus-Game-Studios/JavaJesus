package ca.javajesus.level;

import java.awt.Point;
import java.util.Random;

import ca.javajesus.game.Game;
import ca.javajesus.game.entities.structures.Transporter;
import ca.javajesus.level.tile.Tile;

public class RandomLevel2 extends Level {

	private HeightMap heightm;
	private int width;
	private int height;
	private int[][] heightmap;
	
	protected final byte grass = 0;
	protected final byte sand = 1;
	protected final byte rock = 2;
	protected final byte dirt = 3;
	protected final byte water = 4;

	protected final byte road1 = 5;
	protected final byte road2 = 8;
	protected final byte road3 = 9;

	protected final byte lily = 6;
	protected final byte waterSand = 7;

	protected final byte coniferTrees = 9;
	protected final byte decidiousTrees = 10;
	
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
		heightm = new HeightMap(width, height);
		this.width = width;
		this.height = height;
		heightmap = heightm.generateHeightMap(4);
	}

	protected void generateLevel() {
		for (int y = 0; y < heightmap.length; y++) {
			for (int x = 0; x < heightmap[0].length; x++) {
				int tile = x + y * width;
				if (heightmap[y][x] == 0) {
					tiles[tile] = water;
				} else if (heightmap[y][x] == 1) {
					tiles[tile] = grass;
				} else {
					tiles[tile] = rock;
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
}
