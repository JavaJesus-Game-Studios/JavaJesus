package engine;

/**
 * @author Derek
 * 
 * The Game Engine provides an easy initialize of a game window with
 * customizable game logic and capabilities
 */
public class GameEngine implements Runnable {

	// the thread the loop is running on
	private final Thread gameLoop;

	// the instance of the window
	private Window window;

	// instance of game logic for the engine
	private IGameLogic logic;

	/**
	 * GameEngine
	 * 
	 * @param title - the title of the window
	 * @param width - the width of the window
	 * @param height - the height of the window
	 * @param gameLogic - the game logic interface
	 * @throws Exception - any errors that may occur during initialization
	 */
	public GameEngine(String title, int width, int height, IGameLogic gameLogic) throws Exception {

		// create instance of the new thread
		gameLoop = new Thread(this, "GAME_LOOP_THREAD");

		// create the window
		window = new Window(title, width, height);

		// initialize the logic
		logic = gameLogic;
	}

	/**
	 * start()
	 * 
	 * Starts a thread to handle game logic and rendering
	 */
	public void start() {

		// check if running on mac
		String name = System.getProperty("os.name");

		// Macs can't multithread
		if (name.contains("Mac")) {

			// run on main thread
			//gameLoop.run();
			gameLoop.start();

		} else {
			
			// start the separate thread
			gameLoop.start();

		}
	}

	/**
	 * Initializes and handles game logic as long as the game is running
	 */
	public void run() {

		// initialize the window on same thread for openGL
		init();

		// set up the internal clock
		long lastTime = System.nanoTime();
		
		// allows framerate capping 0: uncapped, 0.5: 120fps, 1: 60fps, 2: 30fps
		//int framerate = 0;

		// time in between rendering in ns
		double nsPerTick = 1000000000 / 60.0;

		// for measuring fps
		int frames = 0;

		// time in between ticks
		long lastTimer = System.currentTimeMillis();
		
		// The current time to be updated each loop iteration
		long now = 0;

		// difference from last time to current
		double delta = 0;
		long sleepTime = 0;
		// run while the window is open
		while (logic.running() && !window.isClosed()) {

			// get difference in time
			now = System.nanoTime();
			// Time elapsed in ns
			delta = now - lastTime;
			lastTime = now;
			/**
			// If we updated and rendered before we finished a tick
			if( delta < nsPerTick) {
				// Sleep to limit CPU usage
				sleepTime = (long) ((nsPerTick - delta)/1000000) *2;
				try {
					Thread.sleep(sleepTime);
				} catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
			**/
			// render a frame
			// each time frame is rendered
			update();
			render();
			frames++;			
			
			// display the fps
			if (System.currentTimeMillis() - lastTimer >= 1000) {
				lastTimer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}

		}

		// remove the window
		window.cleanup();
		
		// handle the logic when the game is stopped
		logic.onClose();

	}

	/**
	 * Base initialization that
	 * consequently delegates to game logic
	 */
	protected void init() {
		
		// initialize the window
		window.init();
		
		
		try {

			// initialize game logic
			logic.init();
			
			// modify any additional attributes of the window
			logic.modifyWindow(window);

			// print any exceptions that occurred
		} catch (Exception e) {
			e.printStackTrace();

			// exit the game
			System.exit(-1);
		}
		
	}

	/**
	 * Delegate input checking and updating to game logic
	 */
	protected void update() {

		// use game logic for handling input
		logic.input(window);

		// update the game logic
		logic.update();
	}

	/**
	 * Delegate rendering to game logic
	 */
	protected void render() {

		// render game logic
		logic.render(window);
	}

}
