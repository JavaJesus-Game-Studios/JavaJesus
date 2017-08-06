package javajesus.entities.solid.trees;

import javajesus.entities.Entity;
import javajesus.graphics.Sprite;
import javajesus.level.Level;

public class MediumTreeAutumn extends Tree {
    /**
     * Medium Tree - Autumn Variety ctor()
     * 
     * @param level - level it is on
     * @param x - x coordinate
     * @param y - y coordinate
     */
    public MediumTreeAutumn(Level level, int x, int y) {
        super(level, x, y, Sprite.MEDIUM_TREE_AUTUMN, 13, 8, 6);
    }

    @Override
    public byte getId(){
        return Entity.MEDIUM_TREE_AUTUMN;
    }
}
