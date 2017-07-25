package javajesus.entities.solid.buildings;

import java.awt.Point;
import java.io.IOException;

import javajesus.entities.Entity;
import javajesus.entities.transporters.Door;
import javajesus.graphics.Sprite;
import javajesus.level.Level;
import javajesus.level.interior.CatholicChurchInterior;

/*
 * A bigger place to pray!
 */
public class CatholicChurch extends Building {

	/**
	 * Creates a catholic church
	 * 
	 * @param level - the level it is on
	 * @param x - the x coord on the level
	 * @param y - the y coord on the level
	 * @throws IOException 
	 */
	public CatholicChurch(Level level, int x, int y) throws IOException {
		super(level, x, y, new int[] { 0xFF111111, 0xFFFFFAB0, 0xFFD30000 }, Sprite.catholic_church);
		
		if (level != null)
		level.add(new Door(level, x + 38, y + 59,
				new CatholicChurchInterior(new Point(x + 44, y + 70), getLevel())));
	}
	
	@Override
    public byte getId(){
        return Entity.CATHOLIC_CHURCH ;
    }

}
