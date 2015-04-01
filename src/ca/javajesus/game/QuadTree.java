package ca.javajesus.game;

import java.util.ArrayList;
import java.util.List;

public class QuadTree {

	// Max number of objects in a Node
	private int MAX_OBJECTS = 80;
	// Max number of subnodes
	private int MAX_LEVELS = 100;
	// Current Node
	private int level;
	// List of objects in the Node
	private List<JavaRectangle> objects;
	// Dimensions of the node
	private JavaRectangle bounds;
	// List of all the nodes
	private QuadTree[] nodes;

	public QuadTree(int pLevel, JavaRectangle pBounds) {
		level = pLevel;
		objects = new ArrayList<JavaRectangle>();
		bounds = pBounds;
		nodes = new QuadTree[4];
	}

	public void clear() {
		objects.clear();
		for (int i = 0; i < nodes.length; i++) {
			if (nodes[i] != null) {
				nodes[i].clear();
				nodes[i] = null;
			}
		}
	}

	private void split() {
		int subWidth = (int) (bounds.getWidth() / 2);
		int subHeight = (int) (bounds.getHeight() / 2);
		int x = (int) bounds.getX();
		int y = (int) bounds.getY();

		nodes[0] = new QuadTree(level + 1, new JavaRectangle(x + subWidth, y,
				subWidth, subHeight));
		nodes[1] = new QuadTree(level + 1, new JavaRectangle(x, y, subWidth,
				subHeight));
		nodes[2] = new QuadTree(level + 1, new JavaRectangle(x, y + subHeight,
				subWidth, subHeight));
		nodes[3] = new QuadTree(level + 1, new JavaRectangle(x + subWidth, y
				+ subHeight, subWidth, subHeight));
	}

	private int getIndex(JavaRectangle pRect) {
		int index = -1;
		double verticalMidpoint = bounds.getX() + (bounds.getWidth() / 2);
		double horizontalMidpoint = bounds.getY() + (bounds.getHeight() / 2);

		// Object can completely fit within the top quadrants
		boolean topQuadrant = (pRect.getY() < horizontalMidpoint && pRect
				.getY() + pRect.getHeight() < horizontalMidpoint);
		// Object can completely fit within the bottom quadrants
		boolean bottomQuadrant = (pRect.getY() > horizontalMidpoint);

		// Object can completely fit within the left quadrants
		if (pRect.getX() < verticalMidpoint
				&& pRect.getX() + pRect.getWidth() < verticalMidpoint) {
			if (topQuadrant) {
				index = 1;
			} else if (bottomQuadrant) {
				index = 2;
			}
		}
		// Object can completely fit within the right quadrants
		else if (pRect.getX() > verticalMidpoint) {
			if (topQuadrant) {
				index = 0;
			} else if (bottomQuadrant) {
				index = 3;
			}
		}

		return index;
	}

	public void insert(JavaRectangle pRect) {
		if (nodes[0] != null) {
			int index = getIndex(pRect);

			if (index != -1) {
				nodes[index].insert(pRect);

				return;
			}
		}

		objects.add(pRect);

		if (objects.size() > MAX_OBJECTS && level < MAX_LEVELS) {
			if (nodes[0] == null) {
				split();
			}

			int i = 0;
			while (i < objects.size()) {
				int index = getIndex(objects.get(i));
				if (index != -1) {
					nodes[index].insert(objects.remove(i));
				} else {
					i++;
				}
			}
		}
	}

	public List<JavaRectangle> retrieve(List<JavaRectangle> returnObjects, JavaRectangle pRect) {
		int index = getIndex(pRect);
		if (index != -1 && nodes[0] != null) {
			nodes[index].retrieve(returnObjects, pRect);
		}

		returnObjects.addAll(objects);

		return returnObjects;
	}

}
