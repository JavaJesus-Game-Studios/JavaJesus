package javajesus.level.interior;

import java.awt.Point;
import java.io.File;
import java.io.IOException;

import javajesus.dataIO.EntityData;
import javajesus.dataIO.LevelData;
import javajesus.entities.transporters.Transporter;
import javajesus.level.Level;

/*
 * An Interior is a level inside a building
 */
public abstract class Interior extends Level {
	
	// level that was exited to come here
	protected Level outside;
	
	/**
	 * Interior ctor()
	 * 
	 * @param path - file path
	 * @param spawn - where the player enters the interior
	 * @param next - the level that was exited
	 * @throws IOException 
	 */
	public Interior(final String path, final Point spawn, final Level outside) throws IOException {
		super("Interior", spawn);
		
		// make sure its not a png file
		if (path.contains(".png")) {
			throw new IOException("Cannot load png files");
		}
		
		// instance data
		this.outside = outside;
		
		// load the tiles from the path
		LevelData.load(new File(Interior.class.getResource(path).getFile()), levelTiles);
		
		// load the entity data
		EntityData.load(this, new File(Interior.class.getResource(path + ENTITY).getFile()));
		
		// add all the transporters
		for (Transporter t: getTransporters()) {
			add(t);
		}
	}
	
	/**
	 * All interiors have transporters to the outside
	 * 
	 * @return list of transporters
	 */
	public abstract Transporter[] getTransporters() throws IOException;
	
}
