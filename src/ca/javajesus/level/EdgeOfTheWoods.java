package ca.javajesus.level;

import java.awt.Point;

import ca.javajesus.game.entities.structures.trees.Forest;

public class EdgeOfTheWoods extends Level{

	public EdgeOfTheWoods() {
		super("/Levels/Wilderness_Areas/Edge_of_the_Woods_Main.png", true);
		this.spawnPoint = new Point(2224, 2816);
		startingSpawnPoint = new Point(2224, 2816);
	}

	protected void initNPCPlacement() {
		
	}

	protected void initSpawnerPlacement() {
		
	}

	protected void initChestPlacement() {
		
	}

	protected void otherEntityPlacement() {
		
		this.addEntity(new Forest(this, 1360, 2040, 3608, 7816));

	}

}
