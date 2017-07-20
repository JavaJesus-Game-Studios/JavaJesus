package javajesus.entities.solid.buildings;

import java.awt.Point;

import javajesus.entities.Entity;
import javajesus.entities.transporters.Transporter;
import javajesus.graphics.Sprite;
import javajesus.level.Level;
import javajesus.level.interior.ApartmentLobby;

/*
 * An apartment building 
 */
public class ApartmentHighRise extends Building {

	// serialization
	private static final long serialVersionUID = 3895374472641199582L;

	/**
	 * Creates an apartment
	 * 
	 * @param level - the level it is on
	 * @param x - the x coord on the level
	 * @param y - the y coord on the level
	 */
	public ApartmentHighRise(Level level, int x, int y) {
		super(level, x, y, new int[] { 0xFF111111, 0xFF673101, 0xFFABD3FF }, Sprite.apartment);

		getLevel().add(new Transporter(level, x + 30, y + 206, new ApartmentLobby(new Point(x + 36, y + 216), level)));
	}

    @Override
    public byte getId()
    {
        return Entity.APARTMENT_HIGH_RISE;
    }

}
