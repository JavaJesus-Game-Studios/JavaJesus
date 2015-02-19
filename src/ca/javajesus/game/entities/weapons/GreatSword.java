package ca.javajesus.game.entities.weapons;

import ca.javajesus.game.entities.Player;
import ca.javajesus.game.gfx.Colors;
import ca.javajesus.game.gfx.Sprite;
import ca.javajesus.level.Level;

public class GreatSword extends Sword {

	private final static Sprite[] sprites = {
			new Sprite("/Swords/GreatSword_Sheet 0.png"),
			new Sprite("/Swords/GreatSword_Sheet 0.png"),
			new Sprite("/Swords/GreatSword_Sheet 15.png"),
			new Sprite("/Swords/GreatSword_Sheet 30.png"),
			new Sprite("/Swords/GreatSword_Sheet 45.png"),
			new Sprite("/Swords/GreatSword_Sheet 60.png"),
			new Sprite("/Swords/GreatSword_Sheet 75.png"),
			new Sprite("/Swords/GreatSword_Sheet 90.png"), };

	public GreatSword(Level level, Player player) {
		super(level, sprites, player, Colors.get(-1, 000, 0xFFDEDEDE, -1));
	}

}
