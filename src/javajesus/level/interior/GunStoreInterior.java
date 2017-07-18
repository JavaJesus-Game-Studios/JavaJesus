package javajesus.level.interior;

import java.awt.Point;

import javajesus.entities.Entity;
import javajesus.entities.Spawner;
import javajesus.entities.npcs.NPC;
import javajesus.entities.solid.furniture.Chest;
import javajesus.entities.transporters.TransporterInterior;
import javajesus.items.Item;
import javajesus.level.Level;

public class GunStoreInterior extends Interior {

	private static final long serialVersionUID = 5126895773468450413L;

	private Point exitPoint;

	public GunStoreInterior(Point point, Level level) {
		super("/VISUAL_DATA/STATICS/ARCHITECTURE/GENERIC/INTERIORS/Gun_Store_Interior.png", new Point(248, 264), level);
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
