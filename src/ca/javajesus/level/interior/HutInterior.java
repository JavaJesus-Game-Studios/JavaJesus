package ca.javajesus.level.interior;

import java.awt.Point;

import ca.javajesus.game.entities.Entity;
import ca.javajesus.game.entities.Spawner;
import ca.javajesus.game.entities.structures.transporters.TransporterInterior;
import ca.javajesus.level.Level;

public class HutInterior extends Interior {

	private Point exitPoint;
	private Entity entity;
	private Spawner spawner;

	public HutInterior(Point point, Level level) {
		super("/Buildings/Generic Interiors/Hut_Interior.png", new Point(256,
				272), level);
		this.exitPoint = point;
	}

	public HutInterior(Point point, Level level, Entity entity, Spawner spawner) {
		super("/Buildings/Generic Interiors/Hut_Interior.png", new Point(256,
				272), level);
		this.exitPoint = point;
		this.entity = entity;
		entity.init(this);
		this.spawner = spawner;
		spawner.init(this);
	}

	public HutInterior(Point point, Level level, Entity entity) {
		super("/Buildings/Generic Interiors/Hut_Interior.png", new Point(256,
				272), level);
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
		this.addEntity(new TransporterInterior(this, 252, 278, nextLevel,
				exitPoint));
	}

}
