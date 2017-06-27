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
	
	/**
	 * Calls this method when the game loop is stopped
	 */
	void onClose();
	
	/**
	 * Called immediately after window initialization
	 */
	void modifyWindow(Window window);
	
	/**
	 * @return whether the game loop should continue running
	 */
	boolean running();

}
