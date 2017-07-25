package javajesus.entities.solid.buildings;

import java.awt.Point;
import java.io.IOException;

import javajesus.entities.Entity;
import javajesus.entities.transporters.Door;
import javajesus.graphics.Sprite;
import javajesus.level.Level;
import javajesus.level.interior.ProjectsLobby;

/*
 * Some building that northway made
 */
public class Projects extends Building {

	/**
	 * Creates a projects building??
	 * 
	 * @param level - the level it is on
	 * @param x - the x coord on the level
	 * @param y - the y coord on the level
	 * @throws IOException 
	 */
	public Projects(Level level, int x, int y) throws IOException {
		super(level, x, y, new int[] { 0xFF111111, 0xFF93DA85, 0xFF6CB1FE }, Sprite.projects);

		if (level != null)
		level.add(new Door(level, x + 44, y + 64, new ProjectsLobby(new Point(x + 45, y + 73), level)));
	}

	@Override
    public byte getId(){
        return Entity.PROJECTS;
    }
}