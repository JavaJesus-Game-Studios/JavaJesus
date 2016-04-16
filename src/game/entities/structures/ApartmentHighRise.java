package game.entities.structures;

import game.entities.Entity;
import game.entities.SolidEntity;
import game.entities.structures.transporters.Transporter;
import game.graphics.Screen;
import game.graphics.Sprite;

import java.awt.Point;
import java.awt.Rectangle;

import level.Level;
import level.interior.ApartmentLobby;

/*
 * An apartment building 
 */
public class ApartmentHighRise extends Entity implements SolidEntity {

	private static final long serialVersionUID = 3895374472641199582L;
	
	// The area where the player can walk behind the building
	private Rectangle shadow;
	
	// the building texture
	private static final Sprite sprite = Sprite.apartment;

	/**
	 * Creates an apartment
	 * @param level the level it is on
	 * @param x the x coord on the level
	 * @param y the y coord on the level
	 */
	public ApartmentHighRise(Level level, int x, int y) {
		super(level, x, y);
		
		shadow = new Rectangle(sprite.getWidth(), (int) (sprite.getHeight() * SolidEntity.TWO_THIRDS));
		shadow.setLocation(x, y);
		
		this.setBounds(x, y + shadow.height, sprite.getWidth(), sprite.getHeight());
		
		getLevel().addEntity(new Transporter(level, x + 30, y + 206, new ApartmentLobby(new Point(x + 36, y + 216), level)));
	}

	/**
	 * Displays the pixels on the screen
	 */
	public void render(Screen screen) {

		screen.render(getX(), getY(), new int[] { 0xFF111111, 0xFF673101, 0xFFABD3FF }, sprite);

	}

	@Override
	public Rectangle getShadow() {
		return shadow;
	}

	@Override
	public void tick() {}

}
