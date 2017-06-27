package javajesus.level.interior;

import java.awt.Point;

import javajesus.entities.Entity;
import javajesus.entities.Spawner;
import javajesus.entities.npcs.NPC;
import javajesus.entities.structures.furniture.Chest;
import javajesus.entities.structures.transporters.TransporterInterior;
import javajesus.level.Level;

public class TippeeInterior extends Interior {

	private static final long serialVersionUID = -2800032767259549825L;

	private Point exitPoint;

	public TippeeInterior(Point point, Level level) {
		super("/Buildings/Generic Interiors/Tippee_Interior.png", new Point(256, 272), level);
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
		return new Entity[] {new TransporterInterior(this, 256, 278, nextLevel, exitPoint)};
	}

}
