package javajesus.level.generation;

import java.util.Random;

import javajesus.level.Level;
import javajesus.utility.movement.Pathfind;

/**
 *	Utility class for generating tile maps using cell automata 
 */
public class CaveGeneration {
	// Constants for readability
	// Walls
	public static final int CAVE_WALL = 0;
	public static final int CAVE_BORDER_WALL = 1;
	//Floor
	public static final int FLOOR = 2;
	//Entities
	public static final int FLOOR_CHEST = 3;
	public static final int FLOOR_SPAWNER = 4;
	public static final int SPAWN_POINT = 5;
	public static final int EXIT_POINT = 6;
	
	
	// Random number generator
	private static final Random rand = new Random();

	/**
	 * Generates a 2D array of a tile map as follows:
	 * 
	 * 0 - 1 initial mapping:
	 * 0 -> Empty space -> 
	 * 	4,5,6 entity data
	 * 
	 * 1 -> wall - > 2 if bordering empty
	 * 
	 * @return the 2D array of the tile map
	 */
	public static final int[][] generateCave(int cycles) {
		
		// Create arrays for generation and return
		int[][] caveMap = new int[Level.LEVEL_HEIGHT][Level.LEVEL_WIDTH];
		int[][] caveReturn = new int[Level.LEVEL_HEIGHT][Level.LEVEL_WIDTH];
		
		// Seed the map for the birth cycle
		fillArray(caveMap);
		// Run cellular automata on seeded map
		cellAutomata(caveMap, cycles, 4, 8, 3, 8);
		
		for (int row = 1; row < Level.LEVEL_HEIGHT - 1; row++) {
			for (int col = 1; col < Level.LEVEL_WIDTH - 1; col++) {
				
				// Fill the return array with more specific numbers
				// Keep caveMap for future path finding
				if (caveMap[row][col] == 1) {
					caveReturn[row][col] = FLOOR;
				} else if(caveMap[row][col] == 0) {
					if (caveMap[row - 1][col - 1]  == 1
							|| caveMap[row - 1][col] == 1
							|| caveMap[row - 1][col + 1] == 1
							|| caveMap[row][col - 1] == 1
							|| caveMap[row][col + 1] == 1
							|| caveMap[row + 1][col - 1] == 1
							|| caveMap[row + 1][col] == 1
							|| caveMap[row + 1][col + 1] == 1) {
						// If wall borders the ground change to cave border wall
						caveReturn[row][col] = CAVE_BORDER_WALL;
					} else {
						// If wall does not border the floor change to cave wall
						caveReturn[row][col] = CAVE_WALL;
					}
				}
				
				// Check for areas of the floor that are surrounded by other floor tiles
				int check = 0;
				for (int rDelta = -1; rDelta <= 1; rDelta++) {
					for (int cDelta = -1; cDelta <= 1; cDelta++) {
						if (caveReturn[row][col] == 1) {
							if (caveReturn[row + rDelta][col + cDelta] == 2) {
								check++;
							}
						}
					}
				}
				
				// If surrounded by three tiles or more add entities with these probabilities:
				// 0.10 - Chest
				// 0.10 - Spawner
				if (check > 3) {
					switch (rand.nextInt(20)) {
					case 1:
						caveReturn[row][col] = FLOOR_CHEST;
						break;
					case 2:
						caveReturn[row][col] = FLOOR_CHEST;
						break;
					case 3:
						caveReturn[row][col] = FLOOR_SPAWNER;
						break;
					case 4:
						caveReturn[row][col] = FLOOR_SPAWNER;
						break;
					default:
						break;
					}
				}
			}
		}
		
		// Find the location for the spawn and exit 
		// so that a path exists between the two
		setLadders(caveMap, caveReturn);
		
		return caveReturn;
	}

	/**
	 * Seeds the array for the birth cycle in order to determine where births occur.
	 * 
	 * @param caveMap - the array to seed.
	 */
	private static final void fillArray(int[][] caveMap) {
		for (int row = 1; row < Level.LEVEL_HEIGHT - 1; row++) {
			for (int col = 1; col < Level.LEVEL_WIDTH - 1; col++) {
				if (rand.nextInt(5) == 0) {
					caveMap[row][col] = 1;
				} else {
					caveMap[row][col] = 0;
				}
			}
		}
	}

	/**
	 * Executes cellular automata on the provided caveMap in order to create cave-like levels.
	 * 
	 * @param caveMap - the caveMap that has been seeded
	 * @param cycles - the number of cycles
	 * @param bBegin - the lower bound for births
	 * @param bEnd - the upper bound for births
	 * @param sBegin - the lower bound for survival
	 * @param sEnd - the upper bound for survival
	 */
	private static final void cellAutomata(int[][] caveMap, int cycles, int bBegin, int bEnd, int sBegin, int sEnd) {
		// Execute automata for specified amount of cycles
		for (int cycle = 0; cycle < cycles; cycle++) {
			
			// The Birth Cycle
			// Create separate array to prevent births from changing 
			// the probability of future births
			int[][] caveMapBirth = new int[Level.LEVEL_HEIGHT][Level.LEVEL_WIDTH];
			
			// Check surroundings of each tile
			for (int row = 1; row < Level.LEVEL_HEIGHT - 1; row++) {
				for (int col = 1; col < Level.LEVEL_WIDTH - 1; col++) {
					int bCounter = 0;
					for (int rDelta = -1; rDelta <= 1; rDelta++) {
						for (int cDelta = -1; cDelta <= 1; cDelta++) {
							if (rDelta == 0 && cDelta == 0) {
								continue;
							} else {
								if (caveMap[row + rDelta][col + cDelta] == 1)
									// If surrounding tile is 'alive' add bCounter
									bCounter++;
							}
						}
					}
					if (bCounter >= bBegin && bCounter <= bEnd) {
						// If bCounter is within range, allow a 'birth'
						caveMapBirth[row][col] = 1;
					}
				}
			}
			
			// Merge the map
			// This keeps original map data and only replaces if caveMapBirth has a one
			merger(caveMapBirth, caveMap);
			
			// The Survival Cycle
			// Create separate array to prevent survivals from changing 
			// the probability of future survivals
			int[][] caveMapSurvival = new int[Level.LEVEL_HEIGHT][Level.LEVEL_WIDTH];
			
			// Check surroundings of each tile
			for (int row = 1; row < Level.LEVEL_HEIGHT - 1; row++) {
				for (int col = 1; col < Level.LEVEL_WIDTH - 1; col++) {
					int sCounter = 0;
					for (int rDelta = -1; rDelta <= 1; rDelta++) {
						for (int cDelta = -1; cDelta <= 1; cDelta++) {
							if (rDelta == 0 && cDelta == 0) {
								continue;
							} else {
								if (caveMap[row + rDelta][col + cDelta] == 1) {
									// If surrounding tile is 'alive' add sCounter
									sCounter++;
								}
							}
						}
					}
					if (sCounter >= sBegin && sCounter <= sEnd) {
						// If bCounter is within range, allow a 'survivor'
						caveMapSurvival[row][col] = 1;
					}
				}
			}
			
			// Merge the map
			// This replaces caveMap entirely with caveMapSurvival
			mergerSurvival(caveMapSurvival, caveMap);
		}
	}

	/**
	 * This will transfer only new births from mergeArray into caveMap
	 * 
	 * @param mergeArray - the birth array
	 * @param caveMap - the main map array
	 */
	private static final void merger(int[][] mergeArray, int[][] caveMap) {
		for (int row = 0; row < Level.LEVEL_HEIGHT; row++) {
			for (int col = 0; col < Level.LEVEL_WIDTH; col++) {
				if (mergeArray[row][col] == 1) {
					caveMap[row][col] = 1;
				}
			}
		}
	}

	/**
	 * This will transfer all survivals and deaths from mergeArray into caveMap
	 * 
	 * @param mergeArray - the survivor array
	 * @param caveMap - the main map array
	 */
	private static final void mergerSurvival(int[][] mergeArray, int[][] caveMap) {
		for (int row = 0; row < Level.LEVEL_HEIGHT; row++) {
			for (int col = 0; col < Level.LEVEL_WIDTH; col++) {
				if (mergeArray[row][col] == 1) {
					caveMap[row][col] = 1;
				} else {
					caveMap[row][col] = 0;
				}
			}
		}
	}
	
	private static final void setLadders(int[][] caveMap, int[][] caveReturn) {
		int spawnX = 0, spawnY = 0, exitX = 0, exitY = 0;
		boolean locCheck = false;
		
		// Find two locations that spawn on ground and have a path between them.
		while (!locCheck) {
			// Generate new locations
			spawnX = rand.nextInt(Level.LEVEL_WIDTH);
			spawnY = rand.nextInt(Level.LEVEL_HEIGHT);
			exitX = rand.nextInt(Level.LEVEL_WIDTH);
			exitY = rand.nextInt(Level.LEVEL_HEIGHT);
			//exitX = spawnX + rand.nextInt(5);
			//exitY = spawnY + rand.nextInt(5);
			
			// Check if they are on floors and a path exists
			// Use logical short circuit to only run pathfind when both are in 
			// floor tiles
			if((caveMap[spawnY][spawnX] == 1) && (caveMap[exitY][exitX] == 1) && (Pathfind.pathExist(caveMap, spawnX, spawnY, exitX, exitY))) {
				
				locCheck = true;
				break;
			}
		} 
		
		caveReturn[spawnY][spawnX] = 5;
		caveReturn[exitY][exitX] = EXIT_POINT;
		if (caveReturn[spawnY][spawnX] == EXIT_POINT) {
			caveReturn[spawnY - 1][spawnX] = 5;
		}
		System.out.println((caveMap[spawnY][spawnX] == 1) + " " + (caveMap[exitY][exitX] == 1) + " " + Pathfind.pathExist(caveMap, spawnX, spawnY, exitX, exitY));
		System.out.println(caveReturn[spawnY][spawnX] + " " + caveReturn[exitY][exitX]);

//		for (int i = -1; i < 2; i++) {
//			for(int j = -1; j < 2; j++) {
//				if(i != 0 && j != 0) {
//					caveReturn[spawnY + i][spawnX + j] = 1;
//					caveReturn[exitY + i][exitX + j] = 1;
//				}
//			}
//		}
	}
}
