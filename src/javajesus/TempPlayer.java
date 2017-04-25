package javajesus;

import org.lwjgl.glfw.GLFW;

import engine.Input;
import engine.graphics.Shader;
import engine.graphics.Texture;
import engine.graphics.VertexArray;
import engine.math.Matrix4f;
import engine.math.Vector3f;

public class TempPlayer {
	
	private float SIZE = 1f;
	private VertexArray mesh;
	private Texture texture;
	
	private Vector3f position = new Vector3f(0, 0, 0);
	
	private float rot;
	private float delta;
	
	public TempPlayer() {
		
		float[] vertices = new float[] {
				
				-SIZE / 2, -SIZE / 2, 0.1f,
				-SIZE / 2,  SIZE / 2, 0.1f,
				 SIZE / 2,  SIZE / 2, 0.1f,
				 SIZE / 2, -SIZE / 2, 0.1f
		};
		
		byte[] indices = new byte[] {
				0, 1, 2,
				2, 3, 0
		};
		
		float[] tcs = new float[] {
				0,1,
				0,0,
				1,0,
				1,1
		};
		
		mesh = new VertexArray(vertices, indices, tcs);
		texture = new Texture("res/Buildings/Trees/Coniferous_Trees/Sequoia_Small.png");
		
	}
	
	public void update() {
		if (Input.keys[GLFW.GLFW_KEY_UP]) {
			position.y++;
		}
		if (Input.keys[GLFW.GLFW_KEY_DOWN]) {
			position.y--;
		}
		if (Input.keys[GLFW.GLFW_KEY_LEFT]) {
			position.x--;
		}
		if (Input.keys[GLFW.GLFW_KEY_RIGHT]) {
			position.x++;
		}
		if (Input.keys[GLFW.GLFW_KEY_Q]) {
			rot++;
		}
		if (Input.keys[GLFW.GLFW_KEY_E]) {
			rot--;
		}
	}
	
	public void render() {
		Shader.PLAYER.enable();
		Shader.PLAYER.setUniformMat4f("ml_matrix", Matrix4f.translate(position).multiply(Matrix4f.rotate(rot)));
		texture.bind();
		mesh.render();
		Shader.PLAYER.disable();
	}

}
