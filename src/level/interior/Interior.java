package level.interior;

import java.awt.Point;

import level.Level;

public abstract class Interior extends Level {
	
	private static final long serialVersionUID = 5670120122380275578L;
	
	protected Level nextLevel;
	
	public Interior(String string, Point point, Level level) {
		super(string, false, "Interior");
		setSpawnPoint(point.x, point.y);
		this.nextLevel = level;
	}
	
	protected void initMapTransporters() {
		
	}
}
