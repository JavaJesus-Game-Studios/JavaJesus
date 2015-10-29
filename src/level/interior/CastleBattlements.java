package level.interior;

import game.entities.Mob.Direction;
import game.entities.structures.transporters.TransporterInterior;
import game.entities.structures.transporters.TransporterStairStone;

import java.awt.Point;

import level.Level;

public class CastleBattlements extends Interior{
	private Point exitPoint;

	public CastleBattlements(Point point, Level level) {
		super("/Buildings/Generic Interiors/Castle_Interiors/Castle_1_Battlements.png", point, level);
		this.exitPoint = point;
	}

	protected void initNPCPlacement() {
					
	}

	protected void initSpawnerPlacement() {

	}

	protected void initChestPlacement() {

	}

	protected void otherEntityPlacement() {
		this.addEntity(new TransporterInterior(this, 504, 472, nextLevel,
				exitPoint));
		
		//Down
		this.addEntity(new TransporterStairStone(this, 376, 304,
				new CastleInteriorFloor1(new Point(384, 224), this), new Point(384, 224), Direction.SOUTH));
		this.addEntity(new TransporterStairStone(this, 648, 304,
				new CastleInteriorFloor1(new Point(640, 224), this), new Point(640, 224), Direction.SOUTH));

	}


}


