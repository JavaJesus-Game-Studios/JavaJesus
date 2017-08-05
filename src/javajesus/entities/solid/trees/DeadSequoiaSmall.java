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
        super(level, x, y, Sprite.DEAD_SEQUOIA_SMALL, 3, 8, 3);
        // need to add the sprite
        // need to fill out width, height, xoffset of collision bounds
    }

    // need to add in the entity ID
    @Override
    public byte getId(){
        return Entity.DEAD_SEQUOIA_SMALL;
    }

}
