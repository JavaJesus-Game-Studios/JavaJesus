package engine.utility;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class BufferUtils {

	/**
	 * Ensure this class is never instantiated
	 */
	private BufferUtils() {

	}

	/**
	 * createByteBuffer() 
	 * Creates a byte buffer
	 * 
	 * @param array the array to use
	 * @return a byte buffer with the corresponding array
	 */
	public static ByteBuffer createByteBuffer(byte[] array) {

		// create a bytebuffer from the array
		ByteBuffer result = ByteBuffer.allocateDirect(array.length).order(ByteOrder.nativeOrder());

		// flip the array for openGL
		result.put(array).flip();

		// return the bytebuffer
		return result;
	}

	/**
	 * createFloatBuffer() 
	 * Creates a float buffer
	 * 
	 * @param array the array to use
	 * @return a float buffer with the corresponding array
	 */
	public static FloatBuffer createFloatBuffer(float[] array) {

		// create a floatbuffer from the array
		// each float has 4 bytes, so multiply length by 4
		FloatBuffer result = ByteBuffer.allocateDirect(array.length << 2).order(ByteOrder.nativeOrder())
		        .asFloatBuffer();

		// flip the array for openGL
		result.put(array).flip();

		// return the floatbuffer
		return result;
	}

	/**
	 * createFloatBuffer() 
	 * Creates a float buffer
	 * 
	 * @param array the array to use
	 * @return a float buffer with the corresponding array
	 */
	public static IntBuffer createIntBuffer(int[] array) {

		// create an intbuffer from the array
		// each int has 4 bytes, so multiply length by 4
		IntBuffer result = ByteBuffer.allocateDirect(array.length << 2).order(ByteOrder.nativeOrder()).asIntBuffer();

		// flip the array for openGL
		result.put(array).flip();

		// return the intbuffer
		return result;
	}

}
