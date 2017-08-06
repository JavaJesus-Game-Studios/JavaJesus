package javajesus.entities.solid.trees;

import javajesus.entities.Entity;
import javajesus.graphics.Sprite;
import javajesus.level.Level;

public class SmallTreeAutumn extends Tree{
    /**
     * Small Tree - Autumn Variety ctor()
     * 
     * @param level - level it is on
     * @param x - x coordinate
     * @param y - y coordinate
     */
    public SmallTreeAutumn(Level level, int x, int y) {
        super(level, x, y, Sprite.SMALL_TREE_AUTUMN, 9, 8, 5);
    }

    @Override
    public byte getId(){
        return Entity.SMALL_TREE_AUTUMN;
    }
}
