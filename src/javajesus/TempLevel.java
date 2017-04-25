package javajesus;

import engine.graphics.Shader;
import engine.graphics.Texture;
import engine.graphics.VertexArray;
import engine.math.Matrix4f;
import engine.math.Vector3f;

public class TempLevel {
	
	private VertexArray background;
	private Texture bgTexture;
	
	// horizontal scroll amount
	private int xScroll;
	
	// location of background
	private int map;
	
	private TempPlayer player;
	
	/**
	 * Temporary
	 */
	public TempLevel() {
		
		// TODO
		float[] vertices = new float[] {
				
				-10f, -10f * 9f / 16f, 0f,
				-10f,  10f * 9f / 16f, 0f,
				  0f,  10f * 9f / 16f, 0f,
				  0f, -10f * 9f / 16f, 0f
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
		
		background = new VertexArray(vertices, indices, tcs);
		bgTexture = new Texture("res/Levels/Cities/Oakwood.png");
		
		player = new TempPlayer();
		
	}
	
	public void tick() {
		xScroll--;
		
		if (-xScroll % 335 == 0) {
			map++;
		}
		
		player.update();
	}
	
	public void render() {
		bgTexture.bind();
		Shader.TILES.enable();
		background.bind();
		for (int i = map; i < map + 3; i++) {
			Shader.TILES.setUniformMat4f("vw_matrix", 
					Matrix4f.translate(new Vector3f(i * 10 + xScroll * .03f, 0f, 0f)));
			background.draw();
		}
		
		Shader.TILES.disable();
		bgTexture.unbind();
		
		player.render();
	}

}
