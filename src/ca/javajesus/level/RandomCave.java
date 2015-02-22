package ca.javajesus.level;

import java.awt.Point;
import java.util.Random;

import ca.javajesus.game.entities.structures.TransporterInterior;

public class RandomCave extends Level {
	private int[][] caveMap;

	Random rand = new Random();

	public RandomCave(int height, int width, int cycles) {
		super(width, height);
		// spawnPoint = new Point(500, 500);

	}

	protected void generateLevel() {
		boolean spawnFound = true;
		caveMap = new CaveGeneration(height, width, 4).generateCave();
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
		this.addEntity(new TransporterInterior(this, spawnPoint.getX(), spawnPoint.getY(), Level.level1,
				new Point(220, 79)));
	}
}
