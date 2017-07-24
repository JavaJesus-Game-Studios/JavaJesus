package javajesus.level.interior;

import java.awt.Point;
import java.io.IOException;

import javajesus.level.Level;

public abstract class Interior extends Level {
	
	// level that was exited to come here
	protected Level nextLevel;
	
	/**
	 * Interior ctor()
	 * 
	 * @param path - file path
	 * @param spawn - where the player enters
	 * @param next - the level that was exited
	 * @throws IOException 
	 */
	public Interior(final String path, final Point spawn, final Level next) throws IOException {
		super(path, "Interior", spawn, 0);
		
		// instance data
		this.nextLevel = next;
	}
	
}
