package level.interior;

import java.awt.Point;

import javajesus.entities.Entity;
import javajesus.entities.Spawner;
import javajesus.entities.npcs.NPC;
import javajesus.entities.structures.furniture.Chest;
import javajesus.entities.structures.transporters.TransporterStair;
import level.Level;
import utility.Direction;

public class ProjectsTop extends Interior {

	private static final long serialVersionUID = -1544110638470769135L;

	public ProjectsTop(Point point, Level level, int floor) {
		super("/Buildings/Generic Interiors/Projects_Interiors/Projects_Top_Floor.png", new Point(588, 688), level);
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
		return new Entity[] { new TransporterStair(this, 344, 528, nextLevel, new Point(353, 480), Direction.EAST,
				TransporterStair.CARPET) };
	}
}