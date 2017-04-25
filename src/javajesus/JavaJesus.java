package javajesus;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.system.MemoryUtil.*;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import engine.Input;
import engine.graphics.Shader;
import engine.math.Matrix4f;
import engine.utility.Strings;
import javajesus.utility.JJStrings;

/**
 * @author Derek
 * This is the main driver of JavaJesus
 * It uses LWJGL to initialize a window
 * to render and tick entities on the screen
 */
public class JavaJesus implements Runnable {

	// Window width (Actual Size)
	public static final int WINDOW_WIDTH = 700 * 12 / 9;

	// Window height (Actual Size)
	public static final int WINDOW_HEIGHT = 700;

	// address of the window
	private long window;

	// whether or not the game is running
	private boolean running;
	
	// Level
	private TempLevel level;

	/**
	 * main()
	 * First method called in the game
	 * 
	 * @param args - runtime arguments
	 */
	public static void main(String[] args) {

		// initialize sound
		// SoundHandler.initialize();

		// initialize the launcher
		// new Launcher().start();

		new JavaJesus().start();
	}

	/**
	 * start()
	 * Starts a thread to handle game logic
	 * and rendering
	 */
	public void start() {

		// the game is now running
		running = true;

		// start the thread
		new Thread(this, JJStrings.NAME).start();
	}

	/**
	 * Initializes and handles game logic as long as the
	 * game is running
	 */
	public void run() {

		// initialize the window on same thread for openGL
		init();

		// set up the projection matrix
		glMatrixMode(GL_PROJECTION);
		
		// resets previous projection matrices
		glLoadIdentity();
		
		// parallel projection
		glOrtho(0, WINDOW_WIDTH, WINDOW_HEIGHT, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		
		// glEnable(GL_DEPTH_TEST);
		
		// enable texture drawing
		glEnable(GL_TEXTURE_2D);

		// set up the internal clock
		long lastTime = System.nanoTime();
		
	    // time in between rendering	
		double nsPerTick = 1000000000 / 60.0;
		
		// for measuring fps
		int frames = 0;
		
		// time in between ticks
		long lastTimer = System.currentTimeMillis();
		
		// difference from last time to current
		double delta = 0;

		// processes game logic
		while (running) {

			// get difference in time
			long now = System.nanoTime();
			delta += (now - lastTime) / nsPerTick;
			lastTime = now;

			// tick when time threshold is met
			while (delta >= 1) {
				
				// check for events
				tick();
				
				delta--;
			}
			
			// each time frame is rendered
			frames++;
			
			// display things on the screen
			render();

			// display the fps
			if (System.currentTimeMillis() - lastTimer >= 1000) {
				lastTimer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}

			// keep running as long as the window is open
			running = !glfwWindowShouldClose(window);

		}

		// free window call backs and destroy window
		glfwFreeCallbacks(window);
		glfwDestroyWindow(window);

		// terminate the window and free memory
		glfwTerminate();
		glfwSetErrorCallback(null).free();
		
	}

	/**
	 * Initialize and display a window for the game
	 */
	public void init() {

		// set error callback stream to System.err
		GLFWErrorCallback.createPrint(System.err).set();
		
		// Check if the window initializes successfully
		if (!glfwInit()) {

			// Window did not initializes, print an error
			throw new IllegalStateException(Strings.ERR_INIT);
		}

		// create the window, save its address
		window = glfwCreateWindow(WINDOW_WIDTH, WINDOW_HEIGHT,
				JJStrings.NAME, NULL, NULL);

		// check to make sure window was created
		if (window == NULL) {

			// Window was not created, print an error
			throw new RuntimeException(Strings.ERR_WINDOW);
		}
		
		// set input listener
		glfwSetKeyCallback(window, new Input());

		// get resolution of monitor
		GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

		// center the position of the window on the screen
		glfwSetWindowPos(window, (vidmode.width() - WINDOW_WIDTH) / 2,
				(vidmode.height() - WINDOW_HEIGHT) / 2);

		// makes the context of the window current on the thread
		glfwMakeContextCurrent(window);

		// turn on v sync (keeps 60 fps)
		glfwSwapInterval(GL_TRUE);

		// display the window
		glfwShowWindow(window);
		
		// makes open GL bindings available for use
		GL.createCapabilities();

		// set the clear color to black
		glClearColor(0f, 0f, 0f, 0f);
		
		// enable depth test
		glEnable(GL_DEPTH_TEST);
		
		// which texture we are using
		glActiveTexture(GL_TEXTURE1);
		
		// load all the shaders
		Shader.loadAll();
		
		// set projection of tile shader
		Matrix4f pr_matrix = Matrix4f.orthographic(-10f, 10f, -10f * 9f / 16f, 10f * 9f / 16f, -1f, 1f);
		Shader.TILES.setUniformMat4f("pr_matrix", pr_matrix);
		
		// id of which texture being used
		Shader.TILES.setUniform1i("tex", 1);
		
		Shader.PLAYER.setUniformMat4f("pr_matrix", pr_matrix);
		
		// id of which texture being used
		Shader.PLAYER.setUniform1i("tex", 1);
		
		// initialize the level
		level = new TempLevel();

	}

	/**
	 * Updates the game and checks for input
	 */
	public void tick() {

		// checks for window events
		glfwPollEvents();
		
		level.tick();
		
		// temporary test
		if (Input.keys[GLFW_KEY_SPACE]) {
			System.out.println("Space!");
		}
	}
	
	public void render() {

		// clear the frame buffer
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		
		// render the level
		level.render();
		
		// begin the pixel matrix
		//glPushMatrix();
		
		// bind texture to load
		//glBindTexture(GL_TEXTURE_2D, LevelTester.level.getTileTextureMap());
		
		// begin the rendering
		/*glBegin(GL_QUADS);
		
		glTexCoord2f(0, 0);
        glVertex2f(0, 0);
        
        glTexCoord2f(1, 0);
        glVertex2f(WINDOW_WIDTH, 0);
        
        glTexCoord2f(1, 1);
        glVertex2f(WINDOW_WIDTH, WINDOW_HEIGHT);
        
        glTexCoord2f(0, 1);
        glVertex2f(0, WINDOW_HEIGHT);
        
        glEnd();
        glPopMatrix();*/

		// swaps buffers using the gpu
		glfwSwapBuffers(window);
	}

}
