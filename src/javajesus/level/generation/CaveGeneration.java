package javajesus.level.generation;

import java.awt.Point;
import java.util.Random;

import javajesus.level.Level;

/**
 *	Utility class for generating tile maps using cell automata 
 */
public class CaveGeneration {
	
	// random number generator
	private static final Random rand = new Random();

	/**
	 * Generates a 2D array of a tile map as follows:
	 * 
	 * 0 - 1 initial mapping:
	 * 0 -> Emtpy space -> 
	 * 	4,5,6 entity data
	 * 
	 * 1 -> wall - > 2 if bordering empty
	 * 
	 * @return the 2D array of the tile map
	 */
	public static final int[][] generateCave(int cycles) {
		
		// alive or dead map
		boolean[][] caveMap = new boolean[Level.LEVEL_HEIGHT][Level.LEVEL_WIDTH];
		int[][] caveReturn = new int[Level.LEVEL_HEIGHT][Level.LEVEL_WIDTH];
		
		// does something
		fillArray(caveMap);
		cellAutomata(4, 8, 3, 8, cycles, caveMap);
		
		// iterate through all tiles and do something
		// please comment this section of code all the way down
		for (int row = 0; row < Level.LEVEL_HEIGHT; row++) {
			for (int col = 0; col < Level.LEVEL_WIDTH; col++) {
				if (row == 0 || row == Level.LEVEL_HEIGHT - 1) {
					caveMap[row][col] = false;
				} else if (col == 0 || col == Level.LEVEL_WIDTH - 1) {
					caveMap[row][col] = false;
				}
			}
		}
		for (int row = 1; row < Level.LEVEL_HEIGHT - 1; row++) {
			for (int col = 1; col < Level.LEVEL_WIDTH - 1; col++) {
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
				int check = 0;
				for (int row2 = -1; row2 <= 1; row2++) {
					for (int col2 = -1; col2 <= 1; col2++) {
						if (caveReturn[row][col] == 1) {
							if (caveReturn[row + row2][col + col2] == 2) {
								check++;
							}
						}
					}
				}
				if (check > 3) {
					switch (rand.nextInt(20)) {
					case 1:
						caveReturn[row][col] = 4;
						break;
					case 2:
						caveReturn[row][col] = 5;
						break;
					case 3:
						caveReturn[row][col] = 5;
						break;
					case 4:
						caveReturn[row][col] = 4;
						break;
					case 5:
						caveReturn[row][col] = 6;
						break;
					default:
						break;
					}
				}
			}
		}
		return caveReturn;
	}

	/**
	 * Does something
	 * @param caveMap
	 */
	private static final void fillArray(boolean[][] caveMap) {
		for (int row = 0; row < Level.LEVEL_HEIGHT; row++) {
			for (int col = 0; col < Level.LEVEL_WIDTH; col++) {
				if (rand.nextInt(5) == 0) {
					caveMap[row][col] = true;
				} else {
					caveMap[row][col] = false;
				}
			}
		}
	}

	/**
	 * Does something
	 * @param bBegin
	 * @param bEnd
	 * @param sBegin
	 * @param sEnd
	 * @param cycles
	 * @param caveMap
	 */
	private static final void cellAutomata(int bBegin, int bEnd, int sBegin, int sEnd, int cycles, boolean[][] caveMap) {
		for (int cycle = 0; cycle < cycles; cycle++) {
			// The Birth Cycle
			boolean[][] caveMapBirth = new boolean[Level.LEVEL_HEIGHT][Level.LEVEL_WIDTH];
			for (int row = 1; row < Level.LEVEL_HEIGHT - 1; row++) {
				for (int col = 1; col < Level.LEVEL_WIDTH - 1; col++) {
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
			merger(caveMapBirth, caveMap);
			// The Survival Cycle
			boolean[][] caveMapSurvival = new boolean[Level.LEVEL_HEIGHT][Level.LEVEL_WIDTH];
			for (int row = 1; row < Level.LEVEL_HEIGHT - 1; row++) {
				for (int col = 1; col < Level.LEVEL_WIDTH - 1; col++) {
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
			mergerSurvival(caveMapSurvival, caveMap);
		}
	}

	/**
	 * Does something
	 * @param mergeArray
	 * @param caveMap
	 */
	private static final void merger(boolean[][] mergeArray, boolean[][] caveMap) {
		for (int row = 0; row < Level.LEVEL_HEIGHT; row++) {
			for (int col = 0; col < Level.LEVEL_WIDTH; col++) {
				if (mergeArray[row][col] == true) {
					caveMap[row][col] = true;
				}
			}
		}
	}

	/**
	 * Does something
	 * @param mergeArray
	 * @param caveMap
	 */
	private static final void mergerSurvival(boolean[][] mergeArray, boolean[][] caveMap) {
		for (int row = 0; row < Level.LEVEL_HEIGHT; row++) {
			for (int col = 0; col < Level.LEVEL_WIDTH; col++) {
				if (mergeArray[row][col] == true) {
					caveMap[row][col] = true;
				} else {
					caveMap[row][col] = false;
				}
			}
		}
	}

	/**
	 * This is never used!??
	 * @param caveMap
	 * @return
	 */
	public Point getSpawnPoint(boolean[][] caveMap) {
		for (int row = 0; row < Level.LEVEL_HEIGHT; row++) {
			for (int col = 0; col < Level.LEVEL_WIDTH; col++) {
				if (caveMap[row][col] == true && rand.nextInt(50) == 0) {
					return new Point(col, row);
				}
			}
		}
		return new Point(10, 10);
	}
}
