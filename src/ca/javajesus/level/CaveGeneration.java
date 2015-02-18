package ca.javajesus.level;

import java.util.Random;

public class CaveGeneration {
	private boolean[][] caveMap;
	private int width;
	private int height;
	private int cycles;

	private Random rand = new Random();

	public CaveGeneration(int height, int width, int cycles) {
		this.height = height;
		this.width = width;
		this.cycles = cycles;
		caveMap = new boolean[height][width];
	}

	public boolean[][] generateCave() {
		fillArray();
		cellAutomata(6, 8, 3, 8);
		return caveMap;
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
					if (caveMap[row - 1][col - 1] == true) {
						bCounter++;
					}
					if (caveMap[row - 1][col] == true) {
						bCounter++;
					}
					if (caveMap[row - 1][col + 1] == true) {
						bCounter++;
					}
					if (caveMap[row][col - 1] == true) {
						bCounter++;
					}
					if (caveMap[row][col + 1] == true) {
						bCounter++;
					}
					if (caveMap[row + 1][col - 1] == true) {
						bCounter++;
					}
					if (caveMap[row + 1][col] == true) {
						bCounter++;
					}
					if (caveMap[row + 1][col + 1] == true) {
						bCounter++;
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
					if (caveMap[row - 1][col - 1] == true) {
						sCounter++;
					}
					if (caveMap[row - 1][col] == true) {
						sCounter++;
					}
					if (caveMap[row - 1][col + 1] == true) {
						sCounter++;
					}
					if (caveMap[row][col - 1] == true) {
						sCounter++;
					}
					if (caveMap[row][col + 1] == true) {
						sCounter++;
					}
					if (caveMap[row + 1][col - 1] == true) {
						sCounter++;
					}
					if (caveMap[row + 1][col] == true) {
						sCounter++;
					}
					if (caveMap[row + 1][col + 1] == true) {
						sCounter++;
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
				} else if (mergeArray[row][col] == false) {
					caveMap[row][col] = false;
				}
			}
		}
	}
}
