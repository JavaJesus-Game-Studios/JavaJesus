package javajesus.level.interior;

import java.awt.Point;

import javajesus.entities.Entity;
import javajesus.entities.Spawner;
import javajesus.entities.npcs.LordHillsborough;
import javajesus.entities.npcs.NPC;
import javajesus.entities.solid.furniture.Chest;
import javajesus.entities.solid.furniture.Throne;
import javajesus.entities.transporters.TransporterInterior;
import javajesus.entities.transporters.TransporterStair;
import javajesus.level.Level;
import javajesus.level.story.LordHillsboroughsDomain;
import javajesus.utility.Direction;

public class CastleInterior extends Interior {

	private static final long serialVersionUID = 3269998252336847965L;

	private Point exitPoint;

	public CastleInterior(Point point, Level level) {
		super("/Buildings/Generic Interiors/Castle_Interiors/Castle_1_Main_Hall.png", new Point(504, 464), level);
		this.exitPoint = point;
	}

	protected NPC[] getNPCPlacement() {
		
		return new NPC[] {new LordHillsborough(this, 504, 160)};
	}

	protected Spawner[] getSpawnerPlacement() {
		return null;
	}

	protected Chest[] getChestPlacement() {
		return null;
	}

	protected Entity[] getOtherPlacement() {

		return new Entity[] {

				new Throne(this, 504, 128), 
				
				new TransporterInterior(this, 504, 472, LordHillsboroughsDomain.level, exitPoint),
				
				new TransporterStair(this, 352, 240, new CastleInteriorFloor1(new Point(344, 288), this),
						new Point(344, 288), Direction.NORTH, TransporterStair.STONE),
				
				new TransporterStair(this, 664, 240, new CastleInteriorFloor1(new Point(680, 288), this),
						new Point(680, 288), Direction.NORTH, TransporterStair.STONE) };

	}

}
