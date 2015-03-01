package ca.javajesus.level.generation;

import java.awt.Point;
import java.util.Random;

public class CaveGeneration {
	private boolean[][] caveMap;
	private int[][] caveReturn;
	private int width;
	private int height;
	private int cycles;

	private Random rand = new Random();

	public CaveGeneration(int height, int width, int cycles) {
		this.height = height;
		this.width = width;
		this.cycles = cycles;
		caveMap = new boolean[height][width];
		caveReturn = new int[height][width];
	}

	public int[][] generateCave() {
		fillArray();
		cellAutomata(4, 8, 3, 8);
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				if (row == 0 || row == height - 1) {
					caveMap[row][col] = false;
				} else if (col == 0 || col == width - 1) {
					caveMap[row][col] = false;
				}
			}
		}
		for (int row = 1; row < height - 1; row++) {
			for (int col = 1; col < width - 1; col++) {
				if (caveMap[row][col]) {
					caveReturn[row][col] = 1;
				} else if (!caveMap[row][col]) {
					if (caveMap[row - 1][col - 1] || caveMap[row - 1][col]
							|| caveMap[row - 1][col + 1]
							|| caveMap[row][col - 1] || caveMap[row][col + 1]
							|| caveMap[row + 1][col - 1]
							|| caveMap[row + 1][col]
							|| caveMap[row + 1][col + 1]) {
						caveReturn[row][col] = 2;
					} else {
						caveReturn[row][col] = 0;
					}
				}
			}
		}
		return caveReturn;
	}

	private void fillArray() {
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				if (rand.nextInt(5) == 0) {
					caveMap[row][col] = true;
				} else {
					caveMap[row][col] = false;
				}
			}
		}
	}

	private void cellAutomata(int bBegin, int bEnd, int sBegin, int sEnd) {
		for (int cycle = 0; cycle < cycles; cycle++) {
			// The Birth Cycle
			boolean[][] caveMapBirth = new boolean[height][width];
			for (int row = 1; row < height - 1; row++) {
				for (int col = 1; col < width - 1; col++) {
					int bCounter = 0;
					for (int row2 = -1; row2 <= 1; row2++) {
						for (int col2 = -1; col2 <= 1; col2++) {
							if (row2 == 0 && col2 == 0) {
								continue;
							} else {
								if (caveMap[row + row2][col + col2] == true)
									bCounter++;
							}
						}
					}
					if (bCounter >= bBegin && bCounter <= bEnd) {
						caveMapBirth[row][col] = true;
					}
				}
			}
			// Merging
			merger(caveMapBirth);
			// The Survival Cycle
			boolean[][] caveMapSurvival = new boolean[height][width];
			for (int row = 1; row < height - 1; row++) {
				for (int col = 1; col < width - 1; col++) {
					int sCounter = 0;
					for (int row2 = -1; row2 <= 1; row2++) {
						for (int col2 = -1; col2 <= 1; col2++) {
							if (row2 == 0 && col2 == 0) {
								continue;
							} else {
								if (caveMap[row + row2][col + col2]) {
									sCounter++;
								}
							}
						}
					}
					if (sCounter >= sBegin && sCounter <= sEnd) {
						caveMapSurvival[row][col] = true;
					}
				}
			}
			// Merging
			mergerSurvival(caveMapSurvival);
		}
	}

	private void merger(boolean[][] mergeArray) {
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				if (mergeArray[row][col] == true) {
					caveMap[row][col] = true;
				}
			}
		}
	}

	private void mergerSurvival(boolean[][] mergeArray) {
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				if (mergeArray[row][col] == true) {
					caveMap[row][col] = true;
				} else {
					caveMap[row][col] = false;
				}
			}
		}
	}

	public Point getSpawnPoint() {
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				if (caveMap[row][col] == true && rand.nextInt(50) == 0) {
					return new Point(col, row);
				}
			}
		}
		return new Point(10, 10);
	}
}
