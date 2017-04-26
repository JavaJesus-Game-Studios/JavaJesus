package javajesus;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;

import org.lwjgl.opengl.GL;

import engine.IGameLogic;
import engine.Window;
import engine.graphics.Shader;
import engine.math.Matrix4f;

/**
 * @author Derek
 *
 * Game Logic for Java Jesus
 */
public class JavaJesus implements IGameLogic {

	// Level
	private TempLevel level;
	
	/**
	 * Initialize open GL components
	 * and JavaJesus objects
	 */
	public void init() throws Exception {
		
		// makes open GL bindings available for use
		GL.createCapabilities();
		
		// set the clear color to black
		glClearColor(0f, 0f, 0f, 0f);
		
		// enable depth test
		glEnable(GL_DEPTH_TEST);
		
		// which texture we are using
		glActiveTexture(GL_TEXTURE1);
		
		// enable texture drawing
		glEnable(GL_TEXTURE_2D);

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
	 * Delegate to the level for input
	 */
	public void input(Window window) {
		level.input(window);
	}

	/**
	 * Updates per second
	 */
	public void update() {
		
		// update the level
		level.tick();

	}

	/**
	 * Displays graphics on the window
	 */
	public void render(Window window) {

		// clear the frame buffer
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

		// render the level
		level.render();

		// swaps buffers using the gpu
		glfwSwapBuffers(window.getId());
	}

}
