package ca.javajesus.level;

import java.awt.Point;
import java.util.Random;

import ca.javajesus.game.entities.structures.TransporterInterior;

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
					tiles[tile] = 20;
				} else if (caveMap[row][col] == false) {
					tiles[tile] = 19;
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
		this.addEntity(new TransporterInterior(this, 500, 500, Level.level1, new Point(220, 79)));
	}
}
