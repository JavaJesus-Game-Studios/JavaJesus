package level;

import game.SoundHandler;
import game.entities.structures.furniture.Chest;
import game.entities.structures.transporters.TransporterLadder;
import items.Item;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import javax.sound.sampled.Clip;

import level.generation.CaveGeneration;

public class RandomCave extends Level {

	private static final long serialVersionUID = 5464371630174344690L;

	private int[][] caveMap;
	private Level prevLevel;
	
	// the entering point of the level before entering the cave
	private Point prevSpawn;

	Random rand = new Random();
	
	private int cycles;

	public RandomCave(int height, int width, int cycles, Level prevLevel, Point prevSpawn) {
		super(width, height, false);
		this.prevLevel = prevLevel;
		this.prevSpawn = prevSpawn;
		this.cycles = 3;
	}

	public Clip getBackgroundMusic() {
		return SoundHandler.sound.background2;
	}

	protected void generateLevel() {

		caveMap = new CaveGeneration(height, width, cycles).generateCave();

		ArrayList<Item> chest1 = new ArrayList<Item>();
		chest1.add(Item.banana);
		chest1.add(Item.revolver);
		chest1.add(Item.shortSword);

		boolean spawnFound = false;
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				int tile = col + row * width;
				if (caveMap[row][col] == 1) {
					tiles[tile] = 20;
					if (row > 5 * 8 && col > 5 * 8) {
						if (!spawnFound) {
							spawnPoint = new Point(col * 8, row * 8);
							this.addEntity(
									new TransporterLadder(this, (int) spawnPoint.getX(), (int) spawnPoint.getY(), prevLevel, prevSpawn));
							spawnFound = true;
						}
					}
				} else if (caveMap[row][col] == 2) {
					tiles[tile] = 19;
				} else if (caveMap[row][col] == 0) {
					tiles[tile] = 0;
				} else if (caveMap[row][col] == 4) {
					tiles[tile] = 20;
					this.addEntity(new Chest(this, col * 8, row * 8, "", rand.nextInt(2) + 1));
				} else if (caveMap[row][col] == 5) {
					tiles[tile] = 20;
					this.addSpawner(col * 8, row * 8, "Demon", 5);
				} else if (caveMap[row][col] == 6) {
					tiles[tile] = 20;
					this.addEntity(new TransporterLadder(this, col * 8, row * 8,
							new RandomLevel(200, 200, new Point(col * 8, row * 8), this), new Point(col * 8, row * 8)));

				}
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
		
	}

	@Override
	protected void initMapTransporters() {
		// TODO Auto-generated method stub

	}
}
