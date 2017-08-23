package javajesus.entities.solid.trees;

import javajesus.entities.Entity;
import javajesus.graphics.Sprite;
import javajesus.level.Level;

public class DeadSequoiaSmall extends Tree
{
    
    /**
     * Dead Small Sequoia ctor()
     * 
     * @param level - level it is on
     * @param x - x coordinate
     * @param y - y coordinate
     */
    public DeadSequoiaSmall(Level level, int x, int y) {
        super(level, x, y, Sprite.DEAD_SEQUOIA_SMALL, 4, 8, 2);
    }

    @Override
    public byte getId(){
        return Entity.DEAD_SEQUOIA_SMALL;
    }

}
