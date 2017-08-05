package javajesus.entities.solid.trees;

import javajesus.entities.Entity;
import javajesus.graphics.Sprite;
import javajesus.level.Level;

public class RedwoodLarge extends Tree
{
    /**
     * Large Redwood ctor()
     * 
     * @param level - level it is on
     * @param x - x coordinate
     * @param y - y coordinate
     */
    public RedwoodLarge(Level level, int x, int y) {
        super(level, x, y, Sprite.REDWOOD_LARGE, 15, 8, 14);
    }

    // need to add in the entity ID
    @Override
    public byte getId(){
        return Entity.REDWOOD_LARGE;
    }

}
