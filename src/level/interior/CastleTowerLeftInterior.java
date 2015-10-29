package level.interior;

import game.entities.structures.transporters.TransporterInterior;

import java.awt.Point;

import level.Level;

public class CastleTowerLeftInterior extends Interior {

	private Point exitPoint;

	public CastleTowerLeftInterior(Point point, Level level) {
		super("/Buildings/Generic Interiors/Castle_Interiors/Castle_Tower_Interior_Left.png",
				new Point(1935, 2088), level);
		this.exitPoint = point;
	}

	protected void initNPCPlacement() {


	}

	protected void initSpawnerPlacement() {

	}

	protected void initChestPlacement() {

	}

	protected void otherEntityPlacement() {
		this.addEntity(new TransporterInterior(this, 2104, 2056, new CastleBattlements(exitPoint, nextLevel),
				exitPoint));
	}

}
