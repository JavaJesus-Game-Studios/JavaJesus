package javajesus.level.interior;

import java.awt.Point;
import java.io.IOException;

import javajesus.entities.Entity;
import javajesus.entities.Spawner;
import javajesus.entities.npcs.NPC;
import javajesus.entities.solid.furniture.Chest;
import javajesus.entities.solid.furniture.DiningTable;
import javajesus.entities.transporters.TransporterInterior;
import javajesus.items.Item;
import javajesus.level.Level;

public class CastleTowerInterior extends Interior {

	private Point exitPoint;

	public CastleTowerInterior(Point point, Level level) throws IOException {
		super("/VISUAL_DATA/STATICS/ARCHITECTURE/GENERIC/INTERIORS/Castle_Tower_Interiors/Castle_Tower_Interior.png",
				new Point(1927, 2080), level);
		this.exitPoint = point;
	}

	protected NPC[] getNPCPlacement() {
		return null;
	}

	protected Spawner[] getSpawnerPlacement() {
		return null;
	}

	protected Chest[] getChestPlacement() {
		return new Chest[] {new Chest(this, 1924, 2011, Item.longSword, Item.knight)};
	}

	protected Entity[] getOtherPlacement() {
		
		return new Entity[] { new TransporterInterior(this, 1935, 2088, nextLevel, exitPoint),
				new DiningTable(this, 1900, 1900)};
	}

}
