package javajesus.entities.solid.buildings.sancisco;

import java.awt.Point;
import java.io.IOException;

import javajesus.entities.Entity;
import javajesus.entities.solid.buildings.Building;
import javajesus.entities.transporters.Door;
import javajesus.graphics.Sprite;
import javajesus.level.Level;
import javajesus.level.interior.ChinatownHouseInterior;

/*
 * A typical house in Chinatown
 */
public class ChinatownHouse extends Building {

	/**
	 * Creates a chinatown house
	 * 
	 * @param level - the level it is on
	 * @param x - the x coord on the level
	 * @param y - the y coord on the level
	 * @throws IOException 
	 */
	public ChinatownHouse(Level level, int x, int y) throws IOException {
		super(level, x, y, new int[] { 0xFF618249, 0xFF992B2B, 0xFFFFFFFF }, Sprite.chinatown_house);

		// change the bounds
		setBounds(getBounds().x + 5, getBounds().y, getBounds().width - 10, getBounds().height);

		if (level != null)
		level.add(new Door(level, x + 26, y + 41,
		        new ChinatownHouseInterior(new Point(x + 32, y + 53), getLevel()),0,0));
	}

	@Override
    public byte getId(){
        return Entity.CHINATOWN_HOUSE;
    }
}
