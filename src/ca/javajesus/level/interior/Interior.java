package ca.javajesus.level.interior;

import java.awt.Point;

import ca.javajesus.level.Level;

public class Interior extends Level {
	
	protected Level nextLevel;
	
	public Interior(String string, Point point, Level level) {
		super(string, false, "Interior");
		this.spawnPoint = point;
		this.nextLevel = level;
	}
	
	protected void initNPCPlacement() {
		
	}

	protected void initSpawnerPlacement() {
	
	}

	protected void initChestPlacement() {
		
	}

	protected void otherEntityPlacement() {
		
	}

	@Override
	protected void initMapTransporters() {
		// TODO Auto-generated method stub
		
	}

}
