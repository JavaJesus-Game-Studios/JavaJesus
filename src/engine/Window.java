package engine;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

import org.lwjgl.glfw.GLFWVidMode;

import engine.utility.Strings;

/**
 * @author Derek
 *
 * The GLFW Window displays pixel information
 * and is automatically V-Synced
 */
public class Window {

	// dimensions of the window
	private int width, height;
	
	// title of the window
	private String name;

	// id of the window
	private long window;
	
	// input listener for the window
	private Input input;

	/**
	 * Window
	 * 
	 * @param title - title of the window
	 * @param width - width of the window
	 * @param height - height of the window
	 */
	public Window(String title, int width, int height) {
		
		// initialize instance data
		name = title;
		this.width = width;
		this.height = height;
		
	}
	
	/**
	 * Initializes the window
	 */
	public void init() {
		
		// ensure latest versions are used for compatibility
		glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
		glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 2);
		glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
		glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GL_TRUE);

		// create the window, save its id
		window = glfwCreateWindow(width, height, name, NULL, NULL);
		
		// check to make sure window was created
		if (window == NULL) {

			// Window was not created, throw an error
			throw new RuntimeException(Strings.ERR_WINDOW);
		}
		
		// set input listener
		glfwSetKeyCallback(window, input = new Input());

		// get resolution of monitor
		GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

		// center the position of the window on the screen
		glfwSetWindowPos(window, (vidmode.width() - width) / 2, (vidmode.height() - height) / 2);

		// makes the context of the window current on the thread
		glfwMakeContextCurrent(window);
		
		// turn on v sync (keeps 60 fps)
		glfwSwapInterval(GL_TRUE);
		
		// display the window
		glfwShowWindow(window);
		
	}

	/**
	 * isKeyPressed()
	 * 
	 * @param keyCode - key identifier: Ex. GLFW_KEY_SPACE
	 * @return whether or not it is pressed
	 */
	public boolean isKeyPressed(int keyCode) {
		return input.isKeyPressed(keyCode);
	}

	/**
	 * @return width of the window
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @return height of the window
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * @return whether or not the window is closed
	 */
	public boolean isClosed() {
		return glfwWindowShouldClose(window);
	}
	
	/**
	 * cleanup()
	 * 
	 * free window call backs and destroy window
	 */
	public void cleanup() {
		glfwFreeCallbacks(window);
		glfwDestroyWindow(window);
	}
	
	/**
	 * @return id of the window
	 */
	public long getId() {
		return window;
	}
	
	public String getName() {
		return name;
	}

}
