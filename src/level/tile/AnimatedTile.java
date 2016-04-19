package level.tile;

/*
 * A Tile that changes its appearance every tick
 */
public class AnimatedTile extends BaseTile{
	
	// the range of animated sprites
	private int[][] animationTileCoords;
	
	// index in the animated tile array
	private int currentAnimationIndex;
	
	// last time changed
	private long lastIterationTime;
	
	// delay between switching animations
	private int animationSwitchDelay;

	/**
	 * Creates a Tile that changes its appearance every tick
	 * @param id UNIQUE identifier
	 * @param animationCoords set of the x and y position of each tile
	 * @param tileColor the color set
	 * @param levelColor the pixel color on the level file
	 * @param animationSwitchDelay the delay between animations
	 */
	public AnimatedTile(int id, int[][] animationCoords, int[] tileColor, int levelColor, int animationSwitchDelay) {
		super(id, animationCoords[0][0], animationCoords[0][1], tileColor, levelColor);
			this.animationTileCoords = animationCoords;
			this.currentAnimationIndex = 0;
			this.lastIterationTime = System.currentTimeMillis();
			this.animationSwitchDelay = animationSwitchDelay;
	}
	
	/**
	 * Handles the animation
	 */
	public void tick() {
			if ((System.currentTimeMillis() - lastIterationTime) >= (animationSwitchDelay)) {
				lastIterationTime = System.currentTimeMillis();
				currentAnimationIndex = (currentAnimationIndex + 1) % animationTileCoords.length;
				setTileID(animationTileCoords[currentAnimationIndex][0], animationTileCoords[currentAnimationIndex][1]);
			}
	}
}