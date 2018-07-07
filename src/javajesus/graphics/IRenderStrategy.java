package javajesus.graphics;

public interface IRenderStrategy {

	/**
	 * @param screen - the screen to render on
	 * @param xOffset - the entity's getX()
	 * @param yOffset - the entity's getY()
	 * @param xTile - the leftmost tile on spritesheet
	 * @param yTile - the topmost tile on spritesheet
	 * @param walkingFlip - whether or not to show the step tile
	 * @param westFlip - whether or not the mob is facing  west
	 * @param isMoving - whether or not the mob is moving
	 * @param sheet - spritesheet
	 * @param color - color set
	 */
	public void render(Screen screen, int xOffset, int yOffset, int xTile, int yTile, boolean walkingFlip, boolean westFlip, boolean isMoving,
	        SpriteSheet sheet, int[] color);

}
