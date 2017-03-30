package level;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import javajesus.SoundHandler;
import javajesus.entities.Entity;
import javajesus.entities.Spawner;
import javajesus.entities.npcs.NPC;
import javajesus.entities.structures.furniture.Chest;
import javajesus.entities.structures.transporters.MapTransporter;
import javajesus.entities.structures.transporters.TransporterLadder;

import javax.sound.sampled.Clip;

import items.Item;
import level.generation.CaveGeneration;

public class RandomCave extends Level {

	private static final long serialVersionUID = 5464371630174344690L;

	private int[][] caveMap;
	private Level prevLevel;

	// the entering point of the level before entering the cave
	private Point prevSpawn;

	Random rand = new Random();

	private int cycles;

	// the number of caves there currently are
	private static int numCaves;

	public RandomCave(int height, int width, int cycles, Level prevLevel, Point prevSpawn) {
		super(width, height, false, "Random Cave " + numCaves++);
		this.prevLevel = prevLevel;
		this.prevSpawn = prevSpawn;
		this.cycles = 3;
	}

	public Clip getBackgroundMusic() {
		return SoundHandler.background2;
	}

	protected void generateLevel() {

		caveMap = new CaveGeneration(getHeight(), getWidth(), cycles).generateCave();

		ArrayList<Item> chest1 = new ArrayList<Item>();
		chest1.add(Item.banana);
		chest1.add(Item.revolver);
		chest1.add(Item.shortSword);

		boolean spawnFound = false;
		for (int row = 0; row < getHeight(); row++) {
			for (int col = 0; col < getWidth(); col++) {
				int tile = col + row * getWidth();
				if (caveMap[row][col] == 1) {
					tiles[tile] = 20;
					if (row > 5 * 8 && col > 5 * 8) {
						if (!spawnFound) {
							setSpawnPoint(col * 8, row * 8);
							add(new TransporterLadder(this, col * 8, row * 8, prevLevel, prevSpawn));
							spawnFound = true;
						}
					}
				} else if (caveMap[row][col] == 2) {
					tiles[tile] = 19;
				} else if (caveMap[row][col] == 0) {
					tiles[tile] = 0;
				} else if (caveMap[row][col] == 4) {
					tiles[tile] = 20;
					add(new Chest(this, col * 8, row * 8, "", rand.nextInt(2) + 1));
				} else if (caveMap[row][col] == 5) {
					tiles[tile] = 20;
					add(new Spawner(this, col * 8, row * 8, Spawner.DEMON, 5));
				} else if (caveMap[row][col] == 6) {
					tiles[tile] = 20;
					add(new TransporterLadder(this, col * 8, row * 8,
							new RandomLevel(200, 200, new Point(col * 8, row * 8), this), new Point(col * 8, row * 8)));

				}
			}
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
