package ca.javajesus.level.generation;

import java.util.Random;

public class HeightMap {
	private int height;
	private int width;
	private boolean checkBuildings;
	private boolean checkCars;
	Random random = new Random();

	protected final byte GRASS = 0;
	protected final byte GRASS2 = 9;
	protected final byte GRASS3 = 10;
	protected final byte GRASS_FLOWER = 11;

	protected final byte SAND = 1;
	protected final byte ROCK = 2;
	protected final byte DIRT = 3;
	protected final byte WATER = 4;

	protected final byte ROAD1 = 5;
	protected final byte ROAD2 = 8;
	protected final byte ROAD3 = 9;

	protected final byte FIRE = 6;
	protected final byte WATERSAND = 7;

	/**
	 * Methods for generating and using a heightmap for random worlds
	 * 
	 * @param height
	 *            The desired height of the heightmap
	 * 
	 * @param width
	 *            The desired width of the heightmap
	 */
	public HeightMap(int height, int width) {
		this.height = height;
		this.width = width;
		checkBuildings = true;
		checkCars = true;
	}

	/**
	 * Constructor
	 * 
	 * @param height
	 *            The desired height of the heightmap
	 * @param width
	 *            The desired width of the heightmap
	 * @param checkBuildings
	 *            Boolean for buildings
	 * @param checkCars
	 *            Boolean for cars
	 */
	public HeightMap(int height, int width, boolean checkBuildings,
			boolean checkCars) {
		this.height = height;
		this.width = width;
		this.checkBuildings = checkBuildings;
	}

	/**
	 * 
	 * @return height of heightmap
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * 
	 * @return width of heightmap
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * 
	 * @return randomized grass tiles
	 */
	private byte GRASS() {
		if (random.nextInt(100) == 0) {
			return GRASS_FLOWER;
		}
		if (random.nextInt(6) == 0) {
			return GRASS2;
		}
		if (random.nextInt(6) == 0) {
			return GRASS3;
		} else {
			return GRASS;
		}
	}

	/**
	 * Checks if there is a grass tile at the desired spot
	 * 
	 * @param row
	 *            The row on the heightmap
	 * @param col
	 *            The col on the heightmap
	 * @param heightmap
	 *            The heightmap you want to check
	 * @return Boolean true if there is grass
	 */
	private boolean checkGrass(int row, int col, HeightMapTile[][] heightmap) {
		if (heightmap[row][col].tile() == 0 || heightmap[row][col].tile() == 9
				|| heightmap[row][col].tile()  == 10 || heightmap[row][col].tile() == 11)
			return true;
		else
			return false;
	}

	/**
	 * Creates a heightmap of height x width
	 * 
	 * @param cycles
	 *            :the number of times the heightmap will be cycled. 4 cycles is
	 *            recommended.
	 * @return An array filled with 0, 1, 2, or 3 where 0 is water, 1 is land, 2
	 *         is mountains and 3 is buildings.
	 */
	public HeightMapTile[][] generateHeightMap(int cycles) {
		Random rand = new Random();
		HeightMapTile[][] heightmap = new HeightMapTile[height][width];

		for (int row = 0; row < heightmap.length; row++) {
			for (int col = 0; col < heightmap[0].length; col++) {
				heightmap[row][col] = new HeightMapTile(rand.nextInt(1000));
			}
		}

		for (int cycle = 0; cycle < cycles; cycle++) {
			for (int row = 0; row < heightmap.length; row++) {
				for (int col = 0; col < heightmap[0].length; col++) {
					int average = 0;
					double avgNum = 0;
					for (int rowSub = -1; rowSub <= 1; rowSub++) {
						for (int colSub = -1; colSub <= 1; colSub++) {
							int row2 = rowSub + row, col2 = colSub+ col;
							if (!(row2 < 0) && !(row2 > heightmap.length - 1) && !(col2 < 0) && !(col2 > heightmap[0].length - 1)) {
								average += heightmap[row2][col2].tile();
								avgNum++;
							}
						}
					}
					heightmap [row][col].setTile((int) (average / avgNum));
				}
			}
		}
		this.finisher(heightmap);
		return heightmap;
	}

	/**
	 * Finisher of the heightmap which changes random numbers to level-readable
	 * numbers
	 * 
	 * @param heightmap
	 *            the heightmap you want to check
	 * @return A number that corresponds to a tile or entity
	 */
	private HeightMapTile[][] finisher(HeightMapTile[][] heightmap) {
		int cutoff = this.getAverage(heightmap) - 10;
		int dirtCutoff = cutoff + 50;
		int mountainCutoff = cutoff + 60;
		for (int row = 0; row < heightmap.length; row++) {
			for (int col = 0; col < heightmap[0].length; col++) {
				if (heightmap[row][col].tile() <= cutoff) {
					heightmap[row][col].setTile(WATER);
				} else if (heightmap[row][col].tile() > cutoff
						&& heightmap[row][col].tile() <= dirtCutoff) {
					heightmap[row][col].setTile(GRASS());
				} else if (heightmap[row][col].tile() > dirtCutoff
						&& heightmap[row][col].tile() <= mountainCutoff) {
					heightmap[row][col].setTile(DIRT);
				} else {
					heightmap[row][col].setTile(ROCK);
				}
			}
		}
		for (int row = 0; row < heightmap.length; row++) {
			for (int col = 0; col < heightmap[0].length; col++) {
				int waterCounter = 0;
				if (this.checkGrass(row, col, heightmap) || heightmap[row][col].tile() == DIRT) {
					for (int rowSum = -1; rowSum <= 1; rowSum++) {
						for (int colSum = -1; colSum <= 1; colSum++) {
							int row2 = row + rowSum, col2 = col + colSum;
							if (!(row2 < 0) && !(row2 > heightmap.length - 1) && !(col2 < 0) && !(col2 > heightmap[0].length - 1) && (row2 != 0) && (col2 != 0)) {
								if (heightmap[row2][col2].tile() == WATER || heightmap[row2][col2].tile() == WATERSAND) {
									waterCounter++;
									heightmap[row2][col2].setTile(WATERSAND);
								}
							}
						}
					}
					if (waterCounter > 0) {
						heightmap[row][col].setTile(SAND);
					}
				}
			}
		}
		//VillageGeneration gen = new VillageGeneration(heightmap);
		//gen.villageGenerator();
		for (int row = 0; row < heightmap.length; row++) {
			for (int col = 0; col < heightmap[0].length; col++) {
				// Spawn random building
				if (checkBuildings) {
					if (random.nextInt(/*(int) gen.villageMap[row][col].getProbability()*/1000) == 0) {
						if (row > 6 && row < heightmap.length - 6 && col > 6
								&& col < heightmap[row].length - 6) {
							boolean grassChecker = true;
							for (int row2 = -6; row2 < 6; row2++) {
								for (int col2 = -7; col2 < 7; col2++) {
									if (!this.checkGrass(row + row2,
											col + col2, heightmap)
											&& row2 > 0
											&& col2 > 0) {
										grassChecker = false;
									} else if (heightmap[row + row2][col + col2].tile() >= 500) {
										grassChecker = false;
									}
								}
							}
							if (grassChecker) {
								heightmap[row][col].setTile(500);
							}
						}
					}
				}
				if (checkCars) {
					if (random.nextInt(2000) == 0)
					if (row > 6 && row < heightmap.length - 6 && col > 6
							&& col < heightmap[row].length - 6) {
						boolean grassChecker = true;
						for (int row2 = -6; row2 < 6; row2++) {
							for (int col2 = -7; col2 < 7; col2++) {
								if (!this.checkGrass(row + row2,
										col + col2, heightmap)
										&& row2 > 0
										&& col2 > 0) {
									grassChecker = false;
								} else if (heightmap[row + row2][col + col2].tile() >= 500) {
									grassChecker = false;
								}
							}
						}
						if (grassChecker) {
							heightmap[row][col].setTile(501);
						}
					}	
				}
				
			}
		}
		return heightmap;
	}

	/**
	 * Finds the average of the entire heightmap, necessary to find if water,
	 * ground, or mountain tile
	 *
	 * @param heightmap
	 *            : the heightmap you want an average of
	 * @return The average of the values in heightmap
	 * 
	 */
	private int getAverage(HeightMapTile[][] heightmap) {
		int sumTotal = 0;
		for (int row = 0; row < heightmap.length; row++) {
			for (int col = 0; col < heightmap[row].length; col++) {
				sumTotal += heightmap[row][col].tile();
			}
		}
		int average = (int) ((double) sumTotal)
				/ (heightmap.length * heightmap[0].length);
		return average;
	}
}
