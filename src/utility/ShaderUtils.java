package utility;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

public class ShaderUtils {
	
	/**
	 * Ensure this class is never instantiated
	 */
	private ShaderUtils() {
		
	}
	
	/**
	 * load()
	 * Creates a program object based on specified vertex
	 * and fragment path
	 * 
	 * @param vertexPath - path to vertex shader
	 * @param fragmentPath - path to fragment shader
	 * @return program object with both shaders
	 */
	public static int load(String vertexPath, String fragmentPath) {
		
		// get the vertex and fragment instructions
		String vert = FileUtils.loadAsString(vertexPath);
		String frag = FileUtils.loadAsString(fragmentPath);
		
		// create the object
		return create(vert, frag);
	}
	
	/**
	 * Creates a program object for rendering
	 * 
	 * @param vert - vertex shader
	 * @param frag - fragment shader
	 * @return pointer to program object
	 */
	public static int create(String vert, String frag) {
		
		// pointer to program object
		int program = glCreateProgram();
		
		// pointers to respective shader objects
		int vertID = glCreateShader(GL_VERTEX_SHADER);
		int fragID = glCreateShader(GL_FRAGMENT_SHADER);
		
		// set the instructions for Per Vertex Stage
		glShaderSource(vertID, vert);
		
		// set the instructions for Per Fragment Stage
		glShaderSource(fragID, frag);
		 
		// compile the new vertex shader
		glCompileShader(vertID);
		
		// make sure the vertex shader is valid
		if (glGetShaderi(vertID, GL_COMPILE_STATUS) == GL_FALSE) {
			System.err.println("FAILED TO COMPILE VERTEX SHADER!");
			System.err.println(glGetShaderInfoLog(vertID));
			return -1;
		}
		
		// compile the new fragment shader
		glCompileShader(fragID);
		
		// make sure the fragment shader is valid
		if (glGetShaderi(fragID, GL_COMPILE_STATUS) == GL_FALSE) {
			System.err.println("FAILED TO COMPILE FRAGMENT SHADER!");
			System.err.println(glGetShaderInfoLog(fragID));
			return -1;
		}
		
		/// attach the respective shaders to the program
		glAttachShader(program, vertID);
		glAttachShader(program, fragID);
		
		// links the program object to new shaders
		glLinkProgram(program);
		
		// validate the program
		glValidateProgram(program);
		
		// free memory
		glDeleteShader(vertID);
		glDeleteShader(fragID);
		
		// return the pointer to the program
		return program;
	}

}
