package javajesus.level;

import java.awt.Point;

import javajesus.entities.Entity;
import javajesus.entities.Spawner;
import javajesus.entities.npcs.NPC;
import javajesus.entities.solid.furniture.Chest;
import javajesus.entities.transporters.MapTransporter;

public class RoadLevel extends Level {

	private static final long serialVersionUID = 4367488134635605719L;

	public RoadLevel() {
		super("/Levels/Testers/road_tester.png", false, "Road Level", new Point(50, 50));
	}

	@Override
	protected NPC[] getNPCPlacement() {
		return null;
	}

	@Override
	protected Spawner[] getSpawnerPlacement() {
		return null;
	}

	@Override
	protected Chest[] getChestPlacement() {
		return null;
	}

	@Override
	protected MapTransporter[] getMapTransporterPlacement() {
		return null;
	}

	@Override
	protected Entity[] getOtherPlacement() {
		return null;
	}

}
