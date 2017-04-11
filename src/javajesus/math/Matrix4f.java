package javajesus.math;

import java.nio.FloatBuffer;

import utility.BufferUtils;

/*
 * A Matrix4f is a 4x4 matrix used for pixel manipulation
 */
public class Matrix4f {

	// SIZE of the matrix
	private static final int SIZE = 4 * 4;
	
	// number of columns
	private static final int SIDE = 4;

	// the matrix, openGL wants single dimension array
	private float[] elements = new float[SIZE];

	/**
	 * identity() 
	 * An identity matrix has 1s along the diagonal and the rest are 0s
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
		result.elements[0 + 0 * SIDE] = 1f;
		result.elements[1 + 1 * SIDE] = 1f;
		result.elements[2 + 2 * SIDE] = 1f;
		result.elements[3 + 3 * SIDE] = 1f;

		// return the identity matrix
		return result;
	}

	/**
	 * orthographic()
	 * An orthographic matrix is a projection matrix defined by 6 clipping planes
	 * where depth does not matter
	 * 
	 * @param left - clipping margin of viewport
	 * @param right - clipping margin of viewport
	 * @param bottom - clipping margin of viewport
	 * @param top - clipping margin of viewport
	 * @param near - distance to render object
	 * @param far - distance to render object
	 * @return an orthographic matrix
	 */
	public static Matrix4f orthographic(float left, float right, float bottom, float top, float near, float far) {
		
		// orthographic matrix
		Matrix4f result = identity();
		
		// diagonal should be 2 / difference in relative axis
		result.elements[0 + 0 * SIDE] = 2f / (right - left);
		result.elements[1 + 1 * SIDE] = 2f / (top - bottom);
		result.elements[2 + 2 * SIDE] = 2f / (near - far);
		
		// last column of the matrix
		result.elements[0 + 3 * SIDE] = (left + right) / (left - right);
		result.elements[1 + 3 * SIDE] = (bottom + top) / (bottom - top);
		result.elements[2 + 3 * SIDE] = (far + near) / (far - near);
		
		// return the orthographic matrix
		return result;
	}
	
	/**
	 * translate()
	 * Creates a matrix based on a corresponding vector
	 * 
	 * @param vector the vector to translate
	 * @return a matrix with the vector as the first column
	 */
	public static Matrix4f translate(Vector3f vector) {
		
		// translated matrix
		Matrix4f result = identity();
		
		// set the vector as the first column of the matrix
		result.elements[0 + 3 * SIDE] = vector.x;
		result.elements[1 + 3 * SIDE] = vector.y;
		result.elements[2 + 3 * SIDE] = vector.z;
		
		// return the vector matrix
		return result;
	}
	
	/**
	 * rotate()
	 * Transformation matrix required to rotate an object
	 * 
	 * @param angle angle to rotate
	 * @return the transformation matrix required to rotate
	 */
	public static Matrix4f rotate(float angle) {
		
		// start with an identity matrix
		Matrix4f result = identity();
		
		// angle in radians
		float rads = (float) Math.toRadians(angle);
		
		// define the transformation matrix with only x-y rotation
		result.elements[0 + 0 * SIDE] = (float) Math.cos(rads);
		result.elements[1 + 0 * SIDE] = (float) Math.sin(rads);
		result.elements[0 + 1 * SIDE] = (float) -Math.sin(rads);
		result.elements[1 + 1 * SIDE] = (float) Math.cos(rads);
		
		// return the rotation matrix
		return result;
	}
	
	/**
	 * multiply()
	 * Multiplies a matrix with another
	 * 
	 * @param matrix
	 * @return
	 */
	public Matrix4f multiply(Matrix4f matrix) {
		
		// start with a blank matrix
		Matrix4f result = new Matrix4f();
		
		// iterate through each row/col
		for(int y = 0; y < SIDE; y++) {
			for(int x = 0; x < SIDE; x++) {
				
				// sum of row x col
				float sum = 0;
				
				// iterate through each element at a particular row/column
				for (int e = 0; e < 4; e++) {
					
					// the element is row x col
					sum += elements[x + e * SIDE] * matrix.elements[e + y * SIDE];
				}
				result.elements[x + y * SIDE] = sum;
			}
		}
		
		// return the resulting matrix
		return result;
		
	}
	
	/**
	 * toFloatBuffer()
	 * Converts a matrix into a float buffer
	 * 
	 * @return the float buffer of the matrix
	 */
	public FloatBuffer toFloatBuffer() {
		return BufferUtils.createFloatBuffer(elements);
	}

}
