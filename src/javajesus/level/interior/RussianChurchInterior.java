package javajesus.level.interior;

import java.awt.Point;

import javajesus.entities.Entity;
import javajesus.entities.Spawner;
import javajesus.entities.npcs.NPC;
import javajesus.entities.structures.furniture.Chest;
import javajesus.entities.structures.transporters.TransporterInterior;
import javajesus.level.Level;

public class RussianChurchInterior extends Interior {

	private static final long serialVersionUID = -5856755717263616939L;

	private Point exitPoint;

	public RussianChurchInterior(Point point, Level level) {
		super("/Buildings/Generic Interiors/Russian_Church_Interior.png", new Point(256, 304), level);
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
