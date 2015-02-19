package ca.javajesus.level;

import java.awt.Point;
import java.util.Random;

public class RandomCave extends Level {
	private boolean[][] caveMap;

	Random rand =  new Random();
	
	public RandomCave(int height, int width, int cycles) {
		super(width, height);
		spawnPoint = new Point(500, 500);
	}
	
	protected void generateLevel() {
		caveMap = new CaveGeneration(height, width, 4).generateCave();
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				int tile = col + row * width;
				if (caveMap[row][col] == true) {
					tiles[tile] = 14;
				} else if (caveMap[row][col] == false) {
					tiles[tile] = 1;
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
		// TODO Auto-generated method stub

	}
}
