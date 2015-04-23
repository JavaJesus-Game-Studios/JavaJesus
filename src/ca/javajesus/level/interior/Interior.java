package ca.javajesus.level.interior;

import java.awt.Point;

import ca.javajesus.level.Level;

public abstract class Interior extends Level {
	
	protected Level nextLevel;
	
	public Interior(String string, Point point, Level level) {
		super(string, false, "Interior");
		this.spawnPoint = point;
		this.nextLevel = level;
	}
	
	protected void initMapTransporters() {
		
	}
}
