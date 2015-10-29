package level.interior;

import game.entities.Entity;
import game.entities.Spawner;
import game.entities.Mob.Direction;
import game.entities.structures.transporters.TransporterInterior;
import game.entities.structures.transporters.TransporterStairCarpet;

import java.awt.Point;

import level.Level;

public class ProjectsLobby extends Interior {

	private Point exitPoint;
	private Entity entity;
	private Spawner spawner;

	public ProjectsLobby(Point point, Level level) {
		super("/Buildings/Generic Interiors/Projects_Interiors/Projects_Lobby.png", new Point(588,
				688), level);
		this.exitPoint = point;
	}

	public ProjectsLobby(Point point, Level level, Entity entity,
			Spawner spawner) {
		super("/Buildings/Generic Interiors/Projects_Interiors/Projects_Lobby.png",
				new Point(588, 688), level);
		this.exitPoint = point;
		this.entity = entity;
		entity.init(this);
		this.spawner = spawner;
		spawner.init(this);
	}

	public ProjectsLobby(Point point, Level level, Entity entity) {
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
		this.addEntity(new TransporterStairCarpet(this, 344, 472, new ProjectsFloor(new Point(353, 536), this, 1), new Point(353, 536), Direction.WEST));
	}
}
