package engine;

/**
 * @author Derek
 * 
 * Outline for basic structure of a game class
 * 
 */
public interface IGameLogic {
	
	/**
	 * Handles initialization of GL components
	 * @throws Exception
	 */
	void init() throws Exception;
	
	/**
	 * Handles input
	 * @param window - window to check for input
	 */
	void input(Window window);
	
	/**
	 * Handles operations once per second
	 */
	void update();
	
	/**
	 * Renders images
	 * @param window - window to render on
	 */
	void render(Window window);

}
