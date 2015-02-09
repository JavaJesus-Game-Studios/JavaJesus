package ca.javajesus.level;

import java.util.Random;

public class HeightMap {
	public int height;
	public int width;

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
	 *            :The desired height of the heightmap
	 * 
	 * @param width
	 *            :The desired width of the heightmap
	 */
	public HeightMap(int height, int width) {
		this.height = height;
		this.width = width;
	}

	private byte GRASS() {
		Random random = new Random();
		switch (random.nextInt(10)) {
		case 0:
			return 9;
		case 1:
			return 10;
		case 2:
			return 11;
		case 3:
			return 9;
		case 4:
			return 10;
		default:
			return 0;

		}
	}

	/**
	 * Creates a heightmap of height x width
	 * 
	 * @param cycles
	 *            :the number of times the heightmap will be cycled. 4 cycles is
	 *            recommended.
	 * @return An array filled with 0, 1, or 2, where 0 is water, 1 is land, and
	 *         2 is mountains.
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

	private int[][] finisher(int[][] heightmap) {
		int cutoff = this.getAverage(heightmap) - 10;
		int dirtCutoff = cutoff + 50;
		int mountainCutoff = cutoff + 65;
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
	 * @return The location of the heightmap 1 2 3
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
