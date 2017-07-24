package javajesus.entities.solid.buildings;

import java.awt.Point;
import java.io.IOException;

import javajesus.entities.Entity;
import javajesus.entities.transporters.Transporter;
import javajesus.graphics.Sprite;
import javajesus.level.Level;
import javajesus.level.interior.RussianChurchInterior;

/*
 * A Russian church
 */
public class RussianOrthodoxChurch extends Building {

	/**
	 * Creates a Russian church
	 * 
	 * @param level - the level it is on
	 * @param x - the x coord on the level
	 * @param y - the y coord on the level
	 * @throws IOException 
	 */
	public RussianOrthodoxChurch(Level level, int x, int y) throws IOException {
		super(level, x, y, new int[] { 0xFF111111, 0xFF0069AC, 0xFFFFBC02 }, Sprite.russian_orthodox_church);

		// change the bounds
		setBounds(getBounds().x + 4, getBounds().y, getBounds().width - 8, getBounds().height);

		if (level != null)
		level.add(new Transporter(level, x + 43, y + 64, new RussianChurchInterior(new Point(x + 49, y + 75), level)));

	}

	@Override
    public byte getId(){
        return Entity.RUSSIAN_ORTHODOX_CHURCH;
    }
}
