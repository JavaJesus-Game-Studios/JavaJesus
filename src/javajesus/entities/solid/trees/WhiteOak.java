package javajesus.entities.solid.trees;

import javajesus.entities.Entity;
import javajesus.graphics.Sprite;
import javajesus.level.Level;

public class WhiteOak extends Tree {
    /**
     * White Oak Tree ctor()
     * 
     * @param level - level it is on
     * @param x - x coordinate
     * @param y - y coordinate
     */
    public WhiteOak(Level level, int x, int y) {
        super(level, x, y, Sprite.WHITE_OAK, 15, 8, 13);
    }

    @Override
    public byte getId(){
        return Entity.WHITE_OAK;
    }

}
