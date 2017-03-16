package game.entities.structures;

import java.awt.Point;

import game.entities.SolidEntity;
import game.entities.structures.transporters.Transporter;
import game.graphics.Sprite;
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

		this.setBounds(getBounds().x + 4, getBounds().y, getBounds().width - 8, getBounds().height);
		
		getLevel().add(new Transporter(level, x + 30, y + 206, new ApartmentLobby(new Point(x + 36, y + 216), level)));
	}

}
