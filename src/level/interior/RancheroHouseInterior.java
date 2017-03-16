package level.interior;

import java.awt.Point;

import game.entities.Entity;
import game.entities.Spawner;
import game.entities.npcs.NPC;
import game.entities.structures.furniture.Chest;
import game.entities.structures.transporters.TransporterInterior;
import level.Level;

public class RancheroHouseInterior extends Interior {

	private static final long serialVersionUID = 7955092802065217014L;

	private Point exitPoint;

	public RancheroHouseInterior(Point point, Level level) {
		super("/Buildings/Generic Interiors/Ranchero_House_Interior.png", new Point(256, 304), level);
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
		return new Entity[] {new TransporterInterior(this, 256, 304, nextLevel, exitPoint)};
	}

}
