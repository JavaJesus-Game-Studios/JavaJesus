package level;

import game.entities.SolidEntity;
import game.entities.Spawner;
import game.entities.npcs.NPC;
import game.entities.structures.CaveEntrance;
import game.entities.structures.Hut;
import game.entities.structures.NiceHouse;
import game.entities.structures.PoorHouse;

import java.awt.Point;
import java.util.Random;

import level.generation.HeightMap;
import level.generation.HeightMapTile;

public class RandomLevel extends Level {

	private static final long serialVersionUID = 9158523502013380330L;

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

	public RandomLevel(int width, int height, Point spawn) {
		super(width, height, false);
		this.spawnPoint = spawn;
	}
	
	private RandomCave lastCave; 
	
	public RandomLevel(int width, int height, Point spawn, RandomCave lastCave) {
		super(width, height, false);
		this.spawnPoint = spawn;
		this.lastCave = lastCave;
	}

	public RandomLevel(int width, int height, Point spawn, boolean load) {
		super(width, height, load);
		this.spawnPoint = spawn;
	}

	protected void generateLevel() {
		
		// generates last cave if there is one
		if (lastCave != null)
			this.addEntity(new CaveEntrance(this, spawnPoint.x - 18,
				spawnPoint.y - 28, lastCave));
		
		Random rand = new Random();
		heightmap = new HeightMap(width, height, true, false)
				.generateHeightMap(20);
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
						this.addEntity(NPC.getRandomNPC(this, (col - 1) * 8,
								(row + 6) * 8));
					}
				}
				if (heightmap[row][col].getCave()) {
					tiles[tile] = heightmap[row][col].tile();
					this.addEntity(new CaveEntrance(this, col * 8 - 18,
							row * 8 - 28, new RandomCave(200, 200, 5, this,
									new Point(col * 8, row * 8))));
				}
				if (heightmap[row][col].getSpawner()) {
					tiles[tile] = heightmap[row][col].tile();
					this.addEntity(new Spawner(this, col * 8, row * 8, "Demon"));
				}
				if (heightmap[row][col].tile() == 0) {
					if (row > heightmap.length / 2.0
							&& col > heightmap[0].length / 2.0) {
						if (spawnPoint != null)
							this.addEntity(new CaveEntrance(this, col * 8 - 18,
									row * 8 - 28, spawnPoint));
						else
							this.addEntity(new CaveEntrance(this, col * 8 - 18,
									row * 8 - 28));
					}
				}
				// addEntity(new PoorHouse(this, spawnPoint.x, spawnPoint.y));
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

	@Override
	protected void initMapTransporters() {
		// TODO Auto-generated method stub

	}
}
