package ca.javajesus.level;

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
	private boolean checkGrass(int row, int col, int[][] heightmap) {
		if (heightmap[row][col] == 0 || heightmap[row][col] == 9
				|| heightmap[row][col] == 10 || heightmap[row][col] == 11) {
			return true;
		} else {
			return false;
		}
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
	public int[][] generateHeightMap(int cycles) {
		Random rand = new Random();
		int[][] heightmap = new int[height][width];

		for (int row = 0; row < heightmap.length; row++) {
			for (int col = 0; col < heightmap[0].length; col++) {
				heightmap[row][col] = rand.nextInt(1000);
			}
		}

		for (int cycle = 0; cycle < cycles; cycle++) {
			for (int row = 0; row < heightmap.length; row++) {
				for (int col = 0; col < heightmap[0].length; col++) {
					if (row == 0) {
						if (col == 0) {
							heightmap[row][col] = (int) ((heightmap[row][col + 1]
									+ heightmap[row + 1][col + 1] + heightmap[row + 1][col]) / 3.0);
						} else if (col == heightmap[0].length - 1) {
							heightmap[row][col] = (int) ((heightmap[row][col - 1]
									+ heightmap[row + 1][col - 1] + heightmap[row + 1][col]) / 3.0);
						} else {
							heightmap[row][col] = (int) ((heightmap[row][col - 1]
									+ heightmap[row + 1][col - 1]
									+ heightmap[row + 1][col]
									+ heightmap[row + 1][col + 1] + heightmap[row][col + 1]) / 5.0);
						}
					}
					if (row == heightmap.length - 1) {
						if (col == 0) {
							heightmap[row][col] = (int) ((heightmap[row][col + 1]
									+ heightmap[row - 1][col + 1] + heightmap[row - 1][col]) / 3.0);
						} else if (col == heightmap[0].length - 1) {
							heightmap[row][col] = (int) ((heightmap[row][col - 1]
									+ heightmap[row - 1][col - 1] + heightmap[row - 1][col]) / 3.0);
						} else {
							heightmap[row][col] = (int) ((heightmap[row][col - 1]
									+ heightmap[row - 1][col - 1]
									+ heightmap[row - 1][col]
									+ heightmap[row - 1][col + 1] + heightmap[row][col + 1]) / 5.0);
						}
					}
					if (row > 0 && row < heightmap.length - 1) {
						if (col == 0) {
							heightmap[row][col] = (int) ((heightmap[row - 1][col]
									+ heightmap[row - 1][col + 1]
									+ heightmap[row][col + 1]
									+ heightmap[row + 1][col + 1] + heightmap[row + 1][col]) / 5.0);
						} else if (col == heightmap[0].length - 1) {
							heightmap[row][col] = (int) ((heightmap[row - 1][col]
									+ heightmap[row - 1][col - 1]
									+ heightmap[row][col - 1]
									+ heightmap[row + 1][col - 1] + heightmap[row - 1][col]) / 5.0);
						} else if (col > 0 && col < heightmap[col].length - 1) {
							heightmap[row][col] = (int) ((heightmap[row - 1][col - 1]
									+ heightmap[row - 1][col]
									+ heightmap[row - 1][col + 1]
									+ heightmap[row][col + 1]
									+ heightmap[row + 1][col + 1]
									+ heightmap[row + 1][col]
									+ heightmap[row + 1][col - 1] + heightmap[row][col - 1]) / 8.0);
						}
					}
				}
			}
		}
		heightmap = this.finisher(heightmap);
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
	private int[][] finisher(int[][] heightmap) {
		int cutoff = this.getAverage(heightmap) - 10;
		int dirtCutoff = cutoff + 50;
		int mountainCutoff = cutoff + 60;
		for (int row = 0; row < heightmap.length; row++) {
			for (int col = 0; col < heightmap[0].length; col++) {
				if (heightmap[row][col] <= cutoff) {
					heightmap[row][col] = WATER;
				} else if (heightmap[row][col] > cutoff
						&& heightmap[row][col] <= dirtCutoff) {
					heightmap[row][col] = GRASS();
				} else if (heightmap[row][col] > dirtCutoff
						&& heightmap[row][col] <= mountainCutoff) {
					heightmap[row][col] = DIRT;
				} else {
					heightmap[row][col] = ROCK;
				}
			}
		}
		for (int row = 0; row < heightmap.length; row++) {
			for (int col = 0; col < heightmap[0].length; col++) {
				// SAND
				if ((heightmap[row][col] == 0 || heightmap[row][col] >= 9)
						|| heightmap[row][col] == DIRT) {
					switch (this.locationChecker(heightmap, row, col)) {
					case 1:
						if (heightmap[row][col + 1] == WATER
								|| heightmap[row + 1][col] == WATER) {
							heightmap[row][col] = SAND;
						}
						break;
					case 2:
						if (heightmap[row][col - 1] == WATER
								|| heightmap[row + 1][col] == WATER
								|| heightmap[row][col + 1] == WATER) {
							heightmap[row][col] = SAND;
						}
						break;
					case 3:
						if (heightmap[row][col - 1] == WATER
								|| heightmap[row + 1][col] == WATER) {
							heightmap[row][col] = SAND;
						}
						break;
					case 4:
						if (heightmap[row - 1][col] == WATER
								|| heightmap[row][col + 1] == WATER
								|| heightmap[row + 1][col] == WATER) {
							heightmap[row][col] = SAND;
						}
						break;
					case 5:
						if (heightmap[row - 1][col] == WATER
								|| heightmap[row][col - 1] == WATER
								|| heightmap[row][col + 1] == WATER
								|| heightmap[row + 1][col] == WATER) {
							heightmap[row][col] = SAND;
						}
						break;
					case 6:
						if (heightmap[row - 1][col] == WATER
								|| heightmap[row][col - 1] == WATER
								|| heightmap[row + 1][col] == WATER) {
							heightmap[row][col] = SAND;
						}
					case 7:
						if (heightmap[row - 1][col] == WATER
								|| heightmap[row][col] == WATER) {
							heightmap[row][col] = SAND;
						}
						break;
					case 8:
						if (heightmap[row][col - 1] == WATER
								|| heightmap[row - 1][col] == WATER
								|| heightmap[row][col + 1] == WATER) {
							heightmap[row][col] = SAND;
						}
						break;
					case 9:
						if (heightmap[row - 1][col] == WATER
								|| heightmap[row][col - 1] == WATER) {
							heightmap[row][col] = SAND;
						}
						break;
					}
				}
			}
		}
		for (int row = 0; row < heightmap.length; row++) {
			for (int col = 0; col < heightmap[0].length; col++) {
				// WATER SAND
				if (heightmap[row][col] == WATER) {
					switch (this.locationChecker(heightmap, row, col)) {
					case 1:
						if (heightmap[row][col + 1] == SAND
								|| heightmap[row + 1][col] == SAND) {
							heightmap[row][col] = WATERSAND;
						}
						break;
					case 2:
						if (heightmap[row][col - 1] == SAND
								|| heightmap[row + 1][col] == SAND
								|| heightmap[row][col + 1] == SAND) {
							heightmap[row][col] = WATERSAND;
						}
						break;
					case 3:
						if (heightmap[row][col - 1] == SAND
								|| heightmap[row + 1][col] == SAND) {
							heightmap[row][col] = WATERSAND;
						}
						break;
					case 4:
						if (heightmap[row - 1][col] == SAND
								|| heightmap[row][col + 1] == SAND
								|| heightmap[row + 1][col] == SAND) {
							heightmap[row][col] = WATERSAND;
						}
						break;
					case 5:
						if (heightmap[row - 1][col] == SAND
								|| heightmap[row][col - 1] == SAND
								|| heightmap[row][col + 1] == SAND
								|| heightmap[row + 1][col] == SAND) {
							heightmap[row][col] = WATERSAND;
						}
						break;
					case 6:
						if (heightmap[row - 1][col] == SAND
								|| heightmap[row][col - 1] == SAND
								|| heightmap[row + 1][col] == SAND) {
							heightmap[row][col] = WATERSAND;
						}
					case 7:
						if (heightmap[row - 1][col] == SAND
								|| heightmap[row][col] == SAND) {
							heightmap[row][col] = WATERSAND;
						}
						break;
					case 8:
						if (heightmap[row][col - 1] == SAND
								|| heightmap[row - 1][col] == SAND
								|| heightmap[row][col + 1] == SAND) {
							heightmap[row][col] = WATERSAND;
						}
						break;
					case 9:
						if (heightmap[row - 1][col] == SAND
								|| heightmap[row][col - 1] == SAND) {
							heightmap[row][col] = WATERSAND;
						}
						break;
					}
				}
			}
		}
		for (int row = 0; row < heightmap.length; row++) {
			for (int col = 0; col < heightmap[0].length; col++) {
				// Spawn random building
				if (checkBuildings) {
					if (random.nextInt(1000) == 0) {
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
									} else if (heightmap[row + row2][col + col2] >= 500) {
										grassChecker = false;
									}
								}
							}
							if (grassChecker) {
								heightmap[row][col] = 500;
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
								} else if (heightmap[row + row2][col + col2] >= 500) {
									grassChecker = false;
								}
							}
						}
						if (grassChecker) {
							heightmap[row][col] = 501;
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
	private int getAverage(int[][] heightmap) {
		int sumTotal = 0;
		for (int row = 0; row < heightmap.length; row++) {
			for (int col = 0; col < heightmap[row].length; col++) {
				sumTotal += heightmap[row][col];
			}
		}
		int average = (int) ((double) sumTotal)
				/ (heightmap.length * heightmap[0].length);
		return average;
	}

	/**
	 * Finds the location of the index in heightmap
	 * 
	 * @param heightmap
	 *            The heightmap you want to check
	 * @return The location of the heightmap 
	 *         1 2 3
	 * 
	 *         4 5 6
	 * 
	 *         7 8 9
	 */
	private int locationChecker(int[][] heightmap, int row, int col) {
		if (row == 0) {
			if (col == 0)
				return 1;
			else if (col == heightmap[0].length - 1)
				return 3;
			else
				return 2;
		} else if (row == heightmap.length - 1) {
			if (col == 0)
				return 7;
			else if (col == heightmap[0].length - 1)
				return 9;
			else
				return 8;
		} else if (col == 0 && (row > 0 || row < heightmap.length - 1)) {
			return 4;
		} else if (col == heightmap.length - 1
				&& (row > 0 || row < heightmap.length - 1)) {
			return 6;
		} else
			return 5;
	}

}
