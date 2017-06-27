package javajesus.entities.structures.sancisco;

import javajesus.entities.SolidEntity;
import javajesus.entities.structures.Building;
import javajesus.entities.structures.transporters.Transporter;
import javajesus.graphics.Sprite;
import javajesus.level.Level;

/*
 * City hall of San Cisco!
 */
public class SanCiscoCityHall extends Building {

	private static final long serialVersionUID = -3512083834295154394L;

	public SanCiscoCityHall(Level level, int x, int y) {
		super(level, x, y, new int[] { 0xFF111111, 0xFFFFFFFF, 0xFFFFBC02 }, Sprite.san_cisco_city_hall,
				SolidEntity.HALF);
		
		this.setBounds(getBounds().x + 4, getBounds().y, getBounds().width - 8, getBounds().height);

		level.add(new Transporter(level, x + 90, y + 104, level));
	}

}