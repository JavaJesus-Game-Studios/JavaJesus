package javajesus.level;

import java.awt.Point;
import java.io.IOException;

import javax.sound.sampled.Clip;

import javajesus.JavaJesus;
import javajesus.SoundHandler;
import javajesus.entities.Chest;
import javajesus.entities.Spawner;
import javajesus.entities.monsters.Skeleton;
import javajesus.entities.transporters.Ladder;
import javajesus.level.generation.CaveGeneration;
import javajesus.level.tile.Tile;
import javajesus.quest.factories.CharacterFactory;

/*
 * A randomly generated cave
 */
public class RandomCave extends Level {

	// unfiltered tile list of the cave
	private int[][] caveMap;
	
	// the level before entering this cave
	private Level prevLevel;

	// the number of caves there currently are
	private static int numCaves;
	
	// the optimal number of cycles for random cave gen
	private static final int CYCLES = 3;

	/**
	 * Generates a random cave level
	 * 
	 * @param cycles - the cycles
	 * @param prevLevel - the previous level
	 * @param spawn - the spawn point in this random cave
	 */
	public RandomCave(Level prevLevel, Point spawn) {
		super("Random Cave " + numCaves++, spawn);
		
		// instance data
		this.prevLevel = prevLevel;
		
	}
	
	/**
	 * Fills the cave map tiles with real tiles
	 * 
	 * @param cycles - the smoothness of the generation
	 */
	@Override
	public void generateLevel(CharacterFactory cf) throws IOException {
		
		// initialize tile array
		levelTiles = new int[LEVEL_WIDTH * LEVEL_HEIGHT];

		// fill the cave map tiles
		caveMap = CaveGeneration.generateCave(CYCLES);

		// whether or not a proper spawn point is found
		//boolean spawnFound = false;
		
		// iterate through the tiles
		for (int row = 0; row < Level.LEVEL_HEIGHT; row++) {
			for (int col = 0; col < Level.LEVEL_WIDTH; col++) {
				
				// get the current tile
				int tile = col + row * Level.LEVEL_WIDTH;
				
				// Set the spawn point to a location where the player can move
				if (caveMap[row][col] == CaveGeneration.FLOOR) {
					levelTiles[tile] = Tile.CAVEFLOOR.getId();
//					if (row > 5 * 8 && col > 5 * 8) {
//						if (!spawnFound) {
//							setSpawnPoint(col * 8, row * 8);
//							add(new Ladder(this, col * 8, row * 8, prevLevel));
//							spawnFound = true;
//						}
//					}
					
					// chance of spawning enemies in cave to fight
					if (Math.random() < (JavaJesus.difficulty / 1000)) {
						add(new Skeleton(this, col * 8, row * 8));
					}
				}
					
				// Add in the wall tiles and entities
				switch (caveMap[row][col]) {
				case CaveGeneration.CAVE_WALL:
					levelTiles[tile] = Tile.CAVEWALL_TOP.getId();
					
					break;
				case CaveGeneration.CAVE_BORDER_WALL:
					levelTiles[tile] = Tile.CAVEWALL_RGHT.getId();
					break;
				case CaveGeneration.FLOOR_CHEST: 
					levelTiles[tile] = Tile.CAVEFLOOR.getId();
					add(new Chest(this, col * 8, row * 8, (byte) 1));
					break;
				case CaveGeneration.FLOOR_SPAWNER:
					levelTiles[tile] = Tile.CAVEFLOOR.getId();
					add(new Spawner(this, col * 8, row * 8, Spawner.DEMON, 5));
					break;
				case CaveGeneration.SPAWN_POINT:
					levelTiles[tile] = Tile.CAVEFLOOR.getId();
					setSpawnPoint(col * 8 - 12, row * 8);
					add(new Ladder(this, col * 8, row * 8, prevLevel));
					System.out.println("Spawn at " + (col * 8) + " " + (row * 8));
					break;
				case CaveGeneration.EXIT_POINT:
					levelTiles[tile] = Tile.CAVEFLOOR.getId();
					add(new Ladder(this, col * 8, row * 8,
							new RandomCave(this, new Point(0, 0))));
					System.out.println("Exit at " + (col * 8) + " " + (row * 8));
					
					// every new exit point increases difficulty
					JavaJesus.difficulty += 0.001f;
					break;
				default:
					break;
				}
					
				
//				else if (caveMap[row][col] == 6) {
//					levelTiles[tile] = Tile.CAVEFLOOR_ROCK.getId();
//					add(new Ladder(this, col * 8, row * 8,
//							new RandomLevel(new Point(col * 8, row * 8), this)/*, new Point(col * 8, row * 8)*/));
//
//				}
			}
		}
	}
	
	@Override
	public Clip getBackgroundMusic() {
		return SoundHandler.background2;
	}

}
