package javajesus.entities.solid.buildings.techtopia;

import java.awt.Point;
import java.io.IOException;

import javajesus.entities.Entity;
import javajesus.entities.solid.buildings.Building;
import javajesus.entities.transporters.Door;
import javajesus.graphics.Sprite;
import javajesus.level.Level;
import javajesus.level.interior.CafeInterior;

/*
 * A Cafe in tech topia
 */
public class Cafe extends Building {

	/**
	 * Creates a cafe
	 * 
	 * @param level - the level it is on
	 * @param x - the x coord on the level
	 * @param y - the y coord on the level
	 * @throws IOException 
	 */
	public Cafe(Level level, int x, int y) throws IOException {
		super(level, x, y, new int[] { 0xFF111111, 0xFFFFFF99, 0xFFFFFFCC }, Sprite.cafe);

		if (level != null)
		level.add(new Door(level, x + 77, y + 43, new CafeInterior(new Point(x + 83, y + 55), getLevel()),0,0));
	}

	@Override
    public byte getId(){
        return Entity.CAFE;
    }
}
