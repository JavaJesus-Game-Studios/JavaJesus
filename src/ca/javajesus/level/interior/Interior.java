package ca.javajesus.level.interior;

import java.awt.Point;

import ca.javajesus.game.entities.structures.Transporter;
import ca.javajesus.game.entities.structures.TransporterInterior;
import ca.javajesus.level.Level;

public class Interior extends Level {
	
	public Interior(String string, Point point) {
		super(string);
		this.spawnPoint = point;	
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
