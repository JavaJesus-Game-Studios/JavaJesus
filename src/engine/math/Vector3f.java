package engine.math;

// TODO
public class Vector3f {
	
	// z is bit depth that determines rendering order
	public float x, y, z;
	
	/**
	 * Vector3f() Constructor
	 * Each texture vector has 3 components
	 * 
	 * @param x x component
	 * @param y y component
	 * @param z z component
	 */
	public Vector3f(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

}
