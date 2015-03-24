package ca.javajesus.level.interior;

import java.awt.Point;
import ca.javajesus.game.entities.Entity;
import ca.javajesus.game.entities.Spawner;

public class ProjectsLobby extends Interior {

	private Point exitPoint;
	private Entity entity;
	private Spawner spawner;
	
	public ProjectsLobby(Point point, Level level) {
		super("/Buildings/Generic Interiors/Projects_Lobby.png", new Point(252, 272), level);	
		this.exitPoint = point;
	}

}
