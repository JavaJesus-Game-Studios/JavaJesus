package javajesus.entities.solid.trees;

import javajesus.entities.Entity;
import javajesus.graphics.Sprite;
import javajesus.level.Level;

public class RedwoodSmall extends Tree
{
    /**
     * Small Redwood ctor()
     * 
     * @param level - level it is on
     * @param x - x coordinate
     * @param y - y coordinate
     */
    public RedwoodSmall(Level level, int x, int y) {
        super(level, x, y, Sprite.REDWOOD_SMALL, 13, 9, 10);
    }

    // need to add in the entity ID
    @Override
    public byte getId(){
        return Entity.REDWOOD_SMALL;
    }
}
