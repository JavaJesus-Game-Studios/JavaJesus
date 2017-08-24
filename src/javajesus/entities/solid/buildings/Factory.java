package javajesus.entities.solid.buildings;

import java.awt.Point;
import java.io.IOException;

import javajesus.entities.Entity;
import javajesus.entities.transporters.Door;
import javajesus.graphics.Sprite;
import javajesus.level.Level;
import javajesus.level.interior.PoorHouseInterior;

/*
 * A large factory
 */
public class Factory extends Building {

	/**
	 * Creates a factory
	 * 
	 * @param level - the level it is on
	 * @param x - the x coord on the level
	 * @param y - the y coord on the level
	 * @throws IOException 
	 */
	public Factory(Level level, int x, int y) throws IOException {
		super(level, x, y, new int[] { 0xFF111111, 0xFF3B312A, 0xFF002244 }, Sprite.factory);

		if (level != null)
		level.add(new Door(level, x + 43, y + 69, new PoorHouseInterior(new Point(x + 40, y + 67), getLevel()),0,0));
	}
	
	@Override
    public byte getId(){
        return Entity.FACTORY ;
    }

}
