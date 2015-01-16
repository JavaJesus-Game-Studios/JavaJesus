package ca.javajesus.level.interior;

import java.awt.Point;

import ca.javajesus.game.entities.structures.Transporter;
import ca.javajesus.level.Level;

public class PoorHouseInterior extends Level {

	public PoorHouseInterior() {
		super("/Buildings/Generic Interiors/Hut_Interior.png");
		this.spawnPoint = new Point(252, 252);	
	}
	
	protected void initNPCPlacement() {
		
	}

	protected void initSpawnerPlacement() {
	
	}

	protected void initChestPlacement() {
		
	}

	protected void otherEntityPlacement() {
		this.addEntity(new Transporter(this, 25, 50, Level.level1, new Point(111, 83)));
	}

}
