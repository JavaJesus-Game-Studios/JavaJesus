package javajesus.entities.solid.trees;

import javajesus.entities.Entity;
import javajesus.graphics.Sprite;
import javajesus.level.Level;

public class RedwoodMedium extends Tree
{
    /**
     * Medium Redwood ctor()
     * 
     * @param level - level it is on
     * @param x - x coordinate
     * @param y - y coordinate
     */
    public RedwoodMedium(Level level, int x, int y) {
        super(level, x, y, Sprite.REDWOOD_MEDIUM, 15, 8, 16);
    }

    // need to add in the entity ID
    @Override
    public byte getId(){
        return Entity.REDWOOD_MEDIUM;
    }
}
