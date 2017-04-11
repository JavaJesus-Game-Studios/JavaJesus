package javajesus.graphics;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import utility.BufferUtils;

/*
 * VertexArray - Not quite sure what everything is
 */
public class VertexArray {

	// number of vertices rendered
	private int count;

	// vertex array object, vertex buffer object, index buffer object, texture
	// buffer obj
	private int vao, vbo, ibo, tbo;

	/**
	 * @param vertices
	 * @param indices
	 * @param textureCoordiantes
	 */
	public VertexArray(float[] vertices, byte[] indices, float[] textureCoordinates) {

		// initialize number of vertices
		count = indices.length;

		// vertex array
		vao = glGenVertexArrays();
		glBindVertexArray(vao);

		// intialize stuff for vbo
		vbo = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		glBufferData(GL_ARRAY_BUFFER, BufferUtils.createFloatBuffer(vertices), GL_STATIC_DRAW);
		glVertexAttribPointer(Shader.VERTEX_ATTRIB, 3, GL_FLOAT, false, 0, 0);
		glEnableVertexAttribArray(Shader.VERTEX_ATTRIB);

		// intialize stuff for tbo
		tbo = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, tbo);
		glBufferData(GL_ARRAY_BUFFER, BufferUtils.createFloatBuffer(textureCoordinates), GL_STATIC_DRAW);
		glVertexAttribPointer(Shader.TCOORD_ATTRIB, 2, GL_FLOAT, false, 0, 0);
		glEnableVertexAttribArray(Shader.TCOORD_ATTRIB);

		// intialize stuff for ibo
		ibo = glGenBuffers();
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, BufferUtils.createByteBuffer(indices), GL_STATIC_DRAW);

		// unbind buffers
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
		glBindVertexArray(0);
	}

	/**
	 * Bind Vertex Array
	 */
	public void bind() {
		glBindVertexArray(vao);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
	}
	
	/**
	 * Unbind Vertex Array
	 */
	public void unbind() {
		glBindVertexArray(0);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
	}
	
	/**
	 * Draws an element on the screen
	 */
	public void draw() {
		glDrawElements(GL_TRIANGLES, count, GL_UNSIGNED_BYTE, 0);
	}
	
	/**
	 * Renders the object on the screen
	 */
	public void render() {
		bind();
		draw();
	}

}
