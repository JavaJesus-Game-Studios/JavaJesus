package javajesus.graphics;

public interface IRenderStrategy {
	
	public final int TILE_SIZE = 8;

	/**
	 * @param screen - the screen to render on
	 * @param xLocation - the entity's getX()
	 * @param yLocation - the entity's getY()
	 * @param xTile - the leftmost tile on spritesheet
	 * @param yTile - the topmost tile on spritesheet
	 * @param walkingFlip - whether or not to show the step tile
	 * @param westFlip - whether or not the mob is facing  west
	 * @param isMoving - whether or not the mob is moving
	 * @param sheet - spritesheet
	 * @param color - color set
	 */
	public void renderNormal(Screen screen, int xLocation, int yLocation, int xTile, int yTile, boolean flip, SpriteSheet sheet, int[] color);
	/**
	 * @param screen - the screen to render on
	 * @param xLocation - the entity's getX()
	 * @param yLocation - the entity's getY()
	 * @param xTile - the leftmost tile on spritesheet
	 * @param yTile - the topmost tile on spritesheet
	 * @param walkingFlip - whether or not to show the step tile
	 * @param westFlip - whether or not the mob is facing  west
	 * @param isMoving - whether or not the mob is moving
	 * @param sheet - spritesheet
	 * @param color - color set
	 */
	public void renderSwimming(Screen screen, int xLocation, int yLocation, int xTile, int yTile, boolean flip, SpriteSheet sheet, int[] color);

}
