package javajesus.utility.movement;

import java.awt.Point;

import javajesus.ai.AIManager;
import javajesus.entities.Mob;

public class ScriptFactory {

	public static final int MDP = 0, DIJKSTRA = 1, TILE_ALIGNMENT = 2;

	public static Script make(Mob source, Mob destination, int type) {
		switch (type) {
		case MDP:
			return new Script(source, AIManager.bestPointToMoveTo(source), 0);
		case TILE_ALIGNMENT:
			int dx = source.getX() % 8;
			int dy = source.getY() % 8;
			if (dx <= 4) {
				dx = -dx;
			} else {
				dx = 8 - dx;
			}
			if (dy <= 4) {
				dy = -dy;
			} else {
				dy = 8 - dy;
			}

			return new Script(source, new Point(source.getX() + dx, source.getY() + dy), 0);
		}
		return null;
	}

}
