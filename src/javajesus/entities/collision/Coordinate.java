package javajesus.entities.collision;

import java.util.ArrayList;

public class Coordinate implements CoordinateSubject {

	private int x, y;

	private ArrayList<CoordinateObserver> observers;

	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
		observers = new ArrayList<>();
	}

	@Override
	public void addObserver(CoordinateObserver o) {

		for (CoordinateObserver co : observers) {
			co.onCollisionBoxAdded(o);
		}

		observers.add(o);

	}

	@Override
	public void removeObserver(CoordinateObserver o) {
		for (CoordinateObserver co : observers) {
			co.onCollisionBoxRemoved(o);
		}

		observers.remove(o);

	}

	@Override
	public void notifyObservers() {

	}

}
