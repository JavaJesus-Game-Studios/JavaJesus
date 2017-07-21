package javajesus.level;

import java.awt.Point;
import java.util.Random;

import javajesus.entities.Entity;
import javajesus.entities.Spawner;
import javajesus.entities.npcs.NPC;
import javajesus.entities.solid.buildings.Building;
import javajesus.entities.solid.buildings.CaveEntrance;
import javajesus.entities.solid.buildings.Hut;
import javajesus.entities.solid.buildings.NiceHouse;
import javajesus.entities.solid.buildings.PoorHouse;
import javajesus.entities.solid.furniture.Chest;
import javajesus.entities.transporters.MapTransporter;
import javajesus.level.generation.HeightMap;
import javajesus.level.generation.HeightMapTile;

public class RandomLevel extends Level {

	private static final long serialVersionUID = 9158523502013380330L;

	private HeightMapTile[][] heightmap;

	// number of random levels
	private static int numLevels;

	/**
	 * Generates a random level with smooth terrain based on a simple array
	 * noise map
	 * 
	 * @param width
	 *            : The width of the level
	 * @param height
	 *            : The height of the level
	 */

	public RandomLevel(int width, int height, Point spawn) {
		super(width, height, false, "Random Level " + numLevels++);
		setSpawnPoint(spawn.x, spawn.y);
	}

	private RandomCave lastCave;

	public RandomLevel(int width, int height, Point spawn, RandomCave lastCave) {
		super(width, height, false, "Random Level " + numLevels++);
		setSpawnPoint(spawn.x, spawn.y);
		this.lastCave = lastCave;
	}

	public RandomLevel(int width, int height, Point spawn, boolean load) {
		super(width, height, load, "Random Level " + numLevels++);
		setSpawnPoint(spawn.x, spawn.y);
	}

	protected void generateLevel() {

		// generates last cave if there is one
		if (lastCave != null)
			add(new CaveEntrance(this, getSpawnPoint().x - 18, getSpawnPoint().y - 28, lastCave));

		Random rand = new Random();
		heightmap = new HeightMap(getWidth(), getHeight(), true, false).generateHeightMap(20);
		for (int row = 0; row < heightmap.length; row++) {
			for (int col = 0; col < heightmap[row].length; col++) {
				int tile = col + row * getWidth();
				if (heightmap[row][col].tile() < getWidth()) {
					levelTiles[tile] = heightmap[row][col].tile();
				}
				if (heightmap[row][col].getHouse()) {
					levelTiles[tile] = heightmap[row][col].tile();
					add(getBuilding(col * 8, row * 8));
					if (rand.nextInt(4) == 0) {
						add(NPC.getRandomNPC(this, (col - 1) * 8, (row + 6) * 8));
					}
				}
				if (heightmap[row][col].getCave()) {
					levelTiles[tile] = heightmap[row][col].tile();
					add(new CaveEntrance(this, col * 8 - 18, row * 8 - 28,
							new RandomCave(200, 200, 5, this, new Point(col * 8, row * 8))));
				}
				if (heightmap[row][col].getSpawner()) {
					levelTiles[tile] = heightmap[row][col].tile();
					add(new Spawner(this, col * 8, row * 8, Spawner.DEMON));
				}
				if (heightmap[row][col].tile() == 0) {
					if (row > heightmap.length / 2.0 && col > heightmap[0].length / 2.0) {
						add(new CaveEntrance(this, col * 8 - 18, row * 8 - 28, getSpawnPoint()));

					}
				}
				// addEntity(new PoorHouse(this, spawnPoint.x, spawnPoint.y));
			}
		}
	}

	private Building getBuilding(int x, int y) {
		Random random = new Random();
		switch (random.nextInt(10)) {
		case 1:
			return new NiceHouse(this, x, y);
		case 2:
			return new Hut(this, x, y);
		default:
			return new PoorHouse(this, x, y);
		}
	}

	@Override
	protected NPC[] getNPCPlacement() {
		return null;
	}

	@Override
	protected Spawner[] getSpawnerPlacement() {
		return null;
	}

	@Override
	protected Chest[] getChestPlacement() {
		return null;
	}

	@Override
	protected MapTransporter[] getMapTransporterPlacement() {
		return null;
	}

	@Override
	protected Entity[] getOtherPlacement() {
		return null;
	}

}
