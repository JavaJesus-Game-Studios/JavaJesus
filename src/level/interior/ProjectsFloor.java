package level.interior;

import java.awt.Point;

import game.entities.Entity;
import game.entities.Spawner;
import game.entities.structures.transporters.TransporterStairCarpet;
import level.Level;
import utility.Direction;

public class ProjectsFloor extends Interior {

	private Point exitPoint;
	private Entity entity;
	private Spawner spawner;
	private int floor;

	public ProjectsFloor(Point point, Level level, int floor) {
		super("/Buildings/Generic Interiors/Projects_Interiors/Projects_Floors_1_and_2.png",
				new Point(588, 688), level);
		this.exitPoint = point;
		this.floor = floor;
	}

	public ProjectsFloor(Point point, Level level, Entity entity,
			Spawner spawner, int floor) {
		super("/Buildings/Generic Interiors/Projects_Interiors/Projects_Floors_1_and_2.png",
				new Point(588, 688), level);
		this.exitPoint = point;
		this.entity = entity;
		entity.init(this);
		this.spawner = spawner;
		spawner.init(this);
		this.floor = floor;
	}

	public ProjectsFloor(Point point, Level level, Entity entity, int floor) {
		super("/Buildings/Generic Interiors/Projects_Floors_1_and_2.png",
				new Point(588, 688), level);
		this.exitPoint = point;
		this.entity = entity;
		entity.init(this);
		this.floor = floor;
	}

	protected void initNPCPlacement() {

	}

	protected void initSpawnerPlacement() {
		if (spawner != null) {
			this.addEntity(spawner);
		}
	}

	protected void initChestPlacement() {
		if (entity != null) {
			this.addEntity(entity);
		}
	}

	protected void otherEntityPlacement() {
		this.addEntity(new TransporterStairCarpet(this, 344, 528, nextLevel,
				new Point(353, 480), Direction.EAST));
		if (floor < 2) {
			this.addEntity(new TransporterStairCarpet(this, 344, 472,
					new ProjectsFloor(new Point(353, 536), this, floor + 1),
					exitPoint, Direction.WEST));
		} else {
			this.addEntity(new TransporterStairCarpet(this, 344, 472,
					new ProjectsTop(new Point(353, 536), this, floor + 1),
					exitPoint, Direction.WEST));
		}
	}
}