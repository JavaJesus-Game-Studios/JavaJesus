package javajesus.math;

public class Matrix4f {

	// SIZE of the matrix
	public static final int SIZE = 4 * 4;

	// the matrix
	public float[] elements = new float[SIZE];

	/**
	 * identity() An identity matrix has 1s along the diagonal and the rest are
	 * 0s
	 * 
	 * @return an identity matrix
	 */
	public static Matrix4f identity() {

		// matrix to return
		Matrix4f result = new Matrix4f();

		// zero fill the matrix
		for (int i = 0; i < SIZE; i++) {
			result.elements[i] = 0f;
		}

		// add 1s along the diagonal
		result.elements[0 + 0 * 4] = 1f;
		result.elements[1 + 1 * 4] = 1f;
		result.elements[2 + 2 * 4] = 1f;
		result.elements[3 + 3 * 4] = 1f;

		// return the identity matrix
		return result;
	}

	/**
	 * orthographic()
	 * An orthographic matrix is a projection matrix defined by 6 clipping planes
	 * where depth does not matter
	 * 
	 * @param left
	 * @param right
	 * @param bottom
	 * @param top
	 * @param near
	 * @param far
	 * @return
	 */
	public static Matrix4f orthographic(float left, float right, float bottom, float top, float near, float far) {
		
		// orthographic matrix
		Matrix4f result = identity();
		
		// some math that I will explain later
		result.elements[0 + 0 * 4] = 2f / (right - left);
		result.elements[1 + 1 * 4] = 2f / (top - bottom);
		result.elements[2 + 2 * 4] = 2f / (near - far);
		
		result.elements[0 + 3 * 4] = (left + right) / (left - right);
		result.elements[1 + 3 * 4] = (bottom + top) / (bottom - top);
		result.elements[2 + 3 * 4] = (far + near) / (far - near);
		
		return result;
	}

}
