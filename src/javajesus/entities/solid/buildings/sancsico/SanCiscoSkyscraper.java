package javajesus.entities.solid.buildings.sancsico;

import javajesus.entities.Entity;
import javajesus.entities.solid.buildings.Building;
import javajesus.graphics.Sprite;
import javajesus.level.Level;

/*
 * A large skyscraper!
 * TODO transporters
 */
public class SanCiscoSkyscraper extends Building {

	public SanCiscoSkyscraper(Level level, int x, int y) {
		super(level, x, y, new int[] { 0xFFEDECE0, 0xFFFFFFFF, 0xFF030074 }, Sprite.sanCisco_skyscraper);

		/*
		 * level.addEntity(new TransporterGlass(level, x + 44, y + 660, new
		 * OldRandomGeneration(Game.WIDTH, Game.HEIGHT))); level.addEntity(new
		 * TransporterGlass(level, x + 100, y + 660, new
		 * OldRandomGeneration(Game.WIDTH, Game.HEIGHT))); level.addEntity(new
		 * TransporterGlass(level, x + 156, y + 660, new
		 * OldRandomGeneration(Game.WIDTH, Game.HEIGHT)));
		 */
	}
	
	@Override
    public byte getId(){
        return Entity.SAN_CISCO_SKYSCRAPER;
    }
}
