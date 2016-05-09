package level.interior;

import java.awt.Point;

import game.entities.structures.transporters.TransporterInterior;
import game.entities.structures.transporters.TransporterStair;
import level.Level;
import utility.Direction;

public class CastleInteriorFloor1 extends Interior {

	private static final long serialVersionUID = 3908944258697010960L;

	private Point exitPoint;

	public CastleInteriorFloor1(Point point, Level level) {
		super("/Buildings/Generic Interiors/Castle_Interiors/Castle_1_Living_Quarters.png", new Point(504, 464), level);
		this.exitPoint = point;
	}

	protected void initNPCPlacement() {

	}

	protected void initSpawnerPlacement() {

	}

	protected void initChestPlacement() {

	}

	protected void otherEntityPlacement() {
		add(new TransporterInterior(this, 504, 472, nextLevel, exitPoint));

		// Up
		add(new TransporterStair(this, 376, 224, new CastleBattlements(new Point(384, 320), this), new Point(384, 320),
				Direction.SOUTH, TransporterStair.STONE));
		add(new TransporterStair(this, 632, 224, new CastleBattlements(new Point(656, 320), this), new Point(656, 320),
				Direction.SOUTH, TransporterStair.STONE));
		// Down
		add(new TransporterStair(this, 336, 280, new CastleInterior(new Point(360, 248), this), new Point(360, 248),
				Direction.SOUTH, TransporterStair.STONE));
		add(new TransporterStair(this, 672, 280, new CastleInterior(new Point(672, 248), this), new Point(672, 248),
				Direction.SOUTH, TransporterStair.STONE));

	}

}
