package ca.javajesus.level;

import java.util.Random;

public class HeightMap {
	public int height;
	public int width;

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
							heightmap[row][col] = (heightmap[row][col + 1]
									+ heightmap[row + 1][col + 1] + heightmap[row + 1][col]) / 3;
						} else if (col == heightmap[0].length - 1) {
							heightmap[row][col] = (heightmap[row][col - 1]
									+ heightmap[row + 1][col - 1] + heightmap[row + 1][col]) / 3;
						} else {
							heightmap[row][col] = (heightmap[row][col - 1]
									+ heightmap[row + 1][col - 1]
									+ heightmap[row + 1][col]
									+ heightmap[row + 1][col + 1] + heightmap[row][col + 1]) / 5;
						}
					}
					if (row == heightmap.length - 1) {
						if (col == 0) {
							heightmap[row][col] = (heightmap[row][col + 1]
									+ heightmap[row - 1][col + 1] + heightmap[row - 1][col]) / 3;
						} else if (col == heightmap[0].length - 1) {
							heightmap[row][col] = (heightmap[row][col - 1]
									+ heightmap[row - 1][col - 1] + heightmap[row - 1][col]) / 3;
						} else {
							heightmap[row][col] = (heightmap[row][col - 1]
									+ heightmap[row - 1][col - 1]
									+ heightmap[row - 1][col]
									+ heightmap[row - 1][col + 1] + heightmap[row][col + 1]) / 5;
						}
					}
					if (row > 0 && row < heightmap.length - 1) {
						if (col == 0) {
							heightmap[row][col] = (heightmap[row - 1][col]
									+ heightmap[row - 1][col + 1]
									+ heightmap[row][col + 1]
									+ heightmap[row + 1][col + 1] + heightmap[row + 1][col]) / 5;
						} else if (col == heightmap[0].length - 1) {
							heightmap[row][col] = (heightmap[row - 1][col]
									+ heightmap[row - 1][col - 1]
									+ heightmap[row][col - 1]
									+ heightmap[row + 1][col - 1] + heightmap[row - 1][col]) / 5;
						} else if (col > 0 && col < heightmap[col].length - 1) {
							heightmap[row][col] = (heightmap[row - 1][col - 1]
									+ heightmap[row - 1][col]
									+ heightmap[row - 1][col + 1]
									+ heightmap[row][col + 1]
									+ heightmap[row + 1][col + 1]
									+ heightmap[row + 1][col]
									+ heightmap[row + 1][col - 1] + heightmap[row][col - 1]) / 8;
						}
					}
				}
			}
		}
		int cutoff = this.getAverage(heightmap);
		int mountainCutoff = cutoff + 100;
		for (int row = 0; row < heightmap.length; row++) {
			for (int col = 0; col < heightmap[0].length; col++) {
				if (heightmap[row][col] <= cutoff) {
					heightmap[row][col] = 0;
				} else if (heightmap[row][col] > cutoff
						&& heightmap[row][col] <= mountainCutoff) {
					heightmap[row][col] = 1;
				} else {
					heightmap[row][col] = 2;
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
			for (int col = 0; col < heightmap[0].length; col++) {
				sumTotal += heightmap[row][col];
			}
		}
		int average = sumTotal / (heightmap.length * heightmap[0].length);
		return average;
	}
}
