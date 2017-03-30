package level.interior;

import java.awt.Point;

import javajesus.entities.Entity;
import javajesus.entities.Spawner;
import javajesus.entities.npcs.NPC;
import javajesus.entities.structures.furniture.Chest;
import javajesus.entities.structures.transporters.TransporterStair;
import level.Level;
import utility.Direction;

public class SkyscraperPent extends Interior {

	private static final long serialVersionUID = -5198926893074056374L;

	private Level level;

	public SkyscraperPent(Point point, Level level) {
		super("/Buildings/Generic Interiors/Skyscraper_Interiors/Skyscraper_Floor_11.png", new Point(500, 500), level);
		this.level = level;
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
		return new Entity[] {new TransporterStair(this, 2112, 2008, level, new Point(2105, 1975), Direction.SOUTH,
				TransporterStair.WOOD)};
	}
}
