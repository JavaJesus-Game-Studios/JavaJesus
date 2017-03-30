package level.interior;

import java.awt.Point;

import javajesus.entities.Entity;
import javajesus.entities.Spawner;
import javajesus.entities.npcs.NPC;
import javajesus.entities.structures.furniture.Chest;
import javajesus.entities.structures.transporters.TransporterInterior;
import javajesus.entities.structures.transporters.TransporterStair;
import level.Level;
import utility.Direction;

public class CastleBattlements extends Interior {

	private static final long serialVersionUID = 3729379964203665940L;

	private Point exitPoint;

	public CastleBattlements(Point point, Level level) {
		super("/Buildings/Generic Interiors/Castle_Interiors/Castle_1_Battlements.png", point, level);
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
		
		return new Entity[] {
				
		new TransporterInterior(this, 504, 472, nextLevel, exitPoint),

		// Down
		new TransporterStair(this, 376, 304, new CastleInteriorFloor1(new Point(384, 224), this),
				new Point(384, 224), Direction.SOUTH, TransporterStair.STONE),
				
		new TransporterStair(this, 648, 304, new CastleInteriorFloor1(new Point(640, 224), this),
				new Point(640, 224), Direction.SOUTH, TransporterStair.STONE)
				
		};

	}

}
