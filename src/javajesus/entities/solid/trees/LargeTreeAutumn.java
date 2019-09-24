package javajesus.entities.solid.trees;

import javajesus.entities.Entity;
import javajesus.graphics.Sprite;
import javajesus.level.Level;

public class LargeTreeAutumn extends Tree {
    /**
     * Large Tree - Autumn Variety ctor()
     * 
     * @param level - level it is on
     * @param x - x coordinate
     * @param y - y coordinate
     */
    public LargeTreeAutumn(Level level, int x, int y) {
        super(level, x, y, Sprite.LARGE_TREE_AUTUMN, 14, 8, 13);
    }

    @Override
    public byte getId(){
        return Entity.LARGE_TREE_AUTUMN;
    }

}
