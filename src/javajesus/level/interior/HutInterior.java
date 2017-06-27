package javajesus.level.interior;

import java.awt.Point;

import javajesus.entities.Entity;
import javajesus.entities.Spawner;
import javajesus.entities.npcs.NPC;
import javajesus.entities.structures.furniture.Chest;
import javajesus.entities.structures.transporters.TransporterInterior;
import javajesus.items.Item;
import javajesus.level.Level;

public class HutInterior extends Interior {

	private static final long serialVersionUID = -8643880355397556987L;

	private Point exitPoint;

	public HutInterior(Point point, Level level) {
		super("/Buildings/Generic Interiors/Hut_Interior.png", new Point(256, 272), level);
		this.exitPoint = point;
	}

	protected NPC[] getNPCPlacement() {
		return null;
	}

	protected Spawner[] getSpawnerPlacement() {
		return null;
	}

	protected Chest[] getChestPlacement() {
		return new Chest[] {new Chest(this, 247, 216, Item.apple, Item.apple, Item.apple, Item.crossBow)};
	}

	protected Entity[] getOtherPlacement() {
		return new Entity[] {new TransporterInterior(this, 252, 278, nextLevel, exitPoint)};
	}

}
