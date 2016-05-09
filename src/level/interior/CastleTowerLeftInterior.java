package level.interior;

import java.awt.Point;

import game.entities.structures.transporters.TransporterInterior;
import level.Level;

public class CastleTowerLeftInterior extends Interior {

	private static final long serialVersionUID = 660767375624744770L;

	private Point exitPoint;

	public CastleTowerLeftInterior(Point point, Level level) {
		super("/Buildings/Generic Interiors/Castle_Interiors/Castle_Tower_Interior_Left.png", new Point(1935, 2088),
				level);
		this.exitPoint = point;
	}

	protected void initNPCPlacement() {

	}

	protected void initSpawnerPlacement() {

	}

	protected void initChestPlacement() {

	}

	protected void otherEntityPlacement() {
		add(new TransporterInterior(this, 2104, 2056, new CastleBattlements(exitPoint, nextLevel), exitPoint));
	}

}
