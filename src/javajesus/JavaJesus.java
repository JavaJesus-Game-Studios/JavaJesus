package javajesus;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwDestroyWindow;
import static org.lwjgl.glfw.GLFW.glfwGetPrimaryMonitor;
import static org.lwjgl.glfw.GLFW.glfwGetVideoMode;
import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSetErrorCallback;
import static org.lwjgl.glfw.GLFW.glfwSetWindowPos;
import static org.lwjgl.glfw.GLFW.glfwShowWindow;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwSwapInterval;
import static org.lwjgl.glfw.GLFW.glfwTerminate;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.system.MemoryUtil.NULL;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import static org.lwjgl.opengl.GL11.*;

import utility.JJStrings;

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

	// handle of the window
	private long window;

	// whether or not the game is running
	private boolean running;

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
		new Thread(this, JJStrings.JAVAJESUS).start();
	}

	/**
	 * Initializes and handles game logic as long as the
	 * game is running
	 */
	public void run() {

		// initialize the window
		init();

		// makes open GL bindings available for use
		GL.createCapabilities();

		// set the clear color
		glClearColor(0f, 0f, 0f, 0f);
		
		// set up the projection matrix
		glMatrixMode(GL_PROJECTION);
		
		// resets previous projection matrices
		glLoadIdentity();
		
		// parallel projection
		glOrtho(0, WINDOW_WIDTH, WINDOW_HEIGHT, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);

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

		// create error callback
		GLFWErrorCallback.createPrint(System.err).set();

		// Check if the window initializes successfully
		if (!glfwInit()) {

			// Window did not initializes, print an error
			throw new IllegalStateException(JJStrings.ERR_INIT);
		}

		// create the window
		window = glfwCreateWindow(WINDOW_WIDTH, WINDOW_HEIGHT,
				JJStrings.JAVAJESUS, NULL, NULL);

		// check to make sure window was created
		if (window == NULL) {

			// Window was not created, print an error
			throw new RuntimeException(JJStrings.ERR_WINDOW);
		}

		// get resolution of monitor
		GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

		// center the position of the window on the screen
		glfwSetWindowPos(window, (vidmode.width() - WINDOW_WIDTH) / 2,
				(vidmode.height() - WINDOW_HEIGHT) / 2);

		// makes the context of the window current on the thread
		glfwMakeContextCurrent(window);

		// turn on v sync
		glfwSwapInterval(1);

		// display the window
		glfwShowWindow(window);

	}

	/**
	 * Updates the game and checks for input
	 */
	public void tick() {

		// checks for window events
		glfwPollEvents();
	}

	public void render() {

		// clear the frame buffer
		glClear(GL_COLOR_BUFFER_BIT);

		// swaps buffers using the gpu
		glfwSwapBuffers(window);
	}

}
