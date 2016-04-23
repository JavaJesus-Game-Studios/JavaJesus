package game.entities.structures;

import game.entities.SolidEntity;
import game.entities.structures.transporters.Transporter;
import game.graphics.Sprite;
import java.awt.Point;
import level.Level;
import level.interior.ApartmentLobby;

/*
 * An apartment building 
 */
public class ApartmentHighRise extends Building {

	private static final long serialVersionUID = 3895374472641199582L;

	/**
	 * Creates an apartment
	 * 
	 * @param level
	 *            the level it is on
	 * @param x
	 *            the x coord on the level
	 * @param y
	 *            the y coord on the level
	 */
	public ApartmentHighRise(Level level, int x, int y) {
		super(level, x, y, new int[] { 0xFF111111, 0xFF673101, 0xFFABD3FF }, Sprite.apartment, SolidEntity.TWO_THIRDS);

		getLevel().addEntity(
				new Transporter(level, x + 30, y + 206, new ApartmentLobby(new Point(x + 36, y + 216), level)));
	}

}
