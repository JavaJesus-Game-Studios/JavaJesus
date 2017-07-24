package javajesus.level.interior;

import java.awt.Point;
import java.io.IOException;

import javajesus.entities.Entity;
import javajesus.entities.Spawner;
import javajesus.entities.npcs.NPC;
import javajesus.entities.solid.furniture.Chest;
import javajesus.entities.transporters.TransporterStair;
import javajesus.level.Level;
import javajesus.utility.Direction;

public class SkyscraperPent extends Interior {

	private Level level;

	public SkyscraperPent(Point point, Level level) throws IOException {
		super("/VISUAL_DATA/STATICS/ARCHITECTURE/GENERIC/INTERIORS/Skyscraper_Interiors/Skyscraper_Floor_11.png", new Point(500, 500), level);
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
