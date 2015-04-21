package ca.javajesus.game;

import java.awt.Rectangle;

import ca.javajesus.game.entities.Entity;

public class JavaRectangle extends Rectangle {

	private static final long serialVersionUID = -3117483081821612098L;
	
	private Entity entity;
	
	public JavaRectangle(int w, int l, Entity entity) {
		super(w, l);
		this.entity = entity;
	}
	
	public JavaRectangle(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	public Entity getEntity() {
		return entity;
	}

}
