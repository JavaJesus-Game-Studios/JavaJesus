package javajesus.entities.solid.buildings;

import java.awt.Point;
import java.io.IOException;

import javajesus.entities.Entity;
import javajesus.entities.transporters.Transporter;
import javajesus.graphics.Sprite;
import javajesus.level.Level;
import javajesus.level.interior.CatholicChapelInterior;

/*
 * A place to pray!
 */
public class CatholicChapel extends Building {

	/**
	 * Creates a catholic chapel
	 * 
	 * @param level - the level it is on
	 * @param x - the x coord on the level
	 * @param y - the y coord on the level
	 * @throws IOException 
	 */
	public CatholicChapel(Level level, int x, int y) throws IOException {
		super(level, x, y, new int[] { 0xFF111111, 0xFFFFFAB0, 0xFFABD3FF }, Sprite.catholic_chapel);

		if (level != null)
		level.add(new Transporter(level, x + 21, y + 47,
		        new CatholicChapelInterior(new Point(x + 27, y + 57), getLevel())));
	}

	@Override
    public byte getId(){
        return Entity.CATHOLIC_CHAPEL ;
    }

}
