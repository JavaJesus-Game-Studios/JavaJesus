package ca.javajesus.level.interior;

import java.awt.Point;

import ca.javajesus.game.entities.Entity;
import ca.javajesus.game.entities.Spawner;
import ca.javajesus.game.entities.structures.transporters.TransporterInterior;
import ca.javajesus.level.Level;

public class ProjectsFloor extends Interior {

	private Point exitPoint;
	private Entity entity;
	private Spawner spawner;

	public ProjectsFloor(Point point, Level level) {
		super("/Buildings/Generic Interiors/Projects_Lobby.png", new Point(588,
				688), level);
		this.exitPoint = point;
	}

	public ProjectsFloor(Point point, Level level, Entity entity,
			Spawner spawner) {
		super("/Buildings/Generic Interiors/Projects_Lobby.png",
				new Point(588, 688), level);
		this.exitPoint = point;
		this.entity = entity;
		entity.init(this);
		this.spawner = spawner;
		spawner.init(this);
	}

	public ProjectsFloor(Point point, Level level, Entity entity) {
		super("/Buildings/Generic Interiors/Projects_Lobby.png",
				new Point(588, 688), level);
		this.exitPoint = point;
		this.entity = entity;
		entity.init(this);
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
		this.addEntity(new TransporterInterior(this, 585, 700, nextLevel,
				exitPoint));
		//this.addEntity(new TransporterInterior(this, 400, 400, new ProjectsFloor(), new Point(585, 700)));
	}
}