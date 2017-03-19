package level.interior;

import java.awt.Point;

import game.entities.Entity;
import game.entities.Spawner;
import game.entities.npcs.NPC;
import game.entities.structures.furniture.Chest;
import game.entities.structures.transporters.TransporterInterior;
import items.Item;
import level.Level;

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
