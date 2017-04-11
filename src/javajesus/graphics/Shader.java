package javajesus.graphics;

import static org.lwjgl.opengl.GL20.*;

import java.util.HashMap;
import java.util.Map;

import javajesus.math.Matrix4f;
import javajesus.math.Vector3f;
import utility.ShaderUtils;

/*
 * A Shader object is used to render textures
 */
public class Shader {
	
	// not sure
	public static final int VERTEX_ATTRIB = 0;
	public static final int TCOORD_ATTRIB = 1;
	
	// can only have one instance of a shader
	public static Shader TILES;
	
	// pointer to program object
	private final int ID;
	
	// used to cache uniform locations for optimization
	private Map<String, Integer> locationCache = new HashMap<String, Integer>();
	
	/**
	 * Creates a shader object
	 * 
	 * @param vertex - vertex path
	 * @param fragment - fragment path
	 */
	public Shader(String vertex, String fragment) {
		ID = ShaderUtils.load(vertex, fragment);
	}
	
	/**
	 * Loads all the shaders in the game
	 */
	public static void loadAll() {
		TILES = new Shader("shaders/bg.vert", "shaders/bg.frag");
	}
	
	/**
	 * @param name name of the uniform
	 * @return pointer to that uniform
	 */
	public int getUniform(String name) {
		
		// check if uniform location is already found
		if (locationCache.containsKey(name)) {
			return locationCache.get(name);
		}
		
		// get uniform variable
		int result = glGetUniformLocation(ID, name);
		
		// make sure the uniform is found
		if (result == -1) {
			System.err.println("Uniform variable not found: " + name);
		} else {
			// save it for later
			locationCache.put(name,  result);
		}
		
		// return the uniform
		return result;
	}
	
	/**
	 * @param name name of the uniform to modify
	 * @param value new value of the uniform
	 */
	public void setUniform1i(String name, int value) {
		glUniform1i(getUniform(name), value);
	}
	
	/**
	 * @param name name of the uniform to modify
	 * @param value new value of the uniform
	 */
	public void setUniform1f(String name, float value) {
		glUniform1f(getUniform(name), value);
	}
	
	/**
	 * @param name name of the uniform to modify
	 * @param value new value of the uniform
	 */
	public void setUniform2f(String name, float x, float y) {
		glUniform2f(getUniform(name), x, y);
	}
	
	/**
	 * @param name name of the uniform to modify
	 * @param value new value of the uniform
	 */
	public void setUniform3f(String name, Vector3f vector) {
		glUniform3f(getUniform(name), vector.x, vector.y, vector.z);
	}
	
	/**
	 * @param name name of the variable to set
	 * @param matrix the value of the uniform variable
	 */
	public void setUniformMat4f(String name, Matrix4f matrix) {
		glUniformMatrix4fv(getUniform(name), false, matrix.toFloatBuffer());
	}
	
	/**
	 * Enable the shader
	 */
	public void enable() {
		glUseProgram(ID);
	}
	
	/**
	 * Disable the shader
	 */
	public void disable() {
		glUseProgram(0);
	}

}
