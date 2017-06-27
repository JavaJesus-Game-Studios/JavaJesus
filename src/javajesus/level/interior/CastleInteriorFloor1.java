package javajesus.level.interior;

import java.awt.Point;

import javajesus.entities.Entity;
import javajesus.entities.Spawner;
import javajesus.entities.npcs.NPC;
import javajesus.entities.structures.furniture.Chest;
import javajesus.entities.structures.transporters.TransporterInterior;
import javajesus.entities.structures.transporters.TransporterStair;
import javajesus.level.Level;
import javajesus.utility.Direction;

public class CastleInteriorFloor1 extends Interior {

	private static final long serialVersionUID = 3908944258697010960L;

	private Point exitPoint;

	public CastleInteriorFloor1(Point point, Level level) {
		super("/Buildings/Generic Interiors/Castle_Interiors/Castle_1_Living_Quarters.png", new Point(504, 464), level);
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
		
		return new Entity[] { new TransporterInterior(this, 504, 472, nextLevel, exitPoint),

				// Up
				new TransporterStair(this, 376, 224, new CastleBattlements(new Point(384, 320), this),
						new Point(384, 320), Direction.SOUTH, TransporterStair.STONE),
				
				new TransporterStair(this, 632, 224, new CastleBattlements(new Point(656, 320), this),
						new Point(656, 320), Direction.SOUTH, TransporterStair.STONE),
				// Down
				new TransporterStair(this, 336, 280, new CastleInterior(new Point(360, 248), this), new Point(360, 248),
						Direction.SOUTH, TransporterStair.STONE),
				
				new TransporterStair(this, 672, 280, new CastleInterior(new Point(672, 248), this), new Point(672, 248),
						Direction.SOUTH, TransporterStair.STONE) };

	}

}
