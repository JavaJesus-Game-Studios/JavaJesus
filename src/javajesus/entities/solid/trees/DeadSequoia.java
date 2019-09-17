package javajesus.entities.solid.trees;

import javajesus.entities.Entity;
import javajesus.graphics.Sprite;
import javajesus.level.Level;

/*
 * A Dead Sequoia Tree
 */
public class DeadSequoia extends Tree {

	/**
	 * DeadSequoia constructor()
	 * 
	 * @param level - level it is on
	 * @param x - x coordinate
	 * @param y - y coordinate
	 */
	public DeadSequoia(Level level, int x, int y) {
		super(level, x, y, Sprite.DEAD_SEQUOIA, 9, 9, 4);

	}

	@Override
    public byte getId(){
        return Entity.DEAD_SEQUOIA;
    }
}
