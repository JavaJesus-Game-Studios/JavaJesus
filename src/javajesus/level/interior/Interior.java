package javajesus.level.interior;

import java.awt.Point;

import javajesus.entities.structures.transporters.MapTransporter;
import javajesus.level.Level;

public abstract class Interior extends Level {
	
	private static final long serialVersionUID = 5670120122380275578L;
	
	protected Level nextLevel;
	
	public Interior(String string, Point point, Level level) {
		super(string, false, "Interior");
		setSpawnPoint(point.x, point.y);
		this.nextLevel = level;
	}
	
	protected MapTransporter[] getMapTransporterPlacement() {
		return null;
	}
}
