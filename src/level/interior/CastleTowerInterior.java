package level.interior;

import java.awt.Point;

import game.entities.Entity;
import game.entities.Spawner;
import game.entities.npcs.NPC;
import game.entities.structures.furniture.Chest;
import game.entities.structures.furniture.DiningTable;
import game.entities.structures.transporters.TransporterInterior;
import items.Item;
import level.Level;

public class CastleTowerInterior extends Interior {

	private static final long serialVersionUID = -4157297082918985024L;
	
	private Point exitPoint;

	public CastleTowerInterior(Point point, Level level) {
		super("/Buildings/Generic Interiors/Castle_Tower_Interiors/Castle_Tower_Interior.png",
				new Point(1935, 2088), level);
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
