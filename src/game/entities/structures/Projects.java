package game.entities.structures;

import java.awt.Point;
import game.entities.SolidEntity;
import game.entities.structures.transporters.Transporter;
import game.graphics.Sprite;
import level.Level;
import level.interior.ProjectsLobby;

/*
 * Some building that northway made
 */
public class Projects  extends Building {

	private static final long serialVersionUID = -7645682813842777028L;

	public Projects(Level level, int x, int y) {
		super(level, x, y, new int[] {0xFF111111, 0xFF93DA85, 0xFF6CB1FE }, Sprite.projects, SolidEntity.HALF);
		
		this.setBounds(getBounds().x + 4, getBounds().y, getBounds().width - 8, getBounds().height);

		level.add(new Transporter(level, x + 44, y + 64,
				new ProjectsLobby(new Point(x + 45, y + 73), level)));
	}

}