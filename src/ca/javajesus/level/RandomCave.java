package ca.javajesus.level;

import java.awt.Point;
import java.util.Random;

import javax.sound.sampled.Clip;

import ca.javajesus.game.SoundHandler;
import ca.javajesus.game.entities.structures.TransporterInterior;
import ca.javajesus.game.entities.structures.TransporterLadder;
import ca.javajesus.level.generation.CaveGeneration;

public class RandomCave extends Level {
	private int[][] caveMap;

	Random rand = new Random();

	public RandomCave(int height, int width, int cycles) {
		super(width, height, false);

	}
	
	public Clip getBackgroundMusic() {
		return SoundHandler.sound.background2;
	}

	protected void generateLevel() {
		boolean spawnFound = true;
		caveMap = new CaveGeneration(height, width, 3).generateCave();
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				int tile = col + row * width;
				if (caveMap[row][col] == 1) {
					tiles[tile] = 20;
					if (row > 300 && col > 300) {
						while (spawnFound) {
							spawnPoint = new Point(col * 8, row * 8);
							spawnFound = false;
						}
					}
				} else if (caveMap[row][col] == 2) {
					tiles[tile] = 19;
				} else if (caveMap[row][col] == 0) {
					tiles[tile] = 0;
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
		this.addEntity(new TransporterLadder(this, spawnPoint.getX(), spawnPoint.getY(), Level.level1,
				new Point(220, 79)));
	}
}
