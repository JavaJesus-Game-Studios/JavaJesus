package javajesus.level.interior;

import java.awt.Point;

import javajesus.entities.Entity;
import javajesus.entities.Spawner;
import javajesus.entities.npcs.NPC;
import javajesus.entities.structures.furniture.Chest;
import javajesus.entities.structures.transporters.TransporterInterior;
import javajesus.items.Item;
import javajesus.level.Level;

public class GunStoreInterior extends Interior {

	private static final long serialVersionUID = 5126895773468450413L;

	private Point exitPoint;

	public GunStoreInterior(Point point, Level level) {
		super("/Buildings/Generic Interiors/Gun_Store_Interior.png", new Point(256, 272), level);
		this.exitPoint = point;
	}

	protected NPC[] getNPCPlacement() {
		return null;
	}

	protected Spawner[] getSpawnerPlacement() {
		return null;
	}

	protected Chest[] getChestPlacement() {
		return new Chest[] {new Chest(this, 343, 96, Item.assaultRifle, Item.shotgun, Item.laserRevolver)};
	}

	protected Entity[] getOtherPlacement() {
		return new Entity[] {new TransporterInterior(this, 256, 272, nextLevel, exitPoint)};
	}

}