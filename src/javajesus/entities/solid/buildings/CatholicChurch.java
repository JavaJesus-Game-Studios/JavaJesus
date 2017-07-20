package javajesus.entities.solid.buildings;

import java.awt.Point;

import javajesus.entities.Entity;
import javajesus.entities.transporters.Transporter;
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
	 */
	public CatholicChurch(Level level, int x, int y) {
		super(level, x, y, new int[] { 0xFF111111, 0xFFFFFAB0, 0xFFD30000 }, Sprite.catholic_church);
		
		level.add(new Transporter(level, x + 38, y + 59,
				new CatholicChurchInterior(new Point(x + 44, y + 70), getLevel())));
	}
	
	@Override
    public byte getId(){
        return Entity.CATHOLIC_CHURCH ;
    }

}
