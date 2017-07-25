package javajesus.level;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import javax.sound.sampled.Clip;

import javajesus.SoundHandler;
import javajesus.entities.Spawner;
import javajesus.entities.solid.furniture.Chest;
import javajesus.entities.transporters.Ladder;
import javajesus.items.Item;
import javajesus.level.generation.CaveGeneration;

/*
 * A randomly generated cave
 */
public class RandomCave extends Level {

	private int[][] caveMap;
	private Level prevLevel;

	// the entering point of the level before entering the cave
	private Point prevSpawn;

	Random rand = new Random();

	private int cycles;

	// the number of caves there currently are
	private static int numCaves;

	public RandomCave(int cycles, Level prevLevel, Point prevSpawn) {
		super("Random Cave " + numCaves++, prevSpawn);
		this.prevLevel = prevLevel;
		this.prevSpawn = prevSpawn;
		this.cycles = 3;
		generateLevel();
	}

	public Clip getBackgroundMusic() {
		return SoundHandler.background2;
	}

	protected void generateLevel() {

		caveMap = new CaveGeneration(Level.LEVEL_HEIGHT, Level.LEVEL_WIDTH, cycles).generateCave();

		ArrayList<Item> chest1 = new ArrayList<Item>();
		chest1.add(Item.banana);
		chest1.add(Item.revolver);
		chest1.add(Item.shortSword);

		boolean spawnFound = false;
		for (int row = 0; row < Level.LEVEL_HEIGHT; row++) {
			for (int col = 0; col < Level.LEVEL_WIDTH; col++) {
				int tile = col + row * Level.LEVEL_WIDTH;
				if (caveMap[row][col] == 1) {
					levelTiles[tile] = 20;
					if (row > 5 * 8 && col > 5 * 8) {
						if (!spawnFound) {
							setSpawnPoint(col * 8, row * 8);
							//add(new Ladder(this, col * 8, row * 8, prevLevel, prevSpawn));
							add(new Ladder(this, col * 8, row * 8, prevLevel));
							spawnFound = true;
						}
					}
				} else if (caveMap[row][col] == 2) {
					levelTiles[tile] = 19;
				} else if (caveMap[row][col] == 0) {
					levelTiles[tile] = 0;
				} else if (caveMap[row][col] == 4) {
					levelTiles[tile] = 20;
					add(new Chest(this, col * 8, row * 8, (byte) 1));
				} else if (caveMap[row][col] == 5) {
					levelTiles[tile] = 20;
					add(new Spawner(this, col * 8, row * 8, Spawner.DEMON, 5));
				} else if (caveMap[row][col] == 6) {
					levelTiles[tile] = 20;
					add(new Ladder(this, col * 8, row * 8,
							new RandomLevel(new Point(col * 8, row * 8), this)/*, new Point(col * 8, row * 8)*/));

				}
			}
		}
	}

}
