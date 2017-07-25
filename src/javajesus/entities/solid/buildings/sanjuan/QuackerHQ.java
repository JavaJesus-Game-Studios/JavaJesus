package javajesus.entities.solid.buildings.sanjuan;

import java.awt.Point;
import java.io.IOException;

import javajesus.entities.Entity;
import javajesus.entities.solid.buildings.Building;
import javajesus.entities.transporters.Door;
import javajesus.graphics.Sprite;
import javajesus.level.Level;
import javajesus.level.interior.PoorHouseInterior;

/*
 * Quack Quack
 */
public class QuackerHQ extends Building {

	/**
	 * Creates a quacker hq
	 * 
	 * @param level - the level it is on
	 * @param x - the x coord on the level
	 * @param y - the y coord on the level
	 * @throws IOException 
	 */
	public QuackerHQ(Level level, int x, int y) throws IOException {
		super(level, x, y, new int[] { 0xFF111111, 0xFFFFFFFF, 0xFF4D4DFF }, Sprite.quacker_hq);

		// change the bounds
		setBounds(getBounds().x + 21, getBounds().y, getBounds().width - 58, getBounds().height);

		if (level != null)
		level.add(new Door(level, x + 53, y + 83, new PoorHouseInterior(new Point(x + 40, y + 67), level)));
	}
	
	@Override
    public byte getId(){
        return Entity.QUACKER_HQ;
    }
}
