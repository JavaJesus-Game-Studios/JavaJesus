package javajesus.entities.solid.trees;

import javajesus.entities.Entity;
import javajesus.graphics.Sprite;
import javajesus.level.Level;

public class DeadSequoiaMedium extends Tree
{
    /**
     * Dead Medium Sequoia ctor()
     * 
     * @param level - level it is on
     * @param x - x coordinate
     * @param y - y coordinate
     */
    public DeadSequoiaMedium(Level level, int x, int y) {
        super(level, x, y, Sprite.DEAD_SEQUOIA_MEDIUM, 6, 8, 5);
    }

    // need to add in the entity ID
    @Override
    public byte getId(){
        return Entity.DEAD_SEQUOIA_MEDIUM;
    }

}
