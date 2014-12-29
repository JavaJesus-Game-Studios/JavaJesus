package ca.javajesus.level.interior;

import java.awt.Point;

import ca.javajesus.level.Level;

public class PoorHouseInterior extends Level {

	public PoorHouseInterior() {
		super("/Buildings/Generic Interiors/Hut_Interior.png");
	}
	
	public Point spawnPoint() {
		return new Point(50, 50);
	}

	protected void initNPCPlacement() {
		
	}

	protected void initSpawnerPlacement() {
	
	}

	protected void initChestPlacement() {
		
	}

	protected void otherEntityPlacement() {
		
	}

}
