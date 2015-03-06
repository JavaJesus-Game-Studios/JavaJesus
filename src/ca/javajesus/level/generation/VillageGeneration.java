package ca.javajesus.level.generation;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class VillageGeneration {
	private int[][] heightMap;
	VillageTile[][] villageMap;
	private ArrayList<Point> possibleVillageCenters = new ArrayList<Point>();
	private ArrayList<Point> finalVillageCenters = new ArrayList<Point>();

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
		villageMap = new VillageTile[heightMap.length][heightmap[0].length];
	}

	public void villageGenerator() {
		this.arrayFill();
		this.locationChooser();
		this.probabilitySetter();
	}

	private void arrayFill() {
		for (int row = 0; row < heightMap.length; row++) {
			for (int col = 0; col < heightMap[0].length; col++) {
				boolean groundCheck = false;
				if (heightMap[row][col] == 0
						|| (heightMap[row][col] >= 9 && heightMap[row][col] <= 11)
						|| heightMap[row][col] == 3)
					groundCheck = true;
				villageMap[row][col] = new VillageTile(groundCheck, new Point(col, row));
			}
		}
	}

	/**
	 * Chooses several locations for the new villages.
	 */
	private void locationChooser() {
		for (int row = 10; row < heightMap.length; row++) {
			for (int col = 10; col < heightMap[0].length; col++) {
				if (row + 10 < heightMap.length && col + 10 < heightMap[0].length) {
					int landAmount = 0;
					for (int row2 = -10; row2 <= 10; row2++) {
						for (int col2 = -10; col2 <= 10; col2++) {
							if (villageMap[row][col].getGroundCheck()) {
								landAmount++;
							}
							//Checking to see if the area (21x21) is 70% land or more, 21 x 21 = 441 x .70 = 308
							if (landAmount >= 308) {
								possibleVillageCenters.add(new Point(col, row));
							}
						}
					}
				}
			}
		}
		//Determining how many villages there should be
		int numVillages = (int)(heightMap.length / 200.0) * (int)(heightMap[0].length / 100.0);
		//Choosing n amount final village spawns
		for (int i = 0; i < numVillages; i++) {
			int index = rand.nextInt(possibleVillageCenters.size() - 1);
			finalVillageCenters.add(possibleVillageCenters.get(index));
			possibleVillageCenters.remove(index);
		}
	}
	
	private void probabilitySetter() {
		for (int row = 0; row < villageMap.length; row++) {
			for (int col = 0; col < villageMap[0].length; col++) {
				double probability = 0;
				for (int i = 0; i < finalVillageCenters.size(); i++) {
					probability += 1.0 / Math.abs(row - finalVillageCenters.get(i).x) + 1.0 / Math.abs(col - finalVillageCenters.get(i).y);
				}
				probability *= 1000;
				if (probability > 50)
					probability = 999;
				villageMap[row][col].setProbability(probability);
			}
		}
	}
}
