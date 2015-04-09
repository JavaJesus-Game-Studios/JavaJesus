package ca.javajesus.level;

import java.awt.Point;

import ca.javajesus.game.entities.vehicles.CenturyLeSabre;

public class SanCisco extends Level {

	public SanCisco() {
		super("/Levels/Cities/San_Cisco.png", false);
		this.spawnPoint = new Point(1050, 1050);
	}

	protected void initNPCPlacement() {
		
		this.addEntity(new CenturyLeSabre(this, 1100, 1100));
		
	}

	protected void initSpawnerPlacement() {
		
	}

	protected void initChestPlacement() {
		
	}
	
	protected void otherEntityPlacement() {
		
	}
	
	

}
