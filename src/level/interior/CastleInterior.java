package level.interior;

import java.awt.Point;

import game.entities.npcs.LordHillsborough;
import game.entities.structures.furniture.Throne;
import game.entities.structures.transporters.TransporterInterior;
import game.entities.structures.transporters.TransporterStair;
import level.Level;
import level.story.LordHillsboroughsDomain;
import utility.Direction;

public class CastleInterior extends Interior {

	private static final long serialVersionUID = 3269998252336847965L;

	private Point exitPoint;

	public CastleInterior(Point point, Level level) {
		super("/Buildings/Generic Interiors/Castle_Interiors/Castle_1_Main_Hall.png", new Point(504, 464), level);
		this.exitPoint = point;
	}

	protected void initNPCPlacement() {
		add(new LordHillsborough(this, 504, 160));
		add(new Throne(this, 504, 128));
	}

	protected void initSpawnerPlacement() {

	}

	protected void initChestPlacement() {

	}

	protected void otherEntityPlacement() {
		add(new TransporterInterior(this, 504, 472, LordHillsboroughsDomain.level, exitPoint));
		add(new TransporterStair(this, 352, 240, new CastleInteriorFloor1(new Point(344, 288), this),
				new Point(344, 288), Direction.NORTH, TransporterStair.STONE));
		add(new TransporterStair(this, 664, 240, new CastleInteriorFloor1(new Point(680, 288), this),
				new Point(680, 288), Direction.NORTH, TransporterStair.STONE));

	}

}
