package javajesus.entities.solid.buildings;

import java.awt.Point;
import java.io.IOException;

import javajesus.entities.Entity;
import javajesus.entities.transporters.Door;
import javajesus.graphics.Sprite;
import javajesus.level.Level;
import javajesus.level.interior.AlphaCave;

/*
 * The cave structure front
 */
public class AlphaCaveEntrance extends Building {

	// color set
	private static final int[] color = { 0xFF301E01, 0xFF632a06, 0xFF000000 };
	
	

	/**
	 * Creates a cave entrance
	 * 
	 * @param level - the level it is on
	 * @param x - the x coord on the level
	 * @param y - the y coord on the level
	 */
	public AlphaCaveEntrance(Level level, int x, int y) throws IOException{
		super(level, x, y, color, Sprite.cave_entrance);

		if (level != null)
			level.add(new Door(level, x + 18, y + 16, new AlphaCave(new Point(x + 43, y + 167), getLevel()), 4,0));
	}

	/**
	 * Creates a cave entrance
	 * 
	 * @param level - the level it is on
	 * @param x - the x coord on the level
	 * @param y - the y coord on the level
	 * @param spawn - the spawn point in the cave interior
	 */
	public AlphaCaveEntrance(Level level, int x, int y, Point spawn) throws IOException{
		super(level, x, y, color, Sprite.cave_entrance);
		
		if (level != null)
		level.add(new Door(level, x + 18, y + 16, new AlphaCave(new Point(x + 43, y + 167), getLevel()),4,0));
	}

	/**
	 * Creates a cave entrance
	 * 
	 * @param level - the level it is on
	 * @param x - the x coord on the level
	 * @param y - the y coord on the level
	 * @param outside - the level it leads to
	 */
	public AlphaCaveEntrance(Level level, int x, int y, Level nextLevel) throws IOException{
		super(level, x, y, color, Sprite.cave_entrance);

		if (level != null)
		level.add(new Door(level, x + 18, y + 16, new AlphaCave(new Point(x + 43, y + 167), getLevel()),4,0));
	}
	
	@Override
    public byte getId(){
        return Entity.ALPHA_CAVE_ENTRANCE ;
    }

}
