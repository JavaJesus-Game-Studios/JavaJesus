package javajesus.entities.solid.buildings;

import java.awt.Point;

import javajesus.entities.Entity;
import javajesus.entities.transporters.Transporter;
import javajesus.graphics.Sprite;
import javajesus.level.Level;
import javajesus.level.interior.RefugeeTentInterior;

/*
 * A small tent
 */
public class RefugeeTent extends Building {

	/**
	 * Creates a refugee tent
	 * 
	 * @param level - the level it is on
	 * @param x - the x coord on the level
	 * @param y - the y coord on the level
	 */
	public RefugeeTent(Level level, int x, int y) {
		super(level, x, y, new int[] { 0xFF111111, 0xFFF8FA8F, 0xFF000000 }, Sprite.refugee_tent);

		level.add(new Transporter(level, x + 34, y + 7, new RefugeeTentInterior(new Point(x + 40, y + 18), level)));
	}

	@Override
    public byte getId(){
        return Entity.REFUGEE_TENT;
    }
}
