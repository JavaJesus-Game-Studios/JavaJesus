package ca.javajesus.level;

import java.util.Random;

public class VillageGeneration {
	private int[][] heightMap;
	private int[][] villageMap;

	Random rand = new Random();

	/**
	 * Constructor: Class contains several methods for village generation used
	 * in conjunction with heightmap
	 * 
	 * @param heightmap
	 *            from heightmap generator
	 */
	public VillageGeneration(int[][] heightmap) {
		this.heightMap = heightmap;
		villageMap = new int[heightMap.length][heightmap[0].length];
	}

	/*public int[][] villageGenerator() {
		this.locationChooser();
		this.villageBoundaryFiller();
		
	}*/
	
	/**
	 * Chooses several locations for the new villages.
	 */
	@SuppressWarnings("unused")
	private void locationChooser() {
		// Used to prevent villages from spawning near each other.
		int xBuffer = 0;
		int yBuffer = 0;
		int villageNumber = 1;
		for (int row = 0; row < villageMap.length; row++) {
			for (int col = 0; col < villageMap[row].length; col++) {
				if (xBuffer == 0 || yBuffer == 0) {
					if (rand.nextInt(500) == 0) {
						villageMap[row][col] = villageNumber;
						villageNumber++;
						xBuffer = 500;
						yBuffer = 500;
					}
				}
				if (xBuffer != 0)
					xBuffer--;
			}
			if (yBuffer != 0)
				yBuffer--;
		}
	}

	/**
	 * Gives the villages a random area
	 * Max area is 200x200
	 */
	@SuppressWarnings("unused")
	private void villageBoundaryFiller() {
		for (int row = 0; row < villageMap.length; row++) {
			for (int col = 0; col < villageMap[row].length; col++) {
				if (villageMap[row][col] > 0) {
					int villageNum = villageMap[row][col];
					int boundarySize = rand.nextInt(100) + 100;
					int halfLength = (int) (boundarySize / 2.0);
					for (int row2 = -halfLength; row2 <= halfLength; row2++) {
						for (int col2 = -halfLength; col2 <= halfLength; col2++) {
							if (row + row2 >= 0 || col + col2 >= 0) {
								villageMap[row + row2][col + col2] = villageNum;
							}
						}
					}
				}
			}
		}
	}
}
