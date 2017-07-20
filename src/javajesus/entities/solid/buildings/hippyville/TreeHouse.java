package javajesus.entities.solid.buildings.hippyville;

import java.awt.Point;

import javajesus.entities.Entity;
import javajesus.entities.solid.buildings.Building;
import javajesus.entities.transporters.Transporter;
import javajesus.graphics.Sprite;
import javajesus.level.Level;
import javajesus.level.interior.TreeHouseInterior;

/*
 * A Tree House
 */
public class TreeHouse extends Building {

	/**
	 * Creates a tree house
	 * 
	 * @param level - the level it is on
	 * @param x - the x coord on the level
	 * @param y - the y coord on the level
	 */
	public TreeHouse(Level level, int x, int y) {
		super(level, x, y, new int[] { 0xFF111111, 0xFF143914, 0xFF522900 }, Sprite.treehouse);

		// change bounds
		setBounds(getBounds().x + 18, getBounds().y, getBounds().width - 36, getBounds().height);

		level.add(new Transporter(level, x + 18, y + 30, new TreeHouseInterior(new Point(x + 24, y + 42), level)));
	}

	@Override
    public byte getId(){
        return Entity.TREE_HOUSE;
    }
}
