package javajesus.entities.solid.buildings;

import java.awt.Point;
import java.io.IOException;

import javajesus.entities.Entity;
import javajesus.entities.transporters.Door;
import javajesus.graphics.Sprite;
import javajesus.level.Level;
import javajesus.level.interior.CastleTowerInterior;

/*
 * The castle arch tower
 */
public class CastleTower extends Building {

	/**
	 * Creates a castle tower
	 * 
	 * @param level - the level it is on
	 * @param x - the x coord on the level
	 * @param y - the y coord on the level
	 * @throws IOException 
	 */
	public CastleTower(Level level, int x, int y) throws IOException {
		super(level, x, y, null, Sprite.castle_tower);
		
		// change bounds
		setBounds(getBounds().x + 11, getBounds().y, getBounds().width - 22, getBounds().height);

		if (level != null)
		level.add(new Door(level, x + 42, y + 157,
		        new CastleTowerInterior(new Point(x + 44, y + 168), level),0,0));

	}
	
	@Override
    public byte getId(){
        return Entity.CASTLE_TOWER;
    }

}
