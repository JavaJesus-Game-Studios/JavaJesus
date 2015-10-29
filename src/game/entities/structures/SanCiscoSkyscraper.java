package game.entities.structures;

import game.entities.SolidEntity;
import game.graphics.Screen;
import game.graphics.Sprite;

import java.awt.Rectangle;

import level.Level;

public class SanCiscoSkyscraper extends SolidEntity {

	public SanCiscoSkyscraper(Level level, int x, int y) {
		super(level, x, y, 106, 338);
		this.shadow = new Rectangle(width, (8 * height / 9));
		this.shadow.setLocation(x, y);
		this.bounds = new Rectangle(width, (height / 9) - 8);
		this.bounds.setLocation(x, y + shadow.height);
		/*
		level.addEntity(new TransporterGlass(level, x + 44, y + 660,
				new OldRandomGeneration(Game.WIDTH, Game.HEIGHT)));
		level.addEntity(new TransporterGlass(level, x + 100, y + 660,
				new OldRandomGeneration(Game.WIDTH, Game.HEIGHT)));
		level.addEntity(new TransporterGlass(level, x + 156, y + 660,
				new OldRandomGeneration(Game.WIDTH, Game.HEIGHT)));*/
	}

	public void render(Screen screen) {

		screen.render(x, y, new int[] { 0xFFEDECE0, 0xFFFFFFFF, 0xFF030074 },
				Sprite.sanCisco_skyscraper);

	}

}
