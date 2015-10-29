package level.interior;

import java.awt.Point;

import level.Level;

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
