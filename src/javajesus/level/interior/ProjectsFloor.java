package javajesus.level.interior;

import java.awt.Point;

import javajesus.entities.Entity;
import javajesus.entities.Spawner;
import javajesus.entities.npcs.NPC;
import javajesus.entities.solid.furniture.Chest;
import javajesus.entities.transporters.TransporterStair;
import javajesus.level.Level;
import javajesus.utility.Direction;

public class ProjectsFloor extends Interior {

	private static final long serialVersionUID = 38509975480014538L;

	private Point exitPoint;
	private int floor;

	public ProjectsFloor(Point point, Level level, int floor) {
		super("/Buildings/Generic Interiors/Projects_Interiors/Projects_Floors_1_and_2.png", new Point(580, 680),
				level);
		this.exitPoint = point;
		this.floor = floor;
	}

	protected NPC[] getNPCPlacement() {
		return null;
	}

	protected Spawner[] getSpawnerPlacement() {
		return null;
	}

	protected Chest[] getChestPlacement() {
		return null;
	}

	protected Entity[] getOtherPlacement() {
		
		Entity[] entities = new Entity[2];
		
		entities[0] = new TransporterStair(this, 344, 528, nextLevel, new Point(353, 480), Direction.EAST,
				TransporterStair.CARPET);
		
		if (floor < 2) {
			entities[1] = new TransporterStair(this, 344, 472, new ProjectsFloor(new Point(353, 536), this, floor + 1), exitPoint,
					Direction.WEST, TransporterStair.CARPET);
		} else {
			entities[1] = new TransporterStair(this, 344, 472, new ProjectsTop(new Point(353, 536), this, floor + 1), exitPoint,
					Direction.WEST, TransporterStair.CARPET);
		}
		
		return entities;
		
	}
}