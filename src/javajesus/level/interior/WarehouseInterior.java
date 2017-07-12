package javajesus.level.interior;

import java.awt.Point;

import javajesus.entities.Entity;
import javajesus.entities.Spawner;
import javajesus.entities.npcs.NPC;
import javajesus.entities.solid.furniture.Chest;
import javajesus.entities.transporters.TransporterInterior;
import javajesus.level.Level;

public class WarehouseInterior extends Interior {

	private static final long serialVersionUID = 179209523701508203L;

	private Point exitPoint;

	public WarehouseInterior(Point point, Level level) {
		super("/Buildings/Generic Interiors/Warehouse_Interior.png", new Point(264, 272), level);
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
