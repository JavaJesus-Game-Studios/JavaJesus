package ca.javajesus.level.interior;

import java.awt.Point;

import ca.javajesus.game.entities.structures.transporters.TransporterInterior;
import ca.javajesus.level.Level;

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
