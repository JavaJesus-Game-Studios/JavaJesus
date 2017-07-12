package javajesus.level.interior;

import java.awt.Point;

import javajesus.entities.transporters.MapTransporter;
import javajesus.level.Level;

public abstract class Interior extends Level {
	
	// serialization
	private static final long serialVersionUID = 5670120122380275578L;
	
	// level that was exited to come here
	protected Level nextLevel;
	
	/**
	 * Interior ctor()
	 * 
	 * @param path - file path
	 * @param spawn - where the player enters
	 * @param next - the level that was exited
	 */
	public Interior(final String path, final Point spawn, final Level next) {
		super(path, false, "Interior", spawn);
		
		// instance data
		this.nextLevel = next;
	}
	
	protected MapTransporter[] getMapTransporterPlacement() {
		return null;
	}
}
