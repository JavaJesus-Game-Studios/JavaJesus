package level.interior;

import java.awt.Point;

import game.entities.structures.transporters.TransporterStair;
import level.Level;
import utility.Direction;

public class ProjectsFloor extends Interior {

	private static final long serialVersionUID = 38509975480014538L;

	private Point exitPoint;
	private int floor;

	public ProjectsFloor(Point point, Level level, int floor) {
		super("/Buildings/Generic Interiors/Projects_Interiors/Projects_Floors_1_and_2.png", new Point(588, 688),
				level);
		this.exitPoint = point;
		this.floor = floor;
	}

	protected void initNPCPlacement() {

	}

	protected void initSpawnerPlacement() {
	}

	protected void initChestPlacement() {
	}

	protected void otherEntityPlacement() {
		add(new TransporterStair(this, 344, 528, nextLevel, new Point(353, 480), Direction.EAST,
				TransporterStair.CARPET));
		if (floor < 2) {
			add(new TransporterStair(this, 344, 472, new ProjectsFloor(new Point(353, 536), this, floor + 1), exitPoint,
					Direction.WEST, TransporterStair.CARPET));
		} else {
			add(new TransporterStair(this, 344, 472, new ProjectsTop(new Point(353, 536), this, floor + 1), exitPoint,
					Direction.WEST, TransporterStair.CARPET));
		}
	}
}