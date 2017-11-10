package javajesus.items;

import javajesus.entities.Player;
import javajesus.graphics.Screen;
import javajesus.graphics.SpriteSheet;
import javajesus.utility.Direction;

/**
 * Function to delegate player rendering to the item class for
 * weapons that require different animation offsets
 */
public interface DifferentOffsetItem {
	public void renderPlayer(Screen screen, Player player, SpriteSheet sheet, Direction shootingDir);
}
