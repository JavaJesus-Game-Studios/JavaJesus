package javajesus.graphics;

import javajesus.graphics.render_strategies.Render1x1;
import javajesus.graphics.render_strategies.Render2x2;
import javajesus.graphics.render_strategies.Render2x3;
import javajesus.graphics.render_strategies.Render2x4;
import javajesus.graphics.render_strategies.Render3x2;
import javajesus.graphics.render_strategies.Render3x3;
import javajesus.graphics.render_strategies.Render3x4;
import javajesus.graphics.render_strategies.Render4x2;
import javajesus.graphics.render_strategies.Render4x4;

public class Animation {
		
	// Keeps track of how many ticks since animation began
	private int tickCount = 0;
	// Keeps track of what frame we're at
	private int frameCount = 0;
	// Keeps track of the xTile
	private int xTile;
	
	private Render1x1 render1x1 = new Render1x1();
	private Render2x2 render2x2 = new Render2x2();
	private Render2x3 render2x3 = new Render2x3();
	private Render2x4 render2x4 = new Render2x4();
	private Render3x2 render3x2 = new Render3x2();
	private Render3x3 render3x3 = new Render3x3();
	private Render3x4 render3x4 = new Render3x4();
	private Render4x2 render4x2 = new Render4x2();
	private Render4x4 render4x4 = new Render4x4();

	
	/**
	 * Animation Rendering with color and rate determined
	 * @param screen the screen to render on
	 * @param xLocation the x location to render the animation
	 * @param yLocation the y location to render the animation
	 * @param frames the list of xOffsets corresponding to the frames on the sprite sheet
	 * @param yTile the yTile of the animations on the sprite sheet
	 * @param xSize the horizontal size of the sprite in tiles
	 * @param ySize the vertical size of the sprite in tiles
	 * @param rate the rate to display the animation at ( 1: 1 frame per tick, 2: 1 frame per 2 ticks, etc.)
	 * @param sheet the sheet that the frames are on
	 * @param color the colors to use in rendering the animation
	 */
	public void renderAnimation(Screen screen, int xLocation, int yLocation, int[] frames, int yTile,
			int xSize, int ySize, int rate, SpriteSheet sheet, int[] color) {
		// Check to see if we render a new frame
		if( tickCount % rate == 0 ) {
			// Get the xTile of the animation
			xTile = frames[frameCount];
			
			// Increment Frame Count
			frameCount++;

			// Render the frame
			renderFrame(screen, xSize, ySize, xLocation, yLocation, xTile, yTile, sheet, color, false);

			// Check to make sure frameCount is in bounds
			if( frameCount >= frames.length )
				frameCount = 0;			
		} 
		// Render the old frame again
		else {
			// Render whatever frame we're currently on
			renderFrame(screen, xSize, ySize, xLocation, yLocation, xTile, yTile, sheet, color, false);
		}
		// Increment tickCount every time this method is called
		tickCount++;
		// Keep tickCount in range
		if(tickCount >= 60)
			tickCount = 0;
	}
	
	/**
	 * Animation Rendering with color and rate determined, renders cycle a specified number of loops
	 * @param screen the screen to render on
	 * @param xLocation the x location to render the animation
	 * @param yLocation the y location to render the animation
	 * @param frames the list of xOffsets corresponding to the frames on the sprite sheet
	 * @param yTile the yTile of the animations on the sprite sheet
	 * @param xSize the horizontal size of the sprite in tiles
	 * @param ySize the vertical size of the sprite in tiles
	 * @param rate the rate to display the animation at ( 1: 1 frame per tick, 2: 1 frame per 2 ticks, etc.)
	 * @param sheet the sheet that the frames are on
	 * @param color the colors to use in rendering the animation
	 * @return returns true once the animation has finished to indicate the cycle has been played
	 */
	public boolean renderAnimationLoops(Screen screen, int xLocation, int yLocation, int[] frames, int yTile,
			int xSize, int ySize, int rate, SpriteSheet sheet, int[] color, boolean flip, int loopNumber) {
		// Check to see if we render a new frame
		if( tickCount % rate == 0 ) {
			// Get the xTile of the animation
			xTile = frames[frameCount];
			// Render the frame
			renderFrame(screen, xSize, ySize, xLocation, yLocation, xTile, yTile, sheet, color, flip);
			
			// Increment Frame Count
			frameCount++;
			// Check to make sure frameCount is in bounds
			if( frameCount >= frames.length ) {
				frameCount = 0;
			}
		} 
		// Render the old frame again
		else {
			// Render whatever frame we're currently on
			renderFrame(screen, xSize, ySize, xLocation, yLocation, xTile, yTile, sheet, color, flip);
		}
		// Increment tickCount every time this method is called
		tickCount++;
		// Return true once the animation has finished playing
		if(tickCount == (frames.length * rate * loopNumber)) {
			tickCount = 0;
			return true;
		}
		return false;
	}
	private void renderFrame(Screen screen, int xSize, int ySize, int xLocation, int yLocation, int xTile, int yTile, 
			SpriteSheet sheet, int[] color, boolean flip) {
		// Use correct render strategy to render the frame
		if( xSize == 1 && ySize == 1 )
			render1x1.renderNormal(screen, xLocation, yLocation, xTile, yTile, flip, sheet, color);
		if( xSize == 2 && ySize == 2 )
			render2x2.renderNormal(screen, xLocation, yLocation, xTile, yTile, flip, sheet, color);
		if( xSize == 2 && ySize == 3 )
			render2x3.renderNormal(screen, xLocation, yLocation, xTile, yTile, flip, sheet, color);
		if( xSize == 3 && ySize == 2 )
			render3x2.renderNormal(screen, xLocation, yLocation, xTile, yTile, flip, sheet, color);
		if( xSize == 2 && ySize == 4 )
			render2x4.renderNormal(screen, xLocation, yLocation, xTile, yTile, flip, sheet, color);
		if( xSize == 3 && ySize == 3 )
			render3x3.renderNormal(screen, xLocation, yLocation, xTile, yTile, flip, sheet, color);
		if( xSize == 3 && ySize == 4 )
			render3x4.renderNormal(screen, xLocation, yLocation, xTile, yTile, flip, sheet, color);
		if( xSize == 4 && ySize == 2 )
			render4x2.renderNormal(screen, xLocation, yLocation, xTile, yTile, flip, sheet, color);
		if( xSize == 4 && ySize == 4 )
			render4x4.renderNormal(screen, xLocation, yLocation, xTile, yTile, flip, sheet, color);

	}
	
	

}
