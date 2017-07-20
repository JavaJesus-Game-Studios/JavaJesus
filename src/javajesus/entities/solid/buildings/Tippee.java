package javajesus.entities.solid.buildings;

import java.awt.Point;

import javajesus.entities.Entity;
import javajesus.entities.transporters.Transporter;
import javajesus.graphics.Sprite;
import javajesus.level.Level;
import javajesus.level.interior.TippeeInterior;

/*
 * It looks like tippee is spelled wrong
 */
public class Tippee extends Building {

	/**
	 * Creates a prison
	 * 
	 * @param level - the level it is on
	 * @param x - the x coord on the level
	 * @param y - the y coord on the level
	 */
	public Tippee(Level level, int x, int y) {
		super(level, x, y, new int[] { 0xFF522900, 0xFF977F66, 0xFF335C33 }, Sprite.tippee);

		level.add(new Transporter(level, x + 10, y + 34, new TippeeInterior(new Point(x + 16, y + 45), level)));
	}

	@Override
    public byte getId(){
        return Entity.TIPPEE;
    }
}
