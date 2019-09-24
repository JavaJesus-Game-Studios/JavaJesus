package javajesus.entities.solid.trees;

import javajesus.entities.Entity;
import javajesus.graphics.Sprite;
import javajesus.level.Level;

public class MediumTree extends Tree {
    /**
     * Medium Tree - Generic Variety ctor()
     * 
     * @param level - level it is on
     * @param x - x coordinate
     * @param y - y coordinate
     */
    public MediumTree(Level level, int x, int y) {
        super(level, x, y, Sprite.MEDIUM_TREE, 12, 8, 10);
    }

    @Override
    public byte getId(){
        return Entity.MEDIUM_TREE;
    }
}
