package ca.javajesus.level.interior;

import java.awt.Point;

import ca.javajesus.game.entities.Entity;
import ca.javajesus.game.entities.Mob.Direction;
import ca.javajesus.game.entities.Spawner;
import ca.javajesus.game.entities.structures.transporters.TransporterStairCarpet;
import ca.javajesus.level.Level;

public class ProjectsFloor extends Interior {

	private Point exitPoint;
	private Entity entity;
	private Spawner spawner;
	private int floor;

	public ProjectsFloor(Point point, Level level, int floor) {
		super("/Buildings/Generic Interiors/Projects_Floors_1_and_2.png",
				new Point(588, 688), level);
		this.exitPoint = point;
		this.floor = floor;
	}

	public ProjectsFloor(Point point, Level level, Entity entity,
			Spawner spawner, int floor) {
		super("/Buildings/Generic Interiors/Projects_Floors_1_and_2.png",
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
		this.addEntity(new TransporterStairCarpet(this, 344, 472, nextLevel,
				exitPoint, Direction.EAST));
		if (floor < 3) {
			this.addEntity(new TransporterStairCarpet(this, 344, 528,
					new ProjectsFloor(new Point(353, 536), this, floor + 1),
					new Point(353, 480), Direction.WEST));
		}
	}
}