package ca.javajesus.level;

import java.awt.Point;

public class RandomCave extends Level {
	private boolean[][] caveMap;

	public RandomCave(int height, int width, int cycles) {
		super(width, height);
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				if (caveMap[row][col] == true) {
					spawnPoint = new Point(row, col);
				}
			}
		}
	}
	
	protected void generateLevel() {
		caveMap = new CaveGeneration(height, width, 20).generateCave();
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				//System.out.print(caveMap[y][x] + " ");
				int tile = x + y * width;
				if (caveMap[y][x] == true) {
					tiles[tile] = 4;
				} else if (caveMap[y][x] == false) {
					tiles[tile] = 1;
				}
			}
			//System.out.println();
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
