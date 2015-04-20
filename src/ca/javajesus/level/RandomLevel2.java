package ca.javajesus.level;

import java.awt.Point;
import java.util.Random;
import ca.javajesus.game.entities.SolidEntity;
import ca.javajesus.game.entities.Spawner;
import ca.javajesus.game.entities.npcs.NPC;
import ca.javajesus.game.entities.structures.CaveEntrance;
import ca.javajesus.game.entities.structures.Hut;
import ca.javajesus.game.entities.structures.NiceHouse;
import ca.javajesus.game.entities.structures.PoorHouse;
import ca.javajesus.level.generation.HeightMap;
import ca.javajesus.level.generation.HeightMapTile;
import ca.javajesus.level.tile.Tile;

public class RandomLevel2 extends Level {
	
	Point spawn;

	private HeightMapTile[][] heightmap;

	/**
	 * Generates a random level with smooth terrain based on a simple array
	 * noise map
	 * 
	 * @param width
	 *            : The width of the level
	 * @param height
	 *            : The height of the level
	 */
	

	public RandomLevel2(int width, int height, Point spawn) {
		super(width, height, false);
		this.spawn = spawn;
	}
	
	public RandomLevel2(int width, int height, Point spawn, boolean load) {
		super(width, height, load);
		this.spawn = spawn;
	}

	
	protected void generateLevel() {
		Random rand = new Random();
		heightmap = new HeightMap(width, height, true, false)
				.generateHeightMap(20);
		boolean spawnFound = true;
		for (int row = 0; row < heightmap.length; row++) {
			for (int col = 0; col < heightmap[row].length; col++) {
				int tile = col + row * width;
				if (heightmap[row][col].tile() < 500) {
					tiles[tile] = heightmap[row][col].tile();
				} 
				if (heightmap[row][col].getHouse()) {
					tiles[tile] = heightmap[row][col].tile();
					this.addEntity(getBuilding(col * 8, row * 8));
					if (rand.nextInt(4) == 0) {
						this.addEntity(NPC.getRandomNPC(this, (col - 1) * 8, (row + 6) * 8));
					}
				}
				if (heightmap[row][col].getCave()) {
					tiles[tile] = heightmap[row][col].tile();
					this.addEntity(new CaveEntrance(this, col * 8 - 18, row * 8 - 28, new RandomCave(level1.width,
							level1.height, 5, this, new Point(col * 8, row * 8))));
				}
				if (heightmap[row][col].getSpawner()) {
					tiles[tile] = heightmap[row][col].tile();
					this.addEntity(new Spawner(this, col * 8, row * 8, "Demon"));
				}
				if (heightmap[row][col].tile() == 0) {
					if (row > heightmap.length / 2.0 && col > heightmap[0].length / 2.0) {
						while (spawnFound) {
							spawnPoint = new Point(col * 8, row * 8);
							if (spawn != null)
								this.addEntity(new CaveEntrance(this, col * 8 - 18, row * 8 - 28, spawn));
							else
								this.addEntity(new CaveEntrance(this, col * 8 - 18, row * 8 - 28));
							spawnFound = false;
						}
					}
				}
				//addEntity(new PoorHouse(this, spawnPoint.x, spawnPoint.y));
			}
		}
	}

	@Override
	protected void initNPCPlacement() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initSpawnerPlacement() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initChestPlacement() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void otherEntityPlacement() {
		// TODO Auto-generated method stub

	}

	private SolidEntity getBuilding(int x, int y) {
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

	public Tile getTile(int x, int y) {
		if (0 > x || x >= width || 0 > y || y >= height)
			return Tile.VOID;
		switch (tiles[x + y * width]) {
		case 0:
			return Tile.GRASS;
		case 1:
			return Tile.SAND;
		case 2:
			return Tile.STONE;
		case 3:
			return Tile.DIRTROAD;
		case 4:
			return Tile.WATER;
		case 5:
			return Tile.ROAD1;
		case 7:
			return Tile.WATERSAND;
		case 8:
			return Tile.ROAD2;
		case 9:
			return Tile.GRASS2;
		case 10:
			return Tile.GRASS3;
		case 11:
			return Tile.GRASS_FLOWER;
		default:
			return Tile.VOID;
		}

	}

	@Override
	protected void initMapTransporters() {
		// TODO Auto-generated method stub
		
	}
}
