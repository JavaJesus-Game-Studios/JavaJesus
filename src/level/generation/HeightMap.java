package level.generation;

import java.util.Random;
import java.util.ArrayList;
import java.awt.Point;
import java.io.Serializable;

import level.tile.Tile;

public class HeightMap implements Serializable {
	
	private static final long serialVersionUID = -6302584582079069785L;
	
	private int height;
	private int width;
	private boolean checkBuildings;
	protected boolean checkCars;
	private ArrayList<Point> possibleVillageCenters = new ArrayList<Point>();
	private ArrayList<Point> finalVillageCenters = new ArrayList<Point>();

	Random random = new Random();

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
		if (Tile.isGrass(heightmap[row][col].tile()))
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
							int row2 = rowSub + row, col2 = colSub + col;
							if (!(row2 < 0) && !(row2 > heightmap.length - 1)
									&& !(col2 < 0)
									&& !(col2 > heightmap[0].length - 1)) {
								average += heightmap[row2][col2].tile();
								avgNum++;
							}
						}
					}
					heightmap[row][col].setTile((int) (average / avgNum));
				}
			}
		}
		this.finisher(heightmap);
		if (checkBuildings)
			this.village(heightmap);
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
	private void finisher(HeightMapTile[][] heightmap) {
		int cutoff = this.getAverage(heightmap) - 10;
		int dirtCutoff = cutoff + 50;
		int mountainCutoff = cutoff + 60;
		for (int row = 0; row < heightmap.length; row++) {
			for (int col = 0; col < heightmap[0].length; col++) {
				if (heightmap[row][col].tile() <= cutoff) {
					heightmap[row][col].setTile(Tile.WATER.getId());
				} else if (heightmap[row][col].tile() > cutoff
						&& heightmap[row][col].tile() <= dirtCutoff) {
					heightmap[row][col].setTile(Tile.GRASS().getId());
				} else if (heightmap[row][col].tile() > dirtCutoff
						&& heightmap[row][col].tile() <= mountainCutoff) {
					heightmap[row][col].setTile(Tile.DIRTROAD.getId());
				} else {
					heightmap[row][col].setTile(Tile.STONE.getId());
				}
			}
		}
		for (int row = 0; row < heightmap.length; row++) {
			for (int col = 0; col < heightmap[0].length; col++) {
				int waterCounter = 0;
				if (this.checkGrass(row, col, heightmap)
						|| heightmap[row][col].tile() == Tile.DIRTROAD.getId()) {
					for (int rowSum = -1; rowSum <= 1; rowSum++) {
						for (int colSum = -1; colSum <= 1; colSum++) {
							int row2 = row + rowSum, col2 = col + colSum;
							if (!(row2 < 0) && !(row2 > heightmap.length - 1)
									&& !(col2 < 0)
									&& !(col2 > heightmap[0].length - 1)
									&& (row2 != 0) && (col2 != 0)) {
								if (heightmap[row2][col2].tile() == Tile.WATER.getId()
										|| heightmap[row2][col2].tile() == Tile.WATERSAND.getId()) {
									waterCounter++;
									heightmap[row2][col2].setTile(Tile.WATERSAND.getId());
								}
							}
						}
					}
					if (waterCounter > 0) {
						heightmap[row][col].setTile(Tile.SAND.getId());
					}
				}
			}
		}
	}

	private void village(HeightMapTile[][] heightmap) {
		for (int row = 0; row < heightmap.length; row++) {
			for (int col = 0; col < heightmap[0].length; col++) {
				boolean groundCheck = false;
				if (heightmap[row][col].tile() == Tile.SAND.getId()
						|| Tile.isGrass(heightmap[row][col].tile()))
					groundCheck = true;
				heightmap[row][col].setGroundCheck(groundCheck);
			}
		}

		for (int row = 10; row < heightmap.length; row++) {
			for (int col = 10; col < heightmap[0].length; col++) {
				if (row + 10 < heightmap.length
						&& col + 10 < heightmap[0].length) {
					int landAmount = 0;
					for (int row2 = -10; row2 <= 10; row2++) {
						for (int col2 = -10; col2 <= 10; col2++) {
							if (heightmap[row][col].groundCheck()) {
								landAmount++;
							}
							// Checking to see if the area (21x21) is 70% land
							// or more, 21 x 21 = 441 x .70 = 308
							if (landAmount >= 308) {
								possibleVillageCenters.add(new Point(col, row));
							}
						}
					}
				}
			}
		}
		// Determining how many villages there should be
		int numVillages = (int) (heightmap.length / 200.0)
				* (int) (heightmap[0].length / 100.0);
		// Choosing n amount final village spawns
		for (int i = 0; i < numVillages; i++) {
			int index = random.nextInt(possibleVillageCenters.size() - 1);
			finalVillageCenters.add(possibleVillageCenters.get(index));
			possibleVillageCenters.remove(index);
		}

		for (int row = 0; row < heightmap.length; row++) {
			for (int col = 0; col < heightmap[0].length; col++) {
				double probability = 0;
				for (int i = 0; i < finalVillageCenters.size(); i++) {
					probability += (1.0 / Math.abs(row
							- finalVillageCenters.get(i).x))
							+ (1.0 / Math.abs(col
									- finalVillageCenters.get(i).y));
				}
				probability *= 1000;
				if (probability > 50)
					probability = 900;
				heightmap[row][col].setProbability(probability);
			}
		}

		for (int row = 0; row < heightmap.length; row++) {
			for (int col = 0; col < heightmap[0].length; col++) {
				boolean grassChecker = true;
				if (row > 8 && row < heightmap.length - 7 && col > 8
						&& col < heightmap[row].length - 7) {
					for (int row2 = -9; row2 < 7; row2++) {
						for (int col2 = -8; col2 < 7; col2++) {
							if (row2 > -2 && col2 > -2) {
								if (heightmap[row + row2][col + col2].tile() == Tile.WATER.getId()
										|| heightmap[row + row2][col + col2].tile() == Tile.WATERSAND.getId()
										|| heightmap[row + row2][col + col2].tile() == Tile.STONE.getId()) {
									grassChecker = false;
								}
							}
							if (heightmap[row + row2][col + col2]
									.getHouse()) {
								grassChecker = false;
							}
						}
					}
				}
				if (heightmap[row][col].probability() < 900) {
					if (random.nextInt((int) heightmap[row][col].probability()) == 0) {
						if (row > 10 && row < heightmap.length - 10 && col > 10
								&& col < heightmap[row].length - 10) {
							if (grassChecker) {
								heightmap[row][col].setHouse();
							}
						}
					}
				} else {
					if (random.nextInt(1000) == 0) {
						if (row > 10 && row < heightmap.length - 10 && col > 10) {
							if (grassChecker) {
								heightmap[row][col].setCave();
							}
						}
					}
					if (random.nextInt(600) == 0) {
						if (row > 10 && row < heightmap.length - 10 && col > 10) {
							if (grassChecker) {
								heightmap[row][col].setSpawner();
							}
						}
					}
				}
			}
		}
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
