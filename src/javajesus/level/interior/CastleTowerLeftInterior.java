package javajesus.level.interior;

import java.awt.Point;

import javajesus.entities.Entity;
import javajesus.entities.Spawner;
import javajesus.entities.npcs.NPC;
import javajesus.entities.solid.furniture.Chest;
import javajesus.entities.transporters.TransporterInterior;
import javajesus.level.Level;

public class CastleTowerLeftInterior extends Interior {

	private static final long serialVersionUID = 660767375624744770L;

	private Point exitPoint;

	public CastleTowerLeftInterior(Point point, Level level) {
		super("/VISUAL_DATA/STATICS/ARCHITECTURE/GENERIC/INTERIORS/Castle_Interiors/Castle_Tower_Interior_Left.png", new Point(1927, 2080),
				level);
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
		return new Entity[] {new TransporterInterior(this, 2104, 2056, new CastleBattlements(exitPoint, nextLevel), exitPoint)};
	}

}
