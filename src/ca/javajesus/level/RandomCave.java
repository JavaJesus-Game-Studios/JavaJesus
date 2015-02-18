package ca.javajesus.level;

import java.awt.Point;

public class RandomCave extends Level {
	private boolean[][] caveMap;
	
	private CaveGeneration caveGen;

	public RandomCave(int height, int width, int cycles) {
		super(width, height);
		caveGen = new CaveGeneration(height, width, 5);
		caveMap = caveGen.generateCave();
		spawnPoint = caveGen.getSpawnPoint();
	}
	
	protected void generateLevel() {
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				int tile = col + row * width;
				if (caveMap[row][col] == true) {
					tiles[tile] = 4;
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
