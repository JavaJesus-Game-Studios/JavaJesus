package level.interior;

import java.awt.Point;

import game.entities.Entity;
import game.entities.Spawner;
import game.entities.npcs.NPC;
import game.entities.structures.furniture.Chest;
import game.entities.structures.transporters.TransporterInterior;
import game.entities.structures.transporters.TransporterStair;
import level.Level;
import utility.Direction;

public class ProjectsLobby extends Interior {

	private static final long serialVersionUID = 8102425096759263851L;

	private Point exitPoint;

	public ProjectsLobby(Point point, Level level) {
		super("/Buildings/Generic Interiors/Projects_Interiors/Projects_Lobby.png", new Point(588, 688), level);
		this.exitPoint = point;
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
		return new Entity[] { new TransporterInterior(this, 585, 700, nextLevel, exitPoint),
				new TransporterStair(this, 344, 472, new ProjectsFloor(new Point(353, 536), this, 1),
						new Point(353, 536), Direction.WEST, TransporterStair.CARPET) };
	}
}
