package javajesus.entities.solid.buildings;

import java.awt.Point;

import javajesus.entities.Entity;
import javajesus.entities.transporters.Transporter;
import javajesus.graphics.Sprite;
import javajesus.level.Level;
import javajesus.level.interior.SkyscraperLobby;

/*
 * A large skyscraper!
 */
public class Skyscraper extends Building {

	/**
	 * Creates a skyscraper
	 * 
	 * @param level - the level it is on
	 * @param x - the x coord on the level
	 * @param y - the y coord on the level
	 */
	public Skyscraper(Level level, int x, int y) {
		super(level, x, y, new int[] { 0xFF111111, 0xFF673101, 0xFFABD3FF }, Sprite.skyscraper);

		level.add(new Transporter(level, x + 38, y + 234, new SkyscraperLobby(new Point(x + 44, y + 243), level)));
	}

	@Override
    public byte getId(){
        return Entity.SKYSCRAPER;
    }
}
