package game.entities.structures;

import game.entities.SolidEntity;
import game.entities.structures.transporters.Transporter;
import game.graphics.Sprite;
import level.Level;

/*
 * City hall of San Cisco!
 */
public class SanCiscoCityHall extends Building {

	private static final long serialVersionUID = -3512083834295154394L;

	public SanCiscoCityHall(Level level, int x, int y) {
		super(level, x, y, new int[] { 0xFF111111, 0xFFFFFFFF, 0xFFFFBC02 }, Sprite.san_cisco_city_hall,
				SolidEntity.FIVE_SIXTHS);
		level.addEntity(new Transporter(level, x + 90, y + 104, level));
	}

}