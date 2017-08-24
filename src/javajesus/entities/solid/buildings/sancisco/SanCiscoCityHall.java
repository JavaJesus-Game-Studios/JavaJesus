package javajesus.entities.solid.buildings.sancisco;

import javajesus.entities.Entity;
import javajesus.entities.solid.buildings.Building;
import javajesus.entities.transporters.Door;
import javajesus.graphics.Sprite;
import javajesus.level.Level;

/*
 * City hall of San Cisco!
 */
public class SanCiscoCityHall extends Building {

	/**
	 * Creates san cisco city hall
	 * 
	 * @param level - the level it is on
	 * @param x - the x coord on the level
	 * @param y - the y coord on the level
	 */
	public SanCiscoCityHall(Level level, int x, int y) {
		super(level, x, y, new int[] { 0xFF111111, 0xFFFFFFFF, 0xFFFFBC02 }, Sprite.san_cisco_city_hall);

		if (level != null)
		level.add(new Door(level, x + 90, y + 104, level,0,0));
	}

	@Override
	public byte getId(){
        return Entity.SAN_CISCO_CITY_HALL;
    }
}