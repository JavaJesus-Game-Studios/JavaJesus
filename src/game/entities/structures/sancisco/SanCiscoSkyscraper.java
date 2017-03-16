package game.entities.structures.sancisco;

import game.entities.SolidEntity;
import game.entities.structures.Building;
import game.graphics.Sprite;
import level.Level;

/*
 * A large skyscraper!
 * TODO transporters
 */
public class SanCiscoSkyscraper extends Building {

	private static final long serialVersionUID = -7590762071562833524L;

	public SanCiscoSkyscraper(Level level, int x, int y) {
		super(level, x, y, new int[] { 0xFFEDECE0, 0xFFFFFFFF, 0xFF030074 },
				Sprite.sanCisco_skyscraper, SolidEntity.SEVEN_EIGTHS);
		
		this.setBounds(getBounds().x + 4, getBounds().y, getBounds().width - 8, getBounds().height);

		/*
		level.addEntity(new TransporterGlass(level, x + 44, y + 660,
				new OldRandomGeneration(Game.WIDTH, Game.HEIGHT)));
		level.addEntity(new TransporterGlass(level, x + 100, y + 660,
				new OldRandomGeneration(Game.WIDTH, Game.HEIGHT)));
		level.addEntity(new TransporterGlass(level, x + 156, y + 660,
				new OldRandomGeneration(Game.WIDTH, Game.HEIGHT)));*/
	}


}
