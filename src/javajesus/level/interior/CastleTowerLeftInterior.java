package javajesus.level.interior;

import java.awt.Point;
import java.io.IOException;

import javajesus.entities.Spawner;
import javajesus.entities.npcs.NPC;
import javajesus.entities.solid.furniture.Chest;
import javajesus.level.Level;

public class CastleTowerLeftInterior extends Interior {

	private Point exitPoint;

	public CastleTowerLeftInterior(Point point, Level level) throws IOException {
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

	/*protected Entity[] getOtherPlacement() {
		return new Entity[] {new TransporterInterior(this, 2104, 2056, new CastleBattlements(exitPoint, nextLevel), exitPoint)};
	}*/

}
