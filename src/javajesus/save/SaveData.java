package javajesus.save;

/*
 * Helper methods to properly compress
 * entity data to a byte stream
 */
public class SaveData {
	
	// different types of data saving and loading
	private static final byte TYPE1 = 0x00, TYPE2 = 0x01, TYPE3 = 0x02, TYPE4 = 0x03;
	
	// position of type data
	private static final long TYPE_MASK = Long.decode("0x00FFFFFFFFFFFFFF");
	private static final long EXTRA_MASK = Long.decode("0x00000000000000FF");
	
	/**
	 * Type 1 does not have any additional data
	 * @param x - x coordinate
	 * @param y - y coordinate
	 * @return the compressed data
	 */
	public static final long type1(short x, short y) {
		
		// construct the data
		long data = (((long) TYPE1) << 56);
		
		// add the x
		data |= (((long) x) << 40);
		
		// add the y
		data |= (((long) y) << 24);
		
		return data;
	}
	
	/**
	 * Type 2 saves an entity's health
	 * @param x - x coordinate
	 * @param y - y coordinate
	 * @param maxHealth - max health
	 * @return the compressed data
	 */
	public static final long type2(short x, short y, short maxHealth) {
		
		// construct the data
		long data = type1(x, y);
		
		// set the type
		data &= TYPE_MASK;
		data |= (((long) TYPE2) << 56);
		
		// add the health
		data |= (((long) maxHealth) << 8);
		
		return data;
	}
	
	/**
	 * Type 3 requires a special byte of extra data
	 * @param x - x coordinate
	 * @param y - y coordinate
	 * @param extra - extra data to be stored
	 * @return the compressed data
	 */
	public static final long type3(short x, short y, byte extra) {
		
		// construct the data
		long data = type1(x, y);

		// set the type
		data &= TYPE_MASK;
		data |= (((long) TYPE3) << 56);

		// add the extra data
		data |= (extra & EXTRA_MASK);

		return data;
	}
	
	/**
	 * Type 4 requires health and an extra byte of data
	 * @param x - x coordinate
	 * @param y - y coordinate
	 * @param maxHealth - max Health
	 * @param extra - extra byte of data
	 * @return the compressed data
	 */
	public static final long type4(short x, short y, short maxHealth, byte extra) {
		
		// construct the data
		long data = type1(x, y);
		
		// set the type
		data &= TYPE_MASK;
		data |= (((long) TYPE4) << 56);
		
		// add the health
		data |= (((long) maxHealth) << 8);
		
		// add the extra data
		data |= (extra & EXTRA_MASK);
		
		return data;
	}

}
