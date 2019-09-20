package javajesus.entities.collision;

public interface CoordinateSubject {
	
	public void addObserver(CoordinateObserver o);
	
	public void removeObserver(CoordinateObserver o);
	
	public void notifyObservers();

}
