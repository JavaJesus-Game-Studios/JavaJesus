package javajesus.entities.collision;

public interface CoordinateObserver {

	public void onCollisionBoxAdded(CoordinateObserver o);

	public void onCollisionBoxRemoved(CoordinateObserver o);

}
